package com.zerobase.fastlms.admin.course.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private String imagePath;

    private String keyword;

    private String subject;

    @Column(length=1000)
    private String summary;

    @Lob
    private String contents;

    private Long price;

    private Long salePrice;

    private LocalDate saleEndDate;

    private LocalDateTime regdate;

    private LocalDateTime updateDate;

    private String filename;
}
