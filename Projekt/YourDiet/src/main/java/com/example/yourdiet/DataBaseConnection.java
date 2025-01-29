package com.example.yourdiet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost/yourdiet";
    private static final String USER = "user";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        Connection databaselink = null;
        try {
            databaselink = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączenie z bazą danych nawiązane pomyślnie.");
        } catch (SQLException e) {
            System.err.println("Nie można nawiązać połączenia z bazą danych.");
            e.printStackTrace();
        }
        return databaselink;
    }
}
