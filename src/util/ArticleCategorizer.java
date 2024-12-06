package util;

public class ArticleCategorizer {

    // Basic function to categorize articles based on keywords
    public static String categorizeArticle(String content) {
        if (content.contains("technology") || content.contains("AI")) {
            return "Technology";
        } else if (content.contains("health") || content.contains("medicine")) {
            return "Health";
        } else if (content.contains("sports")) {
            return "Sports";
        } else {
            return "General";  // Default category
        }
    }
}
