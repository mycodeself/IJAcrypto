package com.ija.IJAcrypto.crypto.rsa;

import com.ija.IJAcrypto.exception.ShareFileException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public class RSACrypto {

    public static byte[] encrypt(byte[] publicKey, byte[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        PublicKey pubKey = RSAKeyUtils.publicKeyFromBytes(publicKey);

        return encrypt(pubKey, input);
    }


    public static byte[] encrypt(PublicKey publicKey, byte[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(input);
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(input);
    }

    public static byte[] decrypt(byte[] privateKey, byte[] input)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        PrivateKey prvKey = RSAKeyUtils.privateKeyFromBytes(privateKey);

        return decrypt(prvKey, input);
    }


    public static byte[] decryptAndEncrypt(byte[] input, byte[] privateKey, byte[] publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        PrivateKey prvKey = RSAKeyUtils.privateKeyFromBytes(privateKey);
        PublicKey pubKey = RSAKeyUtils.publicKeyFromBytes(publicKey);
        byte[] plain = RSACrypto.decrypt(prvKey, input);

        return RSACrypto.encrypt(pubKey, plain);
    }
}
