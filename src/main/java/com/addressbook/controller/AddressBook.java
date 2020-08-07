package com.addressbook.controller;

import com.addressbook.exceptions.AddressBookException;
import com.addressbook.services.AddressBookServiceDataBaseIO;
import com.addressbook.services.AddressBookServiceFileIO;
import com.addressbook.services.AddressBookServiceJsonServerIO;
import com.addressbook.utilities.RegExValidator;

import static com.addressbook.exceptions.AddressBookException.ExceptionType.INVALID_CHOICE;

public class AddressBook {
    public static void main(String[] args) throws AddressBookException {
        System.out.print("Enter Choice to select method:" +
                "\n1. Using File IO" +
                "\n2. Using Json-server IO" +
                "\n3. Using mySQL DataBase IO" +
                "\nChoice: ");
        int choice = RegExValidator.input.nextInt();
        switch (choice) {
            case 1:
                new AddressBookServiceFileIO();
            case 2:
                new AddressBookServiceJsonServerIO();
            case 3:
                new AddressBookServiceDataBaseIO();
            default:
                throw new AddressBookException(INVALID_CHOICE);
        }
    }
}
