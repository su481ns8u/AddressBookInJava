package com.su481ns8u.AddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        List<String> person = new ArrayList<String>();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Address Book !");

        System.out.print("Enter First name: ");
        String fname = input.nextLine();

        System.out.print("Enter Last name: ");
        String lname = input.nextLine();

        System.out.print("Enter Address: ");
        String address = input.nextLine();

        System.out.print("Enter City: ");
        String city = input.nextLine();

        System.out.print("Enter state: ");
        String state = input.nextLine();

        System.out.print("Enter Zip: ");
        String zip = input.nextLine();

        System.out.print("Enter phone number: ");
        String phone = input.nextLine();

        person.add(fname);
        person.add(lname);
        person.add(address);
        person.add(city);
        person.add(state);
        person.add(zip);
        person.add(phone);

        for (String str : person){
            System.out.println(str);
        }
    }
}
