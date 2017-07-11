package ru.aryukov;

import ru.aryukov.domain.User;

import java.sql.Connection;

/**
 * Created by oaryukov on 10.07.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.getConnection();
        SqlUtilsImp sqlUtilsImp = new SqlUtilsImp(connection);
        final String select = "select * from \"user\"";
        User user = sqlUtilsImp.queryExecutor(select, null, rs -> {
            User result = new User();
            while (rs.next()) {
                result.setId(rs.getLong("id"));
                result.setName(rs.getString("name"));
                result.setAge(rs.getInt("age"));
                }
            return result;
        });
        System.out.println(user.toString());
    }
}
