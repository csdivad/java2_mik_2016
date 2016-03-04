package com.csdivad.valami.asd;

public class Main {

	public static void main(String[] args) {
		System.out.print(helloBello());
	}

	public static String helloBello() {
		return "Hello Bello";
	}

	public static HelloModel getModel() {
		HelloModel helloModel = new HelloModel("Tamás", "Dávid");
		return helloModel;
	}
	
	public static HelloModel getNullModel() {
		return null;
	}

}
