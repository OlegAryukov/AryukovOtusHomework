package ru.aryukov;

import java.lang.management.*;
import java.util.List;

/**
 * Created by tully.
 */
class Benchmark implements BenchmarkMBean {
    private int size = 0;

    void run() throws InterruptedException {
        Object[] array = new Object[size];
        System.out.println("Array of size: " + array.length + " created");

        int n = 0;
        int currentSize = size;
        System.out.println("Starting the loop");
        getStatsAboutGeneration();
        while (n < Integer.MAX_VALUE) {
            int i = n % currentSize;
            array[i] = new String(new char[0]);
            n++;
            if (n % currentSize == 0) {
                logs(n);
                currentSize = size;
                array = new Object[currentSize];
                System.out.println(printGCSStats());
                getStatsAboutGeneration();
                Thread.sleep(100);
            }
        }
    }

    public void getStatsAboutGeneration(){
        final List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (final MemoryPoolMXBean pool : pools) {
            final String name = pool.getName();
            System.out.println(name);
            final MemoryUsage usage = pool.getUsage();
            if (name.contains("Eden")) {
                System.out.println("\nFound PS Eden: " + usage);
            } else if (name.contains("Old")) {
                System.out.print("| Found Old Generation: " + usage);
            }
        }
    }

    public static String printGCSStats(){
        long totalGarbageCollections = 0;
        long garbageCollectionTime = 0;

        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = gc.getCollectionCount();

            if (count >= 0) {
                totalGarbageCollections += count;
            }

            long time = gc.getCollectionTime();

            if (time >= 0) {
                garbageCollectionTime += time;
            }
        }
        return "Total Garbage Collections: "
                + totalGarbageCollections + "| Total Garbage Collection Time (ms): "
                + garbageCollectionTime;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    private void logs(int n) {
        System.out.println("Created " + n + " objects");
        System.out.println("Creating new array of size: " + size);
    }
}
