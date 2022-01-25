package com.zerobase.fastlms.admin.course.param;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseAddInputParam {

    private Long id;
    private String subject;

    private long categoryId;
    private String imagePath;
    private String keyword;
    private String summary;
    private String contents;
    private long price;
    private long salePrice;
    private String saleEndDate;
    private LocalDateTime regdate;//등록일(추가날짜)
    private LocalDateTime updateDate;//수정일(수정날짜)

    private String fileName;
}
