package com.zerobase.fastlms.homework2.web;

import com.zerobase.fastlms.homework2.domain.ZerobaseCourseQueryService;
import com.zerobase.fastlms.homework2.type.ZerobaseCourseStatus;
import com.zerobase.fastlms.homework2.web.dto.ZerobaseCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ZeroBaseCourseController {

    private final ZerobaseCourseQueryService zerobaseCourseQueryService;

    @GetMapping("/v1/api/course/{id}")
    public ZerobaseCourseResponse getZerobaseCourse(@PathVariable(value = "id") Long id) {
        return ZerobaseCourseResponse.of(zerobaseCourseQueryService.getOrThrow(id));
    }

    @GetMapping("/v1/api/course")
    public List<ZerobaseCourseResponse> getZerobaseCourses(@RequestParam("status") String status) {
        return zerobaseCourseQueryService.getZerobaseCourseList(ZerobaseCourseStatus.findOrDefault(status))
                .stream()
                .map(ZerobaseCourseResponse::of)
                .collect(Collectors.toList());
    }
}
