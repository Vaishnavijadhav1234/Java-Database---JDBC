import java.sql.*;

public class TestConnection{
    public static void main(String[] args) {
        try {
            // Driver load
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb", "root", "1234");

            System.out.println("Connected Successfully ✅");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}