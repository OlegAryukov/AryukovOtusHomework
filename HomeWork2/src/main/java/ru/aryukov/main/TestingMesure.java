package ru.aryukov.main;

import ru.aryukov.mesurment.ObjectSizeInstrument;

/**
 * Created by olega on 10.04.17.
 */
public class TestingMesure {
    public static void main(String[] args) {
        int[][] a = new int[100][2];
        int[][] b = new int[2][100];
        String str = new String("Hello world Java!");
        try {
            System.out.println(ObjectSizeInstrument.sizeOf(a));
            System.out.println(ObjectSizeInstrument.sizeOf(b));
            System.out.println(ObjectSizeInstrument.sizeOf(str));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
