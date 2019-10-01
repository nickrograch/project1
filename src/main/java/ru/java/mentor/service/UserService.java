package ru.java.mentor.service;

import org.hibernate.SessionFactory;
import ru.java.mentor.util.DbHelper;

import java.io.IOException;

public class UserService {

    private static UserService userService;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() throws IOException {
        if (userService == null) {
            userService = new UserService(DbHelper.getSessionFactory());
        }
        return userService;
    }
}
