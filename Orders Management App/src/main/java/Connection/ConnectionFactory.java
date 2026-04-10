package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Clasa {@code ConnectionFactory} este responsabilă pentru gestionarea conexiunii la baza de date.
 * Aceasta implementează  creearea a unei instanțe de conexiune.
 *
 * Funcționalități principale:
 * <ul>
 *     <li>Crearea unei conexiuni către baza de date MySQL</li>
 *     <li>Închiderea obiectelor de tip {@code Connection}, {@code Statement}, {@code ResultSet}</li>
 * </ul>
 */
public class ConnectionFactory {
    private static final Logger LOGGER= Logger.getLogger(ConnectionFactory.class.getName());
    private static final  String DRIVER="com.mysql.jdbc.Driver";
    private static final  String  DBURL="jdbc:mysql://localhost:3306/warehouse";
    private static final  String  USER="root";
    private static final  String  PASS="CornelSorin2004";
    private static final ConnectionFactory singleInstance = new ConnectionFactory();


    private Connection createConnection() {
        try {
            return DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection failed", e);
        }
        return null;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Failed to close connection", e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Failed to close statement", e);
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Failed to close result set", e);
            }
        }
    }
}
    

