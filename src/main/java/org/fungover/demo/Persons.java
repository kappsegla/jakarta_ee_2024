package org.fungover.demo;

import java.util.List;

public record Persons(List<Person> persons, String statusCode) {
}
