package com.zerobase.fastlms.admin.banner.service;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.entity.Banner;
import com.zerobase.fastlms.admin.banner.model.BannerAddParameter;
import com.zerobase.fastlms.admin.banner.model.BannerListInput;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface BannerService {

    List<BannerDto> getList(BannerListInput parameter);

    ServiceResult add(BannerAddParameter parameter, MultipartFile file);

    BannerDto getById(BannerAddParameter parameter);

    List<Banner> getBanner();
}
