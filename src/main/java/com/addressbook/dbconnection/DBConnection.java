package com.addressbook.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String URL = "jdbc:mysql://localhost:3306/address_book?autoReconnect=true&useSSL=false";
    static String USER = "root";
    static String Password = "asap@Shashank";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, Password);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
