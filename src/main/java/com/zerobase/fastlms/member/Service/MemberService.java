package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.model.MemberStatusInput;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.admin.model.UpdateUserPasswordInput;
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
    List<MemberDto> list(MemberParam memberParam);

    /**
     * 회원 상세 정보 (관리자 페이지에서만 사용가능)
     * @param userId
     * @return
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     * @param memberStatusInput
     * String userId
     * String userStatus
     * @return
     */
    boolean updateStatus(MemberStatusInput memberStatusInput);

    boolean updatePassword(UpdateUserPasswordInput updateUserPasswordInput);


}
