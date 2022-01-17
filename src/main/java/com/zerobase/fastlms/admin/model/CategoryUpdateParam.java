package com.zerobase.fastlms.admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryUpdateParam {

    private Long id;

    private String categoryName;

    private Integer sortValue;

    private boolean usingYn;


}
