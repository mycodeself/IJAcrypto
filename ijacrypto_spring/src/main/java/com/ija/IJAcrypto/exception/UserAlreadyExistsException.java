package com.ija.IJAcrypto.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("The user already exists.");
    }
}
