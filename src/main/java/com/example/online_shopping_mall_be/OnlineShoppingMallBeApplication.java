package com.example.online_shopping_mall_be;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class OnlineShoppingMallBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingMallBeApplication.class, args);
	}

}
