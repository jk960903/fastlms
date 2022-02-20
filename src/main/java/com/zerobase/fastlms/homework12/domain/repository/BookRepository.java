package com.zerobase.fastlms.homework12.domain.repository;



import com.zerobase.fastlms.homework12.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
}
