package com.zerobase.fastlms.notice.service;

import com.zerobase.fastlms.notice.model.NoticeModel;
import com.zerobase.fastlms.notice.param.AddNoticeParam;
import com.zerobase.fastlms.notice.param.GetNoticeParam;
import com.zerobase.fastlms.notice.param.UpdateNoticeParam;
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
        NoticeModel noticeModel = optional.get();
        noticeModel.setViewCount(noticeModel.getViewCount() + 1);

        noticeRepository.save(noticeModel);


        return noticeModel;
    }

    @Override
    public int addNotice(AddNoticeParam addNoticeParam) {
        NoticeModel noticeModel = NoticeModel.of(addNoticeParam);

        noticeModel = noticeRepository.save(noticeModel);

        return noticeModel.getId();
    }

    @Override
    public NoticeModel addNoticeLikeCount(GetNoticeParam getNoticeParam) {
        Optional<NoticeModel> optional = noticeRepository.findById(getNoticeParam.getId());

        if(optional.isEmpty()){
            return null;
        }
        NoticeModel noticeModel = optional.get();
        noticeModel.setLikeNum(noticeModel.getLikeNum() + 1);

        noticeRepository.save(noticeModel);


        return noticeModel;
    }

    @Override
    public NoticeModel updateNotice(UpdateNoticeParam updateNoticeParam) {
        Optional<NoticeModel> optional = noticeRepository.findById(updateNoticeParam.getId());

        if(optional.isEmpty()){
            return null;
        }

        NoticeModel noticeModel = optional.get();

        noticeModel.setContent(updateNoticeParam.getContent());
        noticeModel.setTitle(updateNoticeParam.getTitle());

        noticeRepository.save(noticeModel);

        return noticeModel;
    }


}
