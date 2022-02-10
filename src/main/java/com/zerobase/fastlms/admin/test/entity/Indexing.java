package com.zerobase.fastlms.admin.test.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Indexing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long seq;

    public Integer memberId;

    public Integer FavoriteId;

    public LocalDateTime legdate;
}
