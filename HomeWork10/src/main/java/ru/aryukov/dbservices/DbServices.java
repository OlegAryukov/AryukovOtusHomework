package ru.aryukov.dbservices;

import ru.aryukov.domain.UserEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dev on 14.07.17.
 */
public interface DbServices {

    String getLocalStatus();

    void save(UserEntity userEntity);

    UserEntity read(long id);

    UserEntity readByName(String name);

    List<UserEntity> readAll();

    void shutdown();
}
