package com.zerobase.fastlms.notice.service;

import com.zerobase.fastlms.notice.model.NoticeModel;
import com.zerobase.fastlms.notice.param.AddNoticeParam;
import com.zerobase.fastlms.notice.param.GetNoticeParam;
import com.zerobase.fastlms.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeModel> getNoticeList(){
        return noticeRepository.findAll();
    }

    @Override
    public NoticeModel getNotice(GetNoticeParam getNoticeParam) {
        Optional<NoticeModel> optional = noticeRepository.findById(getNoticeParam.getId());

        if(optional.isEmpty()){
            return null;
        }

        return optional.get();
    }

    @Override
    public void addNotice(AddNoticeParam addNoticeParam) {
        NoticeModel noticeModel = NoticeModel.of(addNoticeParam);

        noticeRepository.save(noticeModel);
    }
}
