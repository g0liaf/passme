package org.example.passme.service;

import org.example.passme.entity.Login;
import org.example.passme.repository.LoginRepository;
import org.example.passme.util.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final Encryptor encryptor;

    @Autowired
    LoginService(LoginRepository loginRepository, Encryptor encryptor) {
        this.loginRepository = loginRepository;
        this.encryptor = encryptor;
    }

    public Login saveLogin(Login login) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        login.setPassword(encryptor.encrypt(login.getPassword()));
        return loginRepository.save(login);
    }
}
