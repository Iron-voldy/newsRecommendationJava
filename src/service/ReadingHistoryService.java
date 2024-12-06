package service;

import model.MySQL;

public class ReadingHistoryService {

    // Insert/update the reading history for a user
    public static void updateReadingHistory(int userId, int articleId, String status) {
        try {
            // Insert the reading history (status can be 'read', 'liked', or 'skipped')
            String insertQuery = "INSERT INTO reading_history (user_id, article_id, status) VALUES (?, ?, ?)";
            Object[] insertParams = {userId, articleId, status};
            MySQL.executeUpdate(insertQuery, insertParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
