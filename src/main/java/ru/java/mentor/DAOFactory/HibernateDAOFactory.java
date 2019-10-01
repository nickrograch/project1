package ru.java.mentor.DAOFactory;

import org.hibernate.Session;

public class HibernateDAOFactory implements DAOFactory{

    @Override
    public DAO createDAO(Object object) {
        return new HibernateDAO((Session) object);
    }
}
