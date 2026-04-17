import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationPage extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JComboBox<String> roleBox;
    JButton registerBtn, backBtn;

    RegistrationPage() {
        setTitle("Register ");
        setSize(400,320);
        setLayout(null);
        getContentPane().setBackground(new Color(30,30,30));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(30,30,320,240);
        panel.setBackground(new Color(255,248,220));
        add(panel);

        JLabel title = new JLabel("Register ");
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

        registerBtn = new JButton("Register Shop");
        registerBtn.setBackground(new Color(212,175,55));
        registerBtn.setBounds(40,190,100,30);
        panel.add(registerBtn);

        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(212,175,55));
        backBtn.setBounds(170,190,100,30);
        panel.add(backBtn);

        registerBtn.addActionListener(this);
        backBtn.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backBtn) {
            this.dispose();
        }

        if (e.getSource() == registerBtn) {

            try {
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jewellerydb","root","1234");

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username,password,role) VALUES(?,?,?)");

                ps.setString(1, userField.getText());
                ps.setString(2, new String(passField.getPassword()));
                ps.setString(3, roleBox.getSelectedItem().toString());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,"Registered Successfully ");

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}