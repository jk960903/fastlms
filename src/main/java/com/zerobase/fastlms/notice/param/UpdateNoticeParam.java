package com.zerobase.fastlms.notice.param;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNoticeParam {

    private int id;

    private String title;

    private String content;


}
