package fr.ans.asaf.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);

    /**
     * Create the Fhir client and set an interceptor
     *
     * @return the client
     */
    private IGenericClient createClient() {
        var ctx = FhirContext.forR4();
        var client = ctx.newRestfulGenericClient("https://gateway.api.esante.gouv.fr/fhir");
        // to debug:
        var logger = new LoggingInterceptor();
        logger.setLogRequestSummary(true);
        logger.setLogResponseBody(false);

        client.registerInterceptor(logger);
        client.registerInterceptor(new IClientInterceptor() {
            @Override
            public void interceptRequest(IHttpRequest iHttpRequest) {
                iHttpRequest.addHeader("ESANTE-API-KEY", "38b12cef-f013-49fd-aef3-2a1859da5184");
            }

            @Override
            public void interceptResponse(IHttpResponse iHttpResponse) {
            }
        });

        return client;
    }

    public void getMedicoSociauxFiness() {
        var client = createClient();

        // construct category search clause
        var codes = Arrays.asList("159","165","166","172","175","176","177", "178","180","182","183","186","188","189","190","192","194","195","196","197","198","202","207","209","213","221","236","238","241","246","247","249","252","253","255","286","295","344","354","370","377","378","379","381","382","390","395","396","402","411","418","427","437","440","441","445","446","448","449","453","460","462","500","501","502");
        var activityClause = Organization.TYPE.exactly()
                .systemAndValues("https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement", codes);

        // construct identifier search clause
        var identifierClause = Organization.IDENTIFIER.hasSystemWithAnyCode("http://finess.sante.gouv.fr");

        // create and execute request
        var bundle = client
                .search()
                .forResource(Organization.class)
                .where(activityClause)
                .and(identifierClause)
                .returnBundle(Bundle.class)
                .execute();

        var hasNext = true;
        var finessOrganizations = new LinkedList<>();

        // for each page
        do {
            logger.info("total result {}", bundle.getTotal());

            // extract data from bundle
            finessOrganizations.add(bundle.getEntry());

            if (bundle.getLink("next") != null) {
                // get the next page
                bundle = client.loadPage().byUrl(bundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
            } else {
                hasNext = false;
            }
        } while(hasNext);

        logger.info("finess organization - {}", finessOrganizations.size());
    }

    public void getHospitals() {
        var client = createClient();

        // hospital:
        var bHospital = client.search().forResource(Organization.class)
                .where(Organization.TYPE.exactly().codes("SA01","SA02","SA03","SA04","SA30","SA34","SA36"))
                .returnBundle(Bundle.class).execute();
        var totalHospital = bHospital.getTotal();
        var typesAsStringList = Arrays.asList("159","165","166","172","175","176","177", "178","180","182","183","186","188","189","190","192","194","195","196","197","198","202","207","209","213","221","236","238","241","246","247","249","252","253","255","286","295","344","354","370","377","378","379","381","382","390","395","396","402","411","418","427","437","440","441","445","446","448","449","453","460","462","500","501","502");
        var goodElements = new ArrayList<>();
        var hasNext = true;
        var treated = 0;

        logger.info("Total results - {}", bHospital.getTotal());

        do {
            var bundleContent = bHospital.getEntry();
            logger.info("Treat {} entry(ies)", bundleContent.size());

            for (var e : bundleContent) {
                var org = (Organization) e.getResource();
                var isHospital = true;

                // check hospital system & code
                for (var type : org.getType()) {
                    for (var coding : type.getCoding()) {
                        logger.info("system - {}", coding.getSystem());

                        if ("https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement".equals(coding.getSystem()) &&
                                typesAsStringList.contains(coding.getCode())) {
                            isHospital = false;
                            break;
                        }
                    }
                }

                // filter good elements
                if(isHospital) {
                    goodElements.add(org);
                }
            }

            // check if result has a next page
            if (bHospital.getLink("next")!=null) {
                bHospital = client.loadPage().byUrl(bHospital.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
            } else {
                hasNext = false;
            }

            treated += bundleContent.size();
            logger.info("Progress treated - {} / {}", treated, bHospital.getTotal());
        } while (hasNext);

        logger.info("Total global - {}", totalHospital);
        logger.info("Total Hospital - {}", goodElements.size());
    }

    public void getBiologyOrganization() {
        var client = createClient();

        // construct biology facility request
        var orgBundle = client.search().forResource(Organization.class)
                .where(Organization.TYPE.exactly().codes("SA25","SA29"))
                .revInclude(PractitionerRole.INCLUDE_ORGANIZATION)
                .returnBundle(Bundle.class).execute();

        var totalElements = orgBundle.getTotal();
        var goodElements = new ArrayList<>();
        var hasNext = true;
        var treated = 0;

        logger.info("Total results - {}", orgBundle.getTotal());

        do {
            var bundleContent = orgBundle.getEntry();
            var organizationMap = new LinkedHashMap<String, Organization>();

            for (var e : bundleContent) {
                // store the organization inside a map
                if(e.getResource() instanceof Organization) {
                    var org = (Organization) e.getResource();
                    organizationMap.put(org.getId(), org);
                }

                if(e.getResource() instanceof PractitionerRole) {
                    var role = (PractitionerRole) e.getResource();
                    var medicOrPharmacist = false;

                    // check if the Role contains a medic or pharmacist code
                    for(var code : role.getCode()) {
                        for(var coding : code.getCoding()) {
                            if (coding.getSystem().equals("https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante") &&
                                    (coding.getCode().equals("10") || coding.getCode().equals("21"))) {
                                medicOrPharmacist = true;
                                break;
                            }
                        }
                    }

                    // if the Role is right and still practicing, link it to the right Organization
                    if(medicOrPharmacist && !role.getPeriod().hasEnd() && role.getOrganization() != null) {
                        var org = (Organization) role.getOrganization().getResource();

                        if(organizationMap.containsKey(org.getId())) {
                            organizationMap.get(org.getId()).addContained(role);
                        }
                    }
                }
            }

            // loop over Organization and keep only those with roles
            for(var org : organizationMap.values()) {
                logger.info("Organization has {} roles", org.getContained().size());
                if(org.getContained().size() > 0) {
                    goodElements.add(org);
                }
            }

            // check if result has a next page
            if (orgBundle.getLink("next")!=null) {
                orgBundle = client.loadPage().byUrl(orgBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
            } else {
                hasNext = false;
            }

            treated += bundleContent.size();
            logger.info("Progress treated - {} / {}", treated, orgBundle.getTotal());
        } while (hasNext);

        logger.info("Total global - {}", totalElements);
        logger.info("Total Biology with  - {}", goodElements.size());
    }
}
