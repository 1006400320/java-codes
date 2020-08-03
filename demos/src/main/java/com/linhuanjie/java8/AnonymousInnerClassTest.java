package com.linhuanjie.java8;

interface IPerson{
    void eatSomething();
}

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        IPerson person = () -> System.out.println("吃啥好呢QAQ");

        person.eatSomething();
    }

}
