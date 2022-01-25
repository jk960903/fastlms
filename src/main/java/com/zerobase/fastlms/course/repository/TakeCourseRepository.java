package com.zerobase.fastlms.course.repository;


import com.zerobase.fastlms.admin.course.entity.Course;
import com.zerobase.fastlms.course.entity.TakeCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TakeCourseRepository extends JpaRepository<TakeCourse,Long> {
    long countTakeCourseByCourseIdAndUserIdAndStatusIn(long courseId,String userId , Collection<String> statusList);

    List<TakeCourse> findTakeCoursesByCourseId(long courseId);
}
