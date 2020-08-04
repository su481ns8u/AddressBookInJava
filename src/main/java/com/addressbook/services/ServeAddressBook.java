package com.addressbook.services;

import com.addressbook.enums.SortParameters;
import com.addressbook.models.Person;
import com.addressbook.utilities.AddressBookUtils;
import com.addressbook.utilities.CheckAndReturnParameters;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServeAddressBook implements IServeAddressBook {
    Scanner input = new Scanner(System.in).useDelimiter("\n");
    AddressBookUtils addressBookUtils = new AddressBookUtils();
    CheckAndReturnParameters checkAndReturnParameters = new CheckAndReturnParameters();

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