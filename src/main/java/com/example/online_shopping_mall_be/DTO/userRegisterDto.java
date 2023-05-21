package com.example.online_shopping_mall_be.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userRegisterDto {
    private String email;
    private String password;
    private String username;
    private String phone; // 핸드폰 번호
    private String address; // 주소
}
