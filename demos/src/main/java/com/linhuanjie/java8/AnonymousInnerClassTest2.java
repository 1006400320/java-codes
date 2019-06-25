package com.linhuanjie.java8;

abstract class AbstractPerson {
    public void print() {
        System.out.println("I like to eat " + this.eat() + " ~~~ ");
    }

    public abstract String eat();
}

public class AnonymousInnerClassTest2 {
    public static void main(String[] args) {
        AbstractPerson person = new AbstractPerson() {
            @Override
            public String eat() {
                return "bananaer";
            }
        };

        person.print();
    }
}
