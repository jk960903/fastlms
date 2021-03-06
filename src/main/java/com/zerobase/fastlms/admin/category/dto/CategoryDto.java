package com.zerobase.fastlms.admin.category.dto;

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

    private long id;
    private String categoryName;
    private int sortValue;
    private boolean usingYn;


    //ADD COLUMNS
    private int courseCount;


    public static List<CategoryDto> of (List<Category> categories) {
        if (categories != null) {
            List<CategoryDto> categoryList = new ArrayList<>();
            for(Category x : categories) {
                categoryList.add(of(x));
            }
            return categoryList;
        }

        return null;
    }

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .sortValue(category.getSortValue())
                .usingYn(category.isUsingYn())
                .build();
    }
}
