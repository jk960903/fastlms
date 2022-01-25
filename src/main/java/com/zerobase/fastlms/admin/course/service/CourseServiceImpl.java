package com.zerobase.fastlms.admin.course.service;

import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.entity.Course;
import com.zerobase.fastlms.admin.course.mapper.CourseMapper;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.param.CourseAddInputParam;
import com.zerobase.fastlms.admin.course.param.CourseDeleteParam;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import com.zerobase.fastlms.admin.course.repository.CourseRepository;
import com.zerobase.fastlms.course.entity.TakeCourse;
import com.zerobase.fastlms.course.model.CourseDetailParam;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import com.zerobase.fastlms.course.repository.TakeCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final TakeCourseRepository takeCourseRepository;

    private LocalDate getLocalDate(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        LocalDate localDate = null;
        try{
            return LocalDate.parse(value,formatter);
        }catch(Exception e){

        }

        return null;
    }

    @Override
    public boolean add(CourseAddInputParam parameter) {

        Course course = Course.builder()
                .categoryId(parameter.getCategoryId())
                .subject(parameter.getSubject())
                .keyword(parameter.getKeyword())
                .summary(parameter.getSummary())
                .contents(parameter.getContents())
                .price(parameter.getPrice())
                .salePrice(parameter.getSalePrice())
                .saleEndDate(getLocalDate(parameter.getSaleEndDate()))
                .regdate(LocalDateTime.now())
                .filename(parameter.getFileName())
                .build();


        courseRepository.save(course);
        return true;
    }

    @Override
    public List<CourseDto> list(CourseListParam courseListParam) {
        long totalCount = courseMapper.selectListCount(courseListParam);

        List<CourseDto> list = courseMapper.selectList(courseListParam);

        if(!CollectionUtils.isEmpty(list)){
            int i = 0 ;
            for(CourseDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - courseListParam.getPageStart() -1-i);
                i++;
            }
        }
        return list;
    }

    @Override
    public CourseDto getById(long id) {

        return courseRepository.findById(id).map(CourseDto::of).orElse(null);
    }

    @Override
    public boolean set(CourseAddInputParam parameter) {

        Optional<Course> optionalCourse = courseRepository.findById(parameter.getId());

        if(optionalCourse.isEmpty()){
            return false;
        }

        Course course = optionalCourse.get();



        course.setCategoryId(parameter.getCategoryId());
        course.setSubject(parameter.getSubject());
        course.setContents(parameter.getContents());
        course.setKeyword(parameter.getKeyword());
        course.setImagePath(parameter.getImagePath());
        course.setPrice(parameter.getPrice());
        course.setSaleEndDate(getLocalDate(parameter.getSaleEndDate()));
        course.setSummary(parameter.getSummary());
        course.setUpdateDate(LocalDateTime.now());
        course.setSalePrice(parameter.getSalePrice());
        course.setFilename(parameter.getFileName());
        courseRepository.save(course);

        return true;
    }

    @Override
    public boolean delete(CourseDeleteParam parameter) {

        List<Long> idList= parameter.getIdList();

        for(Long id : idList){

            Optional<Course> optionalCourse = courseRepository.findById(id);

            if(optionalCourse.isEmpty()){
                continue;
            }

            Course course = optionalCourse.get();

            courseRepository.delete(course);

        }


        return true;

    }

    @Override
    public List<CourseDto> frontList(CourseListParam courseListParam) {
        if(courseListParam.getCategoryId() < 1){
            List<Course> courseList = courseRepository.findAll();

            return CourseDto.of(courseList);
        }


        Optional<List<Course>> optionalCourseList = courseRepository.findByCategoryId(courseListParam.getCategoryId());

        if(optionalCourseList.isEmpty()){
            return null;
        }

        List<Course> courseList = optionalCourseList.get();




        return CourseDto.of(courseList);
    }

    @Override
    public CourseDto frontDetail(CourseDetailParam parameter) {
        Optional<Course> optionalCourse = courseRepository.findById(parameter.getId());

        if(optionalCourse.isEmpty()){
            return null;
        }
        return CourseDto.of(optionalCourse.get());
    }

    @Override
    public ServiceResult req(TakeCourseInput parameter) {
        ServiceResult serviceResult = new ServiceResult();

        Optional<Course> optionalCourse = courseRepository.findById(parameter.getCourseId());

        if(optionalCourse.isEmpty()){
            serviceResult.setResult(false);
            serviceResult.setMessage("강좌 정보가 존재하지 않습니다. ");

            return serviceResult;
        }

        String[] statusList = {TakeCourse.STATUS_CANCEL,TakeCourse.STATUS_REQ,TakeCourse.STATUS_COMPLETE};


        Course course = optionalCourse.get();
        long count = takeCourseRepository.countTakeCourseByCourseIdAndUserIdAndStatusIn(course.getId(), parameter.getUserId(), Arrays.asList(statusList));

        if(count > 0 ){
            serviceResult.setResult(false);
            serviceResult.setMessage("중복된 강좌 입니다. ");

            return serviceResult;
        }

        TakeCourse takeCourse = TakeCourse.builder()
                .courseId(course.getId())
                .userId(parameter.getUserId())
                .payPrice(course.getSalePrice())
                .regdate(LocalDateTime.now())
                .status(TakeCourse.STATUS_REQ)
                .build();

        takeCourseRepository.save(takeCourse);

        return serviceResult;
    }

    @Override
    public List<CourseDto> listAll() {
        return null;
    }
}
