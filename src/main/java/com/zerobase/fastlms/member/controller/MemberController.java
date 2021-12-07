package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.Service.MemberService;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response, MemberInput parameter){
        Member member = new Member();

        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);
        return "member/register_complete";
    }
}
