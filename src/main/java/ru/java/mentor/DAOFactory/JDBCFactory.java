package ru.java.mentor.DAOFactory;

import java.sql.Connection;

public class JDBCFactory implements DAOFactory {
    @Override
    public DAO createDAO() {
        return new JdbcDAO();
    }
}
