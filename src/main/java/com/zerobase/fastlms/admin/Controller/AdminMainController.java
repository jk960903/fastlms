package com.zerobase.fastlms.admin.Controller;

import com.zerobase.fastlms.member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminMainController {

    private final MemberService memberService;

    @GetMapping("/admin/main.do")
    public String main(){
        return "admin/main";
    }
}
