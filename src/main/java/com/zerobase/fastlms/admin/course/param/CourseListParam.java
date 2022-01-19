package com.zerobase.fastlms.admin.course.param;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseListParam extends CommonParam{
    private String userId;
    private String userName;
    private String phone;
    private String password;

}
