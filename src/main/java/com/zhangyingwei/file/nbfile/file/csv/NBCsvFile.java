package com.zhangyingwei.file.nbfile.file.csv;

import com.zhangyingwei.file.nbfile.file.INBFile;
import com.zhangyingwei.file.nbfile.file.NBFile;
import com.zhangyingwei.file.nbfile.utils.IndexUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/9/26.
 */
public class NBCsvFile implements INBCsvFile {
    private NBFile nbFile;
    private String spliter = ",";
    private List<String[]> lines;
    private List<String> dataLines;
    private List<String> columnNames;

    public NBCsvFile(List<String> columnNames,List<String[]> lines,String spliter) {
        this.columnNames = columnNames;
        this.lines = lines;
        this.spliter = spliter;
    }

    public NBCsvFile(List<String> columnNames,List<String[]> lines) {
        this.columnNames = columnNames;
        this.lines = lines;
    }

    public NBCsvFile(String path) throws IOException {
        this.init(new NBFile(path));
    }

    public NBCsvFile(URL url) throws URISyntaxException, IOException {
        this.init(new NBFile(url));
    }

    public NBCsvFile(NBFile nbFile) throws FileNotFoundException {
        this.init(nbFile);
    }

    public NBCsvFile(NBFile nbFile,String spliter) throws FileNotFoundException {
        this.spliter = spliter;
        this.init(nbFile);
    }

    private void init(NBFile nbFile) throws FileNotFoundException {
        this.nbFile = nbFile;
        this.dataLines = this.nbFile.lines();
        this.bulidColumns();
        this.bulidLines();
    }

    private void bulidLines() {
        lines = dataLines.stream().map(line -> line.split(spliter)).collect(Collectors.toList());
        lines.remove(0);
    }

    private void bulidColumns() {
        String columnLine = this.dataLines.get(0);
        this.columnNames = new ArrayList<String>(Arrays.asList(columnLine.split(spliter)));
    }

    @Override
    public int count() {
        return this.lines.size();
    }

    @Override
    public int colums() {
        return this.columnNames.size();
    }

    @Override
    public List<String[]> data() {
        return this.lines;
    }

    @Override
    public List<String[]> data(String columnName1, String... columnNames) {
        return this.subCsvFile(columnName1,columnNames).data();
    }

    @Override
    public List<String[]> data(int index1, int... indexs) {
        return this.subCsvFile(index1, indexs).data();
    }

    @Override
    public NBCsvFile subCsvFile(String columnName1, String... columnNames) {
        List<Integer> indexs = IndexUtils.indexsInList(this.columnNames(), columnName1, columnNames);
        List<String[]> dataWithIndexs = IndexUtils.dataWithIndexs(this.data(), indexs);
        List<String> columnsWithIndexs = new ArrayList<String>();
        columnsWithIndexs.add(columnName1);
        columnsWithIndexs.addAll(Arrays.asList(columnNames));
        return new NBCsvFile(columnsWithIndexs, dataWithIndexs);
    }

    @Override
    public NBCsvFile subCsvFile(int index1, int... indexs) {
        List<Integer> indexList = new ArrayList<Integer>();
        indexList.add(index1);
        for (int index : indexs) {
            indexList.add(index);
        }
        List<String> columnsWithIndexs = IndexUtils.columnsWithIndexs(this.columnNames(), index1, indexs);
        List<String[]> dataWithIndexs = IndexUtils.dataWithIndexs(this.data(), indexList);
        return new NBCsvFile(columnsWithIndexs, dataWithIndexs);
    }

    @Override
    public List<String> columnNames() {
        return this.columnNames;
    }

    @Override
    public INBFile toNBFile() {
        return new NBFile(this);
    }

    @Override
    public String getSpliter() {
        return this.spliter;
    }

    @Override
    public INBCsvFile setSpliter(String spliter) {
        this.spliter = spliter;
        return this;
    }

    @Override
    public byte[] getSource() {
        return this.nbFile.getSource();
    }

    @Override
    public void save(String path) throws IOException {
        this.toNBFile().save(path);
    }
}
