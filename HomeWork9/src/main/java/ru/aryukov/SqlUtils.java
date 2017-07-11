package ru.aryukov;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by oaryukov on 10.07.2017.
 */
public interface SqlUtils {
    <T> T queryExecutor(String sql, ParamsUtils params, ResultUtil<T> handler) throws Exception;

    void save(Object object) throws Exception;

    void load(Object object, Map<String, Object> idValues) throws Exception;

    void executeUpdate(String sql, ParamsUtils params) throws Exception;

    void close() throws SQLException;

}
