package com.zerobase.fastlms.member.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetLoginHistoryParam implements ParameterChecker{

    private String userId;


    @Override
    public boolean checkValidate() {
        if(userId == null || userId.equals("")) return false;
        return true;
    }
}
