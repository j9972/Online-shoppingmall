package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

/*
    user (1) : refreshToken  -> N:1로 단반향 맵핑
    단반향으로 하는 이유는 user가 있는지 우션 확인하고 token을 확인하는 flow가 맞는거 같아서 단 방향
 */
@Entity
@RequiredArgsConstructor
public class refreshToken_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String refreshToken;

    //private created dateTime(6)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    @Builder
    public refreshToken_tb(String refreshToken, user_tb user_id) {
        this.refreshToken = refreshToken;
        this.user_id = user_id;
    }

    /*
        refreshUpdate 메서드는 DB에 저장하고, 사용하는 refreshToken이 유효시간이 만료되었을 때 DB에 업데이트 되는 기능
     */
    public void refreshUpdate(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
