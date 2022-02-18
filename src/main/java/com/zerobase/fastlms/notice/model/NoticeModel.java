package com.zerobase.fastlms.notice.model;

import com.zerobase.fastlms.notice.param.AddNoticeParam;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class NoticeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private int likeNum;
    private int viewCount;


    public static NoticeModel of(AddNoticeParam addNoticeParam){
        NoticeModel noticeModel = NoticeModel.builder()
                .content(addNoticeParam.getContent())
                .regDate(LocalDateTime.now())
                .title(addNoticeParam.getTitle())
                .writer(addNoticeParam.getWriter())
                .likeNum(0)
                .viewCount(0).build();

        return noticeModel;
    }
}
