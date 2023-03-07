package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    회원(1) : 게시글(N)
    게시글(1) : 게시글 사진(N)
    게시글(1) : 댓글(N)
    게시글 ( 1 ) : 좋아요 ( n )
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class post_tb {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user_tb user_id; // FK - 회원 아이디

    private String post_title; // 게시글 제목

    private String post_body; // 게시글 내용

    private Date post_register_date; // 게시글 업로드 날짜

    private Date post_update_date; // 게시글 수정 날짜

    private boolean comment_status; // 댓글 여부

    @OneToMany(mappedBy = "post_id")
    private List<post_photo_tb> post_photos = new ArrayList<>();

    @OneToMany(mappedBy = "post_id")
    private List<comment_tb> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post_id")
    private List<like_tb> likes = new ArrayList<>();
}
