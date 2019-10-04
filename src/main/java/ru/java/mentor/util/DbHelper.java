package ru.java.mentor.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.java.mentor.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static SessionFactory sessionFactory;

    private DbHelper() {}


    public static SessionFactory getSessionFactory()  {
        if (sessionFactory == null) {
            try {
                sessionFactory = createSessionFactory();
            } catch (ReaderException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


    private static Configuration getConfiguration () throws ReaderException {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", PropertyReader.read("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertyReader.read("driver"));
        configuration.setProperty("hibernate.connection.url", PropertyReader.read("url"));
        configuration.setProperty("hibernate.connection.username", PropertyReader.read("username"));
        configuration.setProperty("hibernate.connection.password", PropertyReader.read("password"));
        configuration.setProperty("hibernate.show_sql", PropertyReader.read("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertyReader.read("hbm2ddl"));
        return configuration;
    }

    private static SessionFactory createSessionFactory() throws ReaderException {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getJDBCConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName(PropertyReader.read("driver")).newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append(PropertyReader.read("url")).
                    append(PropertyReader.read("username")).
                    append(PropertyReader.read("password"));

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | ReaderException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
