package com.addressbook.utilities;

import com.addressbook.models.Person;

import java.util.Scanner;

public class AddressBookUtils {
    public static final RegExValidator REG_EX_VALIDATOR = new RegExValidator();

    public Person setName () {
        return new Person(RegExValidator.setNameParameters("First Name"),
                RegExValidator.setNameParameters("Last Name"));
    }

    public void setAllParameters (Person person) {
        person.setPhoneNumber(REG_EX_VALIDATOR.setPhoneNumber());
        person.setAddress(REG_EX_VALIDATOR.setAddress());
        person.setCity(RegExValidator.setNameParameters("City"));
        person.setState(RegExValidator.setNameParameters("State"));
        person.setZip(REG_EX_VALIDATOR.setZip());
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
                person.setAddress(REG_EX_VALIDATOR.setAddress());
                break;
            case 2:
                person.setCity(RegExValidator.setNameParameters("City"));
                break;
            case 3:
                person.setState(RegExValidator.setNameParameters("State"));
                break;
            case 4:
                person.setZip(REG_EX_VALIDATOR.setZip());
                break;
            case 5:
                person.setPhoneNumber(REG_EX_VALIDATOR.setPhoneNumber());
                break;
            default:
                System.out.println("Wrong Choice !!!");
        }
        System.out.println("Update Successful !!!");
    }
}
