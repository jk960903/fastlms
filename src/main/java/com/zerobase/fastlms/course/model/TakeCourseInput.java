package com.zerobase.fastlms.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TakeCourseInput{
    private long courseId;
    private String userId;
}
