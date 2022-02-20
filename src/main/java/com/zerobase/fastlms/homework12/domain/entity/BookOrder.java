package com.zerobase.fastlms.homework12.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "book_order")
@NoArgsConstructor
public class BookOrder extends BaseEntity {
    @Id
    @Column(name = "book_order_id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Builder
    public BookOrder(Long id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

}
