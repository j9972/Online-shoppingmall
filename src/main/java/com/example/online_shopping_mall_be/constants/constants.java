package com.example.online_shopping_mall_be.constants;

public class constants {

    public static final String GOOD = "GOOD";

    public static final String DUPLICATE_DATA = "Duplicate Data";
    public static final String SOMETHING_WENT_WRONG = "Something went wrong";

    public static final String INVALID_DATA = "Invalid Data";

    public static final String UNAUTHORIZED_ACCESS = "UnAuthorized access";

    public static final String STORE_LOCATION = "/Users/jungsuyoung/StudyFolder/onlineShopping/BE/online_shopping_mall_BE/Store_files";

    // jwt 생성을 위해서 일단 secret 키 먼저 생성
    public static final String SECRET_KEY = "4D635166546A576E5A7234753778214125442A462D4A614E645267556B587032";;

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L; // 토큰 만료

    public final static Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // 30 minutes

    public final static Long REFRESH_TOKEN_EXPIRE_COUNT = 7 * 24 * 60 * 60 * 1000L; // 7 days

}
