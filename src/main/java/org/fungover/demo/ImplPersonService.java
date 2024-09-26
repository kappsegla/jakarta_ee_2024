package org.fungover.demo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named("Impl")
public class ImplPersonService implements PersonService {

    public ImplPersonService() {
        System.out.println("PersonService object created");
    }

    public List<Person> getAllPersons() {
        return List.of(new Person("Kalle", 12),
                new Person("Anna", 3));
    }
}
