package com.zerobase.fastlms.member.model;

import lombok.*;
import org.springframework.validation.FieldError;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

    private String field;

    private String message;

    public static ResponseError of(FieldError e){
        return ResponseError.builder()
                .field(e.getField())
                .message(e.getDefaultMessage())
                .build();
    }
}
