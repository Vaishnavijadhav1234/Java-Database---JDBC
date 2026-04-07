import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JComboBox<String> roleBox;
    JButton loginBtn, registerBtn;

    LoginPage() {
        setTitle("Jewellery Shop Login ");
        setSize(400,320);
        setLayout(null);
        getContentPane().setBackground(new Color(30,30,30));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(30,30,320,240);
        panel.setBackground(new Color(255,248,220));
        add(panel);

        JLabel title = new JLabel("Login ");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBounds(120,10,150,30);
        panel.add(title);

        panel.add(new JLabel("Username:")).setBounds(20,60,100,25);
        userField = new JTextField();
        userField.setBounds(120,60,160,25);
        panel.add(userField);

        panel.add(new JLabel("Password:")).setBounds(20,100,100,25);
        passField = new JPasswordField();
        passField.setBounds(120,100,160,25);
        panel.add(passField);

        panel.add(new JLabel("Role:")).setBounds(20,140,100,25);
        roleBox = new JComboBox<>(new String[]{"Admin","Staff"});
        roleBox.setBounds(120,140,160,25);
        panel.add(roleBox);

        loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(212,175,55));
        loginBtn.setBounds(40,190,100,30);
        panel.add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(212,175,55));
        registerBtn.setBounds(170,190,100,30);
        panel.add(registerBtn);

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();
            String role = roleBox.getSelectedItem().toString();

            try {
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jewellerydb","root","1234");

                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=? AND role=?");

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this,"Login Successful ");

                    userField.setText("");
                    passField.setText("");

                    // OPEN DASHBOARD
                    new DashboardPage();

                } else {
                    JOptionPane.showMessageDialog(this,"Invalid Details ");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        if (e.getSource() == registerBtn) {
            new RegistrationPage();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}