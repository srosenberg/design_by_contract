package com.placeiq;

import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.generator.InRange;

@RunWith(Theories.class)
public class SamplesTest {
	
	@Test
	// lame unit test
	public void testReverseFixedInputs() {
		Assert.assertArrayEquals(toArray("moob"), Samples.reverse(toArray("boom"), Character.class));
		
		Assert.assertArrayEquals(toArray("bob"), Samples.reverse(toArray("bob"), Character.class));
	}
	
	@Test
	// randomized testing (manual input generation) 
	public void testReverseRandomInputs() {
		for (int i = 0; i < 100; i++) {
			String s = getRandom();
			System.out.println("original: " + s);
			System.out.println("reversed: " + StringUtils.join(Samples.reverse(toArray(s), Character.class)));
		}
	}
	
	@Theory
	// randomized testing ala quickcheck
	public void testReverseQC(@ForAll String s) {
		assumeTrue(s.length() > 2);

		System.out.println("original: " + s);
		System.out.println("reversed: " + StringUtils.join(Samples.reverse(toArray(s), Character.class)));
	}
	
	@Theory
	public void testIntersection(@ForAll(sampleSize=10) @InRange(minInt = 0, maxInt = 20) int[] a, 
			                     @ForAll(sampleSize=10) @InRange(minInt = 0, maxInt = 20) int[] b) {
		assumeTrue(a != null);
		assumeTrue(b != null);
		
		System.out.println("a: " + StringUtils.join(ArrayUtils.toObject(a), ","));
		System.out.println("b: " + StringUtils.join(ArrayUtils.toObject(b), ","));
		
		int[] result = Samples.intersection(a, b);
		
		System.out.println("result: " + StringUtils.join(ArrayUtils.toObject(result), ","));

		// partial correctness condition (wish there were a way to write quantifiers...)
		for (int x : a) {
			for (int y : b) {
				if (x == y) {
					assertThat(x, isIn(ArrayUtils.toObject(result)));
				}
			}
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
