package com.ija.IJAcrypto.utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void writeFile(String filename, byte[] fileBytes) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write(fileBytes);
        fileOutputStream.close();
    }
}
