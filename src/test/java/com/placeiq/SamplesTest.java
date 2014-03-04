package com.placeiq;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SamplesTest {
	
	@Test
	public void testBanal() {
		Assert.assertArrayEquals(toArray("moob"), Samples.reverse(toArray("boom"), Character.class));
		
		Assert.assertArrayEquals(toArray("bob"), Samples.reverse(toArray("bob"), Character.class));
	}
	
	@Test
	public void testRandom() {
		for (int i = 0; i < 100; i++) {
			String s = getRandom();
			System.out.println("original: " + s);
			System.out.println("reversed: " + StringUtils.join(Samples.reverse(toArray(s), Character.class)));
		}
	}
	
	private String getRandom() {
		String sigma = "abcdefghijklmnopqrstuvwxyz";
		Random gen = new Random();
		
		int length = gen.nextInt(10);
		StringBuilder result = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			result.append(sigma.charAt(gen.nextInt(sigma.length())));
		}
		return result.toString();
	}
	
	private Character[] toArray(String s) {
		return ArrayUtils.toObject(s.toCharArray());
	}
}
