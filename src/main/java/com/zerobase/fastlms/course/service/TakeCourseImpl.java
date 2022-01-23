package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.admin.category.repository.CategoryRepository;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.takecourse.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.takecourse.model.TakeCourseListParameter;
import com.zerobase.fastlms.admin.takecourse.model.TakeCourseStatusParameter;
import com.zerobase.fastlms.course.entity.TakeCourse;
import com.zerobase.fastlms.course.repository.TakeCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TakeCourseImpl implements TakeCourseService{

    private final TakeCourseRepository takeCourseRepository;

    private final CategoryRepository categoryRepository;
    @Override
    public List<TakeCourseDto> list(TakeCourseListParameter parameter) {
        List<TakeCourse> takeCourse = null;
        if(parameter.getSearchCourseId() == 0){
            takeCourse = takeCourseRepository.findAll();

        }else{
            takeCourse = takeCourseRepository.findTakeCoursesByCourseId(parameter.getSearchCourseId());
        }
        return TakeCourseDto.of(takeCourse);
    }

    @Override
    public ServiceResult updateStatus(TakeCourseStatusParameter parameter) {
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(parameter.getId());
        ServiceResult serviceResult = new ServiceResult();
        if(optionalTakeCourse.isEmpty()){
            serviceResult = ServiceResult.builder().result(false).message("없는 수업 입니다. ").build();

            return serviceResult;
        }
        TakeCourse takeCourse = optionalTakeCourse.get();

        takeCourse.setStatus(parameter.getStatus());

        takeCourseRepository.save(takeCourse);

        serviceResult = ServiceResult.builder().result(true).message("success").build();

        return serviceResult;


    }
}
