package ru.java.mentor.DAOFactory;

import java.sql.Connection;

public class JDBCFactory implements DAOFactory {
    @Override
    public DAO createDAO(Object object) {
        return new JdbcDAO((Connection) object);
    }
}
