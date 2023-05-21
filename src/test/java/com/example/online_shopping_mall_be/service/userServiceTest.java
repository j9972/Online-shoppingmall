package com.example.online_shopping_mall_be.service;

import com.example.online_shopping_mall_be.DTO.userRegisterDto;
import com.example.online_shopping_mall_be.Entity.user_tb;
import com.example.online_shopping_mall_be.repository.userRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/*
test code -> exception 이 중요
 */
@Slf4j
@RequiredArgsConstructor
@Transactional // 자동 롤백을 위함
class userServiceTest {

        private final userRepository repository;
        private final userService service;

        @Test
        @DisplayName("회원 가입 성공")
        void register() throws Exception {
                // given -> 어떤 데이터가 주워졌을때
                userRegisterDto user = new userRegisterDto();
                user.setEmail("j@naver.com");
                user.setAddress("대한민국");
                user.setPhone("01012345678");
                user.setPassword("1234");
                user.setUsername("testName");

//                ResponseEntity<String> user = service.signUp(
//                        user_tb.builder()
//                                .username("testName")
//                                .password("1234")
//                                .email("j@naver.com")
//                                .phone("01012345678")
//                                .address("대한민국")
//                                .build()
//                );

                // when -> 어느 가정하에 이걸 실행 했을때
                ResponseEntity<String> user1 = service.signUp(user);

                // then -> 나와야 하는 결과
                user_tb res = repository.findByEmail(user1.toString()).get();
                Assertions.assertThat(user).isEqualTo(res);
        }

}