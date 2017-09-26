package com.zhangyingwei.file.nbfile.csv;


import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by zhangyw on 2017/9/26.
 */
public class NBCsvFileTest {

    @Test
    public void read() throws FileNotFoundException, URISyntaxException, MalformedURLException {
        NBCsvFile csv = new NBCsvFile(new URL("http://archive.ics.uci.edu/ml/machine-learning-databases/00292/Wholesale%20customers%20data.csv"));
        System.out.println(csv.count());
    }
}
