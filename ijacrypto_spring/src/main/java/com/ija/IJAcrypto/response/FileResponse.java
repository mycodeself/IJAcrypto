package com.ija.IJAcrypto.response;

import com.ija.IJAcrypto.model.EncryptedFile;

public class FileResponse {
    private long id;

    private String name;

    private String owner;

    private String mimeType;

    private String checksum;

    public FileResponse(EncryptedFile encryptedFile) {
        this.id = encryptedFile.getId();
        this.name = encryptedFile.getName();
        this.owner = encryptedFile.getOwner().getEmail();
        this.mimeType = encryptedFile.getMimeType();
        this.checksum = encryptedFile.getChecksum();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getChecksum() {
        return checksum;
    }
}
