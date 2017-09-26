package com.zhangyingwei.file.nbfile.file;

import com.zhangyingwei.file.nbfile.file.csv.INBCsvFile;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by zhangyw on 2017/9/26.
 */
public interface INBFile extends INBSave {
    String read() throws FileNotFoundException;
    List<String> lines() throws FileNotFoundException;
    INBCsvFile toNBCsvFile() throws FileNotFoundException;
    INBCsvFile toNBCsvFile(String spliter) throws FileNotFoundException;

    byte[] getSource();
}
