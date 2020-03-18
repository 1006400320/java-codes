package com.linhuanjie.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-10-13 16:29
 * @email: lhuanjie@qq.com
 */
public class Java8Demo1 {
    public static void main(String args[]) {
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        System.out.println("使用 Java 7 语法: ");
        sortUsingJava7(names1);
        System.out.println(names1);

        System.out.println("使用 Java 8 语法: ");
        sortUsingJava8(names2);
        System.out.println(names2);
    }

    // 使用 java 7 排序
    private static void sortUsingJava7(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    // 使用 java 8 排序
    private static void sortUsingJava8(List<String> names) {
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }


    /**
     * filter 过滤
     */
    @Test
    public void test(){

        List<Student> students = new ArrayList<>();

        students.add(new Student(3,"张三",90));
        students.add(new Student(2,"李四",60));
        students.add(new Student(3,"王五",30));
        students.add(new Student(4,"赵六",85));

        int studentId = 3;
        students.stream().filter(o -> o.getId() == studentId).collect(Collectors.toList()).forEach(student -> System.out.println(student));
        Student student = students.stream().filter(s -> s.getId() == studentId).findAny().orElse(null);

        System.out.println(student);

    }

    class Student{
        private int id;
        private String name;
        private int score;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getScore() {
            return score;
        }
        public void setScore(int score) {
            this.score = score;
        }

        public Student(int id, String name, int score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
