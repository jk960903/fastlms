package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    private String categoryName;

    private Integer sortValue;

    private boolean usingYn;

    public static List<CategoryDto> of(List<Category> categories){
        if(categories != null ){
            List<CategoryDto> categoryList = new ArrayList<>();
            for(Category x : categories){
                categoryList.add(of(x));
            }
            return categoryList;
        }
        return null;
    }

    public static CategoryDto of(Category category){
        return CategoryDto.builder()
                .categoryName(category.getCategoryName())
                .id(category.getId())
                .sortValue(category.getSortValue())
                .usingYn(category.isUsingYn())
                .build();
    }
}
