package com.zhangyingwei.file.nbfile.file;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * @author: zhangyw
 * @date: 2017/9/26
 * @time: 下午8:29
 * @desc:
 */
public class NBFileTest {
    @Test
    public void test() throws IOException {
        NBFile file = new NBFile(new URL("http://archive.ics.uci.edu/ml/machine-learning-databases/00292/Wholesale%20customers%20data.csv"));
        file.lines().stream().forEach(System.out::println);
    }
}