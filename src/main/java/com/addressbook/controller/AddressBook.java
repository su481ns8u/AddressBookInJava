package com.addressbook.controller;

import com.addressbook.services.AddressBookServiceDBIO;
import com.addressbook.services.AddressBookServiceFileIO;
import com.addressbook.services.AddressBookServiceJsonServerIO;

import java.util.Scanner;

import static java.lang.System.exit;

public class AddressBook {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Choice to select method:" +
                "\n1. Using File IO" +
                "\n2.Using Json-server IO" +
                "\n3. Using Data Base IO");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                new AddressBookServiceFileIO();
            case 2:
                new AddressBookServiceJsonServerIO();
            case 3:
                new AddressBookServiceDBIO();
            default:
                System.out.println("Invalid Choice !");
                exit(0);
        }
    }
}
