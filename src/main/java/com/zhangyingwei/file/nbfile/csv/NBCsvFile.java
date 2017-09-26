package com.zhangyingwei.file.nbfile.csv;

import com.zhangyingwei.file.nbfile.INBSave;
import com.zhangyingwei.file.nbfile.file.INBFile;
import com.zhangyingwei.file.nbfile.file.NBFile;
import java.io.File;
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

    public NBCsvFile(String path) throws FileNotFoundException {
        this.init(new NBFile(path));
    }

    public NBCsvFile(URL url) throws URISyntaxException, FileNotFoundException {
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
        this.nbFile = this.nbFile;
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

    public File getSourceFile(){
        return this.nbFile.getSourceFile();
    }

    @Override
    public void save(String path) throws IOException {
        this.toNBFile().save(path);
    }
}
