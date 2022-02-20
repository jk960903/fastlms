package com.zerobase.fastlms.domain.entity;


import com.zerobase.fastlms.homework12.domain.entity.BookStock;
import com.zerobase.fastlms.homework12.web.infra.ExceptionCode;
import com.zerobase.fastlms.homework12.web.infra.ZeroBaseException2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class BookStockTest {

    @Test
    @DisplayName("BookStock 재고가 정상적으로 차감된다")
    void success__decreaseStock() {

        final int stockCount = 10;

        BookStock bookStock = BookStock
                .builder()
                .stock(stockCount)
                .build();

        assertThat(bookStock.enoughStock()).isTrue();

        bookStock.decreaseStock();

        assertThat(bookStock.getStock()).isLessThan(stockCount);
    }

    @Test
    @DisplayName("BookStock 재고가 0일때, 차감을 시도하면 익셉션 발생(NOT_ENOUGH_STOCK)")
    void throwException__when__not_enough_stock() {
        BookStock bookStock = BookStock
                .builder()
                .stock(0)
                .build();

        assertThatThrownBy(bookStock::decreaseStock)
                .isInstanceOf(ZeroBaseException2.class)
                .hasFieldOrPropertyWithValue("code", ExceptionCode.NOT_ENOUGH_STOCK);
    }
}
