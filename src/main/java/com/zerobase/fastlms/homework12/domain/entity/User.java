package com.zerobase.fastlms.homework12.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime deletedAt;

    public User(String name, LocalDateTime deletedAt) {
        this.name = name;
        this.deletedAt = deletedAt;
    }

    @Builder
    public User(String name) {
        this.name = name;
    }

    public boolean equalsTo(User seller) {
        return this.id.equals(seller.id);
    }

    public boolean equalsTo(Long id) {
        return this.id.equals(id);
    }

    public boolean isUnregister() {
        return nonNull(deletedAt);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void unregister() {
        this.deletedAt = LocalDateTime.now();
    }
}
