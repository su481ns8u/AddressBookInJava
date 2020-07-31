package com.addressbook.utilities;

import com.addressbook.models.Person;

import java.util.LinkedList;

public interface OperationStrategies {
    LinkedList<Person> convertToList(String filePath);

    void convertToFile(LinkedList<Person> addressBook, String filePath);
}
