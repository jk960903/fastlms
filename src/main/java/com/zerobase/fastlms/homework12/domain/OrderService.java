package com.zerobase.fastlms.homework12.domain;


import com.zerobase.fastlms.homework12.domain.entity.Book;
import com.zerobase.fastlms.homework12.domain.entity.BookOrder;
import com.zerobase.fastlms.homework12.domain.entity.BookStock;
import com.zerobase.fastlms.homework12.domain.entity.User;
import com.zerobase.fastlms.homework12.domain.repository.BookOrderRepository;
import com.zerobase.fastlms.homework12.domain.repository.BookRepository;
import com.zerobase.fastlms.homework12.domain.repository.BookStockRepository;

import com.zerobase.fastlms.homework12.web.infra.ExceptionCode;
import com.zerobase.fastlms.homework12.web.infra.ZeroBaseException2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final BookRepository bookRepository;
    private final BookStockRepository bookStockRepository;
    private final BookOrderRepository bookOrderRepository;

    public Long order(User user, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ZeroBaseException2(ExceptionCode.NOT_FOUND_BOOK));
        BookStock bookStock = bookStockRepository.findByBookId(bookId)
                .orElseThrow(() -> new ZeroBaseException2(ExceptionCode.NOT_FOUND_BOOK_STOCK));

        checkOnSale(book);
        checkEnoughStock(bookStock);



        bookStock.decreaseStock();
        bookStockRepository.save(bookStock);
        BookOrder bookOrder = bookOrderRepository.save(createBookOrder(user, book));
        return bookOrder.getId();
    }

    private BookOrder createBookOrder(User user, Book book) {
        return BookOrder
                .builder()
                .user(user)
                .book(book)
                .build();
    }

    private void checkEnoughStock(BookStock bookStock) {
        if (!bookStock.enoughStock())
            throw new ZeroBaseException2(ExceptionCode.FAIL_BOOK_ORDER, "도서 재고가 부족합니다.");
    }

    private void checkOnSale(Book book) {
        if (!book.onSale())
            throw new ZeroBaseException2(ExceptionCode.BOOK_IS_NOT_SALE,"판매중인 도서가 아닙니다");
    }
}
