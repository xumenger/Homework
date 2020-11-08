package com;

public interface ISort<T> {
    void sort(T[] objs);
    
    int compare(T o1, T o2);
}
