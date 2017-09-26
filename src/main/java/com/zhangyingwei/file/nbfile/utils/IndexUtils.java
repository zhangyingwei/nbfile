package com.zhangyingwei.file.nbfile.utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zhangyw
 * @date: 2017/9/26
 * @time: 下午8:50
 * @desc:
 */
public class IndexUtils {
    public static List<Integer> indexsInList(List<String> list,String name,String... names) {
        List<Integer> indexs = new ArrayList<Integer>();
        int index = list.indexOf(name);
        if (index >= 0) {
            indexs.add(index);
        }
        Arrays.stream(names).map(i -> list.indexOf(i)).filter(i -> i >= 0).forEach(indexs::add);
        return indexs;
    }

    public static List<String[]> dataWithIndexs(List<String[]> data, List<Integer> indexs) {
        return data.stream().map(d -> {
            List<String> res = new ArrayList<String>();
            for (Integer index : indexs) {
                res.add(d[index]);
            }
            return res.toArray(new String[res.size()]);
        }).collect(Collectors.toList());
    }

    public static List<String> columnsWithIndexs(List<String> columns, int index1, int[] indexs) {
        List<String> columnNames = new ArrayList<String>();
        String res = columns.get(index1);
        if(res != null){
            columnNames.add(res);
        }
        for (int index : indexs) {
            String res2 = columns.get(index);
            if(res2 != null){
                columnNames.add(res2);
            }
        }
        return columnNames;
    }
}
