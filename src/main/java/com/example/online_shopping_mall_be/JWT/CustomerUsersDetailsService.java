package com.example.online_shopping_mall_be.JWT;

import com.example.online_shopping_mall_be.Entity.user_tb;
import com.example.online_shopping_mall_be.repository.userRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomerUsersDetailsService implements UserDetailsService {

    private final userRepository userRepository;
    private Optional<user_tb> userDetail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("Inside loadUserByUsername {}", email);

        userDetail = userRepository.findByEmail(email); // email 대신 username 을 쓴것
        log.info("customer > loadUser > userDetail :",userDetail);
        if(!Objects.isNull(userDetail)) {
            log.info("userDetail {} {} ", userDetail, userDetail.get());
            return userDetail.get();
            //return new user_tb(userDetail.get().getEmail(), userDetail.get().getPassword());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        //return null;
    }
    public Optional<user_tb> getUserDetail() {
        return userDetail;
    }
}
