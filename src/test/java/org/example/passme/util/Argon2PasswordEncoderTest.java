package org.example.passme.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class Argon2PasswordEncoderTest {
    @Test
    void encode() {
        PasswordEncoder encoder = new Argon2PasswordEncoder();
        String pass = "password";
        Assertions.assertInstanceOf(String.class, encoder.encode(pass));
        Assertions.assertNotNull(encoder.encode(pass));
        Assertions.assertNotEquals(encoder.encode(pass), encoder.encode(pass));
    }

    @Test
    void matches() {
        PasswordEncoder encoder = new Argon2PasswordEncoder();
        String pass = "password";
        String hash = encoder.encode(pass);
        Assertions.assertTrue(encoder.matches(pass, hash));
    }
}