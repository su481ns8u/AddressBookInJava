package com.su481ns8u.AddressBook;

import java.util.Scanner;

public class Person {
    // VARIABLES DECLARATIONS
    private String fname;
    private String lname;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String phoneNum;

    // ONE WHOLE FUNCTION TO SET PERSON
   void setPerson(String fname, String lname, String address, String city, String state, int zip, String phoneNum){
       this.fname=fname;
       this.lname=lname;
       this.address=address;
       this.city=city;
       this.state=state;
       this.zip=zip;
       this.phoneNum=phoneNum;
   }

   // FUNCTIONS TO SET RECORDS INDIVIDUALLY
   void setFName(String fname){this.fname=fname;}
   void setLName(String lname){this.lname=lname;}
   void setAddress(String address){this.address=address;}
   void setCity(String city){this.city=city;}
   void setState(String state){this.state=state;}
   void setZip(int zip){this.zip=zip;}
   void setPhoneNum(String phoneNum){this.phoneNum=phoneNum;}

   // FUNCTIONS TO GET RECORDS
   String getFName(){return fname;}
   String getLName(){return lname;}
   String getAddress(){return address;}
   String getCity(){return city;}
   String getState(){return state;}
   int getZip(){return zip;}
   String getPhoneNum(){return phoneNum;}

   // FUNCTION TO OUTPUT RECORDS
    @Override
    public String toString(){
        return "\nFirst Name: "+fname+
                "\nLast Name: "+lname+
                "\nAddress: "+address+
                "\nCity: "+city+
                "\nState: "+state+
                "\nZip: "+zip+
                "\nPhone Number: "+phoneNum;
    }
}
