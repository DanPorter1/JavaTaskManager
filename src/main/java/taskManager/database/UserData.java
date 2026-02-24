package taskManager.database;

import taskManager.exception.DataNotSaved;
import taskManager.exception.InvalidPassword;
import taskManager.exception.UsernameAlreadyUsed;
import taskManager.model.User;

import java.sql.*;

import static taskManager.util.DBConnection.getConnection;

public class UserData implements DatabaseActions<User> {

    @Override
    public void insert(User item) throws DataNotSaved, SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, item.getUsername());
            preparedStatement.setString(2, item.getPassword());
            int r = preparedStatement.executeUpdate();
            if (r == 0) {
                throw new DataNotSaved("Failed to save data");
            }
        } catch (SQLException e) {
            throw new SQLException("Error saving data" + e);
        }
    }

    @Override
    public void update(User item) {

    }

    @Override
    public void delete(int id) {

    }

    public void createUserTable() throws SQLException {
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
            throw new SQLException("Failed to create User Table" + e);
        }
    }

    public boolean getUserCheck(String username) throws UsernameAlreadyUsed, SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    throw new UsernameAlreadyUsed("Username already in use please try a different username.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Unable to access data" + e);
        }
        return false;
    }

    public User login(String username, String password) throws InvalidPassword, SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("username"), rs.getString("password"));
                } else {
                    throw new InvalidPassword("Invalid Credentials");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error Accessing DB" + e);
        }
    }
}
