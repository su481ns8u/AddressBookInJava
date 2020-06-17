package com.su481ns8u.AddressBook;

import java.util.LinkedList;
import java.util.Scanner;

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
        for(Person p : addressBook){
            System.out.print(p.getFName()+" "+p.getLName());
            if(fname.equals(p.getFName()) && lname.equals(p.getLName())){
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
                        p.setAddress(address);
                        break;
                    case 2:
                        System.out.print("Enter Updated City: ");
                        String city = input.next();
                        p.setCity(city);
                        break;
                    case 3:
                        System.out.print("Enter Updated State: ");
                        String state = input.next();
                        p.setState(state);
                        break;
                    case 4:
                        System.out.print("Enter Updated Zip: ");
                        int zip = input.nextInt();
                        p.setZip(zip);
                        break;
                    case 5:
                        System.out.print("Enter Updated Phone Number: ");
                        String phoneNum = input.next();
                        p.setPhoneNum(phoneNum);
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
}
