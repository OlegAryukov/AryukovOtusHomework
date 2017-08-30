package ru.aryukov;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by dev on 22.08.17.
 */
public class MergeSort {
    private static final Random RAND = new Random(42);

    public static void main(String[] args) throws Throwable {
        int LENGTH = 1000;
        int RUNS   =  16;

        for (int i = 1; i <= RUNS; i++) {
            int[] a = createRandomArray(LENGTH);

            long startTime1 = System.currentTimeMillis();
            parallelMergeSort(a);
            long endTime1 = System.currentTimeMillis();

            if (!isSorted(a)) {
                throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
            }

            System.out.printf("%10d elements  =>  %6d ms \n", LENGTH, endTime1 - startTime1);
            LENGTH *= 2;
        }
    }

    public static void parallelMergeSort(int[] a) {
        int cores = 8;
        parallelMergeSort(a, cores);
    }

    public static void parallelMergeSort(int[] a, int countOfThreads) {
        if (countOfThreads <= 1) {
            mergeSort(a);
        } else if (a.length >= 2) {
            int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

            Thread leftThread = new Thread(new Sorter(left,  countOfThreads / 2));
            Thread rightThread = new Thread(new Sorter(right, countOfThreads / 2));
            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException ie) {}

            merge(left, right, a);
        }
    }

    public static void mergeSort(int[] a) {
        if (a.length >= 2) {
            int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

            mergeSort(left);
            mergeSort(right);

            merge(left, right, a);
        }
    }

    public static void
    merge(int[] left, int[] right, int[] a) {
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
                a[i] = left[i1];
                i1++;
            } else {
                a[i] = right[i2];
                i2++;
            }
        }
    }

    public static final void swap(int[] a, int i, int j) {
        if (i != j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static int[] createRandomArray(int length) {
        int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = RAND.nextInt(1000000);
        }
        return a;
    }
}
