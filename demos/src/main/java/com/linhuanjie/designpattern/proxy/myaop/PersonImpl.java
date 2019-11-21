package com.linhuanjie.designpattern.proxy.myaop;

public class PersonImpl implements Person {

	public void eat() {
		System.out.println("I am eating");
	}

	public void washHand() {
		System.out.println("I am washing hands");

	}

	public void bath() {
		System.out.println("I am bathing");

	}

}
