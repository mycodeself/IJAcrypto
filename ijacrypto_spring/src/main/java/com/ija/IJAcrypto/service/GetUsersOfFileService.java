package com.ija.IJAcrypto.service;

import com.ija.IJAcrypto.model.SharedFile;
import com.ija.IJAcrypto.model.User;
import com.ija.IJAcrypto.repository.SharedFileRepository;
import com.ija.IJAcrypto.repository.UserRepository;
import com.ija.IJAcrypto.response.SharedFileUserResponse;
import com.ija.IJAcrypto.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GetUsersOfFileService {

    private SharedFileRepository sharedFileRepository;

    private UserRepository userRepository;

    private CurrentUserProvider currentUserProvider;

    public GetUsersOfFileService(SharedFileRepository sharedFileRepository, UserRepository userRepository, CurrentUserProvider currentUserProvider) {
        this.sharedFileRepository = sharedFileRepository;
        this.userRepository = userRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public List<SharedFileUserResponse> handle(long fileId) {
        User currentUser = currentUserProvider.getCurrentUser();
        Iterable<User> users = userRepository.findAll();
        List<SharedFile> sharedFiles = sharedFileRepository.findByIdEncryptedFileId(fileId);
        List<User> usersOfFileShared = sharedFiles.stream().map(u -> u.getUser()).collect(Collectors.toList());
        List<SharedFileUserResponse> response = StreamSupport.stream(users.spliterator(), true)
                .map(u -> new SharedFileUserResponse(u.getId(), u.getEmail(), usersOfFileShared.contains(u)))
                .collect(Collectors.toList());

        response.removeIf(sharedFileUserResponse -> currentUser.getId() == sharedFileUserResponse.getId());

        return response;
    }
}
