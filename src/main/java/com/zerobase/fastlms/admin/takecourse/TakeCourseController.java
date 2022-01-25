package com.zerobase.fastlms.admin.takecourse;

import com.zerobase.fastlms.admin.category.service.CategoryService;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import com.zerobase.fastlms.admin.course.service.CourseService;
import com.zerobase.fastlms.admin.takecourse.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.takecourse.model.TakeCourseListParameter;
import com.zerobase.fastlms.admin.takecourse.model.TakeCourseStatusParameter;
import com.zerobase.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TakeCourseController {

    private final CourseService courseService;

 //   private final CategoryService categoryService;

    private final TakeCourseService  takeCourseService;


    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model, TakeCourseListParameter parameter){

        List<TakeCourseDto> takeCourseDtos = takeCourseService.list(parameter);

        List<CourseDto> courseDtoList = courseService.list(new CourseListParam(parameter.getCategoryId()));
        model.addAttribute("list",takeCourseDtos);
        model.addAttribute("courseList",courseDtoList);

        return "admin/takecourse/list";
    }

    @PostMapping("/admin/takecourse/status.do")
    public String status(Model model , TakeCourseStatusParameter parameter){
        if(parameter.checkValidate()){
            model.addAttribute("message","파라미터가 잘못되었습니다.");
            return "/error/error";
        }
        ServiceResult serviceResult = takeCourseService.updateStatus(parameter);

        if(!serviceResult.isResult()){
            model.addAttribute("message",serviceResult.getMessage());
        }
        model.addAttribute("");

        return "redirect:/admin/takecourse/list";
    }
}
