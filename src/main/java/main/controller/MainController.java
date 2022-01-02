package main.controller;

import com.zerobase.fastlms.Component.MailComponents;
import com.zerobase.fastlms.temp.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {
    private final MailComponents mailComponents;
    @RequestMapping("/")
    public String index(){
        //mailComponents.sendMailTest();

        return "Index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied(){
        return "/error/denied";
    }

    @RequestMapping("/test")
    public String test(){
        Example example = new Example();
        try {
            example.add();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Index";
    }
}
