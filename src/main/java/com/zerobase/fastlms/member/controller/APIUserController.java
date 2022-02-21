package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.controller.param.UserInputParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user")
@ControllerAdvice
@RequiredArgsConstructor
public class APIUserController {

    @PostMapping("/adduser")
    public void addUser(UserInputParam param, Errors errors){

    }


}
