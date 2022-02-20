package com.zerobase.fastlms.homework12.domain.entity;


import com.zerobase.fastlms.homework12.domain.type.YesNo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book extends BaseEntity {
    @Id
    @Column(name = "book_id")
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    @Column
    private YesNo sale;

    @Column
    private int minAge;

    @Builder
    public Book(String title, YesNo sale, int minAge) {
        this.title = title;
        this.sale = sale;
        this.minAge = minAge;
    }

    public boolean onSale() {
        return sale.isYes();
    }
}
