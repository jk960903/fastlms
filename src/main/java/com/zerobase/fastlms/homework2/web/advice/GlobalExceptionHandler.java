package com.zerobase.fastlms.homework2.web.advice;

import com.zerobase.fastlms.homework2.web.exception.ExceptionCode;
import com.zerobase.fastlms.homework2.web.exception.ZerobaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO : 익셉션 핸들러를 작성하세요
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ZerobaseException.class)
    public ResponseEntity<ErrorResult> ZerobaseException(ZerobaseException e){
        ExceptionCode errorCode = e.getCode();

        ErrorResult result = ErrorResult.builder().code(errorCode.name()).message(errorCode.getMessage()).build();
        return new ResponseEntity<>(result,errorCode.getStatus());
    }
}
