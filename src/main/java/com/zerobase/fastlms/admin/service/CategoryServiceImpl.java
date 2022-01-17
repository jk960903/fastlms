package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.error.CategoryNameDuplicateException;
import com.zerobase.fastlms.admin.error.CategoryNotFoundException;
import com.zerobase.fastlms.admin.model.CategoryDeleteParam;
import com.zerobase.fastlms.admin.model.CategoryInsertParam;
import com.zerobase.fastlms.admin.model.CategoryUpdateParam;
import com.zerobase.fastlms.admin.repository.CategoryRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private Sort sortbySortValueDESC(){

        return Sort.by(Sort.Direction.DESC,"sortValue");
    }
    @Override
    public List<CategoryDto> list() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        List<Category> categories = categoryRepository.findAll(sortbySortValueDESC());

        return CategoryDto.of(categories);

    }

    @Override
    public boolean add(CategoryInsertParam categoryInsertParam) {

        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryInsertParam.getCategoryName());

        if(optionalCategory.isPresent()){
            throw new CategoryNameDuplicateException("카테고리 이미 있음");
        }

        Category category = Category.builder()
                .categoryName(categoryInsertParam.getCategoryName())
                .usingYn(true)
                .sortValue(0)
                .build();

        categoryRepository.save(category);


        return false;
    }

    @Override
    public boolean update(CategoryUpdateParam categoryUpdateParam) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryUpdateParam.getId());

        if(!optionalCategory.isPresent()){
            return false;
        }

        Category category = optionalCategory.get();

        category.setCategoryName(categoryUpdateParam.getCategoryName());
        category.setSortValue(categoryUpdateParam.getSortValue());
        category.setUsingYn(categoryUpdateParam.isUsingYn());

        categoryRepository.save(category);

        return true;
    }

    @Override
    public boolean delete(CategoryDeleteParam categoryDeleteParam) {

        categoryRepository.deleteById(categoryDeleteParam.getId());

        return true;
    }
}
