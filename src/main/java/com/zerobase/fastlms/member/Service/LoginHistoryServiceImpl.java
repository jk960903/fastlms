package com.zerobase.fastlms.member.Service;

import com.zerobase.fastlms.member.Repostiory.LoginHistoryRepository;
import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.model.GetLastLoginHistoryParam;
import com.zerobase.fastlms.member.model.GetLoginHistoryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService{

    private final LoginHistoryRepository loginHistoryRepository;


    @Override
    public List<LoginHistory> getLoginHistory(String userId) {

        if(userId == null || userId.equals("")){
            return null;
        }

        Optional<List<LoginHistory>> optionalLoginHistories = loginHistoryRepository.findByUserId(userId);

        if(optionalLoginHistories.isEmpty()){
            return null;
        }

        return optionalLoginHistories.get();


    }

    @Override
    public boolean insertLoginHistory(HttpServletRequest request,String userName) {

        LoginHistory loginHistory = LoginHistory.builder()
                .loginDate(LocalDateTime.now())
                .ip(request.getRemoteAddr())
                .userId(userName)
                .userAgent(request.getHeader("User-Agent")).build();

        loginHistoryRepository.save(loginHistory);

        return true;
    }


}
