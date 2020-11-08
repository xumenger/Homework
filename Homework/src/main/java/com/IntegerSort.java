package com;

public class IntegerSort extends Sort1<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}

}
