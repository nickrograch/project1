package ru.java.mentor.DAOFactory;

import org.hibernate.Session;
import ru.java.mentor.util.PropertyReader;

public class AbstractDAOFactory {

    private static DAO dao;
    private static AbstractDAOFactory abstractDAOFactory;

    public static AbstractDAOFactory getInstance(){
        if (abstractDAOFactory == null){
            abstractDAOFactory = new AbstractDAOFactory();
        }
        return abstractDAOFactory;
    }

    public static DAO getDAO(Session session) {
        return new HibernateDAO(session);
    }
}
