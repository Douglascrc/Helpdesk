package br.com.codigodebase.helpdesk.infrastructure.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class FileUtils {

    public static byte[] convertBase64ToByteArray(String base64String) throws IOException {
        String[] base64Parts = base64String.split(",");
        byte[] fileBytes = Base64.getDecoder().decode(base64Parts[1]);
        return fileBytes;
    }

    public static void saveByteArrayToFile(byte[] data, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }
}
