package com.zerobase.fastlms.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ZerobaseCourse {
    @Id
    private Long id;

    private String name;

    private String logo;

    private String status;

    private LocalDate startAt;

    private LocalDate endAt;

    private boolean hidden;


}
