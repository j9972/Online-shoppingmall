package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    게시글(1) : 게시글 사진(N)
 */

@Entity
public class post_photo_tb {
    @Id @GeneratedValue
    private Long post_photo_id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private post_tb post_id;

    @Lob
    @Column(name = "post_photo", columnDefinition="BLOB")
    private byte[] post_photo; // 게시글 대표 사진
}
