package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.category.service.CategoryService;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.service.CourseService;
import com.zerobase.fastlms.common.ResponseResult;
import com.zerobase.fastlms.common.ResponseResultHeader;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ApiCourseController {

    private final CourseService courseService;

    private final CategoryService categoryService;

    @PostMapping(value = "api/course/req.api",produces = "application/json;")
    public ResponseEntity<?> courseReq(Model model,@RequestBody TakeCourseInput parameter , Principal principal){
        parameter.setUserId(principal.getName());


        ServiceResult result = courseService.req(parameter);
        if(!result.isResult()){
            ResponseResult<Object> responseResult = new ResponseResult<Object>(new ResponseResultHeader(result.isResult(),result.getMessage()),null);


            return ResponseEntity.ok().body(responseResult);
        }
        return ResponseEntity.ok().body(parameter);
    }
}
