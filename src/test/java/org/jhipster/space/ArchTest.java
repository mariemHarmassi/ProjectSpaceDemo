package org.jhipster.space;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.jhipster.space");

        noClasses()
            .that()
                .resideInAnyPackage("org.jhipster.space.service..")
            .or()
                .resideInAnyPackage("org.jhipster.space.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.jhipster.space.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
