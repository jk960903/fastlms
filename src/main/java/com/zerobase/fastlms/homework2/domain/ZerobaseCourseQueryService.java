package com.zerobase.fastlms.homework2.domain;

import com.zerobase.fastlms.homework2.type.ZerobaseCourseStatus;
import com.zerobase.fastlms.homework2.web.exception.ExceptionCode;
import com.zerobase.fastlms.homework2.web.exception.ZerobaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZerobaseCourseQueryService {
    private final ZerobaseCourseRepository zerobaseCourseRepository;

    public ZerobaseCourse getOrThrow(Long id) {
        return zerobaseCourseRepository.findById(id)
                .filter(it -> !it.isHidden())
                .orElseThrow(() -> new ZerobaseException(ExceptionCode.NOT_FOUND_COURSE)); // TODO 적당히 Exception을 바꿔보세요
    }

    public List<ZerobaseCourse> getZerobaseCourseList(ZerobaseCourseStatus status) {

        checkStatus(status);

        return zerobaseCourseRepository.findAll()
                .stream()
                .filter(it -> !it.isHidden())
                .filter(it -> it.isSameStatus(status))
                .collect(Collectors.toList());
    }

    private void checkStatus(ZerobaseCourseStatus status) {
        if (status.isUnknown()){
            throw new ZerobaseException(ExceptionCode.INVALID_COURSE_STATUS);
        }

        if (status.isClose())
            throw new ZerobaseException(ExceptionCode.ALREADY_CLOSED);
    }
}
