/*
 * (c) Copyright 1998-2022, ANS. All rights reserved.
 */

package fr.ans.asaf.demo;


import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.rest.param.DateRangeParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Device;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Samples in this file show how to get results with the search api
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class SimpleSearchTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSearchTest.class);


    /**
     * This sample demonstrates how to fetch a full collection of a type
     */
    @Test
    public void getAllDevices(){

        var client = FhirTestUtils.createClient();

        // Use the search api to read all pages of the Device resource:

        // Read the first page
        var deviceBundle = (Bundle) client.search()
                .forResource(Device.class) // the type of the resource
                .count(500) // page size
                .execute();

        // deviceBundle contains the first page. The resource can have more pages, so we can get next pages like this:
        do {
            var bundleContent = deviceBundle.getEntry();
            // print results:
            for(var entry : bundleContent){
                var device = (Device) entry.getResource();
                logger.debug("Device read from the api with id: {}", device.getId());
            }
            deviceBundle = client.loadPage().byUrl(deviceBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
        }while (deviceBundle.getLink("next") != null);
    }


    /**
     * This example show how to query elements with the _lastUpdated param.
     * With this query, you can make incremental updated of your repository.
     */
    @Test
    public void searchWithLastUpdated(){
        var client = FhirTestUtils.createClient();

        // the _lastUpdated param
        var dateParam = new StringClientParam("_lastUpdated");

        // some uses cases of the _lastUpdatedParam:

        // after the 2022-05-01 (excluded):
        var deviceBundle = (Bundle) client.search().forResource(Device.class)
                .where(dateParam.matches().value("gt2022-05-01"))
                .execute();
        logger.debug("Count after the 2022-05-01: {}", deviceBundle.getTotal());


        // in 2022 (included):
        deviceBundle = (Bundle) client.search().forResource(Device.class)
                .where(dateParam.matches().value("eq2022"))
                .execute();
        logger.debug("Count in 2022: {}", deviceBundle.getTotal());

        // before 2022 (excluded):
        deviceBundle = (Bundle) client.search().forResource(Device.class)
                .where(dateParam.matches().value("lt2022"))
                .execute();
        logger.debug("Count before 2022: {}", deviceBundle.getTotal());


        // Note that Hapi allow the use of a specific api for date:
        deviceBundle = (Bundle) client.search().forResource(Device.class)
                .lastUpdated(new DateRangeParam().setLowerBound("2022"))// is like ?_lastUpdated=ge2022
                .execute();
        logger.debug("Count before 2022 (included): {}", deviceBundle.getTotal());

    }

}
