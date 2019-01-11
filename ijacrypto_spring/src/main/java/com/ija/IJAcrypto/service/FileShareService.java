package com.ija.IJAcrypto.service;

import com.ija.IJAcrypto.crypto.rsa.RSACrypto;
import com.ija.IJAcrypto.exception.ShareFileException;
import com.ija.IJAcrypto.model.EncryptedFile;
import com.ija.IJAcrypto.model.SharedFile;
import com.ija.IJAcrypto.model.SharedFileId;
import com.ija.IJAcrypto.model.User;
import com.ija.IJAcrypto.repository.SharedFileRepository;
import com.ija.IJAcrypto.repository.EncryptedFileRepository;
import com.ija.IJAcrypto.repository.UserRepository;
import com.ija.IJAcrypto.request.ShareFileRequest;
import com.ija.IJAcrypto.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class FileShareService {

    private CurrentUserProvider currentUserProvider;

    private EncryptedFileRepository fileRepository;

    private UserRepository userRepository;

    private SharedFileRepository sharedFileRepository;

    public FileShareService(
            CurrentUserProvider currentUserProvider,
            EncryptedFileRepository fileRepository,
            UserRepository userRepository,
            SharedFileRepository sharedFileRepository
    ) {
        this.currentUserProvider = currentUserProvider;
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.sharedFileRepository = sharedFileRepository;
    }

    public void share(ShareFileRequest request) throws ShareFileException {
        User currentUser = currentUserProvider.getCurrentUser();
        Optional<EncryptedFile> opFile = fileRepository.findById(request.getFileId());
        Optional<User> opUser = userRepository.findById(request.getUserId());

        if(!opFile.isPresent()) {
            throw new ShareFileException("EncryptedFile of id "+ request.getFileId() +" not exists");
        }

        if(!opUser.isPresent()) {
            throw new ShareFileException("User of id "+ request.getUserId() +" not exists");
        }

        EncryptedFile encryptedFile = opFile.get();
        User user = opUser.get();
        User fileOwner = encryptedFile.getOwner();

        if(fileOwner.getId() != currentUser.getId()) {
            throw new ShareFileException("Not authorized. Only the owner can share the encryptedFile");
        }

        try {
            byte[] iv = RSACrypto.decryptAndEncrypt(encryptedFile.getIvByte(), fileOwner.getPrivateKeyBytes(), user.getPublicKeyAsBytes());
            byte[] key = RSACrypto.decryptAndEncrypt(encryptedFile.getAesKeyByte(), fileOwner.getPrivateKeyBytes(), user.getPublicKeyAsBytes());

            SharedFile sharedFile = new SharedFile();
            SharedFileId id = new SharedFileId(encryptedFile, user);
            sharedFile.setId(id);
            sharedFile.setAesKey(key);
            sharedFile.setIv(iv);

            sharedFileRepository.save(sharedFile);

        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | BadPaddingException e) {
            throw new ShareFileException(e.getMessage());
        }
    }

    public void revoke(ShareFileRequest shareFileRequest) throws ShareFileException {
        User currentUser = currentUserProvider.getCurrentUser();
        Optional<EncryptedFile> fileOptional = fileRepository.findById(shareFileRequest.getFileId());

        if(!fileOptional.isPresent()) {
            throw new ShareFileException("The file doesn't not exists.");
        }

        EncryptedFile file = fileOptional.get();

        if(!file.getOwner().equals(currentUser)) {
            throw new ShareFileException("Not authorized.");
        }

        Optional<User> userOptional = userRepository.findById(shareFileRequest.getUserId());

        if(!userOptional.isPresent()) {
            throw new ShareFileException("User not found");
        }

        User user = userOptional.get();

        sharedFileRepository.deleteById(new SharedFileId(file, user));
    }

}
