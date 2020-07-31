package com.addressbook.utilities;

import com.addressbook.models.Person;

import java.io.IOException;
import java.util.LinkedList;

public interface OperationStrategies {
    LinkedList<Person> convertToList(String filePath) throws IOException;

    void convertToFile(LinkedList<Person> addressBook, String filePath);
}
