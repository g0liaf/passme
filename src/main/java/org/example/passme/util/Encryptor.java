package org.example.passme.util;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Encryptor {
    // Need secure refactor
    private static final String PASSWORD = "5f4dcc3b5aa765d61d8327deb882cf99";

    public String encrypt(String plainText) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        byte[] salt = AESUtils.salt();
        var key = AESUtils.getKeyFromPassword(PASSWORD, salt);
        var iv = AESUtils.generateIv();
        byte[] encryptedText = AESUtils.encryptPasswordBased(plainText, key, iv);
        byte[] buffer = new byte[AESUtils.SALT_LENGTH + AESUtils.IV_LENGTH + AESUtils.CIPHER_LENGTH];
        System.arraycopy(salt, 0 , buffer, 0, AESUtils.SALT_LENGTH);
        System.arraycopy(iv.getIV(), 0 , buffer, AESUtils.SALT_LENGTH, AESUtils.IV_LENGTH);
        System.arraycopy(encryptedText, 0, buffer, AESUtils.SALT_LENGTH + AESUtils.IV_LENGTH, AESUtils.CIPHER_LENGTH);

        return Base64.getEncoder().encodeToString(buffer);
    }

    public String decrypt(String cipherText) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(cipherText));
        byte[] salt = new byte[AESUtils.SALT_LENGTH];
        byte[] iv = new byte[AESUtils.IV_LENGTH];
        buffer.get(salt, 0, AESUtils.SALT_LENGTH);
        buffer.get(iv,0, AESUtils.IV_LENGTH);
        byte[] cipher = new byte[AESUtils.CIPHER_LENGTH];
        buffer.get(cipher, 0, AESUtils.CIPHER_LENGTH);
        var key = AESUtils.getKeyFromPassword(PASSWORD, salt);
        var ivGen = AESUtils.generateIv(iv);

        return AESUtils.decryptPasswordBased(Base64.getEncoder().encodeToString(cipher), key, ivGen);
    }
}
