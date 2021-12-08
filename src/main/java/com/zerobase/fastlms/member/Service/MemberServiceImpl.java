package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.member.Repostiory.MemberRepository;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter) {
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
