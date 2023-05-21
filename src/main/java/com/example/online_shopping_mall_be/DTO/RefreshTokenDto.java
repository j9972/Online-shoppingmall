package com.example.online_shopping_mall_be.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class RefreshTokenDto {
    @NonNull
    String refreshToken;
}
