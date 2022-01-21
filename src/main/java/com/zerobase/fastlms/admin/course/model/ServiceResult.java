package com.zerobase.fastlms.admin.course.model;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResult {

    boolean result ;

    String message;
}
