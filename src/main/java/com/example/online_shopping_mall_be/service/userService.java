package com.example.online_shopping_mall_be.service;

import com.example.online_shopping_mall_be.DTO.RefreshTokenDto;
import com.example.online_shopping_mall_be.DTO.userLoginDto;
import com.example.online_shopping_mall_be.DTO.userLoginResponseDto;
import com.example.online_shopping_mall_be.DTO.userRegisterDto;
import com.example.online_shopping_mall_be.Email.EmailValidator;
import com.example.online_shopping_mall_be.Entity.refreshToken_tb;
import com.example.online_shopping_mall_be.Entity.user_Role;
import com.example.online_shopping_mall_be.Entity.user_tb;
import com.example.online_shopping_mall_be.JWT.CustomerUsersDetailsService;
import com.example.online_shopping_mall_be.JWT.JwtUtil;
import com.example.online_shopping_mall_be.JWT.token.ConfirmationToken;
import com.example.online_shopping_mall_be.JWT.token.ConfirmationTokenRepository;
import com.example.online_shopping_mall_be.JWT.token.ConfirmationTokenService;
import com.example.online_shopping_mall_be.constants.constants;
import com.example.online_shopping_mall_be.repository.userRepository;
import com.example.online_shopping_mall_be.utils.EmailUtils;
import com.example.online_shopping_mall_be.utils.basicUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class userService {

    private final EmailValidator emailValidator;
    private final userRepository userRepository;
    private final EmailUtils emailUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final JwtUtil jwtUtil;
    private final CustomerUsersDetailsService customerUsersDetailsService;
    private final RefreshTokenService refreshTokenService;


    @Transactional
    public ResponseEntity<String> signUp(userRegisterDto request) {
        log.info("Inside signUp {}", request.getEmail()); // 이렇게 데이터 가져오는게 맞음

        // user 의 필요 데이터가 전부 있는 지 체크 -> @Notnull 로 처리
        try {
            // 이메일 유효성 검사
            boolean isValidEmail = emailValidator.test(request.getEmail());

            if(!isValidEmail) {
                log.info("email not valid");
                return basicUtils.getResponseEntity(constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

            // 유저 중복 체크
            boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent();

            if(userExists) {
                log.info("이메일 중복 : " + request.getEmail());
                return basicUtils.getResponseEntity(constants.DUPLICATE_DATA, HttpStatus.BAD_REQUEST);
            }

            String hashPWD = passwordEncoder.encode(request.getPassword());
            user_tb user = user_tb.builder()
                    .username(request.getUsername())
                    .password(hashPWD)
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .Role(user_Role.USER)
                    .build();

            userRepository.save(user);

            // uuid token 으로 회원가입시에 이메일 인증하게끔 해준다.
            String token = generateVerificationToken(user);

            log.info("token {} ", token);

            emailUtils.sendSimpleMessage(request.getEmail(), "Complete Registration!", "To confirm your account, please click here : "
                    +"http://localhost:9972/user/accountVerification?token="+token);

            return  basicUtils.getResponseEntity("Successfully Register", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    public ResponseEntity<String> AdminSignUp(userRegisterDto request) {
        log.info("Inside signUp {}", request.getEmail()); // 이렇게 데이터 가져오는게 맞음

        // user 의 필요 데이터가 전부 있는 지 체크 -> @Notnull 로 처리
        try {
            // 이메일 유효성 검사
            boolean validEmail = emailValidator.test(request.getEmail());

            if(!validEmail) {
                log.info("email not valid");
                return basicUtils.getResponseEntity(constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

            // 유저 중복 체크
            boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent();

            if(userExists) {
                log.info("이메일 중복 : " + request.getEmail());
                return basicUtils.getResponseEntity(constants.DUPLICATE_DATA, HttpStatus.BAD_REQUEST);
            }

            String hashPWD = passwordEncoder.encode(request.getPassword());
            user_tb user = user_tb.builder()
                    .username(request.getUsername())
                    .password(hashPWD)
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .Role(user_Role.ADMIN)
                    .build();

            userRepository.save(user);

            // uuid token 으로 회원가입시에 이메일 인증하게끔 해준다.
            String token = generateVerificationToken(user);

            log.info("token {} ", token);

            emailUtils.sendSimpleMessage(request.getEmail(), "Complete Registration!", "To confirm your account, please click here : "
                    +"http://localhost:9972/user/accountVerification?token="+token);

            return  basicUtils.getResponseEntity("Successfully Register", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicUtils.getResponseEntity(constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String generateVerificationToken(user_tb user) {
        String token = UUID.randomUUID().toString();
        log.info("user : {}", user);
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    // 계정 활성화 하기
    public ResponseEntity<String> verifyAccount(String token) {
        log.info("verifyAccount");
        log.info("token {}", token);
        Optional<ConfirmationToken> confirmationTokenOptional = confirmationTokenService.getToken(token);
        confirmationTokenOptional.orElseThrow(() -> new IllegalStateException("token not found"));
        fetchUserAndEnable(confirmationTokenOptional.get());

        return basicUtils.getResponseEntity("token confirmed", HttpStatus.OK);
    }

    @Transactional
    public void fetchUserAndEnable(ConfirmationToken confirmationToken) {
        log.info("in the fetchUserAndEnable ");
        String email = confirmationToken.getUser().getEmail();
        user_tb user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("유저를 찾을 수 없음 " + email));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public ResponseEntity<String> login(userLoginDto request) {
        log.info("Inside login check");

        try {
            // email이 없을 경우 Exception이 발생한다. Global Exception에 대한 처리가 필요하다.
            Optional<user_tb> user = userRepository.findByEmail(request.getEmail());
            if(!passwordEncoder.matches(request.getPassword(), user.get().getPassword())){
                return basicUtils.getResponseEntity("email is not valid", HttpStatus.UNAUTHORIZED);
            }

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );


            if(auth.isAuthenticated()) {
                log.info("auth 인증 됨");
                log.info("customerUsersDetailsService.getUserDetail() : {} ", customerUsersDetailsService.getUserDetail().get().getRole().toString());
                if(customerUsersDetailsService.getUserDetail().get().getEnabled()) {

                    // JWT토큰을 생성하였다. jwt라이브러리를 이용하여 생성.
                    String accessToken = jwtUtil.createAccessToken(user.get().getEmail(), user.get().getRole().toString());
                    String refreshToken = jwtUtil.createRefreshToken(user.get().getEmail(), user.get().getRole().toString());

                    // RefreshToken을 DB에 저장한다. 성능 때문에 DB가 아니라 Redis에 저장하는 것이 좋다.
                    //TODO : confirmation token confrimed_at 해결,  로그아웃 , admin 따로 로그인 처리하기
                    refreshToken_tb refreshTokenEntity = new refreshToken_tb();
                    refreshTokenEntity.setRefreshToken(refreshToken);
                    refreshTokenEntity.setId(user.get().getUser_id());
                    refreshTokenService.addRefreshToken(refreshTokenEntity);

                    userLoginResponseDto loginResponse = userLoginResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .user_id(user.get().getUser_id())
                            .email(user.get().getEmail())
                            .build();

                    //TODO : builder()를 데이터로 뽑는 방법이다
                    return new ResponseEntity(loginResponse, HttpStatus.OK);
                }
            } else {
                log.info("auth 인증 안됨");
                return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval ."+"\"}",
                        HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.error("{}", e);
        }
        return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",
                HttpStatus.BAD_REQUEST);
    }


    /*
        1. accessToken 은 Authorization 에 Bearer 로 해서 보내준다.
        2. refreshToken 은 body 에 담아서 보내주면 된다
     */
    public ResponseEntity<String> logout(RefreshTokenDto request) {
        log.info("request : {}", request);
        return new ResponseEntity<String>("good", HttpStatus.OK);
//        refreshTokenService.deleteRefreshToken(request.getRefreshToken());
//        return new ResponseEntity<String>("logout is success", HttpStatus.OK);
    }

//    public ResponseEntity<String> requestRefresh(RefreshTokenDto request) {
//        log.info("request {}", request);
//        Optional refreshToken = refreshTokenService.findRefreshToken(request.getRefreshToken());
//        if (refreshToken == null) {
//            return new ResponseEntity<String>("Refresh token not found", HttpStatus.BAD_REQUEST);
//        }
//        //Claims claims = jwtUtil.parseRefreshToken(refreshToken.getRefreshToken());
//
//        String userEmail = JwtUtil.extractEmail(request.toString());
//        //String userEmail = Long.valueOf((String) extractEmail.getEmail());
//
//        user_tb user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("Member not found"));
////        public String createAccessToken(String email, String role) {
////            Map<String,Object> claims = new HashMap<>();
////            claims.put("role", role);
////            return createToken(claims, email, constants.ACCESS_TOKEN_EXPIRE_COUNT);
////        }
//        String accessToken = jwtUtil.createAccessToken(user.getEmail(), user.getRole().toString());
//
//        userLoginResponseDto loginResponse = userLoginResponseDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(request.getRefreshToken())
//                .user_id(user.getUser_id())
//                .email(user.getEmail())
//                .build();
//        return new ResponseEntity(loginResponse, HttpStatus.OK);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
