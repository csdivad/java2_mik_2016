package com.csdivad.valami.teszt;

import static org.junit.Assert.*;

import org.junit.Test;

import com.csdivad.valami.asd.Main;

import junit.framework.Assert;

public class Test1 {

	@Test
	public void test() {
		Assert.assertEquals(Main.helloBello(), "Hello Bello");
		Assert.assertNotNull(Main.getModel());
		//Assert.assertNotNull(Main.getNullModel());
	}

}
