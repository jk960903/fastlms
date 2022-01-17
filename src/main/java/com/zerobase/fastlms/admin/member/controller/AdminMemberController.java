package com.zerobase.fastlms.admin.member.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.model.MemberStatusInput;
import com.zerobase.fastlms.member.Service.LoginHistoryService;
import com.zerobase.fastlms.member.Service.MemberService;
import com.zerobase.fastlms.admin.model.UpdateUserPasswordInput;
import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    private final LoginHistoryService loginHistoryService;

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

        model.addAttribute("paper",pageUtil);
        model.addAttribute("list",members);
        model.addAttribute("totalCount",totalCount);
        return "/admin/member/list";
    }


    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam memberParam){
        memberParam.init();

        MemberDto memberDto = memberService.detail(memberParam.getUserId());

        List<LoginHistory> loginHistories = loginHistoryService.getLoginHistory(memberParam.getUserId());

        model.addAttribute("member",memberDto);

        model.addAttribute("loginHistory",loginHistories);

        return "admin/member/detailpage";
    }

    @PostMapping("/admin/member/status.do")
    public String updateStatus(Model model, MemberStatusInput memberStatusInput){
        boolean result = memberService.updateStatus(memberStatusInput);
        if(!result){

        }
        return "redirect:/admin/member/detail.do?userid="+memberStatusInput.getUserId();
    }

    @PostMapping("/admin/member/updatepassword.do")
    public String updatePassword(Model model , UpdateUserPasswordInput updateUserPasswordInput){
        if(!updateUserPasswordInput.checkValidate()){
            return null;
        }
        boolean result = memberService.updatePassword(updateUserPasswordInput);



        return "redirect:/admin/member/detail.do?userid="+updateUserPasswordInput.getUserId();
    }


}
