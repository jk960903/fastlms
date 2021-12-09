package main.controller;

import com.zerobase.fastlms.Component.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {
    private final MailComponents mailComponents;
    @RequestMapping("/")
    public String index(){
        mailComponents.sendMailTest();

        return "Index";
    }


}
