package fr.ans.asaf.demo;

import ca.uhn.fhir.rest.gclient.DateClientParam;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Device;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Samples in this file show how to search Devices
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class DeviceTest {

    private static final Logger logger = LoggerFactory.getLogger(DeviceTest.class);

    /**
     * Search all devices
     */
    @Test
    public void searchAll() {
        // create the client:
        var client = FhirTestUtils.createClient();

        var bundle = client.search().forResource(Device.class).returnBundle(Bundle.class).execute();

        for (var deviceEntry : bundle.getEntry()) {
            // print Organization ids:
            var device = (Device) deviceEntry.getResource();
            logger.info("Device found: id={} name={}", device.getIdElement().getIdPart(), device.getDeviceNameFirstRep().getName());
        }
    }

    /**
     * Search all devices modified from a given date
     */
    @Test
    public void searchFromModifiedDate() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the date search parameter :
        var dateParam = new DateClientParam("_lastUpdated");

        var bundle = client.search()
                .forResource(Device.class)
                .where(dateParam.afterOrEquals().second("2022-08-07T14:51:04"))
                .returnBundle(Bundle.class).execute();

        for (var deviceEntry : bundle.getEntry()) {
            // print Organization ids:
            var device = (Device) deviceEntry.getResource();
            logger.info("Device found: id={} name={}", device.getIdElement().getIdPart(), device.getDeviceNameFirstRep().getName());
        }
    }

    /**
     * FIXME : filter isn't right
     * Search all devices containing arhgos number
     */
    @Test
    public void searchFromType() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // FIXME : filter isn't right
        // create the type search parameter :
        var arhgosParam = new StringClientParam("_number-authorization-arhgos");

        var bundle = client.search()
                .forResource(Device.class)
                .where(arhgosParam.contains().value("44-sdf"))
                .returnBundle(Bundle.class).execute();

        for (var deviceEntry : bundle.getEntry()) {
            // print Organization ids:
            var device = (Device) deviceEntry.getResource();
            logger.info("Device found: id={} type={}", device.getIdElement().getIdPart(), device.getType().getCodingFirstRep().getDisplay());
        }
    }

    /**
     * Search devices by identifier
     */
    @Test
    public void searchByIdentifier() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the identifier search parameters :
        var identifierParams = Device.IDENTIFIER.exactly().codes("dev-device-147", "dev-device-388");

        var bundle = client.search()
                .forResource(Device.class)
                .where(identifierParams)
                .returnBundle(Bundle.class).execute();

        for (var deviceEntry : bundle.getEntry()) {
            // print Device data
            var device = (Device) deviceEntry.getResource();
            logger.info("Device found: id={}", device.getIdentifierFirstRep().getValue());
        }
    }

    /**
     * Search devices by status
     */
    @Test
    public void searchByStatus() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the active search parameters :
        var activeParams = Device.STATUS.exactly().code(Device.FHIRDeviceStatus.ACTIVE.toCode());

        var bundle = client.search()
                .forResource(Device.class)
                .where(activeParams)
                .returnBundle(Bundle.class).execute();

        for (var deviceEntry : bundle.getEntry()) {
            // print Device data
            var device = (Device) deviceEntry.getResource();
            logger.info("Device found: id={} | status={}", device.getIdElement().getIdPart(), device.getStatus().getDisplay());
        }
    }


}
