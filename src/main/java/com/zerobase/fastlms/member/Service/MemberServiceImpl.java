package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.Component.MailComponents;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.mapper.MemberMapper;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.model.MemberStatusInput;
import com.zerobase.fastlms.member.Repostiory.EmailRepository;
import com.zerobase.fastlms.member.Repostiory.LoginHistoryRepository;
import com.zerobase.fastlms.member.Repostiory.MemberRepository;
import com.zerobase.fastlms.member.entity.EmailEntity;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.exception.MemberNotEmailAuthException;
import com.zerobase.fastlms.member.exception.MemberStatusFailException;
import com.zerobase.fastlms.member.exception.MemberStopUserException;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.admin.model.UpdateUserPasswordInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
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

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());

        if(optionalMember.isPresent()){
            //userId??? ???????????? ???????????? ??????
            return false;
        }
        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());


        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder().
                userId(parameter.getUserId()).
                userName(parameter.getUserName()).
                phone(parameter.getPhone()).
                password(encPassword)
                .regdate(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .userStatus(Member.MEMBER_STATUS_REQ)
                .build();

        memberRepository.save(member);

        EmailEntity emailEntity = EmailEntity.builder().
                                    userId(member.getUserId()).EmailTitle("fastlms ????????? ????????? ??????????????????. ")
                                    .EmailContent("<p>"+member.getUserId()+"??? fastlms ????????? ????????? ??????????????????.</p>\n<p>?????? ????????? ??????????????? ????????? ???????????????.</p>\n"
                                            + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+uuid+"'>????????????</a></div>").build();

        emailEntity = emailRepository.save(emailEntity);
        String email = parameter.getUserId();
        /*String subject = "fastlms ????????? ????????? ??????????????????. ";
        String text = "<p>fastlms ????????? ????????? ??????????????????.</p><p>?????? ????????? ??????????????? ????????? ???????????????</p>"
                 + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+uuid+"'>????????????</a></div>";*/

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
        member.setUserStatus(Member.MEMBER_STATUS_ING);
        memberRepository.save(member);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //????????????????????? ?????? ??????
        Optional<Member> optionalMember = memberRepository.findById(username);

        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("?????? ????????? ???????????? ????????????.");
        }

        Member member = optionalMember.get();
        member.setLastLoginDate(LocalDateTime.now());

        memberRepository.save(member);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(Member.MEMBER_STATUS_REQ.equals(member.getUserStatus())){
            throw new MemberNotEmailAuthException("???????????? ????????? ????????? ????????? ?????? ?????????");
        }
        if(Member.Member_Status_Stop.equals(member.getUserStatus())){
            throw new MemberStopUserException("????????? ???????????????.");
        }
        //????????? ????????? ????????????
        if(!member.isEmailAuthYn()){
            throw new MemberNotEmailAuthException("???????????? ????????? ????????? ????????? ?????? ?????????");
        }
        if(member.isAdminYn()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }



        return new User(member.getUserId(),member.getPassword(),grantedAuthorities);
    }

    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter){
        Optional<Member> optionalMember = memberRepository.findByUserIdAndUserName(parameter.getUserId(), parameter.getUserName());

        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("?????? ????????? ???????????? ????????????.");
        }
        Member member = optionalMember.get();

        String uuid = UUID.randomUUID().toString();

        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));

        memberRepository.save(member);

        /*String email = parameter.getUserId();
        String subject = "fastlms ?????? ?????? ????????? ?????? ?????????.";
        String text = "<p>fastlms ???????????? ????????? ?????? ?????????.<p><p> ?????? ????????? ??????????????? ??????????????? ????????? ???????????? </p>"
                +"<div><a target ='_blank' href='http://localhost:8080/member/find/reset_password?id="+uuid+"'>???????????? ????????? ?????? </a></div>";*/
        EmailEntity emailEntity = EmailEntity.builder()
                .userId(member.getUserId())
                .EmailTitle(member.getUserId() + "??? fastlms ?????? ?????? ????????? ?????? ?????????.")
                .EmailContent("<p>fastlms ???????????? ????????? ?????? ?????????.<p><p> ?????? ????????? ??????????????? ??????????????? ????????? ???????????? </p>"
                                +"<div><a target ='_blank' href='http://localhost:8080/member/find/reset_password?id="+uuid+"'>???????????? ????????? ?????? </a></div>")
                .build();
        emailEntity = emailRepository.save(emailEntity);
        mailComponents.sendMail(emailEntity.getId());

        return true;
    }

    @Override
    public boolean resetPassword(String uuid, String password){
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);



        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("?????? ????????? ???????????? ????????????.");
        }

        Member member = optionalMember.get();

        //????????? ?????? ?????? ??????
        if(member.getResetPasswordLimitDt() == null && member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("????????? ????????? ????????????.");
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

        //????????? ?????? ?????? ??????
        if(member.getResetPasswordLimitDt() == null && member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("????????? ????????? ????????????.");
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

    @Override
    public boolean updateStatus(MemberStatusInput memberStatusInput) {

        Optional<Member> optionalMember = memberRepository.findById(memberStatusInput.getUserId());

        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("?????? ????????? ???????????? ????????????.");
        }

        Member member = optionalMember.get();

        String status = memberStatusInput.getUserStatus();

        switch(memberStatusInput.getUserStatus()){
            case Member.MEMBER_STATUS_REQ:
                status = Member.MEMBER_STATUS_REQ;
                break;
            case Member.MEMBER_STATUS_ING:
                status = Member.MEMBER_STATUS_ING;
                break;
            case Member.Member_Status_Stop:
                status =Member.Member_Status_Stop;
                break;
            default:
                throw new MemberStatusFailException("????????? ?????? ???????????????.");

        }


        member.setUserStatus(status);


        memberRepository.save(member);


        return true;
    }

    @Override
    public boolean updatePassword(UpdateUserPasswordInput updateUserPasswordInput) {
        Optional<Member> optionalMember = memberRepository.findById(updateUserPasswordInput.getUserId());

        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("?????? ????????? ???????????? ????????????.");
        }

        Member member = optionalMember.get();

        String encPassword = BCrypt.hashpw(updateUserPasswordInput.getUserPassword(),BCrypt.gensalt());

        member.setPassword(encPassword);
        member.setUpdateDate(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    public void temp(){
        Optional<List<Member>> list = memberRepository.findByRegdateLessThan(LocalDateTime.now());

        int num = 0 ;
    }
}
