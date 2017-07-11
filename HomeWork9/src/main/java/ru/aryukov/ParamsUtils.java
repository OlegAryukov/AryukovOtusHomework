package ru.aryukov;

import java.sql.PreparedStatement;

/**
 * Created by oaryukov on 10.07.2017.
 */
public interface ParamsUtils {
    void setParams(PreparedStatement ps) throws Exception;
}
