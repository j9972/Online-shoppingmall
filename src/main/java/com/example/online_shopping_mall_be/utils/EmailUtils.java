package com.example.online_shopping_mall_be.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailUtils {

    private final JavaMailSender emailSender;
//    private final MimeMessage message;
//    private final MimeMessageHelper messageHelper;


//    public EmailUtils(JavaMailSender emailSender) throws MessagingException {
//        this.emailSender = emailSender;
//        message = this.emailSender.createMimeMessage();
//        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//    }
//
//    public void setSubject(String subject) throws MessagingException {
//        messageHelper.setSubject(subject);
//    }
//
//    public void setText(String htmlContent) throws MessagingException {
//        messageHelper.setText(htmlContent, true);
//    }
//
//    public void setFrom(String email, String role) throws UnsupportedEncodingException, MessagingException {
//        messageHelper.setFrom(email, role);
//    }
//
//    public void setTo(String email) throws MessagingException {
//        messageHelper.setTo(email);
//    }
//
//    public void send() {
//        emailSender.send(message);
//    }


    @Async
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("jh485200@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        log.info("send simple message - {}", message);

//        if(list != null && list.size() > 0)
//            message.setCc(getCcArray(list));

        emailSender.send(message);

    }

//    private String[] getCcArray(List<String> ccList) {
//        String[] cc = new String[ccList.size()];
//        for(int i = 0; i < ccList.size(); i++) {
//            cc[i] = ccList.get(i);
//        }
//
//        return cc;
//    }

    public void forgotMail(String to, String subject, String password) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("jh485200@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String htmlMsg = "<p><b>Your Login details for Shopping mall </b><bt><b>Email: </b>" + to +
                "<br><b>Password: </b> " + password + "<br><a href=\"http:localhost:9972/\"> click here to login</a></p>";

        message.setContent(htmlMsg, "text/html");
        emailSender.send(message);
    }

}
