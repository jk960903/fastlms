package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="members")
@NoArgsConstructor
public class Member implements MemberCode{

    @Id
    private String userId;

    private String userName;

    private String phone;

    private String password;

    private LocalDateTime Regdate;

    private boolean emailAuthYn;

    private String emailAuthKey;//회원가입할때 만드는 키

    private LocalDateTime emailAuthDt;

    private String resetPasswordKey;

    private LocalDateTime resetPasswordLimitDt;

    private LocalDateTime lastLoginDate;

    private LocalDateTime updateDate;

    //관리자 여부
    //회원에 따르 ROLE 을 지정할것이냐
    // 회원 레벨이 회원 / 준회원 / 특별회원 등등 / 관리자
    // ROLE USER / SEMI_USER / SPECIAL_USER / ADMIN

    private boolean adminYn;


    //이용가능 상태 ,정지 상태
    private String userStatus;
}
