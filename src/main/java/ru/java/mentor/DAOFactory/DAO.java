package ru.java.mentor.DAOFactory;

import ru.java.mentor.model.User;

public interface DAO {
    void getAllUsers();
    void addUser(User user);
    void editUser(User user);
    void deleteUser(User user);
}
