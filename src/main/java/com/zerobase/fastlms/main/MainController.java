package com.zerobase.fastlms.main;

import com.zerobase.fastlms.Component.MailComponents;
import com.zerobase.fastlms.admin.banner.entity.Banner;
import com.zerobase.fastlms.admin.banner.service.BannerService;
import com.zerobase.fastlms.temp.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MailComponents mailComponents;

    private final BannerService bannerService;
    @GetMapping("")
    public String index(Model model){
        //mailComponents.sendMailTest();
        List<Banner> bannerList = bannerService.getBanner();
        model.addAttribute("list",bannerList);
        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied(){
        return "/error/denied";
    }

    @RequestMapping("/test")
    public String test(){

        return "Index";
    }


}
