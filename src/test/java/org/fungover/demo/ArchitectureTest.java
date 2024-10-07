package org.fungover.demo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureTest {

    @Test
    @DisplayName("Rule resource layer only depends on service layer")
    void ruleResourcelayerOnlyDependesOnServiceLayer() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.fungover.demo");

        ArchRule rule = classes().that().haveNameMatching(".*Resource")
                .should().onlyHaveDependentClassesThat().resideInAPackage("org.fungover.demo.service");

        rule.check(importedClasses);
    }
}
