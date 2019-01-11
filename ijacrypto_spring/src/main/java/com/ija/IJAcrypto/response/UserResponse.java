package com.ija.IJAcrypto.response;

import com.ija.IJAcrypto.model.User;

public class UserResponse {
    private long id;

    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
