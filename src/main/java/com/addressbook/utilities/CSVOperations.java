package com.addressbook.utilities;

import com.addressbook.models.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class CSVOperations implements OperationStrategies {
    @Override
    public LinkedList<Person> convertToList(String filePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            LinkedList<Person> addressBook = new LinkedList<>();
            String[] nextPerson = csvReader.readNext();
            while ((nextPerson = csvReader.readNext()) != null) {
                addressBook.add(new Person(nextPerson[2],
                        nextPerson[3],
                        nextPerson[0],
                        nextPerson[1],
                        nextPerson[5],
                        nextPerson[6],
                        nextPerson[4]));
            }
            return addressBook;
        }
    }

    @Override
    public void convertToFile(LinkedList<Person> addressBook, String filePath) {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(filePath));
        ) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(addressBook);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}
