package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 비밀번호 초기화 정보를 전송
     */
    boolean sendResetPassword(ResetPasswordInput parameter);

    boolean resetPassword(String uuid, String password);

    /**
     * 입력받은 uuid 값이 유효한지 판단
     */
    boolean checkResetPassword(String uuid);
}
