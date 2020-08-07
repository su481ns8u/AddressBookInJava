package com.addressbook.dbconnection;

import com.addressbook.exceptions.AddressBookException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.addressbook.exceptions.AddressBookException.ExceptionType.DB_CONNECTION_ERROR;

public class DBConnection {
    static String URL = "jdbc:mysql://localhost:3306/address_book?autoReconnect=true&useSSL=false";
    static String USER = "root";
    static String Password = "asap@Shashank";

    public static Connection getConnection() throws AddressBookException {
        try {
            return DriverManager.getConnection(URL, USER, Password);
        } catch (SQLException e) {
            throw new AddressBookException(DB_CONNECTION_ERROR);
        }
    }
}
