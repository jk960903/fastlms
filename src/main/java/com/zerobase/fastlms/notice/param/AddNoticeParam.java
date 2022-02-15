package com.zerobase.fastlms.notice.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddNoticeParam {

    private String content;
    private String title;
    private String writer;

}
