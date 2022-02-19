package com.zerobase.fastlms.homework12.web.infra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    // header
    INVALID_HEADER(400, "유효하지 않은 헤더 입니다"),

    // user
    USER_NOT_FOUND(400, "유저 정보를 찾을 수 없습니다"),
    ACCESS_DENIED(400, "유효하지 않은 접근입니다"),


    NOT_FOUND_BOOK(500, "도서를 찾을 수 없습니다"),
    NOT_FOUND_BOOK_STOCK(500, "재고를 찾을 수 없습니다."),
    BOOK_IS_NOT_SALE(500, "판매중인 도서가 아닙니다"),

    NOT_ENOUGH_STOCK(500, "재고가 부족합니다"),

    FAIL_BOOK_ORDER(500, "도서주문에 실패하였습니다"),
    ;
    private final int status;
    private final String message;
}
