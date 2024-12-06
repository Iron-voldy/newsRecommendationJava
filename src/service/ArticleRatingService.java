package service;

import java.sql.ResultSet;
import model.MySQL;

public class ArticleRatingService {

    // Insert/update the rating for an article by a user
    public static void rateArticle(int userId, int articleId, int rating) {
        try {
            // Check if the user has already rated the article
            String checkQuery = "SELECT * FROM article_ratings WHERE user_id = ? AND article_id = ?";
            Object[] checkParams = {userId, articleId};
            ResultSet rs = MySQL.executeSelect(checkQuery, checkParams);

            // If the user has already rated, update the rating
            if (rs.next()) {
                String updateQuery = "UPDATE article_ratings SET rating = ? WHERE user_id = ? AND article_id = ?";
                Object[] updateParams = {rating, userId, articleId};
                MySQL.executeUpdate(updateQuery, updateParams);
            } else {
                // If the user hasn't rated, insert a new rating
                String insertQuery = "INSERT INTO article_ratings (user_id, article_id, rating) VALUES (?, ?, ?)";
                Object[] insertParams = {userId, articleId, rating};
                MySQL.executeUpdate(insertQuery, insertParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
