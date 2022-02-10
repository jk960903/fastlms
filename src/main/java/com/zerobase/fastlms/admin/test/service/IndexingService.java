package com.zerobase.fastlms.admin.test.service;

import com.zerobase.fastlms.admin.test.entity.Indexing;

import java.util.List;

public interface IndexingService {

    List<Indexing> findMemberId(Integer memberId);

    void AddIndexing();
}
