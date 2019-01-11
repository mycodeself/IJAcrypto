package com.ija.IJAcrypto.crypto.sha;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import java.nio.charset.StandardCharsets;

public class SHA3Generator {
    public static byte[] fromString(String input) {
        return fromByte(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] fromByte(byte[] input) {
        SHA3.DigestSHA3 sha3 = new SHA3.Digest256();
        sha3.update(input);

        return sha3.digest();
    }
}
