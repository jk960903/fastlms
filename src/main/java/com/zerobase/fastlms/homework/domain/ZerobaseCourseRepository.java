package com.zerobase.fastlms.homework.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ZerobaseCourseRepository extends JpaRepository<ZerobaseCourse,Long> {


    List<ZerobaseCourse> findAll();

    Optional<ZerobaseCourse> findByIdAndHidden(long id, boolean hidden);

    List<ZerobaseCourse> findByStatusAndHidden(String status , boolean hidden);

    List<ZerobaseCourse> findByStatusAndStartAtGreaterThanAndEndAtIsLessThan(String status, LocalDate targetDt1,LocalDate targetDt2);
}
