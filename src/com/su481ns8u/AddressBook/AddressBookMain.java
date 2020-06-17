package com.su481ns8u.AddressBook;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        LinkedList<Person> addresssBook = new LinkedList<Person>();
        AddressBook ab = new AddressBook();
        Person p;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Address Book !");
        int flag=0;
        while (flag == 0) {
            System.out.print("\nEnter Choice" +
                    "\n1. Add Person" +
                    "\n2. View Address Book" +
                    "\n3. Edit Record" +
                    "\n4. Exit" +
                    "\nChoice: ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    p = ab.addPerson();
                    addresssBook.add(p);
                    break;
                case 2:
                    if(addresssBook.isEmpty()){
                        System.out.println("Address Book is Empty !!!");
                    } else {
                        for (Person person : addresssBook) {
                            System.out.println(person.toString());
                        }
                    }
                    break;
                case 3:
                    if (addresssBook.isEmpty()){
                        System.out.println("Address Book Empty !!!");
                    } else {
                        addresssBook = ab.editPerson(addresssBook);
                    }
                    break;
                case 4:
                    flag=1;
                    break;
                default:
                    System.out.println("Invalid Choice !!!");
                    break;
            }
        }
    }
}