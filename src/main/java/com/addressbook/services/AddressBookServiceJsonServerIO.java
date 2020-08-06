package com.addressbook.services;

import com.addressbook.models.Person;
import com.addressbook.utilities.AddressBookUtils;
import com.addressbook.utilities.CheckAndReturnParameters;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Scanner;

import static io.restassured.RestAssured.*;

public class AddressBookServiceJsonServerIO implements IAddressBookService {
    AddressBookUtils addressBookUtils = new AddressBookUtils();
    CheckAndReturnParameters checkAndReturnParameters = new CheckAndReturnParameters();
    Scanner input = new Scanner(System.in);

    public AddressBookServiceJsonServerIO() {
        baseURI = "http://localhost:3000/person";
        this.startPoint();
    }

    @Override
    public void addPerson() {
        Person person = addressBookUtils.setName();
        if (checkExist(person.getFirstName(), person.getLastName()) == 0) {
            addressBookUtils.setAllParameters(person);
            given().contentType(ContentType.JSON).accept(ContentType.JSON)
                    .header("Content-Type", "application/json")
                    .body(new GsonBuilder().create().toJson(person))
                    .when().post();
        } else System.out.println("Record already exists cant add !!!");
    }

    @Override
    public void editPerson() {
        System.out.print("Enter Name to edit record: \n");
        String firstName = checkAndReturnParameters.setNameParameters("First Name");
        String lastName = checkAndReturnParameters.setNameParameters("Last Name");
        if (checkExist(firstName, lastName) != 0) {
            Response response = searchByName(firstName, lastName);
            int id = (int) response.jsonPath().getList("id").get(0);
            String address = (String) response.jsonPath().getList("address").get(0);
            String city = (String) response.jsonPath().getList("city").get(0);
            String state = (String) response.jsonPath().getList("state").get(0);
            String zip = (String) response.jsonPath().getList("zip").get(0);
            String phoneNumber = (String) response.jsonPath().getList("phoneNumber").get(0);
            System.out.println(address + city + state + zip + phoneNumber);
            System.out.print("\n\tEnter edit choice" +
                    "\n\t1. Address" +
                    "\n\t2. City" +
                    "\n\t3. State" +
                    "\n\t4. Zip" +
                    "\n\t5. Phone Number" +
                    "\n\tChoice");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    address = checkAndReturnParameters.setAddress();
                    break;
                case 2:
                    city = checkAndReturnParameters.setNameParameters("City");
                    break;
                case 3:
                    state = checkAndReturnParameters.setNameParameters("State");
                    break;
                case 4:
                    zip = checkAndReturnParameters.setZip();
                    break;
                case 5:
                    phoneNumber = checkAndReturnParameters.setPhoneNumber();
                    break;
                default:
                    System.out.println("Wrong Choice !!!");
            }
            System.out.println(address + city + state + zip + phoneNumber);
            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .header("Content-Type", "application/json")
                    .body("{\"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName + "\", " +
                            "\"address\": \"" + address + "\", " +
                            "\"city\": \"" + city + "\", \"state\": \"" + state + "\", " +
                            "\"zip\": \"" + zip + "\", \"phoneNumber\": \"" + phoneNumber + "\"}")
                    .when().put("/" + id);
            System.out.println("Update Successful !!!");
        } else System.out.println("Record not exist !!!");
    }

    @Override
    public void deletePerson() {
        System.out.print("Enter Name to delete record: \n");
        String firstName = checkAndReturnParameters.setNameParameters("First Name");
        String lastName = checkAndReturnParameters.setNameParameters("Last Name");
        if (checkExist(firstName, lastName) != 0) {
            Response response = searchByName(firstName, lastName);
            when().delete("/" + (int) response.jsonPath().getList("id").get(0));
            System.out.println("Delete Successful !!!");
        } else System.out.println("Record Not Exist !!!");
    }

    @Override
    public void searchByCityAndState() {
        System.out.println("Enter city and state to search:");
        String city = checkAndReturnParameters.setNameParameters("City");
        String state = checkAndReturnParameters.setNameParameters("State");
        ArrayList<Object> list = (ArrayList<Object>) responseByQuery("?city=" + city + "&?state=" + state)
                .jsonPath().getList("$");
        list.forEach(a -> System.out.println(a.toString()));
    }

    @Override
    public void searchByCityOrState() {
        System.out.print("\tSearch By" +
                "\n\t1. City" +
                "\n\t2. State" +
                "\n\tChoice:");
        ArrayList<Object> list = null;
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                list = (ArrayList<Object>) responseByQuery("?city=" + checkAndReturnParameters
                        .setNameParameters("City")).jsonPath().getList("$");
                break;
            case 2:
                list = (ArrayList<Object>) responseByQuery("?state=" + checkAndReturnParameters
                        .setNameParameters("State")).jsonPath().getList("$");
                break;
            default:
                System.out.println("Invalid Choice");
        }
        if (list == null) System.out.println("No such records exist !!!");
        else list.forEach(a -> System.out.println(a.toString()));
    }

    @Override
    public void sort() {
        String sortString = "";
        System.out.print("\n\t1. Name" +
                "\n\t2. City" +
                "\n\t3. State" +
                "\n\t4. Zip" +
                "\n\tChoice: ");
        int sortParameter = input.nextInt();
        switch (sortParameter) {
            case 1:
                sortString = "?_sort=firstName,lastName&_order=asc";
                break;
            case 2:
                sortString = "?_sort=city&_order=asc";
                break;
            case 3:
                sortString = "?_sort=state&_order=asc";
                break;
            case 4:
                sortString = "?_sort=zip&_order=asc";
                break;
            default:
                System.out.println("Invalid choice !!!");
                break;
        }
        ArrayList<Object> list = (ArrayList<Object>) responseByQuery(sortString).jsonPath().getList("$");
        list.forEach(a -> System.out.println(a.toString()));
    }

    @Override
    public void displayRecords() {
        ArrayList<Object> list = (ArrayList<Object>) responseByQuery("").jsonPath().getList("$");
        list.forEach(a -> System.out.println(a.toString()));
    }

    public int checkExist(String firstName, String lastName) {
        Response response = searchByName(firstName, lastName);
        return response.jsonPath().getList("id").size();
    }

    public Response searchByName(String firstName, String lastName) {
        return (Response) responseByQuery("?firstName=" + firstName + "&?lastName=" + lastName).body();
    }

    public Response responseByQuery(String query) {
        return (Response) given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get(query)
                .then().extract();
    }
}