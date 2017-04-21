package ru.aryukov.arrayList;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oaryukov on 20.04.2017.
 */
public class MyArrayListTest {

    @Test
    public void whenWeAddElement(){
        MyArrayList<String> myList = new MyArrayList<>(5);
        myList.add("one");
        myList.add("two");
        myList.add("three");
        assertThat(myList.size(), is(3));
    }

    @Test
    public void whenWeRemove(){
        MyArrayList<String> myList = new MyArrayList<>(5);
        myList.add("one");
        myList.add("two");
        myList.add("three");
        myList.remove("two");
        assertThat(myList.size(), is(2));
    }

    @Test
    public void whenWeGetElement(){
        MyArrayList<String> myList = new MyArrayList<>(5);
        myList.add("one");
        myList.add("two");
        myList.add("three");
        myList.get(2);
        String result = "three";
        assertThat(result, is("three"));
    }

    @Test
    public void whenWeInsertByPosition(){
        MyArrayList<String> myList = new MyArrayList<>(5);
        myList.add("one");
        myList.add("two");
        myList.add("three");
        myList.add("five", 1);
        String result = myList.get(1);
        assertThat(result, is("five"));
    }

    @Test
    public void whenWeAddInDefoultCapacity(){
        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("one");
        myList.add("two");
        myList.add("three");
        myList.add("five", 1);
        String result = myList.get(1);
        assertThat(result, is("five"));
        assertThat(myList.size(), is(4));
    }
}
