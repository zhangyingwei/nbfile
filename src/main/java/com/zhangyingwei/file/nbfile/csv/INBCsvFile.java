package com.zhangyingwei.file.nbfile.csv;

import com.zhangyingwei.file.nbfile.INBSave;
import com.zhangyingwei.file.nbfile.file.INBFile;

import java.util.List;

/**
 * Created by zhangyw on 2017/9/26.
 */
public interface INBCsvFile extends INBSave {
    int count();
    int colums();
    List<String[]> data();

    List<String> columnNames();

    INBFile toNBFile();

    String getSpliter();

    INBCsvFile setSpliter(String spliter);
}
