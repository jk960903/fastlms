package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.model.CategoryDeleteParam;
import com.zerobase.fastlms.admin.model.CategoryInsertParam;
import com.zerobase.fastlms.admin.model.CategoryUpdateParam;

public interface CategoryService {

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
}
