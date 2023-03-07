package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    게시글 ( 1 ) : 좋아요 ( n )
 */
@Entity
public class like_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private post_tb post_id; // FK - 게시글 아이디
}
