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
                //.toArray(Method[]::new);
                .collect(Collectors.toList());

        return prepear(allMethods);
    }
     private List<Method> prepear(List<Method> list){
        Method[] array = list.toArray(new Method[list.size()]);
        for ( int i = 0; i < array.length; i++ ){
            if( array[i].getAnnotation(Before.class)!=null ){
                if( i != 0 ){
                    Method tmp = array[0];
                    array[0] = array[i];
                    array[i]  = tmp;
                }
            }else if (array[i].getAnnotation(Test.class)!=null){
                if( i != 1 ){
                    Method tmp = array[1];
                    array[1] = array[i];
                    array[i]  = tmp;
                }
            }else if (array[i].getAnnotation(After.class)!=null){
                if( i != 2 ){
                    Method tmp = array[2];
                    array[2] = array[i];
                    array[i]  = tmp;
                }
            }
        }

        return Arrays.asList(array).stream().collect(Collectors.toList());
     }
}
