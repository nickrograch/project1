package ru.java.mentor.DAOFactory;

import ru.java.mentor.util.PropertyReader;

public class DBConnection {



    DAO dao;

    public DBConnection(DAOFactory daoFactory, Object object){
        dao = daoFactory.createDAO(object);
    }

    public static DBConnection connectionConfiguration(Object object){
        DAOFactory daoFactory;
        DBConnection dbConnection = null;
        if (PropertyReader.getInstacne().getConnection().equals("hibernate")){
            daoFactory = new HibernateDAOFactory();
            dbConnection = new DBConnection(daoFactory, object);

        }
        else{
            daoFactory = new JDBCFactory();
            dbConnection = new DBConnection(daoFactory, object);
        }
        return dbConnection;
    }
}
