package com.ija.IJAcrypto.model;

import com.ija.IJAcrypto.crypto.base64.Base64;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shared_file")
public class SharedFile {

    @EmbeddedId
    private SharedFileId id;

    @Column(columnDefinition="text")
    private String aesKey;

    @Column(columnDefinition="text")
    private String iv;

    public SharedFileId getId() {
        return id;
    }

    public void setId(SharedFileId id) {
        this.id = id;
    }

    public User getUser() {
        return id.getUser();
    }

    public EncryptedFile getFile() {
        return id.getEncryptedFile();
    }

    public String getAesKey() {
        return aesKey;
    }

    public byte[] getAesKeyByte() {
        return Base64.decodeAsByte(aesKey);
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public void setAesKey(byte[] aesKey) {
        this.aesKey = Base64.encode(aesKey);
    }

    public String getIv() {
        return iv;
    }

    public byte[] getIvByte() {
        return Base64.decodeAsByte(iv);
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public void setIv(byte[] iv) {
        this.iv = Base64.encode(iv);
    }
}
