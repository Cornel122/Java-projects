package Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/lant_farmacii?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "CornelSorin2004";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL nu a fost găsit!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Conexiunea la baza de date a eșuat!");
            e.printStackTrace();
            return null;
        }
    }
}