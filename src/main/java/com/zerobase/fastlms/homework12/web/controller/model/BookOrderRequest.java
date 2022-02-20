package com.zerobase.fastlms.homework12.web.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookOrderRequest {
    private Long bookId;

    public static BookOrderRequest of(Long bookId) {
        BookOrderRequest request = new BookOrderRequest();
        request.bookId = bookId;
        return request;
    }
}
