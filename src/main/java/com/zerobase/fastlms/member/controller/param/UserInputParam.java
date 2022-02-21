package com.zerobase.fastlms.member.controller.param;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInputParam {

    @Email(message = "이메일 형식에 맞게 입력해주세요 ")
    @NotBlank(message = "이메일은 필수항목 입니다. ")
    private String email;


    @NotBlank(message = "이름은 필수항목 입니다. ")
    private String userName;

    @NotBlank(message= " 비밀번호는 필수입력 항목입니다. ")
    private String password;

    private String phone;
}


