package com.ija.IJAcrypto.service;

import com.ija.IJAcrypto.exception.UserAlreadyExistsException;
import com.ija.IJAcrypto.model.User;
import com.ija.IJAcrypto.repository.UserRepository;
import com.ija.IJAcrypto.security.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class CreateUserService {
    private UserRepository userRepository;

    private Argon2PasswordEncoder argon2PasswordEncoder;

    public CreateUserService(UserRepository userRepository, Argon2PasswordEncoder argon2PasswordEncoder) {
        this.userRepository = userRepository;
        this.argon2PasswordEncoder = argon2PasswordEncoder;
    }

    public User create(User user) throws NoSuchAlgorithmException, UserAlreadyExistsException {

        if(null != userRepository.findByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        String hashPassword = argon2PasswordEncoder.encode(user.getPassword());

        user.setPassword(hashPassword);
        user.generateKeys();

        userRepository.save(user);

        return user;
    }
}
