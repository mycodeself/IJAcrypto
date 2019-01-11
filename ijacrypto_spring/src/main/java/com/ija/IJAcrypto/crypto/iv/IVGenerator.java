package com.ija.IJAcrypto.crypto.iv;

import java.security.SecureRandom;

public class IVGenerator {
    public static byte[] generate() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);

        return bytes;
    }
}
