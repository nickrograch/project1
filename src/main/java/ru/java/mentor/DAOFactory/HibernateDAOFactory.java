package ru.java.mentor.DAOFactory;

import org.hibernate.Session;

import java.io.IOException;

public class HibernateDAOFactory implements DAOFactory{

    @Override
    public DAO createDAO() throws IOException {
        return new HibernateDAO();
    }
}
