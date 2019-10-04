package ru.java.mentor.DAOFactory;

import ru.java.mentor.model.User;
import ru.java.mentor.util.DbHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDAO implements DAO {
    private Connection connection = DbHelper.getJDBCConnection();

    JdbcDAO() {
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long id = result.getLong("id");
                String name = result.getNString("name");
                String surname = result.getNString("login");
                String fatherName = result.getNString("password");
                User user = new User(id, name, surname, fatherName);
                list.add(user);
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users(name, surname, fathername) values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getFathername());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(User user) {
        try {
            String sql = "UPDATE users set name=?, surname=?, fathername=? where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getFathername());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            long id = user.getId();
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            String name = result.getNString("name");
            String surname = result.getNString("surname");
            String fathername = result.getNString("fathername");
            user = new User(id, name, surname, fathername);
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    private User getUserByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name=?");
        preparedStatement.setString(1, name);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        long id = result.getLong("id");
        String surname = result.getNString("surname");
        String fathername = result.getNString("fathername");
        User user = new User(id, surname, surname, fathername);
        result.close();
        preparedStatement.close();
        return user;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment,  name varchar(256),\n" +
                "         surname varchar(256),\n" +
                "         fathername varchar(256)");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
