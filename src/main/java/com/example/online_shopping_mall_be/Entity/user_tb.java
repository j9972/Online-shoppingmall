package com.example.online_shopping_mall_be.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
    회원(1) : 게시글(N)
    회원(1) : 댓글(N)
    주문(N) : 회원(1)
    장바구니(1) : 회원(1)
    // 회원(1) : 회원 인증(1) -> 보류
    문의(N) : 회원(1)
    리뷰(n) : 상품(1)
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class user_tb implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    @NonNull
    private String password; // 유저 비번

    @Column(unique = true)
    @NonNull
    private String email; // 유저 이메일

    @Column
    @NonNull
    private String username; // 유저 이름

    @Column
    @NonNull
    private String phone; // 핸드폰 번호

    @Column
    @NonNull
    private String address; // 주소

    // TODO : postman 테스트 할때 role, timeStamp 에 대한 처리는 어떻게 해야하나
    @Enumerated(EnumType.STRING)
    private user_Role Role;

    @CreationTimestamp
    private Timestamp createDate; // 회원 가입 일자 -> 객체의 생성 시간을 띄움

    private Boolean enabled = false;

    /*
    //TODO : 횐불 계좌, 은행 , 예금주 성명은 회원가입시가 아니라 환불하는 시기에 적는것이 맞아보임

    private String refund_bank; // 환불 은행

    private Integer refund_bank_account; // 환불 계좌 번호

    private String refund_name; // 환불 계좌 예금주 성명
     */


    // TODO : private Date update_userInfo_date; // 회원 정보 수정 일자 보류

    @OneToMany(mappedBy = "user")
    private List<post_tb> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<comment_tb> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<order_tb> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ask_tb> asks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<review_tb> reviews = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "shopping_basket_id")
    private shopping_basket_tb shopping_basket;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
