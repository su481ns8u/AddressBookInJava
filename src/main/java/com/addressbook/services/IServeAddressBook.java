package com.addressbook.services;

import com.addressbook.enums.SortParameters;
import com.addressbook.models.Person;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Interface for address book services
 */
public interface IServeAddressBook {
    LinkedList<Person> addPerson(LinkedList<Person> addressBook);

    LinkedList<Person> editPerson(LinkedList<Person> addressBook);

    LinkedList<Person> deletePerson(LinkedList<Person> addressBook);

    void sortByParameter(LinkedList<Person> addressBook, SortParameters sortParameter);

    void searchByCityAndState(LinkedList<Person> addressBook);

    void searchByCityOrState(LinkedList<Person> addressBook);
}