package fr.ans.asaf.demo;

import ca.uhn.fhir.rest.gclient.DateClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Practitioner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Samples in this file show how to search Practitioners
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class PractitionerTest {

    private static final Logger logger = LoggerFactory.getLogger(PractitionerTest.class);

    /**
     * Search all practitioners
     */
    @Test
    public void searchAll() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var bundle = client.search().forResource(Practitioner.class).returnBundle(Bundle.class).execute();

        for (var practitionerEntry : bundle.getEntry()) {
            // print Organization ids:
            var practitioner = (Practitioner) practitionerEntry.getResource();
            logger.info("Practitioner found: id={} name={}", practitioner.getIdentifierFirstRep().getValue(), practitioner.getNameFirstRep().getNameAsSingleString());
        }
    }

    /**
     * Search practitioner by identifier
     */
    @Test
    public void searchByIdentifier() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var identifierParams = Practitioner.IDENTIFIER.exactly().codes("p-pra-738", "p-pra-978");

        var bundle = client.search()
                .forResource(Practitioner.class)
                .where(identifierParams)
                .returnBundle(Bundle.class).execute();

        for (var practitionerEntry : bundle.getEntry()) {
            // print Organization ids:
            var practitioner = (Practitioner) practitionerEntry.getResource();
            logger.info("Practitioner found: id={} name={}", practitioner.getIdentifierFirstRep().getValue(), practitioner.getNameFirstRep().getNameAsSingleString());
        }
    }

    /**
     * Search practitioner by name's prefix
     */
    @Test
    public void searchByNamePrefix() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var nameSearchClause = Practitioner.NAME.matches().value("Dr");

        var bundle = client.search()
                .forResource(Practitioner.class)
                .where(nameSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var practitionerEntry : bundle.getEntry()) {
            // print Organization ids:
            var practitioner = (Practitioner) practitionerEntry.getResource();
            logger.info("Practitioner found: id={} name={}", practitioner.getIdentifierFirstRep().getValue(), practitioner.getNameFirstRep().getNameAsSingleString());
        }
    }

    /**
     * Search practitioner by active status
     */
    @Test
    public void searchActive() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var activeSearchClause = Practitioner.ACTIVE.exactly().code("true");

        var bundle = client.search()
                .forResource(Practitioner.class)
                .where(activeSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var practitionerEntry : bundle.getEntry()) {
            // print Organization ids:
            var practitioner = (Practitioner) practitionerEntry.getResource();
            logger.info("Practitioner found: name={} | active={}", practitioner.getNameFirstRep().getNameAsSingleString(), practitioner.getActive());
        }
    }

    /**
     * Search practitioner by modification date
     */
    @Test
    public void searchModifiedFrom() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the date search parameter :
        var dateParam = new DateClientParam("_lastUpdated");

        var bundle = client.search()
                .forResource(Practitioner.class)
                .where(dateParam.afterOrEquals().second("2022-08-08T06:47:02"))
                .returnBundle(Bundle.class).execute();

        for (var practitionerEntry : bundle.getEntry()) {
            // cast entry :
            var practitioner = (Practitioner) practitionerEntry.getResource();
            // print update date & id :
            logger.info("Practitioner found: id={} | lastUpdate={}", practitioner.getIdElement().getIdPart(), practitioner.getMeta().getLastUpdated());
        }
    }


}
