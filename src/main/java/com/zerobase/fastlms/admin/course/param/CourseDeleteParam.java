package com.zerobase.fastlms.admin.course.param;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDeleteParam {

    private List<Long> idList;
}
