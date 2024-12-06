package ui;

import javax.swing.*;
import service.ReadingHistoryService;
import model.MySQL;
import java.awt.*;
import java.sql.ResultSet;

public class ReadingHistoryUI extends JFrame {

    private JTextArea textArea;

    public ReadingHistoryUI() {
        setTitle("Reading History");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and display reading history
        fetchAndDisplayHistory();

        JButton backButton = new JButton("Back to News Feed");
        backButton.addActionListener(e -> {
            dispose();
            new NewsFeedUI().setVisible(true);  // Go back to News Feed UI
        });
        add(backButton, BorderLayout.SOUTH);
    }

    private void fetchAndDisplayHistory() {
        try {
            String query = "SELECT * FROM reading_history WHERE user_id = ?";
            Object[] params = {1};  // Assuming user_id = 1 for now
            ResultSet rs = MySQL.executeSelect(query, params);

            textArea.setText("");  // Clear previous content

            while (rs.next()) {
                String articleTitle = rs.getString("article_id"); // You can join with `news` table for titles
                String status = rs.getString("status");
                textArea.append("Article: " + articleTitle + " | Status: " + status + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching reading history!");
        }
    }

    public static void main(String[] args) {
        new ReadingHistoryUI().setVisible(true);
    }
}
