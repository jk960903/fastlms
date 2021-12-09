package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.Component.MailComponents;
import com.zerobase.fastlms.member.Repostiory.MemberRepository;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

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

        String email = parameter.getUserId();
        String subject = "fastlms 사이트 가입을 축하드립니다. ";
        String text = "<p>fastlms 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요</p>"
                 + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+uuid+"'>가입완료</a></div>";
        mailComponents.sendMail(email,subject,text);

        return true;
    }

    @Override
    public boolean emailAuth(String uuid){

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);

        if(!optionalMember.isPresent()){
            return false;
        }
        Member member = optionalMember.get();

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

        System.out.println(member.getPassword());
        System.out.println(member.getUserId());
        return new User(member.getUserId(),member.getPassword(),grantedAuthorities);
    }
}
