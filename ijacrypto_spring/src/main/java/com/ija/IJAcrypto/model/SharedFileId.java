package com.ija.IJAcrypto.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class SharedFileId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "file_id")
    private EncryptedFile encryptedFile;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SharedFileId(EncryptedFile encryptedFile, User user) {
        this.encryptedFile = encryptedFile;
        this.user = user;
    }

    public SharedFileId() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EncryptedFile getEncryptedFile() {
        return encryptedFile;
    }

    public void setEncryptedFile(EncryptedFile encryptedFile) {
        this.encryptedFile = encryptedFile;
    }

}
