package org.fungover.demo.persons;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.fungover.demo.validate.FirstLetterUppercase;

public record Person(
        @NotEmpty(message = "Empty names not allowed")
        @FirstLetterUppercase
        String name,
        @Min(value = 18, message = "Age should not be less than 18")
        @Max(value = 150, message = "Age should not be greater than 150")
        //@Age
        int age) {
}
