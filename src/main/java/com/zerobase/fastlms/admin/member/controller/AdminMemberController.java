package com.zerobase.fastlms.admin.member.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.member.Service.MemberService;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.temp.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model){
        List<MemberDto> members = memberService.list();

        model.addAttribute("list",members);

        return "admin/member/list";
    }

    @GetMapping("/admin/api/test")
    public String test(){
        Example example = new Example();
        try {
            example.add();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Index";
    }
}
