package com.example.online_shopping_mall_be.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class userLoginResponseDto {
    private String accessToken;
    private String refreshToken;

    private Long user_id;
    private String email;
}
