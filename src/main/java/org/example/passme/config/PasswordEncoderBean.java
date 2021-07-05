package org.example.passme.config;

import org.example.passme.util.Argon2PasswordEncoder;
import org.example.passme.util.Encryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderBean {
    @Bean
    PasswordEncoder encoder() {
        return new Argon2PasswordEncoder();
    }

    @Bean
    Encryptor encryptor() { return new Encryptor();}
}

