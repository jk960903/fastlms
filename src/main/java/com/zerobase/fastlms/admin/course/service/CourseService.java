package com.zerobase.fastlms.admin.course.service;

import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.param.CourseAddInputParam;
import com.zerobase.fastlms.admin.course.param.CourseListParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface CourseService {

    /**
     * 강의 추가
     * @param courseAddInputParam
     * @return
     */
    boolean add(CourseAddInputParam courseAddInputParam);


    /**
     * 강의 리스트
     * @param courseListParam
     * @return
     */
    List<CourseDto> list(CourseListParam courseListParam);

    /**
     * 강의 정보 ID 검색
     * @param id
     * @return
     */
    CourseDto getById(long id);

    /**
     * 강의 수정
     * @param parameter
     * @return
     */
    boolean set(CourseAddInputParam parameter);

}
