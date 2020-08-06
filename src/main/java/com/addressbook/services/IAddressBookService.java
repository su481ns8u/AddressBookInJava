package com.addressbook.services;

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
                    this.deletePerson();
                    break;
                case 5:
                    this.sort();
                    break;
                case 6:
                    this.searchByCityAndState();
                    break;
                case 7:
                    this.searchByCityOrState();
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

    void searchByCityAndState();

    void searchByCityOrState();

    void sort();

    void displayRecords();
}