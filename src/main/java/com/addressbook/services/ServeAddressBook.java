package com.addressbook.services;

import com.addressbook.enums.SortParameters;
import com.addressbook.models.Person;
import com.addressbook.utilities.AddressBookUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.addressbook.enums.SortParameters.*;
import static com.addressbook.enums.SortParameters.ZIP;
import static com.addressbook.utilities.AddressBookUtils.checkAndReturnParameters;

public class ServeAddressBook implements IServeAddressBook {
    AddressBookUtils addressBookUtils = new AddressBookUtils();

    public LinkedList<Person> addPerson(LinkedList<Person> addressBook) {
        Person person = addressBookUtils.setName();
        if (addressBook.contains(person))
            System.out.println("Record already exists cant add !!!");
        else {
            addressBookUtils.setAllParameters(person);
            addressBook.add(person);
        }
        return addressBook;
    }

    public LinkedList<Person> editPerson(LinkedList<Person> addressBook) {
        System.out.print("Enter Name to edit record: \n");
        Person person = addressBookUtils.setName();
        if (addressBook.contains(person)) {
            int index = addressBook.indexOf(person);
            addressBookUtils.editParameters(addressBook.get(index));
        } else System.out.println("Record dose not exist !!!");
        return addressBook;
    }

    public LinkedList<Person> deletePerson(LinkedList<Person> addressBook) {
        System.out.println("Enter name to delete record: ");
        Person person = addressBookUtils.setName();
        if (addressBook.contains(person)) {
            addressBook.removeIf(personArr -> personArr.equals(person));
            System.out.println("Delete Successful !!!");
        } else System.out.println("No Record exists !!!");
        return addressBook;
    }

    public void sortByParameter(LinkedList<Person> addressBook, SortParameters sortParameter) {
        addressBook.stream().sorted(sortParameter.comparator).forEach(System.out::println);
    }

    public void sort(LinkedList <Person> addressBook) {
        System.out.print("\n\t1. Name" +
                "\n\t2. City" +
                "\n\t3. State" +
                "\n\t4. Zip" +
                "\n\tChoice: ");
        Scanner input = new Scanner(System.in);
        int choiceForSort = input.nextInt();
        switch (choiceForSort) {
            case 1:
                this.sortByParameter(addressBook, NAME);
                break;
            case 2:
                this.sortByParameter(addressBook, CITY);
                break;
            case 3:
                this.sortByParameter(addressBook, STATE);
                break;
            case 4:
                this.sortByParameter(addressBook, ZIP);
                break;
            default:
                System.out.println("Invalid choice !!!");
                break;
        }
    }

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

    public void searchByCityOrState(LinkedList<Person> addressBook) {
        List<Person> sampleAddressBook = null;
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
}