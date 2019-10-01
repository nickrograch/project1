package ru.java.mentor.DAOFactory;

import ru.java.mentor.util.PropertyReader;

import java.io.IOException;

public class DBConnection {



    DAO dao;

    public DAO getDao() {
        return dao;
    }


    public DBConnection(DAOFactory daoFactory) throws IOException {
        dao = daoFactory.createDAO();
    }

    public static DBConnection connectionConfiguration() throws IOException {
        DAOFactory daoFactory;
        DBConnection dbConnection = null;
        if (PropertyReader.getInstacne().getConnection().equals("hibernate")){
            daoFactory = new HibernateDAOFactory();
            dbConnection = new DBConnection(daoFactory);

        }
        else{
            daoFactory = new JDBCFactory();
            dbConnection = new DBConnection(daoFactory);
        }
        return dbConnection;
    }
}
