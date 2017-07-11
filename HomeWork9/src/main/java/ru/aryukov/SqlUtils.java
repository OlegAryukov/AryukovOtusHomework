package ru.aryukov;

import ru.aryukov.functionalIntfaces.ParamsSetInterface;
import ru.aryukov.functionalIntfaces.ResultSetInterface;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by oaryukov on 10.07.2017.
 */
public interface SqlUtils {

    void save(Object object) throws Exception;

    void load(Object object, Map<String, Object> idValues) throws Exception;

    void close() throws SQLException;

}
