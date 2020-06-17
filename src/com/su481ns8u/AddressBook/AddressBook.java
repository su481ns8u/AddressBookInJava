package com.su481ns8u.AddressBook;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AddressBook {
    Scanner input = new Scanner(System.in);

    // FUNCTION TO ADD PERSON TO ADDRESS BOOK
    LinkedList<Person> addPerson(LinkedList<Person> addressBook) {
        Person p = new Person();
        System.out.print("Enter First name: ");
        String fname = input.next();
        System.out.print("Enter Last name: ");
        String lname = input.next();
        if (checkExist(fname,lname,addressBook) == true){
            System.out.println("Record already exists cant add !!!");
        } else {
            System.out.print("Enter Address: ");
            String address = input.next();
            System.out.print("Enter City: ");
            String city = input.next();
            System.out.print("Enter state: ");
            String state = input.next();
            System.out.print("Enter Zip: ");
            int zip = input.nextInt();
            System.out.print("Enter phone number: ");
            String phoneNum = input.next();
            p.setPerson(fname, lname, address, city, state, zip, phoneNum);
            addressBook.add(p);
        }
        return addressBook;
    }

    // FUNCTION TO CHECK IF RECORD ALREADY EXISTS OR NOT
    boolean checkExist(String fname, String lname, LinkedList<Person> addressBook){
        boolean result = false;
        for(Person person : addressBook){
            if(fname.equals(person.getFName()) && lname.equals(person.getLName())){
                result = true;
            }
        }
        return result;
    }

    // FUNCTION TO EDIT PERSON RECORD
    LinkedList<Person> editPerson(LinkedList<Person> addressBook){
        System.out.print("Enter First Name to edit record: ");
        String fname = input.next();
        System.out.print("Enter Last Name: ");
        String lname = input.next();
        int flag2 = 0;
        for(Person person : addressBook){
            System.out.print(person.getFName()+" "+person.getLName());
            if(fname.equals(person.getFName()) && lname.equals(person.getLName())){
                System.out.println("\nEnter edit choice" +
                        "\n1. Address" +
                        "\n2. City" +
                        "\n3. State" +
                        "\n4. Zip" +
                        "\n5. Phone Number");
                int choice = input.nextInt();
                switch (choice){
                    case 1:
                        System.out.print("Enter Updated Address: ");
                        String address = input.next();
                        person.setAddress(address);
                        break;
                    case 2:
                        System.out.print("Enter Updated City: ");
                        String city = input.next();
                        person.setCity(city);
                        break;
                    case 3:
                        System.out.print("Enter Updated State: ");
                        String state = input.next();
                        person.setState(state);
                        break;
                    case 4:
                        System.out.print("Enter Updated Zip: ");
                        int zip = input.nextInt();
                        person.setZip(zip);
                        break;
                    case 5:
                        System.out.print("Enter Updated Phone Number: ");
                        String phoneNum = input.next();
                        person.setPhoneNum(phoneNum);
                        break;
                    default:
                        System.out.println("Wrong Choice !!!");
                }
                flag2 = 1;
                System.out.println("Updatation Successful !!!");
            }
            if (flag2 == 0){
                System.out.println("Record dose not exist !!!");
            }
        }
        return addressBook;
    }

    //FUNCTION TO DELETE RECORD FROM THE ADDRESS BOOK
    LinkedList<Person> deletePerson(LinkedList<Person> addressBook){
        System.out.print("Enter First Name to delete record: ");
        String fname = input.next();
        System.out.print("Enter Last Name: ");
        String lname = input.next();
        int flag=0;
        for(Person person : addressBook){
            if(fname.equals(person.getFName()) && lname.equals(person.getLName())){
                addressBook.remove(person);
                System.out.println("Deletion Successful !!!");
                flag=1;
                break;
            }
        }
        if (flag==0){
            System.out.println("No Record exists !!!");
        }
        return addressBook;
    }

    //FUNCTION TO SORT RECORDS BY NAME
    void sortByName(LinkedList<Person> addressBook){
        Map<String,Person> sortedAddressBook = new TreeMap<>();
        for (Person person : addressBook){
            String fullName = person.getFName()+person.getLName();
            sortedAddressBook.put(fullName, person);
        }
        sortedAddressBook.forEach((k,v)->System.out.println(v.toString()));
    }

    //FUNCTION TO SORT RECORDS BY CITY
    void sortByCity(LinkedList<Person> addressBook){
        Map<String,Person> sortedAddressBook = new TreeMap<>();
        for (Person person : addressBook){
            sortedAddressBook.put(person.getCity(), person);
        }
        sortedAddressBook.forEach((k,v)->System.out.println(v.toString()));
    }

    //FUNCTION TO SORT RECORDS BY STATE
    void sortByState(LinkedList<Person> addressBook){
        Map<String,Person> sortedAddressBook = new TreeMap<>();
        for (Person person : addressBook){
            sortedAddressBook.put(person.getState(), person);
        }
        sortedAddressBook.forEach((k,v)->System.out.println(v.toString()));
    }

    //FUNCTION TO SORT RECORDS BY ZIP
    void sortByZip(LinkedList<Person> addressBook){
        Map<Integer,Person> sortedAddressBook = new TreeMap<>();
        for (Person person : addressBook){
            sortedAddressBook.put(person.getZip(), person);
        }
        sortedAddressBook.forEach((k,v)->System.out.println(v.toString()));
    }

    //FUNCTION TO GET PEOPLE BY CITY AND STATE TOGETHER
    void viewByCityAndState(LinkedList<Person> addressBook){
        Map<String,Person> viewAddressBook = new TreeMap<>();
        for (Person person : addressBook){
            String fullAddress = person.getCity()+" "+person.getState();
            viewAddressBook.put(fullAddress, person);
        }
        viewAddressBook.forEach((k,v)->System.out.println(v.toString()));
    }

    //FUNCTION TO SEARCH BY CITY OR STATE
    void searchByCityOrState(LinkedList<Person> addressBook){
        int flag=0;
        System.out.println("\n\tSearch By" +
                "\n\t1. City" +
                "\n\t2. Zip" +
                "\n\tChoice:");
        int choice = input.nextInt();
        switch (choice){
            case 1:
                System.out.print("Enter city: ");
                String city = input.next();
                for (Person person : addressBook){
                    if (city.equals(person.getCity())){
                        System.out.println(person.toString());
                        flag=1;
                    }
                }
                break;
            case 2:
                System.out.print("Enter state: ");
                String state = input.next();
                for (Person person : addressBook){
                    if (state.equals(person.getState())){
                        System.out.println(person.toString());
                        flag=1;
                    }
                }
                break;
        }
        if (flag == 0){
            System.out.println("No such city in Address Book !!!");
        }
    }
}