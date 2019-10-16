package com.linhuanjie.easyexcel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-11-29 17:53
 * @email: lhuanjie@qq.com
 */
public class WriterWithoutHead {

    /**
     * 每行数据是List<String>无表头
     *
     * @throws IOException
     */
    @Test
    public void writeWithoutHead() throws IOException {
        try (OutputStream out = new FileOutputStream("withoutHead.xlsx")) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("sheet1");

            List<List<String>> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                List<String> item = new ArrayList<>();
                item.add("item0" + i);
                item.add("item1" + i);
                item.add("item2" + i);
                data.add(item);
            }
            writer.write0(data, sheet1);
            writer.finish();
        }
    }

    public static void main(String[] args) {

        try (OutputStream out = new FileOutputStream("清理20180101~20180331图片.xlsx")) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("sheet1");
            List<List<String>> data = new ArrayList<>();

            String imgs = "20161114052736556.jpg,20161114052736556.jpg,20161114052747723.jpg,20161114052804260.jpg,20161114052815539.jpg";
            String[] imgsArray = imgs.split(",");
//            java7
//            for (String s : imgsArray) {
//                List<String> item = new ArrayList<>();
//                item.add(s);
//                data.add(item);
//            }

//            java8
            Arrays.stream(imgsArray).distinct()
                    .collect(Collectors.toList())
                    .forEach(s -> data.add(new ArrayList<>(Arrays.asList(s))));
//            Arrays.stream(split).distinct().collect(Collectors.toList()).forEach(s -> System.out.println(s));

            data.stream().forEach(strings -> System.out.println(strings));

            writer.write0(data, sheet1);
            writer.finish();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
