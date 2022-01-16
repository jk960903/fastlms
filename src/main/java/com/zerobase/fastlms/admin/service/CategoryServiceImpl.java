package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.model.CategoryDeleteParam;
import com.zerobase.fastlms.admin.model.CategoryInsertParam;
import com.zerobase.fastlms.admin.model.CategoryUpdateParam;
import com.zerobase.fastlms.admin.repository.CategoryRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public boolean add(CategoryInsertParam categoryInsertParam) {

        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryInsertParam.getCategoryName());


        return false;
    }

    @Override
    public boolean update(CategoryUpdateParam categoryUpdateParam) {
        return false;
    }

    @Override
    public boolean delete(CategoryDeleteParam categoryDeleteParam) {
        return false;
    }
}
