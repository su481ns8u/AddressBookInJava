package com.su481ns8u.AddressBook.controller;

import com.su481ns8u.AddressBook.models.Person;
import com.su481ns8u.AddressBook.services.ServeAddressBook;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Main controller class for address book
 * Program Execution starts from here
 */
public class AddressBook {
    public static void main(String[] args) {
        LinkedList<Person> addressBook = new LinkedList<Person>();
        ServeAddressBook serveAddressBook = new ServeAddressBook();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Address Book !");
        int flag = 0;
        while (flag == 0) {
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
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    addressBook = serveAddressBook.addPerson(addressBook);
                    break;
                case 2:
                    if (addressBook.isEmpty()) {
                        System.out.println("Address Book is Empty !!!");
                    } else {
                        addressBook.forEach(System.out::println);
                    }
                    break;
                case 3:
                    if (addressBook.isEmpty()) {
                        System.out.println("Address Book Empty !!!");
                    } else {
                        addressBook = serveAddressBook.editPerson(addressBook);
                    }
                    break;
                case 4:
                    if (addressBook.isEmpty()) {
                        System.out.println("Address Book Empty !!!");
                    } else {
                        addressBook = serveAddressBook.deletePerson(addressBook);
                    }
                    break;
                case 5:
                    if (addressBook.isEmpty()) {
                        System.out.println("Address Book is Empty !!!");
                    } else {
                        System.out.print("\n\t1. Name" +
                                "\n\t2. City" +
                                "\n\t3. State" +
                                "\n\t4. Zip" +
                                "\n\tChoice: ");
                        int choiceForSort = input.nextInt();
                        switch (choiceForSort) {
                            case 1:
                                serveAddressBook.sortByParameter(addressBook, Comparator.comparing(Person::getFirstName)
                                        .thenComparing(Person::getLastName));
                                break;
                            case 2:
                                serveAddressBook.sortByParameter(addressBook, Comparator.comparing(Person::getCity));
                                break;
                            case 3:
                                serveAddressBook.sortByParameter(addressBook, Comparator.comparing(Person::getState));
                                break;
                            case 4:
                                serveAddressBook.sortByParameter(addressBook, Comparator.comparing(Person::getZip));
                                break;
                            default:
                                System.out.println("Invalid choice !!!");
                                break;
                        }
                    }
                    break;
                case 6:
                    if (addressBook.isEmpty()) {
                        System.out.println("Address Book is Empty !!!");
                    } else {
                        serveAddressBook.searchByCityAndState(addressBook);
                    }
                    break;
                case 7:
                    if (addressBook.isEmpty()) {
                        System.out.println("Address Book is Empty !!!");
                    } else {
                        serveAddressBook.searchByCityOrState(addressBook);
                    }
                    break;
                case 8:
                    flag = 1;
                    break;
                default:
                    System.out.println("Invalid Choice !!!");
                    break;
            }
        }
    }
}