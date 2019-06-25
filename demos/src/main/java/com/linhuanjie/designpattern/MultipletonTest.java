package com.linhuanjie.designpattern;

/**
 * 多例模式
 */
class Multiple {
    public static final int MALE_FLAG = 1;
    public static final int FEMALE_FLAG = 2;
    private static Multiple MULTIPLE;
    private String description;

    private Multiple(String description) {
        this.description = description;
    }

    public static Multiple getInstance(int flag) {
        switch (flag) {
            case MALE_FLAG:
                return new Multiple("男");
            case FEMALE_FLAG:
                return new Multiple("女");
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.description;
    }
}


public class MultipletonTest {
    public static void main(String[] args) {
        Multiple m = Multiple.getInstance(1);
        System.out.println(m);


    }

}
