package com.zhangyingwei.file.nbfile.file.csv;

import com.zhangyingwei.file.nbfile.file.INBSave;
import com.zhangyingwei.file.nbfile.file.INBFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhangyw on 2017/9/26.
 */
public interface INBCsvFile extends INBSave {
    /**
     * 记录条数
     * @return
     */
    int count();

    /**
     * 总过列数
     * @return
     */
    int colums();

    /**
     * 数据
     * @return
     */
    List<String[]> data();

    /**
     * 根据列名获取数据
     * @param columnName1
     * @param columnNames
     * @return
     */
    List<String[]> data(String columnName1,String... columnNames);

    /**
     * 根据索引获取数据
     * @param index1
     * @param indexs
     * @return
     */
    List<String[]> data(int index1,int... indexs);

    /**
     * 根据列名重新生成一个 NBCsvFile
     * @param columnName1
     * @param columnNames
     * @return
     */
    NBCsvFile subCsvFile(String columnName1,String... columnNames);

    /**
     * 根据索引重新生成一个 NBCsvFile
     * @param index1
     * @param indexs
     * @return
     */
    NBCsvFile subCsvFile(int index1,int... indexs);


    /**
     * 列名
     * @return
     */
    List<String> columnNames();

    /**
     * 生成一个 NBFile
     * @return
     */
    INBFile toNBFile();

    /**
     * 获取分隔符
     * @return
     */
    String getSpliter();

    /**
     * 设置分隔符
     * @param spliter
     * @return
     */
    INBCsvFile setSpliter(String spliter) throws FileNotFoundException, IOException;

    /**
     * 获取数据的二进制资源
     * @return
     */
    byte[] getSource();
}
