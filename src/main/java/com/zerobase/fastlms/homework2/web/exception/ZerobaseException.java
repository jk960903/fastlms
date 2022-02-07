package com.zerobase.fastlms.homework2.web.exception;

import lombok.Getter;

@Getter
public class ZerobaseException extends RuntimeException{
    private final ExceptionCode code;

    public ZerobaseException(ExceptionCode code){
        this.code = code;
    }

    public ZerobaseException(String message, ExceptionCode code){
        super(message);
        this.code = code;
    }

    public ZerobaseException(String message, ExceptionCode code , Throwable cause){
        super(message,cause);
        this.code = code;
    }

    public ZerobaseException(ExceptionCode code , Throwable cause){
        super(cause);
        this.code = code;
    }

    public ZerobaseException(String message,Throwable cause, boolean enableSuppression, boolean writableStackTrace,ExceptionCode code){
        super(message,cause,enableSuppression,writableStackTrace);
        this.code = code;
    }
    public ZerobaseException(ExceptionCode code , String message){
        super(message);
        this.code = code;
    }

    public ZerobaseException(ExceptionCode code , String message,Throwable cause){
        super(message,cause);
        this.code = code;
    }
}
