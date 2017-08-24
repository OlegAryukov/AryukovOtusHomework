package ru.aryukov;

/**
 * Created by dev on 22.08.17.
 */
public class Sorter implements Runnable {
    private int[] a;
    private int countOfThreads;

    public Sorter(int[] a, int countOfThreads) {
        this.a = a;
        this.countOfThreads = countOfThreads;
    }

    public void run() {
        MergeSort.parallelMergeSort(a, countOfThreads);
    }
}
