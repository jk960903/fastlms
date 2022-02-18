package com.zerobase.fastlms.notice.controller;

import com.zerobase.fastlms.notice.model.NoticeModel;
import com.zerobase.fastlms.notice.param.AddNoticeParam;
import com.zerobase.fastlms.notice.param.GetNoticeParam;
import com.zerobase.fastlms.notice.param.UpdateNoticeParam;
import com.zerobase.fastlms.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController(value = "/api")
@RequiredArgsConstructor
public class ApiNoticeController {

    private final NoticeService noticeService;

    /**
     * 공지사항 게시판의 목록에 대한 요청을 처리하는 API 만들기
     * 조건
     * REST API 형식으로 구현 METHOD GET
     * 요청 주소 /api/notice
     * 공지사항 게시판의 내용을 추상화한 모델 리턴
     */
    @GetMapping("/notice")
    public List<NoticeModel> Notice2(HttpServletRequest request) throws Exception{
        return noticeService.getNoticeList();
    }

    @GetMapping("/notice2")
    public NoticeModel Notice(HttpServletRequest request, GetNoticeParam getNoticeParam) throws Exception{
        return noticeService.getNotice(getNoticeParam);
    }

    @GetMapping("/notice/count")
    public int getNoticeCount(HttpServletRequest request){
        return noticeService.getNoticeList().size();
    }

    @PostMapping("/notice")
    public NoticeModel addNotice(HttpServletRequest request, AddNoticeParam addNoticeParam) throws  Exception{
        int id = noticeService.addNotice(addNoticeParam);

        return noticeService.getNotice(new GetNoticeParam(id));
    }

    @PostMapping("/addnoticelike")
    public NoticeModel addNoticeLike(HttpServletRequest request, GetNoticeParam param) throws Exception{
        return noticeService.addNoticeLikeCount(param);
    }

    @GetMapping("/notice/{id}")
    public NoticeModel updateNotice(HttpServletRequest request, UpdateNoticeParam param) throws Exception{
        return noticeService.updateNotice(param);
    }
}
