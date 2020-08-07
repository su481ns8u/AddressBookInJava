package com.addressbook.exceptions;

public class AddressBookException extends Exception {
    public enum ExceptionType {
        DB_CONNECTION_ERROR("Data base cant get connected due to some issue"),
        INVALID_CHOICE("Choice entered is not valid"),
        FILE_IO_EXCEPTION("Error in reading or writing file");

        public String message;

        ExceptionType(String message) {
            this.message = message;
        }
    }

    public ExceptionType type;

    public AddressBookException(ExceptionType type) {
        super(type.message);
        this.type = type;
    }
}
