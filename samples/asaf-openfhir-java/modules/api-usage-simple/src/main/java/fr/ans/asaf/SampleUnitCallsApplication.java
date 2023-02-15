package fr.ans.asaf;

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
import picocli.CommandLine;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;


/**
 * Demo application that shows how to make unit calls
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
@CommandLine.Command
public class SampleUnitCallsApplication implements Runnable {

    // logger to display results
    private static final Logger logger = LoggerFactory.getLogger(SampleUnitCallsApplication.class);

    // Options of the application (using the picocli library)
    @CommandLine.Option(names = { "-k", "--key" }, description = "The api key to access the api", required = true)
    private String key;


    @CommandLine.Option(names = { "-r", "--rpps" }, description = "The rpps number", required = true)
    private String rpps;



    /**
     * The run method when a user enter some command
     */
    public void run() {
        var client = this.createClient();

        var practitionerBundle = (Bundle) client.search().forResource(Practitioner.class).where(Practitioner.IDENTIFIER.exactly().codes(rpps)).execute();
        if(!practitionerBundle.hasEntry()){
            logger.info("No results found with this rpps number");
            return;
        }

        // get the practitioner:
        var practitioner = (Practitioner) practitionerBundle.getEntry().get(0).getResource();
        // get associated practitioner roles:
        var practitionerRoleBundle = (Bundle) client.search().forResource(PractitionerRole.class).where(PractitionerRole.PRACTITIONER.hasId(practitioner.getIdElement().getIdPart())).include(new Include("PractitionerRole:organization")).execute();
        var practitionerRolesAndOrganizations = practitionerRoleBundle.getEntry().stream().map(pre -> pre.getResource()).collect(Collectors.toList());
        var practitionerRoles = practitionerRolesAndOrganizations.stream().filter(pre -> pre instanceof PractitionerRole).map(PractitionerRole.class::cast).collect(Collectors.toList());
        var organizations = practitionerRolesAndOrganizations.stream().filter(pre -> pre instanceof Organization).map(Organization.class::cast).collect(Collectors.toList());

        logger.info("---------------------------");
        logger.info("Professionnel "+rpps+": ");
        logger.info("\tId technique (champs id) : " + practitioner.getIdElement().getIdPart());
        logger.info("\tIdentifiants:");
        for(var id : practitioner.getIdentifier()) {
            logger.info("\tNuméro " + id.getSystem() + " : " + id.getValue());
        }

        if(practitioner.hasName()) {
            logger.info("\n\tDémographie:");
            logger.info("\tCivilité : " + practitioner.getNameFirstRep().getPrefix());
        }

        if(!practitionerRoles.isEmpty()) {
            logger.info("\n\tExercices:");
            for (var practitionerRole : practitionerRoles) {
                logger.info("\t\tExercice " + practitionerRole.getIdElement().getIdPart() + ":");
                logger.info("\t\tNoms :" + displayPractitionerRoleName(practitionerRole));
                logger.info("\t\tSpécialités : " + displayCodeableConceptListAsString(practitionerRole.getSpecialty()));
            }
        }
        logger.info("---------------------------");

    }


    /**
     * Show the PractitionerRole name if exists.
     * In the api, the name of the Practitioner role is set in an extension: https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/practitionerRole-name
     * @param pr the practitioner role
     * @return the name as a string
     */
    String displayPractitionerRoleName(PractitionerRole pr){
        var ext = pr.getExtensionByUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/practitionerRole-name");
        if(ext == null) {
            return "-";
        }
        var humanName = (HumanName) ext.getValue();
        return humanName.getGiven() + " " + humanName.getFamily();
    }



    String displayCodeableConceptListAsString(Collection<CodeableConcept> codeableConcepts){
        var sb = new StringBuilder();
        for(var c : codeableConcepts){
            sb.append(this.displayCodingListAsString(c.getCoding()));
        }
        return sb.toString();
    }

    String displayCodingListAsString(Collection<Coding> codings){
        var sb = new StringBuilder();

        for(var coding : codings){
            sb.append(coding.getCode()+ " ");
        }

        return sb.toString();
    }

    /**
     * Create the Fhir client and set an interceptor
     * @return the client
     */
    IGenericClient createClient(){
        var ctx = FhirContext.forR4();
        var client = ctx.newRestfulGenericClient("https://gateway.api.esante.gouv.fr/fhir/v1");
        // to debug:
        var logger = new LoggingInterceptor();
        logger.setLogRequestSummary(true);
        logger.setLogResponseBody(true);
        client.registerInterceptor(logger);

        // the security key:
        client.registerInterceptor(new IClientInterceptor() {
            @Override
            public void interceptRequest(IHttpRequest iHttpRequest) {
                iHttpRequest.addHeader("ESANTE-API-KEY", key);
            }
            @Override
            public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {}
        });

        return client;
    }

    /**
     * The entry point of the application.
     */
    public static void main(String[] args){
        new CommandLine(new SampleUnitCallsApplication()).execute(args);
    }

}
