package com.ija.IJAcrypto;

import com.ija.IJAcrypto.security.Argon2PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IJAcryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IJAcryptoApplication.class, args);
	}

	@Bean
	public Argon2PasswordEncoder argon2PasswordEncoder() {
	    return new Argon2PasswordEncoder();
    }
}
