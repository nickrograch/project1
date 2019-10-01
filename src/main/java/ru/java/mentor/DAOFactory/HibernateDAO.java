package ru.java.mentor.DAOFactory;

import org.hibernate.Session;
import ru.java.mentor.model.User;

public class HibernateDAO implements DAO {

    private Session session;

    public HibernateDAO(Session session) {
        this.session = session;
    }
    @Override
    public void getAllUsers() {

    }

    @Override
    public void addUser(User user) {
        session.save(new User())
    }

    @Override
    public void editUser() {

    }

    @Override
    public void deleteUser() {

    }
}
