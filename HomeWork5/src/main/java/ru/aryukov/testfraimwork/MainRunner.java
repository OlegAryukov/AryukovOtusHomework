package ru.aryukov.testfraimwork;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by oaryukov on 25.06.2017.
 */
public class MainRunner {

    public static void run(Class<?>[] classArray) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class className:classArray) {
            run(className);
        }
    }

    private static void run(Class<?> className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Container container = new Container();
        Collection<Method> coll = container.getMethodsList(className);
        Object object = className.newInstance();
        for (Method method : coll) {
            method.setAccessible(true);
            method.invoke(object);
        }
    }

    public static void run(String pkgName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException{

        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false),
                        new TypeAnnotationsScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(pkgName))));

//        Set<Class<?>> allBefore = reflections.getTypesAnnotatedWith(Before.class);
//        Set<Class<?>> allAfter = reflections.getTypesAnnotatedWith(After.class);
//        Set<Class<?>> allTest = reflections.getTypesAnnotatedWith(Test.class);
//
//        ArrayList<Class<?>> allClass = new ArrayList<>();
//        allClass.addAll(allBefore);
//        allClass.addAll(allAfter);
//        allClass.addAll(allTest);
        Set<Class<?>> allClass = reflections.getSubTypesOf(Object.class);

        run(allClass.toArray(new Class<?>[allClass.size()]));
    }
}
