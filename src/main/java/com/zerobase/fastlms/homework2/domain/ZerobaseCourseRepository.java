package com.zerobase.fastlms.homework2.domain;

import java.util.List;
import java.util.Optional;

public interface ZerobaseCourseRepository {

    Optional<ZerobaseCourse> findById(Long id);

    List<ZerobaseCourse> findAll();
}
