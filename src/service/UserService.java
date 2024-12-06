package service;

import model.MySQL;
import java.sql.ResultSet;

public class UserService {

    // Authenticate user during login
    public static boolean authenticateUser(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            Object[] params = {username, password};
            ResultSet rs = MySQL.executeSelect(query, params);
            return rs.next();  // If a record exists, authentication is successful
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Register a new user
    public static boolean registerUser(String username, String password) {
        try {
            // Check if the username already exists
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            Object[] checkParams = {username};
            ResultSet rs = MySQL.executeSelect(checkQuery, checkParams);

            if (rs.next()) {
                return false;  // Username already exists
            }

            // Insert the new user into the database
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            Object[] insertParams = {username, password};
            MySQL.executeUpdate(insertQuery, insertParams);

            return true;  // Registration successful
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Save user preferences (category and preference score)
    public static void saveUserPreferences(int userId, String category, int preferenceScore) {
        try {
            // Check if preference already exists
            String checkQuery = "SELECT * FROM user_preferences WHERE user_id = ? AND category = ?";
            Object[] checkParams = {userId, category};
            ResultSet rs = MySQL.executeSelect(checkQuery, checkParams);

            // If preference exists, update it; otherwise, insert new preference
            if (rs.next()) {
                String updateQuery = "UPDATE user_preferences SET preference_score = ? WHERE user_id = ? AND category = ?";
                Object[] updateParams = {preferenceScore, userId, category};
                MySQL.executeUpdate(updateQuery, updateParams);
            } else {
                String insertQuery = "INSERT INTO user_preferences (user_id, category, preference_score) VALUES (?, ?, ?)";
                Object[] insertParams = {userId, category, preferenceScore};
                MySQL.executeUpdate(insertQuery, insertParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
