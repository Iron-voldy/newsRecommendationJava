package util;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

public class APIUtil {

    // Method to fetch news articles from NewsAPI based on the category
    public static String fetchNews(String category) throws Exception {
        String apiKey = "21fd696dddb44bc99073f62ceb8597f9";  // Replace with your NewsAPI key
        String urlString = "https://newsapi.org/v2/top-headlines?country=us&category=" + category + "&apiKey=" + apiKey;

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP GET Request Failed with Code: " + connection.getResponseCode());
        }

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }

        bufferedReader.close();
        reader.close();

        return response.toString();
    }
}
