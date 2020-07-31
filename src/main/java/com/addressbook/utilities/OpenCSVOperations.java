package com.addressbook.utilities;

import com.addressbook.models.Person;

import java.util.LinkedList;

public class OpenCSVOperations implements OperationStrategies {
    @Override
    public LinkedList<Person> convertToList(String filePath) {
        return null;
    }

    @Override
    public void convertToFile(LinkedList<Person> addressBook, String filePath) {

    }
}
