package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

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

    /**
     * 회원 목록 리턴(관리자 페이지에서만 사용 가능)
     * @return
     */
    List<MemberDto> list();
}
