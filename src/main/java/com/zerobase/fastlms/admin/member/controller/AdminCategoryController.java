package com.zerobase.fastlms.admin.member.controller;

import com.zerobase.fastlms.admin.model.CategoryInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminCategoryController {

    @GetMapping("/admin/category/list.do")
    public String list(Model model){
        return "/admin/category/list";
    }

    @PostMapping("/admin/category/add.do")
    public String add(Model model, CategoryInput categoryInput){

        return null;
    }
}
