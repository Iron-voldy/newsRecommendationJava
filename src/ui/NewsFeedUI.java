package ui;

import javax.swing.*;
import service.ReadingHistoryService;
import model.News;
import java.awt.*;
import java.util.List;
import service.NewsService;

public class NewsFeedUI extends JFrame {

    private JTextArea textArea;
    private JComboBox<String> categoryComboBox;
    private JPanel buttonPanel;  // Panel to hold the buttons for each article

    public NewsFeedUI() {
        setTitle("News Feed");
        setSize(600, 600);  // Increased height to accommodate more content
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Category ComboBox
        String[] categories = {"Technology", "Health", "Sports"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setSelectedIndex(0);  // Default to Technology

        JButton fetchButton = new JButton("Fetch News");
        fetchButton.addActionListener(e -> fetchAndDisplayNews((String) categoryComboBox.getSelectedItem()));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Category:"));
        topPanel.add(categoryComboBox);
        topPanel.add(fetchButton);
        add(topPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
 
        // Panel to hold buttons for interactions with articles
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));  // Arrange buttons vertically

        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
        buttonScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(buttonScrollPane, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> System.exit(0));  // Exit the application
        bottomPanel.add(logoutButton);

        // Button to open UserPreferencesUI
        JButton preferencesButton = new JButton("Preferences");
        preferencesButton.addActionListener(e -> {
            dispose();  // Close current NewsFeedUI
            new UserPreferencesUI().setVisible(true);  // Open User Preferences UI
        });
        bottomPanel.add(preferencesButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Fetch news by default for "Technology" category
        fetchAndDisplayNews("Technology");
    }

    private void fetchAndDisplayNews(String category) {
        try {
            List<News> articles = NewsService.fetchAndStoreNews(category);
            textArea.setText("");  // Clear previous articles
            buttonPanel.removeAll();  // Clear previous buttons

            // Loop through articles and add them to the display
            for (News article : articles) {
                textArea.append("Title: " + article.getTitle() + "\n");
                textArea.append("Description: " + article.getDescription() + "\n\n");

                // Create buttons for each article
                JPanel articleButtonsPanel = new JPanel();
                JButton readButton = new JButton("Read");
                readButton.addActionListener(e -> ReadingHistoryService.updateReadingHistory(1, article.getId(), "read"));
                articleButtonsPanel.add(readButton);

                JButton likeButton = new JButton("Like");
                likeButton.addActionListener(e -> ReadingHistoryService.updateReadingHistory(1, article.getId(), "liked"));
                articleButtonsPanel.add(likeButton);

                JButton skipButton = new JButton("Skip");
                skipButton.addActionListener(e -> ReadingHistoryService.updateReadingHistory(1, article.getId(), "skipped"));
                articleButtonsPanel.add(skipButton);

                // Add the article buttons panel to the main buttonPanel
                buttonPanel.add(articleButtonsPanel);

                // Add some space between the sets of buttons
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }

            // Revalidate and repaint to show the updated buttons
            buttonPanel.revalidate();
            buttonPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching or displaying news!");
        }
    }

    public static void main(String[] args) {
        new NewsFeedUI().setVisible(true);
    }
}
