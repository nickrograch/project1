package ru.java.mentor.DAOFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.java.mentor.model.User;

import java.util.ArrayList;
import java.util.List;

public class HibernateDAO implements DAO {

    private Session session;

    HibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        Query query = session.createQuery("from User");
        List<User> list = new ArrayList<>(query.list());
        return list;
    }

    @Override
    public void addUser(User user) {
        session.save(user);
    }

    @Override
    public void editUser(User user) {
        session.update(user);
    }

    @Override
    public void deleteUser(User user) {
        session.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        Query query = session.createQuery("from User where id=:id")
                .setParameter("id", id);
        User user = (User) query.uniqueResult();
        return user;
    }
}
