package com.smart.bulkingupdate;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Created by fengjh on 16/12/17.
 */

public class BulkingSign {

    private static final String TAG = BulkingSign.class.getName();

    private static String bytes2Hex(byte[] src) {
        char[] res = new char[src.length * 2];
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[src[i] & 0x0f];
        }

        return new String(res);
    }

    private static String getFileMd5(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);

            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[8192];
            int byteCount;
            while ((byteCount = in.read(bytes)) > 0) {
                digester.update(bytes, 0, byteCount);
            }
            value = bytes2Hex(digester.digest());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static boolean checkMd5(File file, String md5) {
        if (TextUtils.isEmpty(md5)) {
            throw new RuntimeException("md5 cannot be empty");
        }
        String fileMd5 = getFileMd5(file);
        if (md5.equals(fileMd5)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkMd5(String filePath, String md5) {
        return checkMd5(new File(filePath), md5);
    }
}
