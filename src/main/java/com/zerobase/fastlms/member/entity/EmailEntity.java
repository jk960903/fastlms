package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userId;

    @Column(length = 1000)
    private String EmailTitle;

    @Column(length = 1000)
    private String EmailContent;
}
