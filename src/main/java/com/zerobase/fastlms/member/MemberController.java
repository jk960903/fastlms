package com.zerobase.fastlms.member;

import com.zerobase.fastlms.MemberInput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response, MemberInput parameter){
        try{
            String userId = request.getParameter("userId");
            String userName = request.getParameter("");
        }catch(Exception e){

        }
        return "";
    }
}
