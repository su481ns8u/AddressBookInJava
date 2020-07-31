package com.addressbook.controller;

import com.addressbook.models.Person;
import com.addressbook.services.ServeAddressBook;
import com.addressbook.utilities.CSVOperations;
import com.addressbook.utilities.JSONSimpleOperations;
import com.addressbook.utilities.GSonOperations;
import com.addressbook.utilities.OperationStrategies;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import static com.addressbook.enums.SortParameters.*;

/**
 * Main controller class for address book
 * Program Execution starts from here
 */
public class AddressBook {
    public static void main(String[] args) throws IOException {
        final String JSON_SIMPLE_FILE_PATH = "src\\main\\resources\\JSonSimpleAddressBook.json";
        final String OPEN_CSV_FILE_PATH = "src\\main\\resources\\CSVAddressBook.csv";

        LinkedList<Person> addressBook;
        ServeAddressBook serveAddressBook = new ServeAddressBook();
        Scanner input = new Scanner(System.in);
        OperationStrategies operationStrategies = null;
        System.out.println("Welcome to Address Book !");
        int flag = 0;
        String filePath = null;
        System.out.println("\nChoose read and write technique:" +
                "\n1. JSON Simple" +
                "\n2. Open CSV" +
                "\n3. GSon");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                operationStrategies = new JSONSimpleOperations();
                filePath = JSON_SIMPLE_FILE_PATH;
                break;
            case 2:
                operationStrategies = new CSVOperations();
                filePath = OPEN_CSV_FILE_PATH;
                break;
            case 3:
                operationStrategies = new GSonOperations();
                break;
        }
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
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    addressBook = operationStrategies.convertToList(filePath);
                    addressBook = serveAddressBook.addPerson(addressBook);
                    operationStrategies.convertToFile(addressBook, filePath);
                    break;
                case 2:
                    addressBook = operationStrategies.convertToList(filePath);
                    if (addressBook.isEmpty()) System.out.println("Address Book is Empty !!!");
                    else addressBook.forEach(System.out::println);
                    break;
                case 3:
                    addressBook = operationStrategies.convertToList(filePath);
                    if (addressBook.isEmpty()) System.out.println("Address Book Empty !!!");
                    else addressBook = serveAddressBook.editPerson(addressBook);
                    operationStrategies.convertToFile(addressBook, filePath);
                    break;
                case 4:
                    addressBook = operationStrategies.convertToList(filePath);
                    if (addressBook.isEmpty()) System.out.println("Address Book Empty !!!");
                    else addressBook = serveAddressBook.deletePerson(addressBook);
                    operationStrategies.convertToFile(addressBook, filePath);
                    break;
                case 5:
                    addressBook = operationStrategies.convertToList(filePath);
                    if (addressBook.isEmpty()) System.out.println("Address Book is Empty !!!");
                    else {
                        System.out.print("\n\t1. Name" +
                                "\n\t2. City" +
                                "\n\t3. State" +
                                "\n\t4. Zip" +
                                "\n\tChoice: ");
                        int choiceForSort = input.nextInt();
                        switch (choiceForSort) {
                            case 1:
                                serveAddressBook.sortByParameter(addressBook, NAME);
                                break;
                            case 2:
                                serveAddressBook.sortByParameter(addressBook, CITY);
                                break;
                            case 3:
                                serveAddressBook.sortByParameter(addressBook, STATE);
                                break;
                            case 4:
                                serveAddressBook.sortByParameter(addressBook, ZIP);
                                break;
                            default:
                                System.out.println("Invalid choice !!!");
                                break;
                        }
                    }
                    break;
                case 6:
                    addressBook = operationStrategies.convertToList(filePath);
                    if (addressBook.isEmpty()) System.out.println("Address Book is Empty !!!");
                    else serveAddressBook.searchByCityAndState(addressBook);
                    break;
                case 7:
                    addressBook = operationStrategies.convertToList(filePath);
                    if (addressBook.isEmpty()) System.out.println("Address Book is Empty !!!");
                    else serveAddressBook.searchByCityOrState(addressBook);
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