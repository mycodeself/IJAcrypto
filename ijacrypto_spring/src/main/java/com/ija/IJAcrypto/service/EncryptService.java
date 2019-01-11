package com.ija.IJAcrypto.service;

import com.ija.IJAcrypto.crypto.Crypto;
import com.ija.IJAcrypto.crypto.CryptoFactory;
import com.ija.IJAcrypto.crypto.aes.AESKeyGenerator;
import com.ija.IJAcrypto.crypto.iv.IVGenerator;
import com.ija.IJAcrypto.crypto.rsa.RSACrypto;
import com.ija.IJAcrypto.exception.EncryptException;
import com.ija.IJAcrypto.model.EncryptedFile;
import com.ija.IJAcrypto.model.User;
import com.ija.IJAcrypto.repository.EncryptedFileRepository;
import com.ija.IJAcrypto.security.CurrentUserProvider;
import com.ija.IJAcrypto.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class EncryptService {

    private CurrentUserProvider currentUserProvider;

    private EncryptedFileRepository fileRepository;

    public EncryptService(CurrentUserProvider currentUserProvider, EncryptedFileRepository fileRepository) {
        this.currentUserProvider = currentUserProvider;
        this.fileRepository = fileRepository;
    }

    public EncryptedFile encrypt(MultipartFile multipartFile) throws EncryptException {

        try {
            byte[] key = AESKeyGenerator.random().getEncoded();
            byte[] fileBytes = multipartFile.getBytes();
            byte[] iv = IVGenerator.generate();
            EncryptedFile encryptedFile = this.createFileObject(multipartFile, key, iv);
            Crypto crypto = CryptoFactory.createAESCTR(key, iv);
            byte[] fileBytesEncrypted = crypto.encrypt(fileBytes);

            FileUtils.writeFile(encryptedFile.getChecksum(), fileBytesEncrypted);

            this.fileRepository.save(encryptedFile);

            return encryptedFile;
        } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new EncryptException(e.getMessage());
        }

    }

    private EncryptedFile createFileObject(MultipartFile multipartFile, byte[] key, byte[] iv) throws EncryptException {
        User user = this.currentUserProvider.getCurrentUser();
        EncryptedFile encryptedFile = new EncryptedFile();

        try {

            byte[] encryptedIv = RSACrypto.encrypt(user.getPublicKeyAsBytes(), iv);
            byte[] encryptedAesKey = RSACrypto.encrypt(user.getPublicKeyAsBytes(), key);

            encryptedFile.setName(multipartFile.getOriginalFilename());
            encryptedFile.setMimeType(multipartFile.getContentType());
            encryptedFile.setOwner(user);
            encryptedFile.generateChecksum(multipartFile.getBytes());
            encryptedFile.setAesKey(encryptedAesKey);
            encryptedFile.setIv(encryptedIv);

            return encryptedFile;

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | InvalidKeySpecException | IllegalBlockSizeException | IOException e) {
            throw new EncryptException(e.getMessage());
        }
    }

}
