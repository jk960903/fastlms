package com.zerobase.fastlms.admin.category.controller;

import com.zerobase.fastlms.admin.category.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.CategoryDeleteParam;
import com.zerobase.fastlms.admin.model.CategoryInsertParam;
import com.zerobase.fastlms.admin.model.CategoryUpdateParam;
import com.zerobase.fastlms.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/category/list.do")
    public String list(Model model){
        List<CategoryDto> list = categoryService.list();

        model.addAttribute("list",list);

        return "/admin/category/list";
    }

    @PostMapping("/admin/category/add.do")
    public String add(Model model, CategoryInsertParam categoryInput){
        boolean result = categoryService.add(categoryInput);

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/delete.do")
    public String del(Model model, CategoryDeleteParam parameter){

        boolean result = categoryService.delete(parameter);

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/update.do")
    public String update(Model model, CategoryUpdateParam parameter){

        boolean result = categoryService.update(parameter);

        return "redirect:/admin/category/list.do";
    }
}
