package org.example.passme.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptorTest {

    @Test
    void encryptAndDecrypt() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String confidential = "$eCuReP@$$worD1";
        Encryptor encryptor = new Encryptor();
        String cipher = encryptor.encrypt(confidential);
        Assertions.assertEquals(confidential, encryptor.decrypt(cipher));
    }
}