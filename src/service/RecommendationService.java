package service;

import java.sql.ResultSet;
import model.MySQL;
import model.News;
import java.util.List;

public class RecommendationService {

    // Recommend news articles based on user preferences and reading history
    public static List<News> getRecommendedArticles(int userId) {
        List<News> recommendedArticles = null;

        try {
            // Get the user's preferences (preferred categories)
            String query = "SELECT category FROM user_preferences WHERE user_id = ?";
            Object[] params = {userId};
            ResultSet rs = MySQL.executeSelect(query, params);

            // For simplicity, assume we recommend news based on the user's most preferred category
            String category = "";
            if (rs.next()) {
                category = rs.getString("category");
            }

            // Fetch articles from the selected category
            recommendedArticles = NewsService.fetchAndStoreNews(category);  // Use the same fetch and store method

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedArticles;
    }
}
