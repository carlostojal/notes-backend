package com.carlostojal.notesbackend.DBControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/notes", "carlostojal", "password123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
}
