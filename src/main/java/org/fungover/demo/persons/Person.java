package org.fungover.demo.persons;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record Person(
        @NotEmpty(message = "Empty names not allowed")
        String name,
        @Min(value = 18, message = "Age should not be less than 18")
        @Max(value = 150, message = "Age should not be greater than 150")
        int age) {
}
