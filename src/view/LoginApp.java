package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class LoginApp {
    JTextField userName;
    JPasswordField password;
    JFrame frame = new JFrame();

    public LoginApp() {
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

        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
                cleanForm();
            }
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

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String username, passwordDB, query, passDB = null;
        String SUrl, SUsername, SPassword;

        SUrl = "jdbc:postgresql://db.guyatknjmkqoagpnwepe.supabase.co:5432/postgres?user=postgres&password=DzulhiRaihan02";
        SUsername = "postgres";
        SPassword = "DzulhiRaihan02";
        int notFound = 0;

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUsername, SPassword);
            Statement st = con.createStatement();
            if ("".equals(userName.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Username tidak boleh kosong", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(password.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password tidak boleh kosong", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                username = userName.getText();
                passwordDB = password.getText();

                query = "SELECT * FROM \"user\" WHERE username = '" + username + "' AND password = '" + passwordDB
                        + "'";

                // String query2 = "SELECT \"score\".score " +
                // "FROM \"score\" " +
                // "INNER JOIN \"user\" ON \"score\".id_user = \"user\".id_user " +
                // "WHERE username = '" + username + "' AND password = '" + passwordDB + "'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    passDB = rs.getString("password");
                    notFound = 1;
                }
                if (notFound == 1 && Objects.equals(passwordDB, passDB)) {
                    JOptionPane.showMessageDialog(new JFrame(), "Login Berhasil", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    // ResultSet rs2 = st.executeQuery(query2);
                    // while (rs2.next()){
                    // int score = rs2.getInt("score");
                    // }
                    frame.dispose();
                    Menu mainContent = new Menu();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Username atau Password salah", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private void cleanForm() {
        userName.setText("");
        password.setText("");
    }

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {
        RegisterApp registerForm = new RegisterApp();
        frame.dispose();
    }

    public static void main(String[] args) {
        LoginApp loginForm = new LoginApp();

    }
}
