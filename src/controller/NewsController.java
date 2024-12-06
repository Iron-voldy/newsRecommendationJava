package controller;

import service.RecommendationService;
import model.NewsArticle;
import javax.swing.*;
import java.util.List;

public class NewsController {

    private RecommendationService recommendationService = new RecommendationService();

    // Method to fetch and display recommended articles for a user
    public void showRecommendedArticles(int userId) {
        // Get the recommended articles based on user preferences
        List<NewsArticle> articles = recommendationService.getRecommendationsForUser(userId);

        // Display the articles in a new window
        StringBuilder articlesText = new StringBuilder("Recommended Articles:\n");
        for (NewsArticle article : articles) {
            articlesText.append(article.getTitle()).append("\n");
        }
        
        // Show the list of articles using JOptionPane
        JOptionPane.showMessageDialog(null, articlesText.toString());
    }

    // Method to handle fetching and displaying a specific article (for detailed view)
    public void showArticleDetails(int articleId) {
        // Fetch article by ID (this would be done through the database or service)
        NewsArticle article = recommendationService.getArticleById(articleId);

        // Display article details
        String articleDetails = "Title: " + article.getTitle() + "\n"
                + "Content: " + article.getContent() + "\n"
                + "Published on: " + article.getPublishedDate();
        
        JOptionPane.showMessageDialog(null, articleDetails);
    }
}
