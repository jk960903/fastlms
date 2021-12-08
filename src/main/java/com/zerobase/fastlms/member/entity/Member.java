package com.zerobase.fastlms.member.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="members")
public class Member {

    @Id
    private String userId;

    private String userName;

    private String phone;

    private String password;

    private LocalDateTime Regdate;
}
