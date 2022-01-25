package com.zerobase.fastlms.admin.banner.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bannerName;

    private String LinkAddress;

    private LocalDateTime regdate;

    private Integer orderValue;

    private boolean isShow;

    private String alterText;

    private Integer openValue;

    private String fileName;



}
