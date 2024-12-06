package ui;

import javax.swing.*;
import service.UserPreferencesService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPreferencesUI extends JFrame {

    private JComboBox<String> categoryComboBox;

    public UserPreferencesUI() {
        setTitle("User Preferences");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        JLabel categoryLabel = new JLabel("Select Preferred Category:");
        categoryComboBox = new JComboBox<>(new String[]{"Technology", "Health", "Sports", "General"});
        panel.add(categoryLabel);
        panel.add(categoryComboBox);
        add(panel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Preferences");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                UserPreferencesService.saveUserPreferences(1, selectedCategory, 5);  // Example: Save for user ID 1
                JOptionPane.showMessageDialog(UserPreferencesUI.this, "Preferences Saved!");
            }
        });
        add(saveButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new UserPreferencesUI().setVisible(true);
    }
}
