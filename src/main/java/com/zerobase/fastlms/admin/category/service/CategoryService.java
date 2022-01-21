package com.zerobase.fastlms.admin.category.service;

import com.zerobase.fastlms.admin.category.dto.CategoryDto;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.model.CategoryDeleteParam;
import com.zerobase.fastlms.admin.model.CategoryInsertParam;
import com.zerobase.fastlms.admin.model.CategoryUpdateParam;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> list();

    /**
     * 카테고리 신규추가
     * @param categoryInsertParam
     * @return
     */
    boolean add(CategoryInsertParam categoryInsertParam);

    /**
     * 카테고리 수정
     * @param categoryUpdateParam
     * @return
     */
    boolean update(CategoryUpdateParam categoryUpdateParam);

    /**
     * 카테고리 삭제
     * @param categoryDeleteParam
     * @return
     */
    boolean delete(CategoryDeleteParam categoryDeleteParam);

    /**
     * 프론트 카테고리 정보
     * @param categoryDto
     * @return
     */
    public List<CategoryDto> frontList(CategoryDto parameter);
}
