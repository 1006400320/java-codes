package com.linhuanjie.designpattern.proxy.multiple.interfaces;

public class CookImpl implements Cook{
	@Override
	public void doWork()
	{
		System.out.println("cook for you");
	}
}
