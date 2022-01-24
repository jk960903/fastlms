package com.zerobase.fastlms.admin.course.service;

import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.param.CourseAddInputParam;
import com.zerobase.fastlms.admin.course.param.CourseDeleteParam;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import com.zerobase.fastlms.course.model.CourseDetailParam;
import com.zerobase.fastlms.course.model.TakeCourseInput;

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


    boolean delete(CourseDeleteParam parameter);


    /**
     *  프론트 강의 목록
     * @param courseListParam
     * @return
     */
    List<CourseDto> frontList(CourseListParam courseListParam);

    CourseDto frontDetail(CourseDetailParam parameter);

    /**
     * 수강신청
     * @param parameter
     * @return
     */
    ServiceResult req(TakeCourseInput parameter);

    /**
     * 전체 강좌 목록
     * @return
     */
    List<CourseDto> listAll();
}
