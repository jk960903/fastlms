package com.zerobase.fastlms.admin.banner.service;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.entity.Banner;
import com.zerobase.fastlms.admin.banner.mapper.BannerMapper;
import com.zerobase.fastlms.admin.banner.model.BannerAddParameter;
import com.zerobase.fastlms.admin.banner.model.BannerListInput;
import com.zerobase.fastlms.admin.banner.repository.BannerRepository;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BannerServiceImpl implements  BannerService{

    private final BannerRepository bannerRepository;

    private final BannerMapper bannerMapper;

    @Override
    public List<BannerDto> getList(BannerListInput parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> bannerDtos = bannerMapper.selectList(parameter);

        if(!CollectionUtils.isEmpty(bannerDtos)){
            int i = 0 ;
            for(BannerDto x : bannerDtos){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() -1-i);
                i++;
            }
        }
        return bannerDtos;
    }

    @Override
    public ServiceResult add(BannerAddParameter parameter, MultipartFile file) {
        ServiceResult serviceResult = new ServiceResult();
        boolean isOpen = false;

        if(parameter.getIsOpen() == 1){
            isOpen = true;
        }
        String saveFilename = "";
        if(file != null){
            //String basePath = ""
            String basePath = "/Users/leejunkyu/Desktop/제로베이스/files";
            String orignalFileName = file.getOriginalFilename();
            saveFilename = getNewSaveFile(basePath,orignalFileName);
            try{
                File newFile = new File(saveFilename);

                FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(newFile));

            }catch(Exception e){
                log.info(e.getMessage());
            }
        }
        String[] location = saveFilename.split("/files");

        String saveName = location[1];
        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .regdate(LocalDateTime.now())
                .LinkAddress(parameter.getAddress())
                .alterText(parameter.getAlterText())
                .fileName(saveName)
                .isShow(isOpen).orderValue(parameter.getOrderValue()).openValue(parameter.getOpenValue()).build();

        bannerRepository.save(banner);

        serviceResult.setResult(true);
        serviceResult.setMessage("success");

        return serviceResult;
    }

    @Override
    public BannerDto getById(BannerAddParameter parameter) {
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());

        if(optionalBanner.isEmpty()){
            return null;
        }

        return BannerDto.of(optionalBanner.get());
    }

    @Override
    public List<Banner> getBanner() {

        return bannerRepository.findByIsShow(true);
    }

    private String getNewSaveFile(String basePath,String originalFileName){


        String[] dirs = {
                String.format("%s/%d",basePath,LocalDateTime.now().getYear()),
                String.format("%s/%d/%02d/",basePath,
                        LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()),
                String.format("%s/%d/%02d/%03d/",basePath,LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()
                        ,LocalDateTime.now().getDayOfMonth())

        };

        for(String dir : dirs){
            File file = new File(dir);
            if(!file.isDirectory()){
                file.mkdir();
            }
        }

        String fileExtension = "";

        if(originalFileName != null){
            int dotPos = originalFileName.lastIndexOf(".");
            if(dotPos > -1 ){
                fileExtension = originalFileName.substring(dotPos+1);
            }

        }
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String newFileName = String.format("%s%s",dirs[2],uuid);
        if(fileExtension.length() > 0){
            newFileName +="." + fileExtension;
        }

        return newFileName;
    }

}
