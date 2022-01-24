package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

//entity 는 로우데이터 dto는 가공데이터라고 생각하면 될듯합니다.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    long totalCount;

    long seq;

    String userStatus;

    LocalDateTime lastLoginDate;

    private String regdateText;

    private String updateDateText;


    //관리자 여부
    //회원에 따르 ROLE 을 지정할것이냐
    // 회원 레벨이 회원 / 준회원 / 특별회원 등등 / 관리자
    // ROLE USER / SEMI_USER / SPECIAL_USER / ADMIN

    //이용가능 상태 ,정지 상태



    public static com.zerobase.fastlms.member.dto.MemberDto of(Member member){
        return null;
    }
}
