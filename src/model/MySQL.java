package model;

import java.sql.*;

public class MySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/news_recommendation_system";
    private static final String USER = "root";  // Database username
    private static final String PASSWORD = "12345678";  // Database password
    private static Connection connection;

    static {
        try {
            // Initialize the database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Execute SELECT queries (e.g., for fetching data)
    public static ResultSet executeSelect(String query, Object[] params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        // Set parameters in the query
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement.executeQuery();
    }

    // Execute INSERT, UPDATE, DELETE queries (e.g., for modifying data)
    public static void executeUpdate(String query, Object[] params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        // Set parameters in the query
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        statement.executeUpdate();
    }
}
