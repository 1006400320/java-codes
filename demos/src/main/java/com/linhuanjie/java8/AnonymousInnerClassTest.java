package com.linhuanjie.java8;

interface IPerson{
    public void eatSomething();
}

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        IPerson person = new IPerson(){
            @Override
            public void eatSomething(){
                System.out.println("吃啥好呢QAQ");
            }
        };

        person.eatSomething();
    }

}
