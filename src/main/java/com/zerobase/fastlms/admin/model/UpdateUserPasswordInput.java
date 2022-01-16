package com.zerobase.fastlms.admin.model;

import com.zerobase.fastlms.member.model.ParameterChecker;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserPasswordInput implements ParameterChecker {
    private String userId;

    private String userPassword;

    @Override
    public boolean checkValidate(){
        if(userId == null || userId.equals("") || userPassword == null || userPassword.equals("")){
            return false;
        }
        return true;
    }

}
