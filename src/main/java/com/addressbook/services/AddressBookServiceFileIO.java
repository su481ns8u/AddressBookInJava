package com.addressbook.services;

import com.addressbook.enums.SortParameters;
import com.addressbook.models.Person;
import com.addressbook.utilities.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.addressbook.enums.SortParameters.*;
import static com.addressbook.utilities.AddressBookUtils.checkAndReturnParameters;
import static java.lang.System.exit;

public class AddressBookServiceFileIO implements IAddressBookService {
    final String JSON_SIMPLE_FILE_PATH = "src\\main\\resources\\JSonSimpleAddressBook.json";
    final String OPEN_CSV_FILE_PATH = "src\\main\\resources\\CSVAddressBook.csv";
    final String GSon_FILE_PATH = "src\\main\\resources\\GSonAddressBook.json";

    LinkedList<Person> addressBook = new LinkedList<>();
    OperationStrategies operationStrategies = null;
    Scanner input = new Scanner(System.in);
    String filePath = null;
    AddressBookUtils addressBookUtils = new AddressBookUtils();

    public AddressBookServiceFileIO() {
        this.setOperationStrategies();
        this.startPoint();
    }

    public void setOperationStrategies() {
        System.out.println("Welcome to Address Book !");
        System.out.print("\nChoose read and write technique:" +
                "\n1. JSON Simple" +
                "\n2. Open CSV" +
                "\n3. GSon" +
                "\n Choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                this.operationStrategies = new JSONSimpleOperations();
                this.filePath = JSON_SIMPLE_FILE_PATH;
                break;
            case 2:
                this.operationStrategies = new CSVOperations();
                this.filePath = OPEN_CSV_FILE_PATH;
                break;
            case 3:
                this.operationStrategies = new GSonOperations();
                this.filePath = GSon_FILE_PATH;
                break;
            default:
                exit(0);
        }
    }

    public void addPerson() {
        try {
            this.addressBook = this.operationStrategies.convertToList(filePath);
        } catch (IOException ignored) {
        }
        Person person = addressBookUtils.setName();
        if (this.addressBook.contains(person)) System.out.println("Record already exists cant add !!!");
        else {
            addressBookUtils.setAllParameters(person);
            this.addressBook.add(person);
        }
        this.operationStrategies.convertToFile(this.addressBook, filePath);
    }

    public void editPerson() {
        try {
            this.addressBook = this.operationStrategies.convertToList(filePath);
        } catch (IOException ignored) {
        }
        System.out.print("Enter Name to edit record: \n");
        Person person = addressBookUtils.setName();
        if (this.addressBook.contains(person)) {
            int index = this.addressBook.indexOf(person);
            addressBookUtils.editParameters(this.addressBook.get(index));
        } else System.out.println("Record dose not exist !!!");
        this.operationStrategies.convertToFile(this.addressBook, this.filePath);
    }

    public void deletePerson() {
        try {
            this.addressBook = this.operationStrategies.convertToList(filePath);
        } catch (IOException ignored) {
        }
        System.out.println("Enter name to delete record: ");
        Person person = addressBookUtils.setName();
        if (addressBook.contains(person)) {
            addressBook.removeIf(personArr -> personArr.equals(person));
            System.out.println("Delete Successful !!!");
        } else System.out.println("No Record exists !!!");
        this.operationStrategies.convertToFile(this.addressBook, this.filePath);
    }

    private void sortByParameter(SortParameters sortParameter) {
        addressBook.stream().sorted(sortParameter.comparator).forEach(System.out::println);
    }

    public void sort() {
        try {
            this.addressBook = this.operationStrategies.convertToList(filePath);
        } catch (IOException ignored) {
        }
        System.out.print("\n\t1. Name" +
                "\n\t2. City" +
                "\n\t3. State" +
                "\n\t4. Zip" +
                "\n\tChoice: ");
        int sortParameter = input.nextInt();
        switch (sortParameter) {
            case 1:
                this.sortByParameter(NAME);
                break;
            case 2:
                this.sortByParameter(CITY);
                break;
            case 3:
                this.sortByParameter(STATE);
                break;
            case 4:
                this.sortByParameter(ZIP);
                break;
            default:
                System.out.println("Invalid choice !!!");
                break;
        }
    }

    public void searchByCityAndState() {
        try {
            this.addressBook = this.operationStrategies.convertToList(filePath);
        } catch (IOException ignored) {
        }
        System.out.println("Enter name to delete record: ");
        System.out.print("Enter city and state to search: ");
        String city = checkAndReturnParameters.setNameParameters("City");
        String state = checkAndReturnParameters.setNameParameters("State");
        List<Person> sampleAddressBook = this.addressBook.stream().filter
                (person -> person.getState().equals(state) && person.getCity().equals(city))
                .collect(Collectors.toList());
        if (sampleAddressBook.size() == 0) System.out.println("No such record exists !!!");
        else sampleAddressBook.forEach(System.out::println);
    }

    public void searchByCityOrState() {
        List<Person> sampleAddressBook = null;
        try {
            this.addressBook = this.operationStrategies.convertToList(filePath);
        } catch (IOException ignored) {
        }
        System.out.print("\tSearch By" +
                "\n\t1. City" +
                "\n\t2. Zip" +
                "\n\tChoice:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                String city = checkAndReturnParameters.setNameParameters("City");
                sampleAddressBook = addressBook.stream()
                        .filter(person -> person.getCity().equals(city))
                        .collect(Collectors.toList());
                break;
            case 2:
                String state = checkAndReturnParameters.setNameParameters("State");
                sampleAddressBook = addressBook.stream()
                        .filter(person -> person.getState().equals(state))
                        .collect(Collectors.toList());
                break;
        }
        if (sampleAddressBook == null) System.out.println("No such city or state in Address Book !!!");
        else sampleAddressBook.forEach(System.out::println);
    }

    @Override
    public void displayRecords() {
        try {
            this.addressBook = this.operationStrategies.convertToList(filePath);
        } catch (IOException ignored) {
        }
        this.addressBook.forEach(System.out::println);
    }
}