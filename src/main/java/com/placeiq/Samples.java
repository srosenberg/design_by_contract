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
		@SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(elemType, a.length);
		System.arraycopy(a, 0, result, 0, a.length);
		
		int mid = result.length / 2;
		int last = result.length - 1;
		
		for (int i = 0; i <= mid; i++) {
			swap(result, i, last-i);
		}
		return result;
	}
	
    //@Requires( { "a != null && 0 <= i && i < a.length && 0 <=j && j < a.length" })
    //@Ensures( { "a[i] == old(a[j]) && a[j] == old(a[i])" })
    //TODO: need a way to specify universal quantification, i.e., forall k :: k != i && k != j ==> old(a[k]) == a[k]
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
	
	@Requires({ " a != null && b != null" })
	@Ensures({ "result != null && Arrays.equals(old(a), a) && Arrays.equals(old(b), b)" })
	public static int[] intersection(int[] a, int[] b) {
		int[] result = new int[Math.min(a.length, b.length)];
		
		if (result.length == 0) {
			return result;
		}
		Arrays.sort(a);
		Arrays.sort(b);
		
		int i, j, k;
		
		i = j = k = 0;
		
		while  (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				i++;
			} else if (a[i] > b[j]) {
				j++;
			} else {
				result[k++] = a[i];
				i++;
				j++;
			}
		}
		return Arrays.copyOf(result, k);
	}
}
