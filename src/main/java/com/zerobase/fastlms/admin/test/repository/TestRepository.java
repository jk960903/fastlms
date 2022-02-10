package com.zerobase.fastlms.admin.test.repository;

import com.zerobase.fastlms.admin.test.entity.Indexing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Indexing,Long> {

    Optional<List<Indexing>> findIndexingsByMemberId(Integer memberId);
}
