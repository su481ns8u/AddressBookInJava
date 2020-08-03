package com.addressbook.utilities;

import com.addressbook.models.Person;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class GSonOperations implements OperationStrategies {
    @Override
    public LinkedList<Person> convertToList(String filePath) throws IOException {
        LinkedList<Person> address = new LinkedList<>();
        try {
            Person[] addressBook = new Gson().fromJson(new FileReader(filePath), Person[].class);
            if (addressBook.length == 0) return address;
            address.addAll(Arrays.asList(addressBook));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void convertToFile(LinkedList<Person> addressBook, String filePath) {
        String jsonString = new Gson().toJson(addressBook);
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
