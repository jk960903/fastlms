package com.zerobase.fastlms.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseResult<T> {

    ResponseResultHeader header;

    T body;

}
