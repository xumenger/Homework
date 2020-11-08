package com;

import java.util.Date;

/**
 * 测试方法
 * 
 *
 */
public class TestSort {

	public static void main(String args[]) {
		// IntegerSort 测试
		Integer[] array1 = {2, 5, -2, 6, -3, 8, 0, -7, -9, 4};
		
		IntegerSort sort1 = new IntegerSort();
		sort1.sort(array1);
		
		for (int i=0; i<array1.length; i++) {
			System.out.println(array1[i]);
		}
		
		
		// UserInfoSort 测试
		UserInfo u1 = new UserInfo(2, "2", "2", new Date());
		UserInfo u2 = new UserInfo(5, "5", "5", new Date());
		UserInfo u3 = new UserInfo(3, "3", "3", new Date());
		
		UserInfo[] array2 = {u1, u2, u3};
		
		UserInfoSort sort2 = new UserInfoSort();
		sort2.sort(array2);
		
		for (int i=0; i<array2.length; i++) {
			System.out.println(array2[i]);
		}
	}
	
	
}
