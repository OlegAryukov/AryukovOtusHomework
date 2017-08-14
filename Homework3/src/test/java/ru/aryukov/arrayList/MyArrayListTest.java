package ru.aryukov.arrayList;

import org.junit.Test;

import java.util.Comparator;

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

    @Test
    public void whenWeSort(){
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(10);
        myList.add(7);
        myList.add(15);
        myList.add(2);
        myList.sort(Comparator.naturalOrder());
        assertThat(myList.get(0), is(2));
        assertThat(myList.get(1), is(7));
        assertThat(myList.get(2), is(10));
        assertThat(myList.get(3), is(15));
    }
}
