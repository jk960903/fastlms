package com.zerobase.fastlms.admin.member.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.Service.MemberService;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.temp.Example;
import com.zerobase.fastlms.util.PageUtil;
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
    public String list(Model model, MemberParam memberParam){
        memberParam.init();

        List<MemberDto> members = memberService.list(memberParam);




        long totalCount = 0;

        if(members != null && members.size() > 0 ){
            totalCount = members.size();
        }

        String queryString = memberParam.getQueryString();
        PageUtil pageUtil = new PageUtil(totalCount,memberParam.getPageSize(), memberParam.getPageIndex(), queryString);

        model.addAttribute("paper",pageUtil.pager());
        model.addAttribute("list",members);
        model.addAttribute("totalCount",totalCount);
        return "admin/member/list";
    }


    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam memberParam){
        memberParam.init();

        MemberDto memberDto = memberService.detail(memberParam.getUserId());

        model.addAttribute("member",memberDto);

        return "admin/member/detailpage";
    }


}
