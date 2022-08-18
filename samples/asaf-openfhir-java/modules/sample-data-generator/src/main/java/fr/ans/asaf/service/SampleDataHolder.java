package fr.ans.asaf.service;

import org.hl7.fhir.r4.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of generated FHIR resources
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class SampleDataHolder {
    List<Organization> organizationList = new ArrayList<>();
    List<Device> deviceList = new ArrayList<>();
    List<Practitioner> practitionerList = new ArrayList<>();
    List<PractitionerRole> practitionerRoleList = new ArrayList<>();
    List<HealthcareService> healthcareServiceList = new ArrayList<>();

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public List<Practitioner> getPractitionerList() {
        return practitionerList;
    }

    public List<PractitionerRole> getPractitionerRoleList() {
        return practitionerRoleList;
    }

    public List<HealthcareService> getHealthcareServiceList() {
        return healthcareServiceList;
    }
}
