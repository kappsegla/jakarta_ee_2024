package org.fungover.demo.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.fungover.demo.persons.Person;

import java.util.List;

@ApplicationScoped
@Named("Second")
public class SecondPersonService implements PersonService {
    @Override
    public List<Person> getAllPersons() {
        return List.of();
    }

    @Override
    public void addPerson(Person person) {

    }

    @Override
    public int getCount() {
        return 0;
    }
}
