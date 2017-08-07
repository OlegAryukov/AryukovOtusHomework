package ru.aryukov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.aryukov.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by oaryukov on 10.07.2017.
 */
public class SqlUtilsTest {
    private SqlUtilsImp sqlHelper;

    @Before
    public void initTestTable() throws Exception {
        Connection connection = new ConnectionHelper().getConnection();
        sqlHelper = new SqlUtilsImp(connection);
       sqlHelper.update("delete from user_entity_one", null);
    }

    @After
    public void close() throws SQLException {
        sqlHelper.close();
    }

    @Test
    public void queryExecutor() throws Exception {
        final String userName = "TestUser";
        final Integer age = 30;

        final String insert = "insert into user_entity_one(name, age) values (?, ?)";

        sqlHelper.update(insert, ps -> {
            ps.setString(1, userName);
            ps.setInt(2, age);
        });

        final String select = "select * from user_entity_one";
        User user = sqlHelper.queryExecutor(select, null, rs -> {
            User result = new User();
            while (rs.next()) {
                result.setId(rs.getLong("id"));
                result.setName(rs.getString("name"));
                result.setAge(rs.getInt("age"));
            }
            return result;
        });

        assertEquals("user Name", userName, user.getName());
        assertEquals("user Age", age, user.getAge());
    }


    @Test
    public void save() throws Exception {
        final User user = new User();
        user.setName("userName");
        user.setAge(33);

        sqlHelper.save(user);

        final String select = "select * from user_entity_one";
        final User userFact = sqlHelper.queryExecutor(select, null, rs -> {
            User result = new User();
            while (rs.next()) {
                result.setId(rs.getLong("id"));
                result.setName(rs.getString("name"));
                result.setAge(rs.getInt("age"));
            }
            return result;
        });

        assertEquals("user Name", user.getName(), userFact.getName());
        assertEquals("user Age", user.getAge(), userFact.getAge());
    }

    @Test
    public void load() throws Exception {
        final String userName = "userName1";
        final Integer age = 10;
        final String insert = "insert into user_entity_one(name, age) values (?, ?)";

        sqlHelper.update(insert, ps -> {
            ps.setString(1, userName);
            ps.setInt(2, age);
        });

        final String select = "select * from user_entity_one";
        final User user = sqlHelper.queryExecutor(select, null, rs -> {
            User result = new User();
            while (rs.next()) {
                result.setId(rs.getLong("id"));
                result.setName(rs.getString("name"));
                result.setAge(rs.getInt("age"));
            }
            return result;
        });


        final User userFact = new User();
        final Map<String, Object> ids = new HashMap<>();
        ids.put("id", user.getId());
        sqlHelper.load(userFact, ids);

        System.out.println("loaded user:" + userFact);
        assertEquals("user", user, userFact);
    }
}

