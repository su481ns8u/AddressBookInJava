package com.su481ns8u.AddressBook;

import java.util.Scanner;

public class Person {
    private String fname;
    private String lname;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String phoneNum;

    void addPerson() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter First name: ");
        this.fname = input.nextLine();
        System.out.print("Enter Last name: ");
        this.lname = input.nextLine();
        System.out.print("Enter Address: ");
        this.address = input.nextLine();
        System.out.print("Enter City: ");
        this.city = input.nextLine();
        System.out.print("Enter state: ");
        this.state = input.nextLine();
        System.out.print("Enter Zip: ");
        this.zip = input.nextInt();
        System.out.print("Enter phone number: ");
        this.phoneNum = input.next();
    }

    @Override
    public String toString(){
        return "\nFirst Name: "+fname+
                "\nLast Name: "+lname+
                "\nAddress: "+address+
                "\nCity: "+city+
                "\nState: "+state+
                "\nZip: "+zip+
                "\nPhone Number"+phoneNum;
    }
}
