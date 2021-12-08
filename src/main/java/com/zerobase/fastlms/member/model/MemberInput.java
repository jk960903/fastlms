package com.zerobase.fastlms.member.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInput{

    private String userId;
    private String userName;
    private String password;
    private String phone;

}
