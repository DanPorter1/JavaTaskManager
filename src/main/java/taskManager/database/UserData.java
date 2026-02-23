package taskManager.database;

import taskManager.model.User;

import java.sql.*;

import static taskManager.util.DBConnection.getConnection;

public class UserData implements DatabaseActions<User> {

    @Override
    public void insert(User item) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, item.getUsername());
            preparedStatement.setString(2, item.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting user : " + e.getMessage());
        }
    }

    @Override
    public void update(User item) {

    }

    @Override
    public void delete(int id) {

    }

    public void createUserTable(){
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(255) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL
                )""";
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement())
        {
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error Creating User Table : " + e.getMessage());
        }
    }

    public boolean getUserCheck(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
             preparedStatement.setString(1, username);
             try (ResultSet rs = preparedStatement.executeQuery()) {
                 return rs.next();
             }
        } catch (SQLException e) {
            System.err.println("Error getting username : " + e.getMessage());
            return false;
        }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("username"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Login DB Error: " + e.getMessage());
        }
        return null;
    }
}
