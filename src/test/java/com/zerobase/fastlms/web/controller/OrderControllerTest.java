package com.zerobase.fastlms.web.controller;


import com.zerobase.fastlms.homework12.web.controller.model.BookOrderRequest;
import com.zerobase.fastlms.homework12.web.infra.ExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest extends AbstractMockMvcTestBoilerplate {

    private final Long MOCK_BOOK_ID = 2L;
    private final Long MOCK_USER_ID = 1L;

    @Test
    @DisplayName("X-USER-ID 가 존재하지 않을때")
    void not_found_header() throws Exception {

        RequestBuilder requestBuilder = post("/api/book/order")
                .contentType(APPLICATION_JSON)
                .content(toJson(BookOrderRequest.of(MOCK_BOOK_ID)));

        mvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.error_code").value(ExceptionCode.INVALID_HEADER.name()))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("판매중인 책이 아닐떄")
    void not_found_book() throws Exception {

        RequestBuilder requestBuilder = post("/api/book/order")
                .contentType(APPLICATION_JSON)
                .header("X-USER-ID", MOCK_USER_ID)
                .content(toJson(BookOrderRequest.of(1L)));

        mvc.perform(requestBuilder)
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.error_code").value(ExceptionCode.BOOK_IS_NOT_SALE.name()))
                .andExpect(jsonPath("$.message").exists());
    }
}