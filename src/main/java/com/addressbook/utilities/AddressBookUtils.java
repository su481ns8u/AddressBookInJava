package com.addressbook.utilities;

import com.addressbook.models.Person;

import java.util.Scanner;

public class AddressBookUtils {
    public static final CheckAndReturnParameters checkAndReturnParameters = new CheckAndReturnParameters();

    public Person setName () {
        return new Person(checkAndReturnParameters.setNameParameters("First Name"),
                checkAndReturnParameters.setNameParameters("Last Name"));
    }

    public void setAllParameters (Person person) {
        person.setPhoneNumber(checkAndReturnParameters.setPhoneNumber());
        person.setAddress(checkAndReturnParameters.setAddress());
        person.setCity(checkAndReturnParameters.setNameParameters("City"));
        person.setState(checkAndReturnParameters.setNameParameters("State"));
        person.setZip(checkAndReturnParameters.setZip());
    }

    public void editParameters (Person person) {
        System.out.print("\n\tEnter edit choice" +
                "\n\t1. Address" +
                "\n\t2. City" +
                "\n\t3. State" +
                "\n\t4. Zip" +
                "\n\t5. Phone Number" +
                "\n\tChoice");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                person.setAddress(checkAndReturnParameters.setAddress());
                break;
            case 2:
                person.setCity(checkAndReturnParameters.setNameParameters("City"));
                break;
            case 3:
                person.setState(checkAndReturnParameters.setNameParameters("State"));
                break;
            case 4:
                person.setZip(checkAndReturnParameters.setZip());
                break;
            case 5:
                person.setPhoneNumber(checkAndReturnParameters.setPhoneNumber());
                break;
            default:
                System.out.println("Wrong Choice !!!");
        }
        System.out.println("Update Successful !!!");
    }
}
