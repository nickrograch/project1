package ru.java.mentor.DAOFactory;

import ru.java.mentor.model.User;

import java.sql.Connection;
import java.util.List;

public class JdbcDAO implements DAO {

    private Connection connection;

    public JdbcDAO(){

    }

    @Override
    public List<User> getAllUsers() {

        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void editUser(long id, String name, String surname, String fathername) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public User getUserById(long id) {
        return null;
    }





}
