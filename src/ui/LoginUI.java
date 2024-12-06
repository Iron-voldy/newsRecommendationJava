package ui;

import javax.swing.*;
import service.UserService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginUI window = new LoginUI();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginUI() {
        initialize();
    }

    private void initialize() {
        setTitle("Login");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (UserService.authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(LoginUI.this, "Login Successful");
                    dispose();
                    new NewsFeedUI().setVisible(true);  // Open News Feed UI
                } else {
                    JOptionPane.showMessageDialog(LoginUI.this, "Invalid Username or Password");
                }
            }
        });
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            dispose();  // Close LoginUI
            new RegisterUI().setVisible(true);  // Open Register UI
        });
        buttonPanel.add(registerButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
