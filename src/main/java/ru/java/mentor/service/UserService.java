package ru.java.mentor.service;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.java.mentor.DAOFactory.AbstractDAOFactory;
import ru.java.mentor.DAOFactory.DAO;
import ru.java.mentor.model.User;
import ru.java.mentor.util.DbHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {

    private static UserService userService;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() throws IOException {
        if (userService == null) {
            userService = new UserService(DbHelper.getSessionFactory());
        }
        return userService;
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> list = null;
        try{
            DAO dao = AbstractDAOFactory.getDAO(session);
            list =  dao.getAllUsers();
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            session.getTransaction().rollback();
            session.close();
        }
        return list;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DAO dao = AbstractDAOFactory.getDAO(session);
        dao.addUser(user);
        session.getTransaction().commit();
        session.close();
    }

    public void editUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DAO dao = AbstractDAOFactory.getDAO(session);
        dao.editUser(user);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DAO dao = AbstractDAOFactory.getDAO(session);
        dao.deleteUser(user);
        session.getTransaction().commit();
        session.close();
    }

    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DAO dao = AbstractDAOFactory.getDAO(session);
        User user = dao.getUserById(id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

}
