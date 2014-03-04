package com.placeiq;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;


public class Samples {
   
    @Requires({ " a != null" })
	@Ensures({ "Samples.isReverseOf(result, a)" })
	public static<T> T[] reverse(T[] a, Class<T> elemType) {
		T[] result = (T[]) Array.newInstance(elemType, a.length);
		System.arraycopy(a, 0, result, 0, a.length);
		
		int mid = result.length / 2;
		int last = result.length - 1;
		
		for (int i = 0; i <= mid; i++) {
			swap(result, i, last-i);
		}
		return result;
	}
	
	public final static<T> void swap(T[] a, int i, int j) {
		T tmp = a[i];
		
		a[i] = a[j];
		a[j] = tmp;
	}
	
	@Requires({ "a != null && b != null" })
	public static<T> boolean isReverseOf(T[] a, T[] b) {
		//N.B. must copy since Arrays.asList doesn't
		T[] aCopy = Arrays.copyOf(a, a.length);
	
		List<T> aReversed = Arrays.asList(aCopy);
		Collections.reverse(aReversed);
		
		return a.length == b.length && aReversed.equals(Arrays.asList(b));
	}
}
