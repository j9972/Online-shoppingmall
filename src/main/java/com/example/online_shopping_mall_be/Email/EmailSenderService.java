package com.example.online_shopping_mall_be.Email;

import com.example.online_shopping_mall_be.utils.EmailUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    /*
    인증키 생성

    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }
    */

    //인증코드 난수 발생 -> 인증키
    private String getAuthCodeKey(int size) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    //인증메일 보내기
//    public String sendAuthMail(String email) {
//        //6자리 난수 인증번호 생성
//        String authKey = getAuthCodeKey(6);
//
//        //인증메일 보내기
//        try {
//            EmailUtils sendMail = new EmailUtils(javaMailSender);
//            sendMail.setSubject("회원가입 이메일 인증");
//            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
//                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
//                    .append("<a href='http://localhost:9972/user/checkToken?email=")
//                    .append(email)
//                    .append("&authKey=")
//                    .append(authKey)
//                    .append("' target='_blenk'>이메일 인증 확인</a>")
//                    .toString());
//            sendMail.setFrom("jh485200@gmial.com", "관리자");
//            sendMail.setTo(email);
//            sendMail.send();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return authKey;
//    }
}
