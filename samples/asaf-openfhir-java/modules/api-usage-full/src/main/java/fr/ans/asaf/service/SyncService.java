package fr.ans.asaf.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.PractitionerRole;

import java.util.stream.Collectors;

/**
 * Sample service that can fetch data from a FHIR and store result into the {@link StorageService}.
 *
 * This sample was build for IrisDp FHIR Api.
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class SyncService {

    private StorageService storageService = new StorageService();

    /**
     * Create the Fhir client and set an interceptor
     * @return the client
     */
    IGenericClient createClient(){
        var ctx = FhirContext.forR4();
        var client = ctx.newRestfulGenericClient("http://localhost:8080/fhir/v1");
        // to debug:
        var logger = new LoggingInterceptor();
        logger.setLogRequestSummary(true);
        logger.setLogResponseBody(true);

        client.registerInterceptor(logger);
        return client;
    }


    /**
     * Find all {@link PractitionerRole} of the remote service and filter by specialty with code "SM02".
     * Store the result in the {@link StorageService}.
     */
    public void syncFullSimple(){
        var client = createClient();
        var fhirBundle = (Bundle) client.search().forResource(PractitionerRole.class)
                // we want to filter on the specialty:
                .where(PractitionerRole.SPECIALTY.exactly().codes("SM02"))
                .count(10)
                .execute();
        do {
            var bundleContent = fhirBundle.getEntry();
            // Store Practitioner roles:
            storageService.storePractitionerRole(bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).map(PractitionerRole.class::cast).collect(Collectors.toList()));
            // load the next page:
            fhirBundle = client.loadPage().byUrl(fhirBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
        }while (fhirBundle.getLink("next") != null);
    }

    /**
     * Find all {@link PractitionerRole} of the remote service and filter by specialty with code "SM02".
     * Use the FHIR _include parameter to get Organization and Practitioner too.
     * Store the result in the {@link StorageService} with one call by type of resource FHIR.
     */
    public void syncFullWithRelatedObjects(){
        var client = createClient();
        var fhirBundle = (Bundle) client.search().forResource(PractitionerRole.class)
                // we want to filter on the specialty:
                .where(PractitionerRole.SPECIALTY.exactly().codes("SM02"))
                .include(PractitionerRole.INCLUDE_PRACTITIONER.asNonRecursive())
                .include(PractitionerRole.INCLUDE_ORGANIZATION.asNonRecursive())
                .count(10)
                .execute();
        do {
            var bundleContent = fhirBundle.getEntry();
            // Store all resources:
            var practitionerRoles = bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "PractitionerRole".equals(e.fhirType())).map(PractitionerRole.class::cast).collect(Collectors.toList());
            var practitioners = bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Practitioner".equals(e.fhirType())).map(Practitioner.class::cast).collect(Collectors.toList());
            var organizations = bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Organization".equals(e.fhirType())).map(Organization.class::cast).collect(Collectors.toList());

            storageService.storePractitionerRole(practitionerRoles);
            storageService.storePractitioner(practitioners);
            storageService.storeOrganization(organizations);

            // load the next page:
            fhirBundle = client.loadPage().byUrl(fhirBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
        }while (fhirBundle.getLink("next") != null);
    }




}
