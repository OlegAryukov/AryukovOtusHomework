package ru.aryukov.ORM;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oaryukov on 09.06.2017.
 */
public class MyPersistanceImpl implements IMyPersistance {
    private static final List nullArray = new ArrayList();

    private Connection conn;

    public MyPersistanceImpl(Connection conn) {
        this.conn = conn;
    }


}
