package com.zerobase.fastlms.member.Repostiory;

import com.zerobase.fastlms.member.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity,String> {
    Optional<EmailEntity> findById(int id);
}
