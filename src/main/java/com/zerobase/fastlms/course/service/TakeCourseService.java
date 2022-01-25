package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.takecourse.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.takecourse.model.TakeCourseListParameter;
import com.zerobase.fastlms.admin.takecourse.model.TakeCourseStatusParameter;

import java.util.List;

public interface TakeCourseService {
    List<TakeCourseDto> list(TakeCourseListParameter parameter);

    ServiceResult updateStatus(TakeCourseStatusParameter parameter);
}
