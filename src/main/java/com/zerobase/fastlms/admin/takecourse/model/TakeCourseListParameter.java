package com.zerobase.fastlms.admin.takecourse.model;

import com.zerobase.fastlms.admin.course.param.CommonParam;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TakeCourseListParameter extends CommonParam {

    private long categoryId;

    private long searchCourseId;
}
