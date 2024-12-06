package service;

import java.sql.ResultSet;
import model.MySQL;
import model.News;
import util.APIUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

public class NewsService {  // Make sure it's public

    // Fetch news articles from NewsAPI, store them in the database, and return the articles
    public static List<News> fetchAndStoreNews(String category) {
        List<News> newsList = new ArrayList<>();

        try {
            // Fetch news articles from NewsAPI
            String jsonResponse = APIUtil.fetchNews(category);  // This should return a JSON string

            // Parse the response
            JSONObject responseObject = new JSONObject(jsonResponse);
            JSONArray articles = responseObject.getJSONArray("articles");

            // Loop through articles and store in the database, also add to the list
            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                String title = article.getString("title");
                String description = article.optString("description", "");  // Use optString to avoid null
                String url = article.getString("url");

                // Insert the article into the database
                String insertQuery = "INSERT INTO news (title, description, url, category) VALUES (?, ?, ?, ?)";
                Object[] insertParams = {title, description, url, category};
                MySQL.executeUpdate(insertQuery, insertParams);

                // Fetch the inserted article's ID (assuming the auto-generated ID is used)
                String selectQuery = "SELECT id FROM news WHERE title = ? AND url = ?";
                Object[] selectParams = {title, url};
                ResultSet rs = MySQL.executeSelect(selectQuery, selectParams);
                int articleId = 0;
                if (rs.next()) {
                    articleId = rs.getInt("id");
                }

                // Add the article to the list of articles with the fetched ID
                News news = new News(articleId, title, description, url, category);
                newsList.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
