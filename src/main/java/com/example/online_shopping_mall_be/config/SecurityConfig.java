package com.example.online_shopping_mall_be.config;

import com.example.online_shopping_mall_be.JWT.JwtFilter;
import com.example.online_shopping_mall_be.JWT.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final JwtUtil jwtUtil;
    private final AuthenticationProvider authenticationProvider;


    // HttpSecurity 설정
    /*
        .authorizeRequests() → .authorizeHttpRequests()
        .antMatchers() → .requestMatchers()
        .access("hasAnyRole('ROLE_A','ROLE_B')") → .hasAnyRole("A", "B")
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling()
                .and()
                .authenticationProvider(authenticationProvider)
                .sessionManagement() // authentication state 를 저장하지 않음을 도움
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

