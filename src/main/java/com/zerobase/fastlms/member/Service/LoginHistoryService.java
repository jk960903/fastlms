package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.model.GetLastLoginHistoryParam;
import com.zerobase.fastlms.member.model.GetLoginHistoryParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface LoginHistoryService {

    List<LoginHistory> getLoginHistory(String userId);

    boolean insertLoginHistory(HttpServletRequest request,String userName);

}
