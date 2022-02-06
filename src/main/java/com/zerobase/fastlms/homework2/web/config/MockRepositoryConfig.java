package com.zerobase.fastlms.homework2.web.config;

import com.zerobase.fastlms.homework2.domain.ZerobaseCourse;
import com.zerobase.fastlms.homework2.domain.ZerobaseCourseRepository;
import com.zerobase.fastlms.homework2.type.ZerobaseCourseStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class MockRepositoryConfig {

    @Bean
    public ZerobaseCourseRepository zerobaseCourseRepository() {
        return new ZerobaseCourseMockRepository(createMock());
    }

    private Map<Long, ZerobaseCourse> createMock() {
        return Map.ofEntries(
                Map.entry(1L, ZerobaseCourse
                        .builder()
                        .id(1L)
                        .name("Java 백엔드 개발자 취업 (2기)")
                        .status(ZerobaseCourseStatus.IN_PROGRESS)
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build()),
                Map.entry(2L, ZerobaseCourse
                        .builder()
                        .id(2L)
                        .name("프론트엔드 개발자되기")
                        .status(ZerobaseCourseStatus.IN_PROGRESS)
                        .startAt(LocalDate.now().minusMonths(2))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build()),
                Map.entry(3L, ZerobaseCourse
                        .builder()
                        .id(3L)
                        .name("바로 써먹는 데이터 분석")
                        .status(ZerobaseCourseStatus.CLOSE)
                        .startAt(LocalDate.now().minusMonths(2))
                        .endAt(LocalDate.now().minusMonths(1))
                        .build()),
                Map.entry(4L, ZerobaseCourse
                        .builder()
                        .id(4L)
                        .name("직접 만드는 파이썬 자동화 48일")
                        .status(ZerobaseCourseStatus.OPEN)
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build()),
                Map.entry(5L, ZerobaseCourse
                        .builder()
                        .id(5L)
                        .name("Java 백엔드 개발자 취업 (1기)")
                        .status(ZerobaseCourseStatus.OPEN)
                        .startAt(LocalDate.now().plusMonths(1))
                        .endAt(LocalDate.now().plusMonths(2))
                        .build()),
                Map.entry(6L, ZerobaseCourse
                        .builder()
                        .id(6L)
                        .name("테스트용 강의")
                        .status(ZerobaseCourseStatus.OPEN)
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(2))
                        .hidden(true)
                        .build())
        );
    }


    public static class ZerobaseCourseMockRepository implements ZerobaseCourseRepository {

        private final Map<Long, ZerobaseCourse> memoryData;

        public ZerobaseCourseMockRepository(Map<Long, ZerobaseCourse> memoryData) {
            this.memoryData = memoryData;
        }

        public Optional<ZerobaseCourse> findById(Long id) {
            return Optional.ofNullable(memoryData.getOrDefault(id, null));
        }

        public List<ZerobaseCourse> findAll() {
            return memoryData.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }
    }
}
