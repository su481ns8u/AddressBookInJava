package com.su481ns8u.AddressBook.services;

import com.su481ns8u.AddressBook.models.Person;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class checks the inputs and returns proper parameters
 */
public class CheckAndReturnParameters {
    Scanner input = new Scanner(System.in).useDelimiter("\n");

    /* Set named parameters like First Name, Last Name, City, State */
    public String setNameParameters(String name) {
        System.out.print("\nEnter " + name + ": ");
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
        System.out.print("\nEnter Address: ");
        String address = input.next();
        if (address.matches("^[A-Z][a-z]{2,}+([\\ ]?+[a-zA-Z]{1,})*$")) {
            return address;
        } else {
            System.out.println("Invalid address enter again");
            return setAddress();
        }
    }

    /* Set Zip */
    public int setZip() {
        System.out.print("\nEnter Zip: ");
        String zip = input.next();
        if (zip.matches("^[1-9][0-9]{5,}$")) {
            return Integer.parseInt(zip);
        } else {
            System.out.println("Invalid zip enter again");
            return setZip();
        }
    }

    /* Set phone number */
    public String setPhoneNumber() {
        System.out.print("\nEnter phone number: ");
        String phoneNumber = input.next();
        if (phoneNumber.matches("^[1-9]+[0-9]+[\\ ]?+[1-9][0-9]{9}$")) {
            return phoneNumber;
        } else {
            System.out.println("Invalid phone number enter again");
            return setPhoneNumber();
        }
    }

    /* check if name exists in address book */
    boolean checkExist(String firstName, String lastName, LinkedList<Person> addressBook) {
        return addressBook.stream().anyMatch(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }
}
