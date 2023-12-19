package view;

import javax.swing.*;

import API.JDBC;
import API.Login;

import java.awt.*;

public class LoginApp {
    private JTextField userName;
    private JPasswordField password;
    private JFrame frame = new JFrame();

    public LoginApp() {
        new JDBC();
        JPanel panel = new JPanel();
        // JFrame frame = new JFrame();
        frame.setSize(360, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);  
        frame.setLocationRelativeTo(null);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setBounds(50, 10, 80, 25);
        panel.add(loginLabel);

        JLabel welcomeLabel = new JLabel("Please Enter Your Details");
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        welcomeLabel.setBounds(50, 35, 150, 25);
        panel.add(welcomeLabel);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(50, 60, 80, 25);
        panel.add(userLabel);

        userName = new JTextField(20);
        userName.setBounds(50, 90, 250, 25);
        panel.add(userName);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(50, 110, 80, 25);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(50, 140, 250, 25);
        panel.add(password);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 190, 250, 25);
        loginBtn.setBackground(Color.BLUE);
        loginBtn.setForeground(Color.WHITE);
        panel.add(loginBtn);

        JLabel signUpLabel = new JLabel("not have an account ?");
        signUpLabel.setBounds(50, 220, 150, 25);
        signUpLabel.setForeground(Color.GRAY);
        panel.add(signUpLabel);

        JButton signUpBtn = new JButton("Register Here");
        signUpBtn.setForeground(Color.BLUE);
        signUpBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        signUpBtn.setBounds(190, 220, 110, 25);
        panel.add(signUpBtn);

        frame.setVisible(true);

        loginBtn.addActionListener(e -> {
            String user = userName.getText();
            String pass = String.valueOf(password.getPassword());
            try{
                Login.login(user, pass);
                if (Login.isLogin){
                    new MenuPage();
                    frame.dispose();
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
            cleanForm();
        });


        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtn.doClick();
            }
        });

        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBtnActionPerformed(evt);
            }
        });
    }

    private void cleanForm() {
        userName.setText("");
        password.setText("");
    }

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new RegisterApp();
        frame.dispose();
    }
    

}
