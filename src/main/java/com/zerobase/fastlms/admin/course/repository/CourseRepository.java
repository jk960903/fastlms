package com.zerobase.fastlms.admin.course.repository;

import com.zerobase.fastlms.admin.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {


    Optional<List<Course>> findByCategoryId(long categoryId);
}
