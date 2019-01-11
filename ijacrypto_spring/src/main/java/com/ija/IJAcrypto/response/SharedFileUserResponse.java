package com.ija.IJAcrypto.response;

public class SharedFileUserResponse {

    private long id;

    private String email;

    private boolean hasPermission;

    public SharedFileUserResponse(long id, String email, boolean hasPermission) {
        this.id = id;
        this.email = email;
        this.hasPermission = hasPermission;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }
}
