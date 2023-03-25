package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        Statement statement;

        String sql = "CREATE TABLE IF NOT EXISTS user6 (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
        "userName VARCHAR(255) NOT NULL," +
        "lastName VARCHAR(30)," +
                "age INT NOT NULL)";

        try {
            statement = connection.createStatement();

            statement.execute(sql);
            System.out.println("Database created!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {

        Statement statement;

        String sql = "DROP TABLE IF EXISTS user6";

        try {
            statement = connection.createStatement();

            statement.executeUpdate(sql);

            System.out.println("Database deleted!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement statement;

        String sql = "INSERT user6 (userName, lastName, age) VALUES (?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        PreparedStatement statement;

        String sql = "DELETE FROM user6 WHERE id=?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM user6";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("userName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {

        PreparedStatement statement;

        String sql = "DELETE FROM user6";

        try {
            statement= connection.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
