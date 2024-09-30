package org.fungover.demo.persons;

import java.util.List;

public record Persons(List<Person> persons, String statusCode) {
}
