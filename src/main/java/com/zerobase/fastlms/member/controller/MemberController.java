package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.UpdateUserPasswordInput;
import com.zerobase.fastlms.member.Service.LoginHistoryService;
import com.zerobase.fastlms.member.Service.MemberService;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final LoginHistoryService loginHistoryService;

    @GetMapping("/member/login")
    public String login(HttpServletRequest request){

        return "member/login";
    }





    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

    @GetMapping("/member/find/password")
    public String findPassword(){
        return "member/find/password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(ResetPasswordInput resetPasswordInput,
                                    Model model){
        boolean result = false;
        try{
            result = memberService.sendResetPassword(resetPasswordInput);
        }catch(Exception e){

        }
        model.addAttribute("result",result);
        return "/member/find/find_password_result";

    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response, MemberInput parameter){
        boolean result = false;

        try{
            result = memberService.register(parameter);
        }catch(Exception e){

        }

        model.addAttribute("result", result);
        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);

        boolean result = memberService.emailAuth(id);
        model.addAttribute("result",result);
        return "member/email_auth";
    }


    @GetMapping("/member/find/reset_password")
    public String resetPassword(Model model,HttpServletRequest request){
        String uuid = request.getParameter("id");

        boolean result = false;

        try{
            result = memberService.checkResetPassword(uuid);
        }catch(Exception e){

        }

        model.addAttribute("result",result);

        return "/member/find/reset_password";
    }

    @PostMapping("/member/find/reset_password")
    public String resetPasswordSubmit(Model model , ResetPasswordInput parameter){
        boolean result = false;

        try{
            memberService.resetPassword(parameter.getId(),parameter.getPassword());
        }catch(Exception e){

        }

        model.addAttribute("result",result);
        return "/member/find/reset_password_result";
    }

    @GetMapping("/member/info")
    public String memberInfo(Principal principal,Model model){

        String userId = principal.getName();

        MemberDto memberDto = memberService.detail(userId);

        model.addAttribute("detail",memberDto);

        return "/member/info";
    }


    @GetMapping("/member/password")
    public String memberPassword(Model model , Principal principal){
        String userId = principal.getName();

        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail",detail);

        return "member/password";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(Model model , Principal principal, UpdateUserPasswordInput parameter){
        String userId = principal.getName();

        parameter.setUserId(userId);
        boolean result = memberService.updatePassword(parameter);

        return "member/info";
    }
}
