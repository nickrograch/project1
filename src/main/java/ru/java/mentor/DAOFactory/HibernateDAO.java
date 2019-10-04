package ru.java.mentor.DAOFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.java.mentor.model.User;
import ru.java.mentor.util.DbHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HibernateDAO implements DAO {

    private SessionFactory sessionFactory = DbHelper.getSessionFactory();

    public HibernateDAO() {
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> list = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void editUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where id=:id")
                .setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
