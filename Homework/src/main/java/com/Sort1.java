package com;

/**
 * 归并排序
 *
 * @param <T>
 */
public abstract class Sort1<T> implements ISort<T> {
	
	public String getName()
	{
		return "归并排序";
	}
	
	public void sort(T[] objs)
	{
		T[] tmpArray = objs.clone();
        mergeSort(objs, tmpArray, 0, objs.length - 1);
	}
	
	public abstract int compare(T o1, T o2);
	
	private void mergeSort(T[] a, T[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }
	
	private void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        //合并操作
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (compare(a[leftPos], a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }
        // 复制前半部分
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }
        //复制后半部分
        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }
        // 回写原数组
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

}
