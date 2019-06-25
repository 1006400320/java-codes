package com.linhuanjie.designpattern;

/**
 * 工厂模式
 */
public class FactoryPattern {

    public static void main(String[] args) {
//        原始写法
//        IFruit fruit = new Apple();
//        fruit.eat();
        String name = "bananer";
        String name2 = "apple";
        IFruit fruit = FruitFactory.getInstance(name2);
        fruit.eat();
    }

}


interface IFruit{
    public void eat();
}

class Apple implements IFruit{

    @Override
    public void eat() {
        System.out.println("削皮吃苹果！");
    }
}

class Bananer implements IFruit{

    @Override
    public void eat() {
        System.out.println("剥皮吃香蕉！");
    }
}

class FruitFactory{
    public static IFruit getInstance(String name){
        if("apple".equals(name)){
            return new Apple();
        }else if("bananer".equals(name)){
            return new Bananer();
        }else{
            return null;
        }
    }

}