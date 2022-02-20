package com.zerobase.fastlms.homework12.domain.repository;



import com.zerobase.fastlms.homework12.domain.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
