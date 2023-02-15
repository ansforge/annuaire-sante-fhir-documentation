/*
 * (c) Copyright 1998-2022, ANS. All rights reserved.
 */

package fr.ans.asaf.demo;


import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Organization;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Samples in this file show how to get results with a parameterized request
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class ParametersSearchTest {

    private static final Logger logger = LoggerFactory.getLogger(ParametersSearchTest.class);


    /**
     * This sample demonstrates how to fetch with no modifier parameter
     */
    @Test
    public void searchNoModifierParameter() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the name search parameter :
        var matchSearchClause = Organization.NAME.matches().value("Renard");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(matchSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: name={}", organization.getName());
        }
    }


    /**
     * This sample demonstrates how to fetch with contains parameter
     */
    @Test
    public void searchContainsParameter() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the name search parameter :
        var containsSearchClause = Organization.NAME.contains().value("EURL");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(containsSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: name={}", organization.getName());
        }
    }


    /**
     * This sample demonstrates how to fetch with exact parameter
     */
    @Test
    public void searchExactParameter() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the name search parameter :
        var exactSearchClause = Organization.NAME.matchesExactly().value("Gautier EURL");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(exactSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
        }
    }


    /**
     * This sample demonstrates how to fetch with token parameter
     */
    @Test
    public void searchTokenCode() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the name search parameter :
        var tokenSearchClause = Organization.IDENTIFIER.exactly().code("org-org-148");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(tokenSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
        }
    }


    /**
     * This sample demonstrates how to fetch with "and" operand parameter
     */
    @Test
    public void searchAndParameter() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the two name search parameters :
        var firstSearchClause = Organization.NAME.contains().value("Renard");
        var secondSearchClause = Organization.NAME.contains().value("et");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(firstSearchClause)
                .and(secondSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: id={} | name={}", organization.getIdElement().getIdPart(), organization.getName());
        }
    }


    /**
     * This sample demonstrates how to fetch with "or" operand parameter
     */
    @Test
    public void searchOrParameter() {
        // create the client:
        var client = FhirTestUtils.createClient();

        // create the two name search parameters :
        var orSearchClause = Organization.NAME.contains().values("Renard", "et");

        var bundle = client.search()
                .forResource(Organization.class)
                .where(orSearchClause)
                .returnBundle(Bundle.class).execute();

        for (var organizationEntry : bundle.getEntry()) {
            // cast entry :
            var organization = (Organization) organizationEntry.getResource();
            // print data :
            logger.info("Organization found: id={} | name={}", organization.getIdElement().getIdPart(), organization.getName());
        }
    }

}
