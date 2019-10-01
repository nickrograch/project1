package ru.java.mentor.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.java.mentor.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static SessionFactory sessionFactory;

    private DbHelper() {}


    public static SessionFactory getSessionFactory() throws IOException {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }


    private static Configuration getConfiguration () throws IOException {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", PropertyReader.getInstacne().read("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertyReader.getInstacne().read("driver"));
        configuration.setProperty("hibernate.connection.url", PropertyReader.getInstacne().read("url"));
        configuration.setProperty("hibernate.connection.username", PropertyReader.getInstacne().read("username"));
        configuration.setProperty("hibernate.connection.password", PropertyReader.getInstacne().read("password"));
        configuration.setProperty("hibernate.show_sql", PropertyReader.getInstacne().read("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertyReader.getInstacne().read("hbm2ddl"));
        return configuration;
    }

    private static SessionFactory createSessionFactory() throws IOException {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getJDBCConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName(PropertyReader.getInstacne().read("driver")).newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append(PropertyReader.getInstacne().read("url")).
                    append(PropertyReader.getInstacne().read("username")).
                    append(PropertyReader.getInstacne().read("password"));

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
