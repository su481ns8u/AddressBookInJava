package com.addressbook.models;

import com.opencsv.bean.CsvBindByName;

/**
 * Class to generate and retrieve person
 */
public class Person {
    // VARIABLES DECLARATIONS
    @CsvBindByName(required = true, column = "FIRST NAME")
    private final String firstName;

    @CsvBindByName(required = true, column = "LAST NAME")
    private final String lastName;

    @CsvBindByName(required = true, column = "ADDRESS")
    private String address;

    @CsvBindByName(required = true, column = "CITY")
    private String city;

    @CsvBindByName(required = true, column = "STATE")
    private String state;

    @CsvBindByName(required = true, column = "ZIP")
    private int zip;

    @CsvBindByName(required = true, column = "MOBILE NO.")
    private String phoneNumber;

    // ONE WHOLE FUNCTION TO SET PERSON
    public Person(String firstName,
                          String lastName,
                          String address,
                          String city,
                          String state,
                          int zip,
                          String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // FUNCTIONS TO GET RECORDS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    // FUNCTION TO OUTPUT RECORDS
    @Override
    public String toString() {
        return "\nFirst Name: " + firstName +
                "\tLast Name: " + lastName +
                "\nAddress: " + address +
                "\nCity: " + city +
                "\tState: " + state +
                "\tZip: " + zip +
                "\nPhone Number: " + phoneNumber;
    }
}