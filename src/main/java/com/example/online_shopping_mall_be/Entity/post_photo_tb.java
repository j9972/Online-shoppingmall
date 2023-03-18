package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    게시글(1) : 게시글 사진(N)
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class post_photo_tb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_photo_id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private post_tb post;

    @Lob
    @Column(name = "thumnail_photo", columnDefinition="BLOB")
    private byte[] thumnail_photo; // 게시글 대표 사진
}
