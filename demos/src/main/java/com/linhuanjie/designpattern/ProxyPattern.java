package com.linhuanjie.designpattern;

/**
 * 代理模式
 */

interface ISubject{
    public abstract void save();
}

class RealSubject implements ISubject{

    @Override
    public void save() {
        System.out.println("我来救人啦QAQ");
    }
}

class ProxySubject implements ISubject{
    private ISubject subject;
    public ProxySubject (RealSubject subject){
        this.subject = subject;
    }

    @Override
    public void save() {
        System.out.println("1、救人第一步：先打败坏人");
        this.subject.save();
        System.out.println("3、救完人了，快溜");
    }
}

class Factory{
    public static ISubject getInstance(){
        return new ProxySubject(new RealSubject());
    }

}

public class ProxyPattern {
    public static void main(String[] args) {
//        ISubject subject = new ProxySubject(new RealSubject());
        ISubject subject = Factory.getInstance();
        subject.save();
    }
}
