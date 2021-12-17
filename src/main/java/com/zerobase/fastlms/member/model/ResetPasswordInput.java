package com.zerobase.fastlms.member.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResetPasswordInput {

    private String userId;
    private String userName;
    private String id;
    private String password;
}
