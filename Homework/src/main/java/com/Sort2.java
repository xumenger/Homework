package com;

/**
 * 快速排序
 *
 * @param <T>
 */
public abstract class Sort2<T> implements ISort<T> {
	
	public String getName()
	{
		return "快速排序";
	}
	
	public void sort(T[] objs)
	{
		int low = 0;
        int high = objs.length - 1;
        quickSort(objs, low, high);
	}
	
	public abstract int compare(T o1, T o2);
	
	private void quickSort(T[] objs, int low, int high) {
		if (low >= high) return;

        //递归划分
        int pivot = partition(objs, low, high);
        quickSort(objs, low, pivot - 1);
        quickSort(objs, pivot + 1, high);
	}
	
	private int partition(T[] objs, int low, int high) {

        //设置最小值为轴
        T pivot = objs[low];

        int i = low, j = high;
        while (i < j) {
            // 找到右边第比轴小的值
            while (j > i && compare(objs[j], pivot) >= 0) --j;
            // 找到左边第比轴大的值
            while (i < j && compare(objs[j], pivot) <= 0) ++i;
            // 交互2个值
            if (i < j) {
                T tmp = objs[i];
                objs[i] = objs[j];
                objs[j] = tmp;
            }
        }
        //最终将轴归位
        objs[low] = objs[i];
        objs[i] = pivot;

        return i;
    }
}
