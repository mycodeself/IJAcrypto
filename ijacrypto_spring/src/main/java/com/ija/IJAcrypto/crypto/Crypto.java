package com.ija.IJAcrypto.crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public class Crypto {

    private Cipher cipher;

    private SecretKeySpec key;

    private IvParameterSpec iv;

    public Crypto(Cipher cipher, SecretKeySpec key) {
        this.cipher = cipher;
        this.key = key;
    }

    public Crypto(Cipher cipher, SecretKeySpec key, IvParameterSpec iv) {
        this.cipher = cipher;
        this.key = key;
        this.iv = iv;
    }

    public byte[] encrypt(byte[] input) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        this.initEncryptCipher();
        ByteArrayInputStream inputByte = new ByteArrayInputStream(input);
        CipherInputStream cipherInput = new CipherInputStream(inputByte, this.cipher);
        ByteArrayOutputStream outputByte = new ByteArrayOutputStream();
        int output;

        while((output = cipherInput.read()) >= 0) {
            outputByte.write(output);
        }

        return outputByte.toByteArray();
    }

    public void encrypt(File input, String outputPath) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        byte[] fileBytes = Files.readAllBytes(input.toPath());
        byte[] encrypted = this.encrypt(fileBytes);
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        fileOutputStream.write(encrypted);
        fileOutputStream.close();
    }

    public byte[] decrypt(byte[] input) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        this.initDecryptCipher();
        ByteArrayOutputStream outputByte = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputByte, this.cipher);
        cipherOutputStream.write(input);
        cipherOutputStream.close();

        return outputByte.toByteArray();
    }

    public void decrypt(File input, String outputPath) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        byte[] fileBytes = Files.readAllBytes(input.toPath());
        byte[] encrypted = this.decrypt(fileBytes);
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        fileOutputStream.write(encrypted);
        fileOutputStream.close();
    }

    private void initEncryptCipher() throws InvalidAlgorithmParameterException, InvalidKeyException {
        if(this.iv != null) {
            this.cipher.init(Cipher.ENCRYPT_MODE, this.key, this.iv);
        } else {
            this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
        }
    }

    private void initDecryptCipher() throws InvalidKeyException, InvalidAlgorithmParameterException {
        if(this.iv != null) {
            this.cipher.init(Cipher.DECRYPT_MODE, this.key, this.iv);
        } else {
            this.cipher.init(Cipher.DECRYPT_MODE, this.key);
        }
    }
}
