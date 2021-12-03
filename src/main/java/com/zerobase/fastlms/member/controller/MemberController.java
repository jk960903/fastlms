package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.Repostiory.MemberRepository;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response, MemberInput parameter){
        Member member = new Member();
        try{
            member.setUserId(parameter.getUserId());
            member.setPassword(parameter.getPassword());
            member.setPhone(parameter.getPhone());
            member.setUserName(parameter.getUserName());
            member.setRegdate(LocalDateTime.now());

            memberRepository.save(member);
        }catch(Exception e){

        }
        return "member/register_complete";
    }
}
