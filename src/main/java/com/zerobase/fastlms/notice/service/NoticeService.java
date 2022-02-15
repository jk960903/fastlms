package com.zerobase.fastlms.notice.service;

import com.zerobase.fastlms.notice.model.NoticeModel;
import com.zerobase.fastlms.notice.param.AddNoticeParam;
import com.zerobase.fastlms.notice.param.GetNoticeParam;

import java.util.List;

public interface NoticeService {

    List<NoticeModel> getNoticeList();

    NoticeModel getNotice(GetNoticeParam getNoticeParam);

    void addNotice(AddNoticeParam addNoticeParam);
}
