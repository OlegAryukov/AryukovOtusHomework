package ru.aryukov;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by oaryukov on 10.07.2017.
 */
public interface ResultUtil<T> {
    T handle(ResultSet resultSet) throws SQLException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException;
}

