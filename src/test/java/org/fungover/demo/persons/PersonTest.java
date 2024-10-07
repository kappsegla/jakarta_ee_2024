package org.fungover.demo.persons;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    private final Validator validator;

    public PersonTest() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    @Test
    void shouldNotAllowEmptyName() {
        Person person = new Person("", 20);

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Set<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertThat(violations).isNotEmpty();
        assertThat(messages).contains("Empty names not allowed");
    }

    @Test
    void shouldNotAllowNameWithoutFirstLetterUppercase() {
        Person person = new Person("john", 20);

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Set<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertThat(violations).isNotEmpty();
        assertThat(messages).contains("First letter must be uppercase");
    }

    @Test
    void shouldNotAllowAgeLessThan18() {
        Person person = new Person("John", 17);

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Set<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertThat(violations).isNotEmpty();
        assertThat(messages).contains("Age should not be less than 18");
    }

    @Test
    void shouldNotAllowAgeGreaterThan150() {
        Person person = new Person("John", 151);

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Set<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertThat(violations).isNotEmpty();
        assertThat(messages).contains("Age should not be greater than 150");
    }

    @Test
    void shouldAllowValidPerson() {
        Person person = new Person("John", 30);

        Set<ConstraintViolation<Person>> violations = validator.validate(person);

        assertThat(violations).isEmpty();
    }
}
