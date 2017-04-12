package ru.aryukov.main;

import ru.aryukov.mesurment.ObjectSizeInstrument;

import java.lang.management.ManagementFactory;

/**
 * Created by olega on 10.04.17.
 */
public class TestingMesure {
    /**
     * Main method.
     * @param args params.
     * @throws InterruptedException exception.
     * @throws IllegalAccessException exception.
     */
    public static void main(String... args) throws InterruptedException, IllegalAccessException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        //Runtime runtime = Runtime.getRuntime();
        //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        int size = 2 * 1024 * 1024;
        Object[] array = new Object[size];
        System.out.println("Array of size: " + array.length + " created");
        System.out.println("Empty Array: " + ObjectSizeInstrument.sizeOf(array));
        Thread.sleep(10 * 1000);

        int n = 0;
        System.out.println("Starting the loop");
        while (n < Integer.MAX_VALUE) {
            int i = n % size;
            array[i] = new String(""); //no String pool
            n++;
            if (n % 1024 == 0) {
                Thread.sleep(1);
            }
            if (n % size == 0) {
                System.out.println("Fill Array: " + ObjectSizeInstrument.sizeOf(array));
                System.out.println("Created " + n + " objects");
                System.out.println("Creating new array");
                array = new Object[size];
            }
        }
    }
}
