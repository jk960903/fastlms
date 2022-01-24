package com.zerobase.fastlms.admin.banner.controller;


import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.entity.Banner;
import com.zerobase.fastlms.admin.banner.model.BannerAddParameter;
import com.zerobase.fastlms.admin.banner.model.BannerListInput;
import com.zerobase.fastlms.admin.banner.service.BannerService;
import com.zerobase.fastlms.admin.course.controller.BaseController;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BannerController extends BaseController {


    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String BannerList(Model model, BannerListInput parameter){
        List<BannerDto> bannerList = bannerService.getList(parameter);
        /*(int i = 0 ; i <bannerList.size(); i++){
            BannerDto temp = bannerList.get
        }*/

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(bannerList)) {
            totalCount = bannerList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", bannerList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        return "admin/banner/list";
    }

    @GetMapping(value={"/admin/banner/add.do","/admin/banner/edit.do"})
    public String AddBanner(Model model , HttpServletRequest request,BannerAddParameter parameter){

        boolean editMode = request.getRequestURI().contains("/edit.do");
        BannerDto detail = new BannerDto();
        if (editMode) {
            long id = parameter.getId();
            BannerDto existBanner = bannerService.getById(parameter);
            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "error/error";
            }
            detail = existBanner;
        }

        model.addAttribute("detail",detail);
        model.addAttribute("editMode",editMode);


        return "admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String AddBanner(Model model , MultipartFile file , BannerAddParameter parameter,HttpServletRequest request){

        boolean editMode = request.getRequestURI().contains("/edit.do");
        BannerDto detail = null;
        if (editMode) {
            long id = parameter.getId();
            BannerDto existBanner = bannerService.getById(parameter);
            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "error/error";
            }
            detail = existBanner;
        }


        ServiceResult serviceResult = bannerService.add(parameter,file);


        return "admin/banner/list";
    }
}
