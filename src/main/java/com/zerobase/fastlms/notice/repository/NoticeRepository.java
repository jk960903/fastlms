package com.zerobase.fastlms.notice.repository;

import com.zerobase.fastlms.notice.model.NoticeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<NoticeModel,Integer> {

    //<List<NoticeModel>> findByA
}
