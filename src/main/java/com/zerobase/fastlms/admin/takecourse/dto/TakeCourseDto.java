package com.zerobase.fastlms.admin.takecourse.dto;


import com.zerobase.fastlms.course.entity.TakeCourse;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TakeCourseDto {

    private long seq;

    private Long id;

    private Long courseId;

    String userId;

    long payPrice; // 결제 금액

    String status; //상태 (수강 신청 ,결제 완료 , 수강 취소)

    LocalDateTime regdate;

    private String regdateText;

    private String userName;

    String phone;

    String subject;

    public static List<TakeCourseDto> of(List<TakeCourse> list){
        List<TakeCourseDto> lists = new ArrayList<>();

        int seq = 1;
        for(TakeCourse x : list){
            lists.add(TakeCourseDto.of(x,seq));
            seq++;
        }

        return lists;
    }

    public static TakeCourseDto of(TakeCourse takeCourse , int seq){
        TakeCourseDto takeCourseDto = TakeCourseDto.builder()
                .seq(seq)
                .id(takeCourse.getId())
                .courseId(takeCourse.getCourseId())
                .userId(takeCourse.getUserId())
                .payPrice(takeCourse.getPayPrice())
                .status(takeCourse.getStatus())
                .regdate(takeCourse.getRegdate())
                .userName(takeCourse.getMember().getUserName())
                .phone(takeCourse.getMember().getPhone())
                .subject(takeCourse.getCourse().getSubject())
                .regdateText(getRegdateText(takeCourse.getRegdate()))
                .build();
        return takeCourseDto;
    }


    public static String getRegdateText(LocalDateTime regdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return regdate.format(formatter);
    }
}
