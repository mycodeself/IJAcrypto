package com.ija.IJAcrypto.service;

import com.ija.IJAcrypto.crypto.Crypto;
import com.ija.IJAcrypto.crypto.CryptoFactory;
import com.ija.IJAcrypto.crypto.rsa.RSACrypto;
import com.ija.IJAcrypto.crypto.sha.SHA3Generator;
import com.ija.IJAcrypto.exception.DecryptException;
import com.ija.IJAcrypto.model.EncryptedFile;
import com.ija.IJAcrypto.model.User;
import com.ija.IJAcrypto.repository.EncryptedFileRepository;
import com.ija.IJAcrypto.security.CurrentUserProvider;
import com.ija.IJAcrypto.utils.FileUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class DecryptService {

    private EncryptedFileRepository fileRepository;

    private CurrentUserProvider currentUserProvider;

    public DecryptService(EncryptedFileRepository fileRepository, CurrentUserProvider currentUserProvider) {
        this.fileRepository = fileRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public byte[] decrypt(long id) throws DecryptException {
        EncryptedFile encryptedFile = this.fileRepository.findById(id).get();
        User owner = encryptedFile.getOwner();
        try {
            byte[] iv = RSACrypto.decrypt(owner.getPrivateKeyBytes(), encryptedFile.getIvByte());
            byte[] aesKey = RSACrypto.decrypt(owner.getPrivateKeyBytes(), encryptedFile.getAesKeyByte());
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, 0, aesKey.length, "AES");
            Crypto crypto = CryptoFactory.createAESCTR(secretKeySpec, iv);
            byte[] fileBytes = Files.readAllBytes(Paths.get(encryptedFile.getChecksum()));
            byte[] fileBytesDecrypted = crypto.decrypt(fileBytes);
            byte[] checksum = SHA3Generator.fromByte(fileBytesDecrypted);

            if(!Hex.toHexString(checksum).equals(encryptedFile.getChecksum())) {
                throw new DecryptException("The checksum are not equals");
            }

            return  fileBytesDecrypted;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidKeySpecException e) {
            throw new DecryptException(e.getMessage());
        }
    }
}
