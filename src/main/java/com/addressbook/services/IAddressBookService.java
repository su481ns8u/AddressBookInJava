package com.addressbook.services;

import com.addressbook.utilities.RegExValidator;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Interface for address book services
 */
public interface IAddressBookService {
    default void startPoint() {
        while (true) {
            System.out.print("\nEnter Choice" +
                    "\n1. Add Person" +
                    "\n2. View Address Book" +
                    "\n3. Edit Record" +
                    "\n4. Delete Person" +
                    "\n5. Sort" +
                    "\n6. Search by City and State" +
                    "\n7. Search By City or State" +
                    "\n8. Exit" +
                    "\nChoice: ");
            int choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    this.addPerson();
                    break;
                case 2:
                    this.displayRecords();
                    break;
                case 3:
                    this.editPerson();
                    break;
                case 4:
                    System.out.println("Enter name to delete record: ");
                    this.deletePerson();
                    break;
                case 5:
                    System.out.print("\t1. Name" +
                            "\n\t2. City" +
                            "\n\t3. State" +
                            "\n\t4. Zip" +
                            "\n\tChoice: ");
                    this.sort(RegExValidator.input.nextInt());
                    break;
                case 6:
                    System.out.println("Enter city and state to search:");
                    this.searchByCityAndState(RegExValidator.setNameParameters("City"),
                            RegExValidator.setNameParameters("State"));
                    break;
                case 7:
                    System.out.print("\tSearch By" +
                            "\n\t1. City" +
                            "\n\t2. State" +
                            "\n\tChoice:");
                    this.searchByCityOrState(RegExValidator.input.nextInt());
                    break;
                case 8:
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice !!!");
                    break;
            }
        }
    }

    void addPerson();

    void editPerson();

    void deletePerson();

    void searchByCityAndState(String city, String state);

    void searchByCityOrState(int choice);

    void sort(int choice);

    void displayRecords();
}