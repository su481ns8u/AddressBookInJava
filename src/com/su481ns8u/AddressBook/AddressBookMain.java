package com.su481ns8u.AddressBook;

import java.util.LinkedList;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        LinkedList<Person> addressBook = new LinkedList<Person>();
        AddressBook ab = new AddressBook();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Address Book !");
        int flag=0;
        while (flag == 0) {
            System.out.print("\nEnter Choice" +
                    "\n1. Add Person" +
                    "\n2. View Address Book" +
                    "\n3. Edit Record" +
                    "\n4. Delete Person" +
                    "\n5. Exit" +
                    "\nChoice: ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    addressBook = ab.addPerson(addressBook);
                    break;
                case 2:
                    if(addressBook.isEmpty()){
                        System.out.println("Address Book is Empty !!!");
                    } else {
                        for (Person person : addressBook) {
                            System.out.println(person.toString());
                        }
                    }
                    break;
                case 3:
                    if (addressBook.isEmpty()){
                        System.out.println("Address Book Empty !!!");
                    } else {
                        addressBook = ab.editPerson(addressBook);
                    }
                    break;
                case 4:
                    if(addressBook.isEmpty()){
                        System.out.println("Address Book Empty !!!");
                    } else {
                        addressBook = ab.deletePerson(addressBook);
                    }
                    break;
                case 5:
                    flag=1;
                    break;
                default:
                    System.out.println("Invalid Choice !!!");
                    break;
            }
        }
    }
}