package ru.java.mentor.DAOFactory;

import java.sql.Connection;

public class JdbcDAO implements DAO {

    private Connection connection;

    public JdbcDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void getAllUsers() {

    }

    @Override
    public void addUser() {

    }

    @Override
    public void editUser() {

    }

    @Override
    public void deleteUser() {

    }
}
