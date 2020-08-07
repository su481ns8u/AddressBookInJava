package com.addressbook.services;

import com.addressbook.dbconnection.DBConnection;
import com.addressbook.exceptions.AddressBookException;
import com.addressbook.utilities.RegExValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddressBookServiceDataBaseIO implements IAddressBookService {
    RegExValidator regExValidator = new RegExValidator();
    Scanner input = new Scanner(System.in);

    public AddressBookServiceDataBaseIO() {
        this.startPoint();
    }

    @Override
    public void addPerson() {
        String query = "INSERT INTO `address_book`.`person`" +
                "(`firstName`,`lastName`,`address`,`city`,`state`,`zip`,`mobile`)" +
                "VALUES(?,?,?,?,?,?,?);";
        String firstName = RegExValidator.setNameParameters("First Name");
        String lastName = RegExValidator.setNameParameters("Last Name");
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            if (!checkExist(firstName, lastName, connection)) {
                statement.setString(3, regExValidator.setAddress());
                statement.setString(4, RegExValidator.setNameParameters("City"));
                statement.setString(5, RegExValidator.setNameParameters("State"));
                statement.setString(6, regExValidator.setZip());
                statement.setString(7, regExValidator.setPhoneNumber());
                statement.execute();
            } else System.out.println("Record already exists !!!");
            statement.close();
            connection.close();
        } catch (SQLException | AddressBookException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editPerson() {
        try {
            Connection connection = DBConnection.getConnection();
            String firstName = RegExValidator.setNameParameters("First Name");
            String lastName = RegExValidator.setNameParameters("Last Name");
            if (checkExist(firstName, lastName, connection)) {
                String field = null;
                String value = null;
                System.out.print("\n\tEnter edit choice" +
                        "\n\t1. Address" +
                        "\n\t2. City" +
                        "\n\t3. State" +
                        "\n\t4. Zip" +
                        "\n\t5. Phone Number" +
                        "\n\tChoice");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        field = "address";
                        value = regExValidator.setAddress();
                        break;
                    case 2:
                        field = "city";
                        value = RegExValidator.setNameParameters("City");
                        break;
                    case 3:
                        field = "state";
                        value = RegExValidator.setNameParameters("State");
                        break;
                    case 4:
                        field = "zip";
                        value = regExValidator.setZip();
                        break;
                    case 5:
                        field = "mobile";
                        value = regExValidator.setPhoneNumber();
                        break;
                    default:
                        System.out.println("Wrong Choice !!!");
                }
                connection.prepareStatement("UPDATE address_book.person SET " + field + " = '" + value + "' " +
                        "WHERE firstName = '" + firstName + "' AND lastName ='" + lastName + "';").executeUpdate();
            } else System.out.println("Person Does Not Exist !!!");
            connection.close();
        } catch (SQLException | AddressBookException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deletePerson() {
        try {
            Connection connection = DBConnection.getConnection();
            String firstName = RegExValidator.setNameParameters("First Name");
            String lastName = RegExValidator.setNameParameters("Last Name");
            if (checkExist(firstName, lastName, connection)) {
                connection.prepareStatement("DELETE FROM address_book.person WHERE " +
                        "firstName = '" + firstName + "' AND lastName = '" + lastName + "';")
                        .executeUpdate();
                System.out.println("Deletion Successful !!!");
            } else System.out.println("No such record found !!!");
            connection.close();
        } catch (SQLException | AddressBookException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void searchByCityAndState(String city, String state) {
        this.setQueryAndPrint("SELECT * FROM address_book.person " +
                "WHERE city = '" + city + "' AND state = '" + state + "';");
    }

    @Override
    public void searchByCityOrState(int choice) {
        String key = null;
        String value = null;
        switch (choice) {
            case 1:
                key = "city";
                value = RegExValidator.setNameParameters("City");
                break;
            case 2:
                key = "state";
                value = RegExValidator.setNameParameters("State");
                break;
            default:
                System.out.println("Wrong choice !!!");
        }
        this.setQueryAndPrint("SELECT * FROM address_book.person WHERE " + key + " = '" + value + "';");
    }

    @Override
    public void sort(int choice) {
        String sortColumn = null;
        switch (choice) {
            case 1:
                sortColumn = "firstName & lastName";
                break;
            case 2:
                sortColumn = "city";
                break;
            case 3:
                sortColumn = "state";
                break;
            case 4:
                sortColumn = "zip";
                break;
            default:
                System.out.println("Invalid choice !!!");
                break;
        }
        setQueryAndPrint("SELECT * FROM address_book.person order by " + sortColumn + " asc;");
    }

    @Override
    public void displayRecords() {
        this.setQueryAndPrint("SELECT * FROM address_book.person");
    }

    private boolean checkExist(String firstName, String lastName, Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM address_book.person WHERE " +
                "firstName='" + firstName + "' AND lastName='" + lastName + "';").executeQuery();
        return resultSet.next();
    }

    private void setQueryAndPrint(String query) {
        try {
            Connection connection = DBConnection.getConnection();
            ResultSet resultSet = connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery();
            int rows = 0;
            if (resultSet.last()) {
                rows = resultSet.getRow();
                resultSet.beforeFirst();
            }
            if (rows != 0) while (resultSet.next())
                System.out.println("\nFirst Name: " + resultSet.getString("firstName") +
                        " Last Name: " + resultSet.getString("lastName") +
                        "\nAddress: " + resultSet.getString("address") +
                        " City: " + resultSet.getString("city") +
                        " State: " + resultSet.getString("state") +
                        "\nZip: " + resultSet.getString("zip") +
                        " Mobile No.: " + resultSet.getString("mobile"));
            else System.out.println("No such records exist !!!");
            connection.close();
        } catch (SQLException | AddressBookException throwables) {
            throwables.printStackTrace();
        }
    }
}
