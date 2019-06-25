package com.linhuanjie.easyexcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-11-29 20:35
 * @email: lhuanjie@qq.com
 */
public class ReadWithHead {
    @Test
    public void testReadWithHead() {
        try (InputStream inputStream = new FileInputStream("withHead.xls");) {

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, new AnalysisEventListener() {
                @Override
                public void invoke(Object object, AnalysisContext context) {
                    System.out.println("context："+context);
                    System.out.println("一共有" + context.getTotalCount() + "行数据");
                    System.out.println("getCurrentRowAnalysisResult:"+context.getCurrentRowAnalysisResult());
                    System.out.println("getCurrentRowNum:"+context.getCurrentRowNum());
                    System.out.println("getCurrentSheet:"+context.getCurrentSheet());
                    System.out.println("getCustom:"+context.getCustom());
                    System.out.println("getEventListener:"+context.getEventListener());
                    System.out.println("getExcelHeadProperty："+context.getExcelHeadProperty());
                    System.out.println("getExcelType："+context.getExcelType());
                    System.out.println("getInputStream："+context.getInputStream());
                    System.out.println("object："+object.toString());
                    System.out.println("===================");
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.out.println("something after analysisContext...");
                }
            });
            excelReader.read(new Sheet(1),ExcelPropertyIndexModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class ExcelPropertyIndexModel extends BaseRowModel {

        @ExcelProperty(value = "姓名", index = 0)
        private String name;

        @ExcelProperty(value = "年龄", index = 1)
        private String age;

        @ExcelProperty(value = "邮箱", index = 2)
        private String email;

        @ExcelProperty(value = "地址", index = 3)
        private String address;

        @ExcelProperty(value = "性别", index = 4)
        private String sax;

        @ExcelProperty(value = "高度", index = 5)
        private String heigh;

        @ExcelProperty(value = "备注", index = 6)
        private String last;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSax() {
            return sax;
        }

        public void setSax(String sax) {
            this.sax = sax;
        }

        public String getHeigh() {
            return heigh;
        }

        public void setHeigh(String heigh) {
            this.heigh = heigh;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        @Override
        public String toString() {
            return "ExcelPropertyIndexModel [name=" + name + ", age=" + age + ", email=" + email + ", address=" + address
                    + ", sax=" + sax + ", heigh=" + heigh + ", last=" + last + "]";
        }
    }
}
