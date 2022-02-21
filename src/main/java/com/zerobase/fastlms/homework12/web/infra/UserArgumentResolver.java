package com.zerobase.fastlms.homework12.web.infra;

import com.zerobase.fastlms.homework12.domain.entity.User;
import com.zerobase.fastlms.homework12.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final String USER_HEADER = "X-USER-ID";


    private final UserRepository userRepository;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println(parameter.getParameterAnnotations());
        System.out.println(parameter.getParameterType());
        return parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)webRequest.getNativeRequest();
        String header = httpServletRequest.getHeader(USER_HEADER);
        if(header == null){
            throw new ZeroBaseException2(ExceptionCode.INVALID_HEADER);
        }
        User user = null;

        if(webRequest.getParameter("id") !=null){

            Optional<User> optionalUser = userRepository.findById(Long.parseLong(webRequest.getParameter("id")));

            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }

        }
        return user;
    }
}
