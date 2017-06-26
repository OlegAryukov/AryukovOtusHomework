package ru.aryukov.testfraimwork;

/**
 * Created by oaryukov on 26.06.2017.
 */
public class Assretation {

    public static void assertEquals(String expected, String available){
        if(available.equals(expected)){
            System.out.println("Test equals pass");
        } else {
            fail(available);
        }
    }
    public static void assertNotNull(Object o){
        if (o != null){
            System.out.println("Test not NULL pass");
        } else {
            fail(o.toString());
        }
    }
    private static void fail(String message){
        throw new AssertionError(message);
    }
}
