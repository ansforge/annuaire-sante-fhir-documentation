package fr.ans.asaf.demo;

import ca.uhn.fhir.rest.gclient.DateClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.HealthcareService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Samples in this file show how to search Healthcare Service
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class HealthCareServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(HealthCareServiceTest.class);

    /**
     * Search all healthcare services
     */
    @Test
    public void searchAll() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var bundle = client.search().forResource(HealthcareService.class).returnBundle(Bundle.class).execute();

        for (var healthcareServiceEntry : bundle.getEntry()) {
            // print HealthcareService data:
            var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
            logger.info("Healthcare Service found: id={}", healthcareService.getIdentifierFirstRep().getValue());
        }
    }

    /**
     * Search healthcare services by identifier
     */
    @Test
    public void searchByIdentifier() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var typeSearchClause = HealthcareService.IDENTIFIER.exactly().codes("52-52-49883");

        var bundle = client.search()
                .forResource(HealthcareService.class)
                .where(typeSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var healthcareServiceEntry : bundle.getEntry()) {
            // print HealthcareService data:
            var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
            logger.info("Healthcare Service found: id={}", healthcareService.getIdElement().getIdPart());
        }
    }

    /**
     * Search active healthcare services
     */
    @Test
    public void searchActive() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var activeSearchClause = HealthcareService.ACTIVE.exactly().code("true");

        var bundle = client.search()
                .forResource(HealthcareService.class)
                .where(activeSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var healthcareServiceEntry : bundle.getEntry()) {
            // print HealthcareService data:
            var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
            logger.info("Healthcare Service found: id={} | status={}", healthcareService.getIdentifierFirstRep().getValue(), healthcareService.getActive());
        }
    }

    /**
     * Search healthcare services "Chirurgie ambulatoire"
     */
    @Test
    public void searchByCharacteristic1() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var characteristicSearchClause = HealthcareService.CHARACTERISTIC.exactly().codes(
      "https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite",
                "07"
        );

        var bundle = client.search()
                .forResource(HealthcareService.class)
                .where(characteristicSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var healthcareServiceEntry : bundle.getEntry()) {
            // print HealthcareService data:
            var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
            var healthcareServiceCoding = healthcareService.getCharacteristicFirstRep().getCodingFirstRep();
            String characteristicData = healthcareServiceCoding.getSystem().concat("|").concat(healthcareServiceCoding.getCode());
            logger.info("Healthcare Service found: id={} | characteristic={}", healthcareService.getIdElement().getIdPart(), characteristicData);
        }
    }

    /**
     * Search healthcare services "Hébergement complet internat"
     */
    @Test
    public void searchByCharacteristic2() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var characteristicSearchClause = HealthcareService.CHARACTERISTIC.exactly().systemAndValues(
                "https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite",
                "11"
        );

        var bundle = client.search()
                .forResource(HealthcareService.class)
                .where(characteristicSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var healthcareServiceEntry : bundle.getEntry()) {
            // print HealthcareService data:
            var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
            var healthcareServiceCoding = healthcareService.getCharacteristicFirstRep().getCodingFirstRep();
            String characteristicData = healthcareServiceCoding.getSystem().concat("|").concat(healthcareServiceCoding.getCode());
            logger.info("Healthcare Service found: id={} | characteristic={}", healthcareService.getIdElement().getIdPart(), characteristicData);
        }
    }

    @Test
    public void searchModifiedFrom() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the date search parameter :
        var dateParam = new DateClientParam("_lastUpdated");

        var bundle = client.search()
                .forResource(HealthcareService.class)
                .where(dateParam.afterOrEquals().day("2022-08-18"))
                .returnBundle(Bundle.class).execute();

        for (var healthcareServiceEntry : bundle.getEntry()) {
            // cast entry :
            var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
            // print update date & id :
            logger.info("HealthcarService found: id={} lastUpdate={}", healthcareService.getIdElement().getIdPart(), healthcareService.getMeta().getLastUpdated());
        }
    }


}
