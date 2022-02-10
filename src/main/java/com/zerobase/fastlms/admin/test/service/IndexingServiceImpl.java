package com.zerobase.fastlms.admin.test.service;


import com.zerobase.fastlms.admin.test.entity.Indexing;
import com.zerobase.fastlms.admin.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndexingServiceImpl implements IndexingService{

    private final TestRepository testRepository;

    @Override
    public List<Indexing> findMemberId(Integer memberId) {
        return testRepository.findIndexingsByMemberId(memberId).get();
    }

    @Override
    public void AddIndexing() {
        int memberid = 1;

        List<Indexing> list= new ArrayList<>();
        for(int i = 1; i < 1000; i++){
            for(int j = 1; j < 1000; j++){
                Indexing temp = Indexing.builder().memberId(i).FavoriteId(j).legdate(LocalDateTime.now()).build();

                list.add(temp);
            }
        }

        testRepository.saveAll(list);
    }


}
