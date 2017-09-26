package com.zhangyingwei.file.nbfile.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: zhangyw
 * @date: 2017/9/26
 * @time: 下午8:31
 * @desc:
 */
public class IOUtils {
    public static byte[] readBytes(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        return readBytes(input);
    }

    public static byte[] readBytes(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30 * 1000);
        InputStream input = conn.getInputStream();
        return readBytes(input);
    }

    public static byte[] readBytes(InputStream input) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = input.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        input.close();
        return bos.toByteArray();
    }
}
