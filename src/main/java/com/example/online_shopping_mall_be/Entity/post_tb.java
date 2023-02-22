package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;

/*
    회원(1) : 게시글(N)
    게시글(1) : 게시글 사진(N)
    게시글(1) : 댓글(N)
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class post_tb {

    @Id @GeneratedValue
    private Long post_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user_tb user_id;

    private String post_title;

    private String post_body;

    private Date post_register_date;

    private Date post_update_date;

    private boolean comment_status;

    @OneToMany(mappedBy = "post_id")
    private List<post_photo_tb> post_photos = new ArrayList<>();

    @OneToMany(mappedBy = "post_id")
    private List<comment_tb> comments = new ArrayList<>();
}
