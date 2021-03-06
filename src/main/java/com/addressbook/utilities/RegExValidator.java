package com.addressbook.utilities;

import java.util.Scanner;

/**
 * Class checks the inputs and returns proper parameters
 */
public class RegExValidator {
    public static final Scanner input = new Scanner(System.in).useDelimiter("\n");

    /* Set named parameters like First Name, Last Name, City, State */
    public static String setNameParameters(String name) {
        System.out.print("Enter " + name + ": ");
        String param = input.next();
        if (param.matches("^[A-Z][a-z]{2,}$")) {
            return param;
        } else {
            System.out.println("Invalid " + name + " enter again");
            return setNameParameters(name);
        }
    }

    /* Set Address */
    public String setAddress() {
        System.out.print("Enter Address: ");
        String address = input.next();
        if (address.matches("^[A-Z][a-z]{2,}+([ ]?+[a-zA-Z]+)*$")) return address;
        else {
            System.out.println("Invalid address enter again");
            return setAddress();
        }
    }

    /* Set Zip */
    public String setZip() {
        System.out.print("Enter Zip: ");
        String zip = input.next();
        if (zip.matches("^[1-9][0-9]{5,}$")) return zip;
        else {
            System.out.println("Invalid zip enter again");
            return setZip();
        }
    }

    /* Set phone number */
    public String setPhoneNumber() {
        System.out.print("Enter phone number: ");
        String phoneNumber = input.next();
        if (phoneNumber.matches("^[1-9]+[0-9]+[ ]?+[1-9][0-9]{9}$")) return phoneNumber;
        else {
            System.out.println("Invalid phone number enter again");
            return setPhoneNumber();
        }
    }
}
