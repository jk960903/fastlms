package com.zerobase.fastlms.admin.banner.mapper;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.model.BannerListInput;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    long selectListCount(BannerListInput parammeter);

    List<BannerDto> selectList(BannerListInput parameter);
}
