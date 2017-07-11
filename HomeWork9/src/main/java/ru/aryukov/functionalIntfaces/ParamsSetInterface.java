package ru.aryukov.functionalIntfaces;

import java.sql.PreparedStatement;

/**
 * Created by oaryukov on 10.07.2017.
 */
@FunctionalInterface
public interface ParamsSetInterface {
    void setParams(PreparedStatement ps) throws Exception;
}
