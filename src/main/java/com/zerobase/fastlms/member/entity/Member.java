package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="members")
@NoArgsConstructor
public class Member {

    @Id
    private String userId;

    private String userName;

    private String phone;

    private String password;

    private LocalDateTime Regdate;

    private boolean emailAuthYn;

    private String emailAuthKey;//회원가입할때 만드는 키

    private LocalDateTime emailAuthDt;


}
