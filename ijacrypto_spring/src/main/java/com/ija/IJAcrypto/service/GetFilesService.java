package com.ija.IJAcrypto.service;

import com.ija.IJAcrypto.model.EncryptedFile;
import com.ija.IJAcrypto.model.SharedFile;
import com.ija.IJAcrypto.model.User;
import com.ija.IJAcrypto.repository.EncryptedFileRepository;
import com.ija.IJAcrypto.repository.SharedFileRepository;
import com.ija.IJAcrypto.response.FilesResponse;
import com.ija.IJAcrypto.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFilesService {

    EncryptedFileRepository fileRepository;

    SharedFileRepository sharedFileRepository;

    CurrentUserProvider currentUserProvider;

    public GetFilesService(
            EncryptedFileRepository fileRepository,
            SharedFileRepository sharedFileRepository,
            CurrentUserProvider currentUserProvider) {
        this.fileRepository = fileRepository;
        this.sharedFileRepository = sharedFileRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public FilesResponse getFiles() {
        User currentUser = currentUserProvider.getCurrentUser();
        List<EncryptedFile> ownFiles = this.fileRepository.findByOwnerId(currentUser.getId());
        List<SharedFile> sharedFiles = this.sharedFileRepository.findByIdUserId(currentUser.getId());

        return new FilesResponse(ownFiles, sharedFiles);
    }
}
