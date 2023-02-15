package fr.ans.asaf.service;

import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * A sample storage service that do nothing.
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);


    public void storePractitioner(List<Practitioner> practitioners){
        for(var p : practitioners) {
            logger.info("Storing a practitioner with id: {}", p.getId());
            // you can get and store more information like:
            p.getIdentifier(); // the list of identifiers of Practitioner
            p.getQualification(); // the list of qualification of Practitioner role...

        }
    }

    public void storePractitionerRole(List<PractitionerRole> practitionerRoles){
        for(var p : practitionerRoles) {
            logger.info("Storing a practitioner role with id: {}", p.getId());
            // you can get and store more information like:
            p.getIdentifier(); // the list of identifiers of PractitionerRole
            p.getSpecialty(); // the list of specialty of PractitionerRole role...
        }
    }

    public void storeOrganization(List<Organization> organizations){
        for(var o : organizations) {
            logger.info("Storing a organization with id: {}", o.getId());
            // you can get and store more information like:
            o.getIdentifier(); // the list of identifiers of Organization
        }
    }

}
