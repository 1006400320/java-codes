package com.linhuanjie.designpattern.proxy.myaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *  动态代理实现简单的aop模式
 *  https://www.icourse163.org/learn/ECNU-1206500807?tid=1206823217#/learn/content?type=detail&id=1212876684&cid=1216362111
 */
public class Main {

	
	public static void initXml(){
		XmlReader.readXml("C:/IDEA/IdeaProjects/java-codes/demos/src/main/resources/aops.xml");  // C:\IDEA\IdeaProjects\java-codes\demos\src\main\resources
		ResourceListener.addListener("C:/IDEA/IdeaProjects/java-codes/demos/src/main/resources/");
	}	
	
	public static void main(String[] args) throws Exception{
		Main.initXml();		
		
		Person action = new PersonImpl();
		ProxyHandler mh = new ProxyHandler(action);
		ClassLoader cl = Main.class.getClassLoader();
		Class<?> proxyClass = Proxy.getProxyClass(cl, new Class<?>[]{Person.class});
        Person proxy = (Person) proxyClass.getConstructor(new Class[]{InvocationHandler.class}).
                newInstance(new Object[]{mh});
        
        while(true)
        {
			proxy.eat();
			try{
				Thread.sleep(3000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
