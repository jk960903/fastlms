package com.zerobase.fastlms.homework2.web.advice;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResult {
    private String code;
    private String message;
}
