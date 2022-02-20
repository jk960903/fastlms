package com.zerobase.fastlms.homework12.domain.repository;


import com.zerobase.fastlms.homework12.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
