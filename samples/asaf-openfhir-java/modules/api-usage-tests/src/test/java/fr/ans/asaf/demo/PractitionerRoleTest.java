package fr.ans.asaf.demo;

import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * Samples in this file show how to search Practitioner Roles
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class PractitionerRoleTest {

    private static final Logger logger = LoggerFactory.getLogger(PractitionerRoleTest.class);

    /**
     * Search all practitioner roles
     */
    @Test
    public void searchAll() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var bundle = client.search().forResource(PractitionerRole.class).returnBundle(Bundle.class).execute();

        for (var roleEntry : bundle.getEntry()) {
            // print PractitionerRole data:
            var role = (PractitionerRole) roleEntry.getResource();
            logger.info("Practitioner Role found: id={} code={}", role.getIdElement().getIdPart(), role.getCodeFirstRep().getCodingFirstRep().getCode());
        }
    }


    /**
     * Search practitioner role by roles
     */
    @Test
    public void searchByRoles() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var firstCodeClause = PractitionerRole.ROLE.exactly().code("40");
        var secondCodeClause = PractitionerRole.ROLE.exactly().code("E");

        var bundle = client.search()
                .forResource(PractitionerRole.class)
                .where(firstCodeClause)
                .and(secondCodeClause)
                .returnBundle(Bundle.class).execute();

        for (var roleEntry : bundle.getEntry()) {
            // print PractitionerRole data:
            var role = (PractitionerRole) roleEntry.getResource();
            var roleCodes = role.getCode().stream().map(code ->
                    code.getCoding().stream().map(coding -> coding.getSystem() + ":" + coding.getCode()).collect(Collectors.joining("|"))
            ).collect(Collectors.joining(" - "));

            logger.info("Practitioner Role found: id={} codes={}", role.getIdElement().getIdPart(), roleCodes);
        }
    }

    /**
     * Search practitioner role by speciality
     */
    @Test
    public void searchBySpeciality() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var codeClause = PractitionerRole.ROLE.exactly().code("40");
        var specialityClause = PractitionerRole.SPECIALTY.exactly().code("SCD01");

        var bundle = client.search()
                .forResource(PractitionerRole.class)
                .where(codeClause)
                .and(specialityClause)
                .returnBundle(Bundle.class).execute();

        for (var roleEntry : bundle.getEntry()) {
            // print PractitionerRole data:
            var role = (PractitionerRole) roleEntry.getResource();

            // concat roles
            var roleCodes = role.getCode().stream().map(code ->
                    code.getCoding().stream().map(coding -> coding.getSystem().concat(":").concat(coding.getCode())).collect(Collectors.joining("|"))
            ).collect(Collectors.joining(" - "));

            // concat specialty
            roleCodes = roleCodes.concat("|")
                    .concat(role.getSpecialtyFirstRep().getCodingFirstRep().getSystem())
                    .concat(":")
                    .concat(role.getSpecialtyFirstRep().getCodingFirstRep().getCode());

            logger.info("Practitioner Role found: id={} codes={}", role.getIdElement().getIdPart(), roleCodes);
        }
    }

    /**
     * Search practitioner role by smart card type
     */
    @Test
    public void searchBySmartCardType() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the date search parameter :
        var smartCardClause = new StringClientParam("type-smartcard").matches().value("CPS");

        var bundle = client.search()
                .forResource(PractitionerRole.class)
                .where(smartCardClause)
                .returnBundle(Bundle.class).execute();

        for (var roleEntry : bundle.getEntry()) {
            // cast entry :
            var role = (PractitionerRole) roleEntry.getResource();
            // print update date & id :
            logger.info("Practitioner Role found: id={}", role.getIdElement().getIdPart());
        }
    }

    /**
     * Search active practitioner roles
     */
    @Test
    public void searchActive() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var activeSearchClause = PractitionerRole.ACTIVE.exactly().code("true");

        var bundle = client.search()
                .forResource(PractitionerRole.class)
                .where(activeSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var roleEntry : bundle.getEntry()) {
            // print PractitionerRole data:
            var role = (PractitionerRole) roleEntry.getResource();
            logger.info("Practitioner Role found: id={} active={}", role.getIdElement().getIdPart(), role.getActive());
        }
    }

    /**
     * Search practitioner role by practitioner
     */
    @Test
    public void searchByPractitioner() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var pratictionerSearchClause = PractitionerRole.PRACTITIONER.hasId("003-138020");

        var bundle = client.search()
                .forResource(PractitionerRole.class)
                .where(pratictionerSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var roleEntry : bundle.getEntry()) {
            // print PractitionerRole data:
            var role = (PractitionerRole) roleEntry.getResource();
            logger.info("Practitioner Role found: id={} practitioner={}", role.getIdElement().getIdPart(), role.getPractitioner().getReference());
        }
    }

    /**
     * Search practitioner roles by role
     */
    @Test
    public void searchByRole() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var roleSearchClause = PractitionerRole.ROLE.exactly().code("10");

        var bundle = client.search()
                .forResource(PractitionerRole.class)
                .where(roleSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var roleEntry : bundle.getEntry()) {
            // print PractitionerRole data:
            var role = (PractitionerRole) roleEntry.getResource();
            logger.info("Practitioner Role found: id={} code={}", role.getIdElement().getIdPart(), role.getCodeFirstRep().getCodingFirstRep().getCode());
        }
    }


}
