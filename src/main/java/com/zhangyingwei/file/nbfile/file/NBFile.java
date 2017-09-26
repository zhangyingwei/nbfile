package com.zhangyingwei.file.nbfile.file;

import com.zhangyingwei.file.nbfile.csv.INBCsvFile;
import com.zhangyingwei.file.nbfile.csv.NBCsvFile;
import com.zhangyingwei.file.nbfile.net.NetUtils;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/9/26.
 */
public class NBFile implements INBFile {
    private byte[] bytes;
    private List<String> lines;

    public NBFile(String path) throws FileNotFoundException {
        File file = new File(path);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
    }

    public NBFile(URL url) throws IOException {
        this.bytes = NetUtils.readBytes(url);

    }

    public NBFile(NBCsvFile nbCsvFile) {
        String columns = String.join(nbCsvFile.getSpliter(), nbCsvFile.columnNames());
        List<String> lineDatas = nbCsvFile.data().stream().map(datas -> String.join(nbCsvFile.getSpliter(), datas)).collect(Collectors.toList());
        this.lines = new ArrayList<String>();
        lines.add(columns);
        lines.addAll(lineDatas);
        this.file = nbCsvFile.getSourceFile();
    }

    @Override
    public String read() throws FileNotFoundException {
        return String.join("\n", this.lines());
    }

    @Override
    public List<String> lines() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.bytes)));
        this.lines = reader.lines().collect(Collectors.toList());
        return this.lines;
    }

    @Override
    public INBCsvFile toNBCsvFile() throws FileNotFoundException {
        return new NBCsvFile(this);
    }

    @Override
    public INBCsvFile toNBCsvFile(String spliter) throws FileNotFoundException {
        return new NBCsvFile(this,spliter);
    }

    @Override
    public void save(String path) throws IOException {
        File outFile = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        writer.write(this.read().toCharArray());
        writer.close();
    }

    public File getSourceFile() {
        return file;
    }
}
