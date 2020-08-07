package com.addressbook.fileselectionstrategy;

import com.addressbook.exceptions.AddressBookException;
import com.addressbook.models.Person;

import java.io.IOException;
import java.util.LinkedList;

public interface OperationStrategies {
    LinkedList<Person> convertToList(String filePath) throws IOException, AddressBookException;

    void convertToFile(LinkedList<Person> addressBook, String filePath) throws AddressBookException;
}
