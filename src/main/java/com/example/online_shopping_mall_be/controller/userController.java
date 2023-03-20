package com.example.online_shopping_mall_be.controller;

import com.example.online_shopping_mall_be.service.userService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor // 생성자DI
public class userController {

    private final userService userService;
}
