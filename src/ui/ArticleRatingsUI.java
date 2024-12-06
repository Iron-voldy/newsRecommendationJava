package ui;

import javax.swing.*;
import service.ArticleRatingService;
import model.News;
import java.awt.*;
import java.awt.event.ActionListener;

public class ArticleRatingsUI extends JFrame {

    private JComboBox<String> ratingComboBox;
    private JTextField articleIdField;
    
    public ArticleRatingsUI() {
        setTitle("Rate Articles");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel articleIdLabel = new JLabel("Article ID:");
        articleIdField = new JTextField();
        panel.add(articleIdLabel);
        panel.add(articleIdField);

        JLabel ratingLabel = new JLabel("Rating (1-5):");
        ratingComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        panel.add(ratingLabel);
        panel.add(ratingComboBox);

        JButton rateButton = new JButton("Rate Article");
        rateButton.addActionListener(e -> {
            int articleId = Integer.parseInt(articleIdField.getText());
            int rating = Integer.parseInt((String) ratingComboBox.getSelectedItem());
            ArticleRatingService.rateArticle(1, articleId, rating);  // Assume user ID = 1
            JOptionPane.showMessageDialog(this, "Article Rated Successfully!");
        });
        
        add(panel, BorderLayout.CENTER);
        add(rateButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new ArticleRatingsUI().setVisible(true);
    }
}
