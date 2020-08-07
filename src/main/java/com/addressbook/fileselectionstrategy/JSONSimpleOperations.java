package com.addressbook.fileselectionstrategy;

import com.addressbook.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class JSONSimpleOperations implements OperationStrategies{
    @Override
    public void convertToFile(LinkedList<Person> addressBook, String jsonPath) {
        JSONArray personList = new JSONArray();
        for (Person person : addressBook) {
            JSONObject personDetails = new JSONObject();
            personDetails.put("First Name", person.getFirstName());
            personDetails.put("Last Name", person.getLastName());
            personDetails.put("Mobile No.", person.getPhoneNumber());
            personDetails.put("Address", person.getAddress());
            personDetails.put("City", person.getCity());
            personDetails.put("State", person.getState());
            personDetails.put("Zip", person.getZip());

            JSONObject personObject = new JSONObject();
            personObject.put("person", personDetails);

            personList.add(personObject);
        }
        try {
            FileWriter fileWriter = new FileWriter(jsonPath);
            fileWriter.append(personList.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<Person> convertToList(String jsonPath) {
        JSONParser jsonParser = new JSONParser();
        LinkedList<Person> addressBook = new LinkedList<>();
        try {
            FileReader fileReader = new FileReader(jsonPath);
            Object obj = jsonParser.parse(fileReader);
            JSONArray personList = (JSONArray) obj;
            personList.forEach(person -> addressBook.add(parsePersonObject((JSONObject) person)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    private Person parsePersonObject(JSONObject personJson) {
        JSONObject personObj = (JSONObject) personJson.get("person");
        return new Person((String) personObj.get("First Name"),
                (String) personObj.get("Last Name"),
                (String) personObj.get("Address"),
                (String) personObj.get("City"),
                (String) personObj.get("State"),
                (String) personObj.get("Zip"),
                (String) personObj.get("Mobile No."));
    }
}
