package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.Component.MailComponents;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.mapper.MemberMapper;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.Repostiory.EmailRepository;
import com.zerobase.fastlms.member.Repostiory.MemberRepository;
import com.zerobase.fastlms.member.entity.EmailEntity;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.exception.MemberNotEmailAuthException;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final MailComponents mailComponents;

    private final MemberMapper memberMapper;

    private final EmailRepository emailRepository;
    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember =memberRepository.findById(parameter.getUserId());

        if(optionalMember.isPresent()){
            //userId에 해당하는 데이터가 존재
            return false;
        }
        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());


        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder().
                userId(parameter.getUserId()).
                userName(parameter.getUserName()).
                phone(parameter.getPhone()).
                password(encPassword)
                .Regdate(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .build();

        memberRepository.save(member);

        EmailEntity emailEntity = EmailEntity.builder().
                                    userId(member.getUserId()).EmailTitle("fastlms 사이트 가입을 축하드립니다. ")
                                    .EmailContent("<p>"+member.getUserId()+"님 fastlms 사이트 가입을 축하드립니다.</p>\n<p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>\n"
                                            + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+uuid+"'>가입완료</a></div>").build();

        emailEntity = emailRepository.save(emailEntity);
        String email = parameter.getUserId();
        /*String subject = "fastlms 사이트 가입을 축하드립니다. ";
        String text = "<p>fastlms 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요</p>"
                 + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+uuid+"'>가입완료</a></div>";*/

        mailComponents.sendMail(emailEntity.getId());

        return true;
    }

    @Override
    public boolean emailAuth(String uuid){

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);

        if(!optionalMember.isPresent()){
            return false;
        }

        Member member = optionalMember.get();
        if(member.isEmailAuthYn()){
            return false;
        }
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //데이터베이스에 맞게 구현
        Optional<Member> optionalMember = memberRepository.findById(username);

        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //이메일 인증이 되지않음
        if(!member.isEmailAuthYn()){
            throw new MemberNotEmailAuthException("이메일을 활성화 이후에 로그인 하여 주세요");
        }
        if(member.isAdminYn()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        System.out.println(member.getPassword());
        System.out.println(member.getUserId());
        return new User(member.getUserId(),member.getPassword(),grantedAuthorities);
    }

    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter){
        Optional<Member> optionalMember = memberRepository.findByUserIdAndUserName(parameter.getUserId(), parameter.getUserName());

        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        String uuid = UUID.randomUUID().toString();

        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));

        memberRepository.save(member);

        /*String email = parameter.getUserId();
        String subject = "fastlms 비밀 번호 초기화 메일 입니다.";
        String text = "<p>fastlms 비밀번호 초기화 메일 입니다.<p><p> 아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요 </p>"
                +"<div><a target ='_blank' href='http://localhost:8080/member/find/reset_password?id="+uuid+"'>비밀번호 초기화 링크 </a></div>";*/
        EmailEntity emailEntity = EmailEntity.builder()
                .userId(member.getUserId())
                .EmailTitle(member.getUserId() + "님 fastlms 비밀 번호 초기화 메일 입니다.")
                .EmailContent("<p>fastlms 비밀번호 초기화 메일 입니다.<p><p> 아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요 </p>"
                                +"<div><a target ='_blank' href='http://localhost:8080/member/find/reset_password?id="+uuid+"'>비밀번호 초기화 링크 </a></div>")
                .build();
        emailEntity = emailRepository.save(emailEntity);
        mailComponents.sendMail(emailEntity.getId());

        return true;
    }

    @Override
    public boolean resetPassword(String uuid, String password){
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);



        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        //초기화 날짜 유효 체크
        if(member.getResetPasswordLimitDt() == null && member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }


        String encPassword = BCrypt.hashpw(password,BCrypt.gensalt());
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        member.setPassword(encPassword);
        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean checkResetPassword(String uuid){
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);

        if(!optionalMember.isPresent()){
            return false;
        }

        Member member = optionalMember.get();

        //초기화 날짜 유효 체크
        if(member.getResetPasswordLimitDt() == null && member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }




        return true;
    }

    public List<MemberDto> list(MemberParam memberParam){
        long totalCount = memberMapper.selectListCount(memberParam);

        List<MemberDto> list = memberMapper.selectList(memberParam);

        if(CollectionUtils.isEmpty(list)){
            int i =0;
            for(MemberDto x : list){
                x.setTotalCount(totalCount);

                x.setSeq(totalCount - memberParam.getPageStart() -i);
                i++;
            }
        }
        return list;
        //return memberRepository.findAll();
    }

    @Override
    public MemberDto detail(String userId) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()){
            return null;
        }
        Member member = optionalMember.get();
        MemberDto memberDto = MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                .password(member.getPassword())
                .adminYn(member.isAdminYn())
                .regdate(member.getRegdate())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt()).build();

        return memberDto;
    }
}
