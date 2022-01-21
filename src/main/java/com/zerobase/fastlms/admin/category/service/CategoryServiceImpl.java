package com.zerobase.fastlms.admin.category.service;

import com.zerobase.fastlms.admin.category.dto.CategoryDto;
import com.zerobase.fastlms.admin.category.repository.CategoryRepository;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import com.zerobase.fastlms.admin.course.repository.CourseRepository;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.error.CategoryNameDuplicateException;
import com.zerobase.fastlms.admin.model.CategoryDeleteParam;
import com.zerobase.fastlms.admin.model.CategoryInsertParam;
import com.zerobase.fastlms.admin.model.CategoryUpdateParam;
import com.zerobase.fastlms.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    private final CategoryMapper categoryMapper;
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

    @Override
    public List<CategoryDto> frontList(CategoryDto parameter) {
        return categoryMapper.selectList(parameter);
    }

}
