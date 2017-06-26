package ru.aryukov;

import ru.aryukov.testfraimwork.MainRunner;
import ru.aryukov.tests.TestOne;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by oaryukov on 26.06.2017.
 */
public class Application {
    public static void main(String[] args) {
        try {
            Class[] arrayClass = new Class<?>[]{TestOne.class};
            MainRunner.run(arrayClass);
            MainRunner.run("ru.aryukov.tests");
        }  catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
