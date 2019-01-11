package com.ija.IJAcrypto.crypto;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class CryptoFactory {

    public static Crypto createAESCTR(SecretKeySpec key, byte[] iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        return new Crypto(cipher, key, ivParameterSpec);
    }

    public static Crypto createAESCTR(byte[] key, byte[] iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec =  new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        return new Crypto(cipher, secretKeySpec, ivParameterSpec);
    }


    public static Crypto createAESECBPKCS5Padding(SecretKeySpec key)
            throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        return new Crypto(cipher, key);
    }
}
