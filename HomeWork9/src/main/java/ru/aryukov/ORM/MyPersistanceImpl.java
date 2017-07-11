package ru.aryukov.ORM;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by oaryukov on 09.06.2017.
 */
public class MyPersistanceImpl implements IMyPersistance {
    private static final List nullArray = new ArrayList();

    private Connection conn;

    public MyPersistanceImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public Object selectRow(Class<?> clazz, Map<String, Object> params) {
        return null;
    }

    @Override
    public void insert(Object obj) {

    }

    @Override
    public void delete(Class<?> clazz, Map<String, Object> params) {

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void setConnection(Connection conn) {

    }
}
