package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    댓글(N) : 게시글(1)
    댓글(N) : 회원(1)
    댓글(1) : 좋아요(n)
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class comment_tb {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private user_tb user;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private post_tb post;

    @OneToMany(mappedBy = "comment")
    private List<like_tb> likes = new ArrayList<>();

    @Column
    @NotNull
    private String comment_body; // 댓글 내용

    private Date comment_register_date; // 댓글 업로드 일정

    private Date comment_update_date; // 댓글 수정 일정
}
