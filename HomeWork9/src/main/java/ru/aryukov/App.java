package ru.aryukov;

import ru.aryukov.DAO.UserDAO;
import ru.aryukov.domain.User;
import ru.aryukov.testutils.ConnectionTester;

/**
 * Created by oaryukov on 08.06.2017.
 */
public class App {
    public static void main(String[] args) {
        ConnectionTester.testConnection();
        UserDAO userDAO = new UserDAO();
        User user = new User(32,"Oleg");
        userDAO.create(user);

    }
}