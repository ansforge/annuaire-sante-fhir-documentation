/*
 * (c) Copyright 1998-2022, ANS. All rights reserved.
 */

package fr.ans.asaf.demo;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * This test show how to get data of the repository with consistency on multiple resources.
 * With this sample, you can get Practitioner, PractitionerRoles and Organizations and ensure that no modification will corrupt your search
 * (if a modification/deletion of an object occur during the fetch, you will not show this modification).
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class ConsistentSearchTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSearchTest.class);

    // Create the Hapi fhir client:

    /**
     * Create the Fhir client and set an interceptor
     * @return the client
     */
    IGenericClient createClient(){
        var ctx = FhirContext.forR4();
        var client = ctx.newRestfulGenericClient("https://hapi.fhir.org/baseR4");
        // TODO set the interceptor
        return client;
    }

    /**
     * In this sample, we want to get all practitioner, practitioner roles and organizations that have a PractitionerRole with specialty "s3".
     *
     * Important note: The search is consistent but you can get elements related to PractitionerRoles more than one time during the import. This is due to the paging.
     * PractitionerRole elements will be unique.
     */
    @Test
    public void searchPractitionerRoleRelatedDataWithConsistency(){
        var client = createClient();

        // The practitioner role know the practitioner and the organization, so the main request is on PractitionerRole and then we ask
        // the server to include related Organization and Practitioner:

        var bundleWithAllResources = (Bundle) client.search().forResource(PractitionerRole.class)
                // inclusion:
                .include(PractitionerRole.INCLUDE_PRACTITIONER.asNonRecursive()) // the server doesn't support recursive one
                .include(PractitionerRole.INCLUDE_ORGANIZATION.asNonRecursive())
                // specialty filter:
            //    .where(PractitionerRole.SPECIALTY.exactly().codes("s3"))
                .count(10)
                .execute();

        // FIXME ce cas d'usage n'est pas géniale, on récupère des doublons etc...
        // FIXME Préférer ne pas parler de la consistance et plutot orienter sur un revinclude pour medecins / roles

        // the bundle contains the page of PractitionerRole elements and additional elements for related Organization and Practitioner:
        for(var entry : bundleWithAllResources.getEntry()){
            switch (entry.getResource().fhirType()) {
                case "PractitionerRole":
                    break;
                case "Practitioner":
                    break;
                case "Organization":
                    break;
                default:
                    break;
            }
        }

        // if you want to know which Practitioner/Organization is corresponding to a PractitionerRole you can do it with practitioner/organization field in the PractitionerRole resource:

        var practitionerRoles = bundleWithAllResources.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "PractitionerRole".equals(e.fhirType())).map(PractitionerRole.class::cast).collect(Collectors.toList());
        var practitioners = bundleWithAllResources.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Practitioner".equals(e.fhirType())).map(Practitioner.class::cast).collect(Collectors.toList());
        var organizations = bundleWithAllResources.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Organization".equals(e.fhirType())).map(Organization.class::cast).collect(Collectors.toList());


        practitionerRoles.forEach(practitionerRole -> {
            var practitionerReference = practitionerRole.getPractitioner();
            if(!practitionerReference.isEmpty()) {
                var practitionerCount = practitioners.stream().filter(p -> ("Practitioner/"+p.getIdElement().getIdPart()).equals(practitionerReference.getReference())).count();
                logger.debug("Found related practitioners: {}", practitionerCount);
            }// else no matching practitioner


            var organizationReference = practitionerRole.getOrganization();
            if(!practitionerReference.isEmpty()) {
                var organizationCount = organizations.stream().filter(o -> ("Organization/"+o.getIdElement().getIdPart()).equals(organizationReference.getReference())).count();
                logger.debug("Found related organizations: {}", organizationCount);
            }// else no matching organization


        });

    }



}
