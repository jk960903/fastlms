package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.member.Repostiory.MemberRepository;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember =memberRepository.findById(parameter.getUserId());

        if(optionalMember.isPresent()){
            //userId에 해당하는 데이터가 존재
            return false;
        }
        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setPassword(parameter.getPassword());
        member.setPhone(parameter.getPhone());
        member.setUserName(parameter.getUserName());
        member.setRegdate(LocalDateTime.now());

        memberRepository.save(member);
        return false;
    }
}
