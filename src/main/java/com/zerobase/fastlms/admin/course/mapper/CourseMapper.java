package com.zerobase.fastlms.admin.course.mapper;

import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import com.zerobase.fastlms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    long selectListCount(CourseListParam parammeter);

    List<CourseDto> selectList(CourseListParam parameter);
}
