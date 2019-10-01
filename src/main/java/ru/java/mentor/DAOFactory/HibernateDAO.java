package ru.java.mentor.DAOFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.java.mentor.model.User;
import ru.java.mentor.util.DbHelper;

import java.io.IOException;
import java.util.List;

public class HibernateDAO implements DAO {

    private SessionFactory sessionFactory = DbHelper.getSessionFactory();

    public HibernateDAO() throws IOException {

    }
    @Override
    public List<User> getAllUsers() {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User");
            List<User> list = (List<User>)query.list();
            session.getTransaction().commit();
            session.clear();
            return list;
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    @Override
    public void addUser(User user) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(user.getName(), user.getSurname(), user.getFatherName()));
            session.getTransaction().commit();
            session.clear();
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    @Override
    public void editUser(long id, String name, String surname, String fathername) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = getUserById(id);
            user.setName(name);
            user.setSurname(surname);
            user.setFatherName(fathername);
            session.update(user);
            session.getTransaction().commit();
            session.clear();
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    @Override
    public void deleteUser(long id) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("From User where id = :id");
            query.setParameter("id", id);
            List list = query.list();
            session.delete(list.get(0));
            session.getTransaction().commit();
            session.clear();
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    @Override
    public User getUserById(long id) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Car where id = :id");
            query.setParameter("id", id);
            List list = query.list();
            User user = (User)list.get(0);
            session.getTransaction().commit();
            session.clear();
            return user;
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }
}
