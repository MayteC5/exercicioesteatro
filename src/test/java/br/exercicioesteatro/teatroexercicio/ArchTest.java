package br.exercicioesteatro.teatroexercicio;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.exercicioesteatro.teatroexercicio");

        noClasses()
            .that()
            .resideInAnyPackage("br.exercicioesteatro.teatroexercicio.service..")
            .or()
            .resideInAnyPackage("br.exercicioesteatro.teatroexercicio.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.exercicioesteatro.teatroexercicio.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
