package com.zerobase.fastlms.homework12.web.controller.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookOrderResponse {
    private Long orderId;
}
