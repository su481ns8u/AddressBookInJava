package com.addressbook.services;

import com.addressbook.enums.SortParameters;
import com.addressbook.models.Person;
import com.addressbook.utilities.CheckAndReturnParameters;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Class for address book services
 */
public class ServeAddressBook implements IServeAddressBook {
    Scanner input = new Scanner(System.in).useDelimiter("\n");
    CheckAndReturnParameters checkAndReturnParameters = new CheckAndReturnParameters();

    // FUNCTION TO ADD PERSON TO ADDRESS BOOK
    public LinkedList<Person> addPerson(LinkedList<Person> addressBook) {
        String firstName = checkAndReturnParameters.setNameParameters("First Name");
        String lastName = checkAndReturnParameters.setNameParameters("Last Name");
        if (checkAndReturnParameters.checkExist(firstName, lastName, addressBook))
            System.out.println("Record already exists cant add !!!");
        else {
            String phoneNumber = checkAndReturnParameters.setPhoneNumber();
            String city = checkAndReturnParameters.setNameParameters("City");
            String state = checkAndReturnParameters.setNameParameters("State");
            int zip = checkAndReturnParameters.setZip();
            String address = checkAndReturnParameters.setAddress();
            Person person = new Person(firstName, lastName, address, city, state, zip, phoneNumber);
            addressBook.add(person);
        }
        return addressBook;
    }

    // FUNCTION TO EDIT PERSON RECORD
    public LinkedList<Person> editPerson(LinkedList<Person> addressBook) {
        System.out.print("Enter Name to edit record: \n");
        String firstName = checkAndReturnParameters.setNameParameters("First Name");
        String lastName = checkAndReturnParameters.setNameParameters("Last Name");
        if (checkAndReturnParameters.checkExist(firstName, lastName, addressBook)) {
            for (Person person : addressBook)
                if (firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName())) {
                    System.out.print("\n\tEnter edit choice" +
                            "\n\t1. Address" +
                            "\n\t2. City" +
                            "\n\t3. State" +
                            "\n\t4. Zip" +
                            "\n\t5. Phone Number" +
                            "\n\tChoice");
                    int choice = input.nextInt();
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
                    break;
                }
        } else System.out.println("Record dose not exist !!!");
        return addressBook;
    }

    // FUNCTION TO DELETE RECORD FROM THE ADDRESS BOOK
    public LinkedList<Person> deletePerson(LinkedList<Person> addressBook) {
        System.out.println("Enter name to delete record: ");
        String firstName = checkAndReturnParameters.setNameParameters("First Name");
        String lastName = checkAndReturnParameters.setNameParameters("Last Name");
        int flag = 0;
        if (checkAndReturnParameters.checkExist(firstName, lastName, addressBook)) {
            addressBook.removeIf
                    (person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
            System.out.println("Delete Successful !!!");
            flag = 1;
        }
        if (flag == 0) System.out.println("No Record exists !!!");
        return addressBook;
    }

    // Function to sort records on basis of entered parameter
    public void sortByParameter(LinkedList<Person> addressBook, SortParameters sortParameter) {
        addressBook.stream().sorted(sortParameter.comparator).forEach(System.out::println);
    }

    // FUNCTION TO GET PEOPLE BY CITY AND STATE TOGETHER
    public void searchByCityAndState(LinkedList<Person> addressBook) {
        System.out.print("Enter city and state to search: ");
        String city = checkAndReturnParameters.setNameParameters("City");
        String state = checkAndReturnParameters.setNameParameters("State");
        List<Person> sampleAddressBook = addressBook.stream().filter
                (person -> person.getState().equals(state) && person.getCity().equals(city))
                .collect(Collectors.toList());
        if (sampleAddressBook.size() == 0) System.out.println("No such record exists !!!");
        else sampleAddressBook.forEach(System.out::println);
    }

    //FUNCTION TO SEARCH BY CITY OR STATE
    public void searchByCityOrState(LinkedList<Person> addressBook) {
        List<Person> sampleAddressBook = null;
        System.out.print("\tSearch By" +
                "\n\t1. City" +
                "\n\t2. Zip" +
                "\n\tChoice:");
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
}