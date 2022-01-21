package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.category.dto.CategoryDto;
import com.zerobase.fastlms.admin.course.controller.BaseController;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import com.zerobase.fastlms.admin.course.service.CourseService;
import com.zerobase.fastlms.admin.category.service.CategoryService;
import com.zerobase.fastlms.course.model.CourseDetailParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("course")
    public String course(Model model,
                         CourseListParam parameter) {
        List<CourseDto> courseDtoList = courseService.frontList(parameter);
        model.addAttribute("list",courseDtoList);

        List<CategoryDto> categoryDtoList = categoryService.frontList(CategoryDto.builder().build());
        model.addAttribute("categoryList",categoryDtoList);

        return "course/index";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(Model model , CourseDetailParam parameter){


        CourseDto courseDto = courseService.frontDetail(parameter);
        model.addAttribute("detail",courseDto);

        return "course/detail";
    }


}
