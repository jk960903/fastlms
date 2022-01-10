package com.zerobase.fastlms.Component;

import com.zerobase.fastlms.member.Repostiory.EmailRepository;
import com.zerobase.fastlms.member.entity.EmailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MailComponents {
    private final JavaMailSender javaMailSender;

    private final EmailRepository emailRepository;
    public void sendMailTest(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("jk960903@naver.com");
        simpleMailMessage.setSubject("제로베이스 이메일 테스트");
        simpleMailMessage.setText("제로베이스 이메일 테스트 입니다.");

        javaMailSender.send(simpleMailMessage);
    }

    public boolean sendMail(int id){

        boolean result = false;

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

                Optional<EmailEntity> emailOption = emailRepository.findById(id);

                if(emailOption.isPresent()){
                    EmailEntity email = emailOption.get();
                    mimeMessageHelper.setTo(email.getUserId());
                    mimeMessageHelper.setSubject(email.getEmailTitle());
                    mimeMessageHelper.setText(email.getEmailContent(),true);
                }

            }
        };
        try {
            javaMailSender.send(msg);
            result = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

}
