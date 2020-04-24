package com.linhuanjie.javase.serializable;

import org.junit.Test;

import java.io.*;

/**
 *
 * @Author : linhuanjie
 * @Description :
 * @CreateTime : 2020/4/24 14:51
 **/
public class SerializableDemo {

    public static void main(String[] args) throws Exception {


    }

    @Test
    public void deserializationTest() throws Exception {
        SerializableUser user2 = deserialization();
        System.out.println("user2.toString() = " + user2.toString());

    }

    @Test
    public void serializationTest() throws Exception {
        SerializableUser user = new SerializableUser(1, "name", 3);
        System.out.println(user.toString());

        serialization(user);
    }

    public static SerializableUser deserialization() throws Exception {
        FileInputStream fio = new FileInputStream("F://temp2.txt");
        ObjectInputStream ois = new ObjectInputStream(fio);
        return (SerializableUser) ois.readObject();
    }


    public static void serialization(SerializableUser user) throws Exception {
        FileOutputStream fos = new FileOutputStream("F://temp2.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        oos.flush();
        oos.close();
    }


}

