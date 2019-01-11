package com.ija.IJAcrypto.model;

import com.ija.IJAcrypto.crypto.base64.Base64;
import com.ija.IJAcrypto.crypto.sha.SHA3Generator;
import org.bouncycastle.util.encoders.Hex;
import javax.persistence.*;

@Entity
@Table(name = "encrypted_file")
public class EncryptedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    private String name;

    private String mimeType;

    private String checksum;

    @Column(columnDefinition="text")
    private String aesKey;

    @Column(columnDefinition="text")
    private String iv;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void generateChecksum(byte[] fileBytes) {
        byte[] checksum = SHA3Generator.fromByte(fileBytes);
        this.checksum = Hex.toHexString(checksum);
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
