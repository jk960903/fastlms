package com.zerobase.fastlms.homework;

import com.zerobase.fastlms.homework.domain.HiddenEnum;
import com.zerobase.fastlms.homework.domain.StatusEnum;
import com.zerobase.fastlms.homework.domain.ZerobaseCourse;
import com.zerobase.fastlms.homework.domain.ZerobaseCourseRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Homework {
    private final ZerobaseCourseRepository repository;

    // TODO: 테스트가 통과할 수 있도록 아래 메서드 들을 작성하세요.

    public Optional<ZerobaseCourse> getZerobaseCourse(Long id) {
        // TODO: id 가 일치하며, hidden = false 인 강의만 조회되어야 함
        return repository.findByIdAndHidden(id, HiddenEnum.FALSE.getHidden());
    }

    public List<ZerobaseCourse> getZerobaseCourse(String status) {
        // TODO: status가 일치하고, hidden = false 인 강의들이 조회되어야 함
        return repository.findByStatusAndHidden(status,HiddenEnum.FALSE.getHidden());
    }

    public List<ZerobaseCourse> getOpenZerobaseCourse(LocalDate targetDt) {
        // TODO: status = "OPEN" 이고, hidden = false 이며,
        //  startAt <= targetDt && targetDt <= endAt 인 강의만 조회되어야함.
        return repository.findByStatusAndStartAtGreaterThanAndEndAtIsLessThan(StatusEnum.OPEN.getStatus(),targetDt,targetDt);
    }
}
