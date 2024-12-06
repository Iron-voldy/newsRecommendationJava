package ui;

import javax.swing.*;
import service.UserService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegisterUI window = new RegisterUI();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RegisterUI() {
        initialize();
    }

    private void initialize() {
        setTitle("Register");
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

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (UserService.registerUser(username, password)) {
                    JOptionPane.showMessageDialog(RegisterUI.this, "Registration Successful");
                    dispose();
                    new LoginUI().setVisible(true);  // Go back to Login UI
                } else {
                    JOptionPane.showMessageDialog(RegisterUI.this, "Username already exists!");
                }
            }
        });
        getContentPane().add(registerButton, BorderLayout.SOUTH);

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            dispose();  // Close RegisterUI
            new LoginUI().setVisible(true);  // Open Login UI
        });
        getContentPane().add(backButton, BorderLayout.NORTH);
    }
}
