package com.zerobase.fastlms.member.Repostiory;

import com.zerobase.fastlms.member.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity,String> {
}
