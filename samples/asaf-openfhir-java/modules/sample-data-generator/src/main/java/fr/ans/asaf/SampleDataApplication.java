package fr.ans.asaf;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import fr.ans.asaf.service.SampleDataService;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DomainResource;

import java.io.IOException;
import java.util.List;

/**
 * A sample application that can generate fake data and insert it into a FHIR server.
 * The application can generate Device, Organization, Practitioner, PractitionerRole, HealthcareService.
 *
 * The generated samples contains date that match the ANS profiles.
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class SampleDataApplication {


    /**
     * Entry point that generate data
     */
    public static void main(String[] args){
        var client = setupClient("http://localhost:8083/fhir/v1", "1234");
        var samples = SampleDataService.generate(1000);

        insertResources(client, samples.getDeviceList());
        insertResources(client, samples.getOrganizationList());
        insertResources(client, samples.getPractitionerList());
        insertResources(client, samples.getPractitionerRoleList());
        insertResources(client, samples.getHealthcareServiceList());
    }

    private static void insertResources(IGenericClient client, List<? extends DomainResource> resourceList){
        var bundle = new Bundle();
        bundle.setType(Bundle.BundleType.TRANSACTION);
        for(var r : resourceList) {
            bundle.addEntry()
                    .setFullUrl(r.getResourceType().name() + "/" + r.getIdElement().getValue())
                    .setResource(r)
                    .getRequest()
                    .setUrl(r.getResourceType().name())
                    .setMethod(Bundle.HTTPVerb.PUT);
        }
        client.transaction().withBundle(bundle).execute();
    }


    private static IGenericClient setupClient(String baseUrl, String writeModeSecureKey) {
        var ctx = FhirContext.forR4();
        var client = ctx.newRestfulGenericClient(baseUrl);
        client.registerInterceptor(new LoggingInterceptor(false));
        client.registerInterceptor(new IClientInterceptor() {
            @Override
            public void interceptRequest(IHttpRequest iHttpRequest) {
                iHttpRequest.addHeader("DP_SECURE_KEY", writeModeSecureKey);
            }

            @Override
            public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {

            }
        });
        return client;
    }

}
