package com.zhangyingwei.file.nbfile.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhangyw on 2017/9/26.
 */
public class NetUtils {
    public static byte[] readBytes(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        conn.connect();
        InputStream input = conn.getInputStream();
        byte[] bytes = new byte[input.available()];
        input.read(bytes);
        input.close();
        return bytes;
    }
}
