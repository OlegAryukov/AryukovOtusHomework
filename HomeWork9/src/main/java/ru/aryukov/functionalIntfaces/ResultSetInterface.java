package ru.aryukov.functionalIntfaces;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by oaryukov on 10.07.2017.
 */
@FunctionalInterface
public interface ResultSetInterface<T> {
    T handle(ResultSet resultSet) throws SQLException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException;
}

