package com.ija.IJAcrypto.model;

import com.ija.IJAcrypto.crypto.base64.Base64;
import com.ija.IJAcrypto.crypto.rsa.RSAKeyUtils;

import javax.persistence.*;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(columnDefinition="text")
    private String publicKey;

    @Column(columnDefinition="text")
    private String privateKey;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void generateKeys() throws NoSuchAlgorithmException {
        KeyPair keyPair = RSAKeyUtils.generate();
        String publicKey = Base64.encode(keyPair.getPublic().getEncoded());
        String privateKey = Base64.encode(keyPair.getPrivate().getEncoded());
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public byte[] getPrivateKeyBytes() {
        return Base64.decodeAsByte(privateKey);
    }

    public byte[] getPublicKeyAsBytes() {
        return Base64.decodeAsByte(publicKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
