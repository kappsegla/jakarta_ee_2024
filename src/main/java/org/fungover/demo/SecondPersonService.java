package org.fungover.demo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named("Second")
public class SecondPersonService implements PersonService {
    @Override
    public List<Person> getAllPersons() {
        return List.of();
    }
}
