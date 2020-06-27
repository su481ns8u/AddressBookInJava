package com.su481ns8u.AddressBook.services;

import com.su481ns8u.AddressBook.models.Person;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Interface for address book services
 */
public interface IServeAddressBook {
    public LinkedList<Person> addPerson(LinkedList<Person> addressBook);
    public LinkedList<Person> editPerson(LinkedList<Person> addressBook);
    public LinkedList<Person> deletePerson(LinkedList<Person> addressBook);
    public void sortByParameter(LinkedList<Person> addressBook, Comparator comparator);
    public void searchByCityAndState(LinkedList<Person> addressBook);
    public void searchByCityOrState(LinkedList<Person> addressBook);
}
