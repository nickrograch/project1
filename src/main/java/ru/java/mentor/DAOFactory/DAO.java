package ru.java.mentor.DAOFactory;

import ru.java.mentor.model.User;

import java.util.List;

public interface DAO {
    List<User> getAllUsers();
    void addUser(User user);
    void editUser(long id, String name, String surname, String fathername);
    void deleteUser(long id);
    User getUserById(long id);
}
