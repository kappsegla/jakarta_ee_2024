package org.fungover.demo.service;

import org.fungover.demo.persons.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    void addPerson(Person person);

    int getCount();
}
