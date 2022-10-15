package indi.wangsc.paperwork.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileType {
    private static final Map<String, String> fileTypesMap = new HashMap<>();

    static {
        fileTypesMap.put("D0CF11E0", "Office 2003");
        fileTypesMap.put("504B0304", "Office 2007");
    }

    public static String judge(String filePath) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[4];
            fileInputStream.read(bytes, 0, bytes.length);
            String hexString=byteToHexString(bytes);
            return fileTypesMap.get(hexString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String hexValue;
        for (var curByte : bytes) {
            hexValue = Integer.toHexString(curByte & 0xFF).toUpperCase();
            if (hexValue.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hexValue);
        }
        return stringBuilder.toString();
    }
}
