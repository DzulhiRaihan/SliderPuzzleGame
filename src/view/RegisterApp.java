package view;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RegisterApp {
    JTextField userName;
    JPasswordField passwordText;
    JPasswordField confirmPassword;
    JFrame frame = new JFrame();
    public RegisterApp(){
//        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(400, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setBounds(50, 10, 80, 25);
        panel.add(registerLabel);

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

        passwordText = new JPasswordField();
        passwordText.setBounds(50, 140, 250, 25);
        panel.add(passwordText);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password : ");
        confirmPasswordLabel.setBounds(50, 160, 120, 25);
        panel.add(confirmPasswordLabel);

        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(50, 190, 250, 25);
        panel.add(confirmPassword);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(50, 235, 250, 25);
        registerBtn.setBackground(Color.BLUE);
        registerBtn.setForeground(Color.WHITE);
        panel.add(registerBtn);

        JLabel loginLabel = new JLabel("Already have an account ?");
        loginLabel.setBounds(50, 270, 150, 25);
        loginLabel.setForeground(Color.GRAY);
        panel.add(loginLabel);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(210, 270, 90, 25);
        loginBtn.setForeground(Color.BLUE);
        panel.add(loginBtn);

        frame.setVisible(true);

        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterBtnActionPerformed(evt);
            }
        });

        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });
    }

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        frame.dispose();
        LoginApp loginForm = new LoginApp();
    }
    private void RegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String username, password;
        String SUrl, SUsername, SPassword;

        SUrl = "jdbc:postgresql://db.ingwwupmzptwgnsqxhjg.supabase.co:5432/postgres?user=postgres&password=Kelompok10_";
        SUsername = "postgres";
        SPassword = "Kelompok10_";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUsername, SPassword);
            Statement st = con.createStatement();
            if ("".equals(userName.getText())){
                JOptionPane.showMessageDialog(new JFrame(), "Username tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(passwordText.getText())){
                JOptionPane.showMessageDialog(new JFrame(), "Password tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(confirmPassword.getText())){
                JOptionPane.showMessageDialog(new JFrame(), "Confirm Password tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!passwordText.getText().equals(confirmPassword.getText())){
                JOptionPane.showMessageDialog(new JFrame(), "Password tidak sama", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                username = userName.getText();
                password = passwordText.getText();

                String query = "INSERT INTO \"user\" (username,password) VALUES ('" + username + "', '" + password +"')";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(new JFrame(), "Register Berhasil", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new LoginApp();
            }
            con.close();

        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        RegisterApp regis = new RegisterApp();
    }
}
