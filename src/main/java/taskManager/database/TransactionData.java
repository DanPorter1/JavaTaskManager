package taskManager.database;

import taskManager.exception.DataNotSaved;
import taskManager.model.Transaction;

import java.sql.*;

import static taskManager.util.DBConnection.getConnection;

public class TransactionData  implements DatabaseActions<Transaction> {

    public void createTransactionTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS transactions (
                 tid INT AUTO_INCREMENT PRIMARY KEY,
                 tx_type VARCHAR(255) NOT NULL,
                 amount FLOAT NOT NULL,
                 description VARCHAR(255) NOT NULL,
                 date DATETIME )""";

        try (Connection conn = getConnection();
                Statement statement = conn.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new SQLException("Failed to create transaction table" + e);
        }
    }

    @Override
    public void insert(Transaction t) throws SQLException, DataNotSaved {
        String sql = "INSERT INTO transactions (tx_type, amount, description, date VALUES (?,?,?,?)";
        try (Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql) )
        {
            preparedStatement.setString(1, t.getType().name());
            preparedStatement.setDouble(2, t.getAmount());
            preparedStatement.setString(3, t.getReference());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(t.getDate()));
            int r = preparedStatement.executeUpdate();
            if (r == 0) {
                throw new DataNotSaved("Unable to save transaction data");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void update(Transaction t) {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

}
