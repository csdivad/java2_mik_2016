package com.csdivad.valami.asd;

public class HelloModel {
	private String hello1;
	private String hello2;
		
	public HelloModel(String hello1, String hello2) {
		super();
		this.hello1 = hello1;
		this.hello2 = hello2;
	}
	
	public String getHello1() {
		return hello1;
	}
	public void setHello1(String hello1) {
		this.hello1 = hello1;
	}
	public String getHello2() {
		return hello2;
	}
	public void setHello2(String hello2) {
		this.hello2 = hello2;
	}
		
}
