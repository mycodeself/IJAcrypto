package com.ija.IJAcrypto.crypto.base64;

public class Base64 {

    public static String encode(byte[] input) {
        byte[] base64 = org.bouncycastle.util.encoders.Base64.encode(input);

        return new String(base64);
    }

    public static String decode(String input) {
        byte[] plain = decodeAsByte(input);

        return new String(plain);
    }

    public static byte[] decodeAsByte(String input) {
        return org.bouncycastle.util.encoders.Base64.decode(input.getBytes());
    }
}
