package com.zerobase.fastlms.homework12.domain.repository;



import com.zerobase.fastlms.homework12.domain.entity.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookStockRepository extends JpaRepository<BookStock, Long> {
    Optional<BookStock> findByBookId(long bookId);
}
