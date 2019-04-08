package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static String DB_URL = "jdbc:mysql://localhost:3306/wc-assignment";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";

    public static Connection getConnection() {
        return getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    public static Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
