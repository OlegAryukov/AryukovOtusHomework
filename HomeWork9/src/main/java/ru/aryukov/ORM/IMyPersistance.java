package ru.aryukov.ORM;

import java.sql.Connection;
import java.util.Map;

/**
 * Created by oaryukov on 08.06.2017.
 */
public interface IMyPersistance {

    public Object selectRow(Class<?> clazz, Map<String, Object> params);

    public void insert(Object obj);

    public void delete(Class<?> clazz, Map<String, Object> params);

    public Connection getConnection();

    public void setConnection(Connection conn);
}
