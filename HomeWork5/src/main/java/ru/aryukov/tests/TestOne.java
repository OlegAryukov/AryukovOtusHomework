package ru.aryukov.tests;


import com.sun.org.apache.xpath.internal.operations.String;
import ru.aryukov.annotations.After;
import ru.aryukov.annotations.Before;
import ru.aryukov.annotations.Test;
import ru.aryukov.testfraimwork.Assertation;

/**
 * Created by oaryukov on 26.06.2017.
 */
public class TestOne {

    @Before
    void beforeTest(){
        System.out.println("Hello @Before test");
    }

    @Test
    void testTest(){
        System.out.println("Test @Test");
        Assertation.assertEquals("Hi", "Hi");
    }

    @After
    void afterTest(){
        System.out.println("Test @After");
        Assertation.assertNotNull(new String());
    }
}
