package com.zerobase.fastlms.admin.dto;

import lombok.*;

import java.time.LocalDateTime;

//entity 는 로우데이터 dto는 가공데이터라고 생각하면 될듯합니다.
@Data
public class MemberDto {

    String userId;

    String password;
    String phone;
    String userName;
    LocalDateTime regdate;

    LocalDateTime emailAuthDt;
    String emailAuthKey;
    boolean emailAuthYn;


    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;
}
