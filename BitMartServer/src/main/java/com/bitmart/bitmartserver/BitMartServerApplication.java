package com.bitmart.bitmartserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = "com.bitmart.bitmartserver")
public class BitMartServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitMartServerApplication.class, args);
	}

}
