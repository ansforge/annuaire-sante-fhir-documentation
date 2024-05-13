package fr.ans.asaf.demo;

import ca.uhn.fhir.rest.gclient.DateClientParam;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Organization;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * Samples in this file show how to search Organizations
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class OrganizationTest {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationTest.class);

    /**
     * Search all organizations
     */
    @Test
    public void searchAll() {
        // create the client:
        var client = FhirTestUtils.createClient();
        var bundle = client.search().forResource(Organization.class).returnBundle(Bundle.class).execute();
        for (var organizationEntry : bundle.getEntry()) {
            // print Organization ids:
            var organization = (Organization) organizationEntry.getResource();
            logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
        }
    }

    @Test
    public void searchModifiedFrom() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the date search parameter :
        var dateParam = new DateClientParam("_lastUpdated");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(dateParam.afterOrEquals().second("2022-08-05T14:51:04"))
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print update date & id :
            logger.info("Organization found: id={} lastUpdate={}", organization.getIdElement().getIdPart(), organization.getMeta().getLastUpdated());
        }
    }

    @Test
    public void searchByIdentifiers() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the identifier search parameter :
        var idParam = new StringClientParam("identifier");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(idParam.matches().values("001604103000", "01603998400", "001604252500"))
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print id :
            logger.info("Organization found: id={}", organization.getIdentifierFirstRep().getValue());
        }
    }

    @Test
    public void searchByFinessNumber() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create finess where clause
        var finessSearchClause = Organization.IDENTIFIER.exactly().systemAndValues(
                "https://finess.esante.gouv.fr", "010000602", "010000628", "010000735");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(finessSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print id :
            logger.info("Organization found: id={}", organization.getIdentifierFirstRep().getValue());
        }
    }

    @Test
    public void searchByType() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the type search parameter :
        var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
                "http://interopsante.org/fhir/CodeSystem/fr-v2-3307", "GEOGRAPHICAL-ENTITY");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(typeSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print id :
            var organizationCodes = organization.getType().stream().map(type -> type.getCodingFirstRep().getCode()).collect(Collectors.joining(" - "));
            logger.info("Organization found: name={} type={}", organization.getName(), organizationCodes);
        }
    }


    @Test
    public void searchByINSEENomenclature() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the search parameter :
        var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
                "https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5", "82.19Z");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(typeSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            var organizationCodes = organization.getType().stream().map(type -> type.getCodingFirstRep().getCode()).collect(Collectors.joining(" - "));
            logger.info("Organization found: id={} type={}", organization.getName(), organizationCodes);
        }
    }

    @Test
    public void searchByActivity() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the activity search parameter :
        var activitySearchClause = Organization.TYPE.exactly().systemAndValues(
                "https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite", "SA29");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(activitySearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            var activityName = organization.getTypeFirstRep().getCodingFirstRep().getDisplay();
            logger.info("Organization found: name={} activity={}", organization.getName(), activityName);
        }
    }

    @Test
    public void searchByNameTwoTerms() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the name search parameter :
        var nameSearchClause = Organization.NAME.contains().values("imagerie", "centre");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(nameSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: name={}", organization.getName());
        }
    }

    @Test
    public void searchByZipCode() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the postal code search parameter :
        var nameSearchClause = Organization.ADDRESS_POSTALCODE.matchesExactly().values("13290", "13321");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(nameSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: name={} | zipCode={}", organization.getName(), organization.getAddressFirstRep().getPostalCode());
        }
    }


}
