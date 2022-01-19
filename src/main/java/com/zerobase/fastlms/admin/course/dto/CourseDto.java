package com.zerobase.fastlms.admin.course.dto;

import com.zerobase.fastlms.admin.course.entity.Course;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private long id;
    private long categoryId;
    private String imagePath;
    private String keyword;
    String subject;
    private String summary;
    private String contents;
    private long price;
    private long salePrice;
    private LocalDate saleEndDate;
    private LocalDateTime regdate;//등록일(추가날짜)
    private LocalDateTime updateDate;//수정일(수정날짜)


    //추가컬럼
    long totalCount;
    long seq;


    public static CourseDto of(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .categoryId(course.getCategoryId())
                .imagePath(course.getImagePath())
                .keyword(course.getKeyword())
                .subject(course.getSubject())
                .summary(course.getSummary())
                .contents(course.getContents())
                .price(course.getPrice())
                .salePrice(course.getSalePrice())
                .saleEndDate(course.getSaleEndDate())
                .regdate(course.getRegdate())
                .updateDate(course.getUpdateDate())
                .build();
    }


}
