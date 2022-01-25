package com.zerobase.fastlms.admin.takecourse.model;

import com.zerobase.fastlms.course.entity.TakeCourse;
import com.zerobase.fastlms.member.model.ParameterChecker;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TakeCourseStatusParameter implements ParameterChecker {

    private long id;

    private String status;

    @Override
    public boolean checkValidate() {
        if(id > 0 && (status.equals(TakeCourse.STATUS_REQ)
                || status.equals(TakeCourse.STATUS_CANCEL) || status.equals(TakeCourse.STATUS_COMPLETE))){
            return true;
        }
        return false;
    }
}
