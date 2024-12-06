package service;

import java.sql.ResultSet;
import model.MySQL;

public class UserPreferencesService {

    // Insert or update the user preferences for a category
    public static void saveUserPreferences(int userId, String category, int preferenceScore) {
        try {
            // Check if the user already has a preference for this category
            String checkQuery = "SELECT * FROM user_preferences WHERE user_id = ? AND category = ?";
            Object[] checkParams = {userId, category};
            ResultSet rs = MySQL.executeSelect(checkQuery, checkParams);

            // If the preference exists, update it
            if (rs.next()) {
                String updateQuery = "UPDATE user_preferences SET preference_score = ? WHERE user_id = ? AND category = ?";
                Object[] updateParams = {preferenceScore, userId, category};
                MySQL.executeUpdate(updateQuery, updateParams);
            } else {
                // Otherwise, insert a new preference
                String insertQuery = "INSERT INTO user_preferences (user_id, category, preference_score) VALUES (?, ?, ?)";
                Object[] insertParams = {userId, category, preferenceScore};
                MySQL.executeUpdate(insertQuery, insertParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
