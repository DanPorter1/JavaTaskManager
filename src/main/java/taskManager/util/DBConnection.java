package taskManager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:h2:file:./db/tempDB";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to DB" + conn.getMetaData().getURL());
            }
        } catch (SQLException e) {
            System.err.println("Error Connecting to DB: " + e.getMessage());
            System.exit(1);
        }
    }
}
