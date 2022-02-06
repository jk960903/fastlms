package com.zerobase.fastlms.homework2.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_COURSE(HttpStatus.valueOf(500),"강의를 찾을 수 없습니다."),
    INVALID_COURSE_STATUS(HttpStatus.valueOf(400),"유효하지 상태코드 입니다."),
    ALREADY_CLOSED(HttpStatus.valueOf(400),"종료된 강의는 조회할 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
