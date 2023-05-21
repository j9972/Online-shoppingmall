package com.example.online_shopping_mall_be.controller;

import com.example.online_shopping_mall_be.DTO.RefreshTokenDto;
import com.example.online_shopping_mall_be.DTO.userLoginDto;
import com.example.online_shopping_mall_be.DTO.userRegisterDto;
import com.example.online_shopping_mall_be.Email.EmailSenderService;
import com.example.online_shopping_mall_be.constants.constants;
import com.example.online_shopping_mall_be.service.userService;
import com.example.online_shopping_mall_be.utils.basicUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor // 생성자 DI
@RequestMapping("/user")
public class userController {

    private final userService userService;

    private final EmailSenderService emailSenderService;

    // 회원가입
    @PostMapping("/signup") // http://localhost:9972/user/signup
    public ResponseEntity<String> signUp(@RequestBody userRegisterDto request) {
        try{
            return userService.signUp(request);
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
            ex.printStackTrace();
        }
        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 관리자 회원가입
    @PostMapping("/signup/admin") // http://localhost:9972/user/signup/admin
    public ResponseEntity<String> AdminSignUp(@RequestBody userRegisterDto request) {
        try{
            return userService.AdminSignUp(request);
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
            ex.printStackTrace();
        }
        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // check token
    @GetMapping("/accountVerification") // http://localhost:9972/user/accountVerification/{token}
    public ResponseEntity<String> verifyAccount(@RequestParam String token) {
        //return new ResponseEntity<>(token,HttpStatus.OK);
        try{
            return userService.verifyAccount(token);
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
            ex.printStackTrace();
        }
        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login") // http://localhost:9972/user/login
    public ResponseEntity<String> login(@RequestBody userLoginDto request) {
        try {
            return userService.login(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/logout") // http://localhost:9972/user/logout
    public ResponseEntity<String> logout(@RequestBody RefreshTokenDto request) {
        try {
            return userService.logout(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @PostMapping("/refreshToken") // http://localhost:9972/user/refreshToken
//    public ResponseEntity<String> requestRefresh(@RequestBody RefreshTokenDto request) {
//        try {
//            return userService.requestRefresh(request);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
