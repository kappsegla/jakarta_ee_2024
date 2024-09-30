package org.fungover.demo.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.fungover.demo.persons.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
@Named("Impl")
public class ImplPersonService implements PersonService {

    //Use decorator to make a normal ArrayList synchronized. Demands manual synchronization for iterator, stream
    private List<Person> persons = Collections.synchronizedList(new ArrayList<Person>());
    private List<Person> people = new CopyOnWriteArrayList<>();

    public ImplPersonService() {
        System.out.println("PersonService object created");
    }

    public List<Person> getAllPersons() {
        return List.copyOf(persons);
    }

    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public int getCount() {
        return persons.size();
    }
}
