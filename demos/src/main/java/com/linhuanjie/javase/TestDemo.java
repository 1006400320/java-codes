package com.linhuanjie.javase;

import java.lang.reflect.Field;

public class TestDemo {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Person p = new Person();
        p.print();

        Student s = new Student();
        s.print();

        System.out.println("===============================");
        Class clazz = Class.forName("com.linhuanjie.javase.Person");
        Person p1 = (Person) clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getType().toString().contains("String")){
                field.set(p1,field.toString());
            } else {
                field.set(p1,1);
            }
            int i = 1+2;
        }
        System.out.println(p1.toString());
    }

}


class Person {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public void print(){
        System.out.println("TuT");
    }
}


class Student extends Person{
    @Override
    public void print(){
        System.out.println("QAQ");
    }
}