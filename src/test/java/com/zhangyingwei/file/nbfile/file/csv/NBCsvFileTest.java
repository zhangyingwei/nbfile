package com.zhangyingwei.file.nbfile.file.csv;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by zhangyw on 2017/9/26.
 */
public class NBCsvFileTest {

    @Test
    public void read() throws IOException, URISyntaxException {
        NBCsvFile csv = new NBCsvFile(new URL("http://archive.ics.uci.edu/ml/machine-learning-databases/00292/Wholesale%20customers%20data.csv"));
        System.out.println(csv.count());
        System.out.println(csv.columnNames());
        System.out.println(csv.colums());
//        csv.save("data.csv");
        csv.data("Channel").forEach(line -> {
            System.out.println(Arrays.toString(line));
        });
        csv.subCsvFile(0,3).data().stream().map(Arrays::toString).forEach(System.out::println);
    }
}
