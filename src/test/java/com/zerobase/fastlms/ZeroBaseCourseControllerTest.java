package com.zerobase.fastlms;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import static com.zerobase.fastlms.homework2.web.exception.ExceptionCode.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ZeroBaseCourseControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext ctx) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @DisplayName("강의 단건조회 api 테스트")
    @ParameterizedTest(name = "/v1/api/course/{arguments} 테스트")
    @ValueSource(longs = {1, 2, 3, 4, 5})
    public void success__getZerobaseCourse(long id) throws Exception {
        mockMvc.perform(get("/v1/api/course/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.start_at", notNullValue()))
                .andExpect(jsonPath("$.end_at", notNullValue()));
    }

    @DisplayName("강의 단건조회 실패 api 테스트")
    @ParameterizedTest(name = "/v1/api/course/{arguments} 테스트")
    @ValueSource(longs = {-1, 0, 6, 7, Long.MAX_VALUE})
    public void fail__getZerobaseCourse(long id) throws Exception {
        mockMvc.perform(get("/v1/api/course/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.code", is(NOT_FOUND_COURSE.name())))
                .andExpect(jsonPath("$.message", is(NOT_FOUND_COURSE.getMessage())));
    }

    @DisplayName("강의 다건조회 api 테스트")
    @ParameterizedTest(name = "/v1/api/course?status={arguments} 테스트")
    @ValueSource(strings = {"OPEN", "IN_PROGRESS"})
    public void success__getZerobaseCourses(String status) throws Exception {
        mockMvc.perform(get("/v1/api/course?status=" + status).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.[*].status", Matchers.everyItem(is(status))));
    }

    @DisplayName("강의 다건조회 실패 api 테스트(CLOSE)")
    public void fail____getZerobaseCourses() throws Exception {
        mockMvc.perform(get("/v1/api/course?status=CLOSE").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.code", is(ALREADY_CLOSED.name())))
                .andExpect(jsonPath("$.message", is(ALREADY_CLOSED.getMessage())));
    }

    @DisplayName("강의 다건조회 실패 api 테스트(INVALID)")
    @ParameterizedTest(name = "/v1/api/course?status={arguments} 테스트")
    @ValueSource(strings = {"INVALID", "HELLO", "FOO", "", "1111"})
    public void fail____getZerobaseCourses(String status) throws Exception {
        mockMvc.perform(get("/v1/api/course?status=" + status).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code", is(INVALID_COURSE_STATUS.name())))
                .andExpect(jsonPath("$.message", is(INVALID_COURSE_STATUS.getMessage())));
    }
}
