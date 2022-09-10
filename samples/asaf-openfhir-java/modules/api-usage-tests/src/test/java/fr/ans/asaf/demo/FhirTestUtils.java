package fr.ans.asaf.demo;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

import java.io.IOException;


/**
 * Utility classe used for testing
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public final class FhirTestUtils {


    /**
     * Create a HAPI fhir client
     * @return the fhir client
     */
    public static IGenericClient createClient(){
        var ctx = FhirContext.forR4();
        var client = ctx.newRestfulGenericClient("https://gateway.api.esante.gouv.fr/fhir");
        client.registerInterceptor(new LoggingInterceptor());
        client.registerInterceptor(new IClientInterceptor() {
            @Override
            public void interceptRequest(IHttpRequest iHttpRequest) {
                iHttpRequest.addHeader("ESANTE-API-KEY", "eb2e94fa-ffe6-491f-aa9d-073f6a5a2415");
            }

            @Override
            public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {

            }
        });
        return client;
    }


    private FhirTestUtils(){}

}
