# NBFile 一个文件处理包装类

NBFile 也可以叫 [牛逼]File ，虽然现在还简单，我希望它最终会成为一个牛逼的文件包装类。

# 来源

主要是为了解决数据处理时一些简单的文件操作

## 内容

目前主要包含了两个类 NBFile、NBCsvFile，一个是处理文件的类，一个是处理 csv 文件的类。

## 使用方法

### NBFile

```
public NBFile(String path) throws IOException；

public NBFile(File file) throws IOException；

public NBFile(URL url) throws IOException；
```

以上就是 NBFile 全部的构造方法，我们可以通过 本地文件、网址等两种方式创建文件

```
public String read() throws FileNotFoundException；

public List<String> lines() throws FileNotFoundException；

public void save(String path) throws IOException；

public byte[] getSource()；
```

以上是 NBFile 的所有方法，下边简单举一个例子。

```
public class NBFileTest {
    @Test
    public void test() throws IOException {
        NBFile file = new NBFile(new URL("http://archive.ics.uci.edu/ml/machine-learning-databases/00292/Wholesale%20customers%20data.csv"));
        file.lines().stream().forEach(System.out::println);
    }
}
```

结果会打印出文件中的每一行。

### NBCsvFDile

```
public NBCsvFile(List<String> columnNames,List<String[]> lines,String spliter)；

public NBCsvFile(List<String> columnNames,List<String[]> lines)；

public NBCsvFile(String path) throws IOException；

public NBCsvFile(URL url) throws URISyntaxException, IOException；

public NBCsvFile(NBFile nbFile) throws FileNotFoundException；

public NBCsvFile(NBFile nbFile,String spliter) throws FileNotFoundException；
```

以上就是 NBCsvFile 全部的构造方法，我们可以通过 本地文件、网址、本地数据等多种方式创建文件

```
@Override
public int count()；

public int colums();

public List<String[]> data();

public List<String[]> data(String columnName1, String... columnNames);

public List<String[]> data(int index1, int... indexs);

public NBCsvFile subCsvFile(String columnName1, String... columnNames);

public NBCsvFile subCsvFile(int index1, int... indexs);

public List<String> columnNames();

public INBFile toNBFile();

public String getSpliter();

public INBCsvFile setSpliter(String spliter);

public byte[] getSource();

public void save(String path) throws IOException;
```
以上是 NBCsvFile 的所有方法，下边简单举一个例子。

```
NBCsvFile csv = new NBCsvFile(new URL("http://archive.ics.uci.edu/ml/machine-learning-databases/00292/Wholesale%20customers%20data.csv"));
System.out.println(csv.count()); //打印出总行数
System.out.println(csv.columnNames()); //打印出所有的列名
System.out.println(csv.colums()); //打印出列数
csv.save("data.csv"); //保存数据到 data.csv
csv.data("Channel").forEach(line -> {
    System.out.println(Arrays.toString(line));
}); // 从原数据中筛选出列名为 Channel 的列并打印出来
csv.subCsvFile(0,3).data().stream().map(Arrays::toString).forEach(System.out::println);
//从原数据中筛选出第一列与第四列并打印出来
```



