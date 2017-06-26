package ru.aryukov.testfraimwork;

import ru.aryukov.annotations.After;
import ru.aryukov.annotations.Before;
import ru.aryukov.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by oaryukov on 25.06.2017.
 */
public class Container {

    List<Method> allMethods = new ArrayList<>();

    public List<Method> getMethodsList(Class<?> className) {
        Collection<Method> collection = Arrays.asList(className.getDeclaredMethods());

        Predicate<Method> methodPredicate = method -> method.getAnnotation(Before.class) != null
                || method.getAnnotation(After.class) != null
                || method.getAnnotation(Test.class) != null ;

        allMethods = collection.stream()
                .filter(methodPredicate)
                .collect(Collectors.toList());
        return allMethods;
    }
    
}
