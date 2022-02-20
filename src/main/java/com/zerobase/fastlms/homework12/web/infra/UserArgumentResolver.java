package com.zerobase.fastlms.homework12.web.infra;

import com.zerobase.fastlms.homework12.domain.entity.User;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final String USER_HEADER = "X-USER-ID";

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
        String Id = webRequest.getParameter("bookId");
        Object object = parameter.getParameterAnnotation(RequestBody.class);

        return null;
    }
}
