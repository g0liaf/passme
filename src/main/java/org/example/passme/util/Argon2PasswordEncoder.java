package org.example.passme.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Argon2PasswordEncoder implements PasswordEncoder {
    private final Argon2 argon2;

    public Argon2PasswordEncoder() {
        argon2 = Argon2Factory.create();
    }

    @Override
    public String encode(CharSequence charSequence) {
        return argon2.hash(10, 65536, 1, charSequence.toString().toCharArray());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return argon2.verify(s, charSequence.toString().toCharArray());
    }
}
