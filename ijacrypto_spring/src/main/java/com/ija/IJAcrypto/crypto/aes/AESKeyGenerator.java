package com.ija.IJAcrypto.crypto.aes;

import com.ija.IJAcrypto.crypto.sha.SHA3Generator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESKeyGenerator {
    public static SecretKeySpec generate(String input) {
        byte[] hash = SHA3Generator.fromString(input);

        return new SecretKeySpec(hash,"AES");
    }

    public static SecretKey random() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
}
