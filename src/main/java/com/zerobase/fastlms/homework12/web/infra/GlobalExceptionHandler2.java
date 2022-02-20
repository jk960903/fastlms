package com.zerobase.fastlms.homework12.web.infra;


import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler2 {

    @ResponseBody
    @ExceptionHandler(value = ZeroBaseException2.class)
    public ResponseEntity<ErrorResult> error(ZeroBaseException2 exception) {
        return new ResponseEntity<>(ErrorResult.of(exception), HttpStatus.valueOf(exception.getHttpStatus()));
    }

    @Value
    @Builder
    static class ErrorResult{
        private String errorCode;
        private String message;

        static ErrorResult of(ZeroBaseException2 bookStoreException){
            return ErrorResult
                    .builder()
                    .errorCode(bookStoreException.getErrorCode())
                    .message(bookStoreException.getMessage())
                    .build();
        }
    }
}
