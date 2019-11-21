package com.linhuanjie.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 动态代理
 *                           https://www.icourse163.org/learn/ECNU-1206500807?tid=1206823217#/learn/content?type=detail&id=1212893564&cid=1216349908
 * @CreateTime : 2019.11.21 22:11
 **/
public class DynamicProxyDemo {

    public static void main(String[] args) {
        //1.创建目标对象
        SubjectImpl realSubject = new SubjectImpl();

        //2.创建调用处理器对象
        ProxyHandler handler = new ProxyHandler(realSubject);

        //3.动态生成代理对象
        Subject proxySubject =(Subject) Proxy.newProxyInstance(SubjectImpl.class.getClassLoader(),SubjectImpl.class.getInterfaces(), handler);
        //proxySubject真实类型com.sun.proxy.$Proxy0
        //proxySubject继承Proxy类，实现Subject接口
        //newProxyInstance的第二个参数，就是指定代理对象的接口

        //4.客户端通过代理对象调用方法
        //本次调用将自动被代理处理器的invoke方法接收
        proxySubject.request();

        System.out.println(proxySubject.getClass().getName());
        System.out.println(proxySubject.getClass().getSuperclass().getName());
    }
}

interface Subject {
    public void request();
}

//目标对象
class SubjectImpl implements Subject {
    @Override
    public void request() {
        System.out.println("I am dealing the request.");
    }
}

/**
 * 代理类的调用处理器
 */
class ProxyHandler implements InvocationHandler {
    private Subject subject;

    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }

    //此函数在代理对象调用任何一个方法时都会被调用。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println(proxy.getClass().getName());
        //定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
        System.out.println("====before====");
        Object result = method.invoke(subject, args);
        System.out.println("====after====");
        return result;
    }
}

















