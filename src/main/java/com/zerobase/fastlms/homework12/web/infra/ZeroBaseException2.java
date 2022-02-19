package com.zerobase.fastlms.homework12.web.infra;

public class ZeroBaseException2 extends RuntimeException {

    private ExceptionCode code;
    private int httpStatus;
    private String message;

    public ZeroBaseException2(ExceptionCode code) {
        this.code = code;
        this.httpStatus = code.getStatus();
        this.message = code.getMessage();
    }

    public ZeroBaseException2(ExceptionCode code, int httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = code.getMessage();
    }

    public ZeroBaseException2(ExceptionCode code, String errorMessage) {
        this.code = code;
        this.httpStatus = code.getStatus();
        this.message = errorMessage;
    }

    public ZeroBaseException2(ExceptionCode code, int httpStatus, String errorMessage) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = errorMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return code.name();
    }
}
