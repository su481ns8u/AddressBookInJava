package com.addressbook.enums;

import com.addressbook.models.Person;

import java.util.Comparator;

public enum SortParameters {
    NAME(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName)),
    CITY(Comparator.comparing(Person::getCity)),
    STATE(Comparator.comparing(Person::getState)),
    ZIP(Comparator.comparing(Person::getZip));

    public final Comparator<? super Person> comparator;

    SortParameters(Comparator<? super Person> comparator) {
        this.comparator = comparator;
    }
}
