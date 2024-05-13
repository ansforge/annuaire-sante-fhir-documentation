package fr.ans.asaf.service;

import com.github.javafaker.Faker;
import org.hl7.fhir.r4.model.*;


import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * A service that generate sample data
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class SampleDataService {

    /**
     * Fake data generator
     */
    static final Faker faker = new Faker(Locale.FRANCE, new Random(4000000));


    /**
     * Generate sample data. This program will create some data and make some link between this data.
     *
     * @param count the number of iteration we want (on iteration generate multiple entities)
     * @return generated data
     */
    public static SampleDataHolder generate(int count) {

        var sampleDataHolder = new SampleDataHolder();

        for (var i = 1; i < count + 1; i++) {
            var org = randomOrganization("org-" + i);

            var device = randomDevice("device-" + i, org);
            device.setOwner(new Reference("Organization/org-" + i % 100));

            var practitioner = randomPractitioner("pra-"+i);
            var practitionerRole = randomPractitionerRole("prarole-"+i);
            practitionerRole.setPractitioner(new Reference().setReference("Practitioner/"+practitioner.getIdElement().getIdPart()));
            practitionerRole.setOrganization(new Reference().setReference("Organization/"+org.getIdElement().getIdPart()));

            var healthcareService = randomHealthcareService("hcs-"+i);

            sampleDataHolder.organizationList.add(org);
            sampleDataHolder.practitionerList.add(practitioner);
            sampleDataHolder.practitionerRoleList.add(practitionerRole);
            sampleDataHolder.healthcareServiceList.add(healthcareService);
            sampleDataHolder.deviceList.add(device);
        }
        return sampleDataHolder;
    }

    /**
     * Create a random {@link Device}
     * @param id the id
     * @param attachedTo an Organization to attach the Device
     * @return the generated Device
     */
    public static Device randomDevice(String id, Organization attachedTo) {
        Device device = new Device();
        device.getMeta().setProfile(List.of(new CanonicalType("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-rass")));
        device.setId(id);
        device.addIdentifier().setSystem("http://sample/org/ids").setValue("dev-" + id);
        device.addIdentifier().setSystem("http://otherid/org/ids").setValue("dev-" + faker.code().isbnRegistrant());
        device.addDeviceName()
                .setName(faker.app().name())
                .setType(Device.DeviceNameType.MANUFACTURERNAME);
        device.setModelNumber(faker.app().name());
        device.addVersion()
                .setValue(faker.app().version());

        // set type
        CodeableConcept codeableConcept = new CodeableConcept();
        codeableConcept.addCoding(new Coding("https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd", "05602", "Scanographe à utilisation médicale"));
        device.setType(codeableConcept);

        // set number arhgos
        device.addExtension()
                .setUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS")
                .setValue(new StringType("56565.6456.45789531230001"));

        var spec = new CodeableConcept();
        spec.addCoding().setSystem("http://sample.com/devicespec/").setCode(faker.code().asin());
        device.addSpecialization()
                .setVersion(faker.app().version())
                .setSystemType(spec);

        device.setStatus(Device.FHIRDeviceStatus.ACTIVE);
        return device;
    }



    /**
     * Create a random {@link Organization}
     * @param id the id
     * @return the generated Organization
     */
    public static Organization randomOrganization(String id) {
        var address1 = faker.address();
        var address2 = faker.address();

        var organization = new Organization();
        organization.getMeta().setProfile(List.of(new CanonicalType("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/organization-rass")));
        organization.setId(id);
        organization.addIdentifier().setSystem("http://sample/org/ids").setValue("org-" + id);
        organization.addIdentifier().setSystem("http://otherid/org/ids").setValue("org-" + faker.code().isbnRegistrant());
        organization.addIdentifier().setSystem("https://finess.esante.gouv.fr").setValue(faker.idNumber().valid()).setUse(Identifier.IdentifierUse.OFFICIAL);
        organization.setName(faker.company().name());
        organization.setActive(true);

        CodeableConcept codeableConcept = new CodeableConcept();
        codeableConcept.addCoding(new Coding("https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite", "SA29", "Laboratoire d'analyses et de biologie médicale"));
        organization.addType(codeableConcept);

        codeableConcept = new CodeableConcept();
        codeableConcept.addCoding(new Coding("https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5", "82.19Z", "Photocopie prépa. documents & aut. activ. spéc. soutien de bureau"));
        organization.addType(codeableConcept);

        // LEGAL-ENTITY ou GEOGRAPHICAL-ENTITY à générer de manière aléatoire
        codeableConcept = new CodeableConcept();
        int entityRandom = faker.random().nextInt(0, 1);
        codeableConcept.addCoding(new Coding("http://interopsante.org/fhir/CodeSystem/fr-v2-3307", entityRandom == 1 ? "LEGAL-ENTITY" : "GEOGRAPHICAL-ENTITY", null));
        organization.addType(codeableConcept);

        var endpointOrg = new Reference("Organization/FakeOrg");
        organization.setEndpoint(List.of(endpointOrg));

        var contactComponent = organization.addContact();
        contactComponent.addTelecom().setValue(faker.phoneNumber().phoneNumber()).setRank(1).setUse(ContactPoint.ContactPointUse.HOME);
        contactComponent.addTelecom().setValue(faker.phoneNumber().phoneNumber()).setRank(1).setUse(ContactPoint.ContactPointUse.WORK);

        organization.addType().addCoding().setSystem("http://sample/org/org/type").setCode("someorg").setDisplay("some org");

        for (var address : List.of(address1, address2)) {

            organization.addAddress().setCity(address.city())
                    .setCountry(address.country())
                    .setPostalCode(address.zipCode())
                    .setType(org.hl7.fhir.r4.model.Address.AddressType.POSTAL)
                    .setUse(org.hl7.fhir.r4.model.Address.AddressUse.HOME)
                    .addLine(address.streetAddress());
        }
        return organization;
    }


    /**
     * Create a random {@link Practitioner}
     * @param id the id
     * @return the generated Practitioner
     */
    public static Practitioner randomPractitioner(String id) {
        var practitioner = new Practitioner();
        practitioner.getMeta().setProfile(List.of(new CanonicalType("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/practitioner-rass")));
        practitioner.setId(id);
        practitioner.addIdentifier().setSystem("http://sample/p/ids").setValue("p-" + id);
        practitioner.addIdentifier().setSystem("http://otherid/p/ids").setValue("p-" + faker.code().isbnRegistrant());
        practitioner.addName(new HumanName().addGiven(faker.name().firstName()).setFamily(faker.name().name()).addPrefix(faker.name().prefix()));
        practitioner.setActive(true);
        return practitioner;
    }

    static List<String> PRACTITIONER_ROLE_SPECIALTIES = List.of("C", "PAC", "SH", "CAPA", "CEX");
    static List<String> PRACTITIONER_ROLE_SPECIALTIES_CODE = List.of("SCD01", "SM02", "SM59", "SM60", "SM61", "SM62", "SM63", "SM68");
    static List<String> PRACTITIONER_ROLE_SPECIALTIES_DISPLAY = List.of("Orthopédie dento-faciale", "Anesthésie-réanimation", "Médecine d'urgence", "Médecine légale et expertises médicales", "Médecine vasculaire", "Endocrinologie, diabétologie, nutrition", "Biologie médicale option biologie générale", "Chirurgie maxillo-faciale (réforme 2017)");
    static List<String> PRACTITIONER_ROLE_OCCUPATION = List.of("10", "21", "26", "28", "31", "40");
    static List<String> PRACTITIONER_ROLE_OCCUPATION_DISPLAY = List.of("Médecin", "Pharmacien", "Audioprothésiste", "Opticien-Lunetier", "Assistant dentaire", "Chirurgien dentiste");

    static List<String> PRACTITIONER_ROLE_CATEGORY = List.of("E", "C", "M");
    static List<String> PRACTITIONER_ROLE_CATEGORY_DISPLAY = List.of("Etudiant", "Civil", "Militaire");

    public static PractitionerRole randomPractitionerRole(String id) {
        var practitionerRole = new PractitionerRole();
        practitionerRole.getMeta().setProfile(List.of(new CanonicalType("https://apifhir.annuaire.sante.fr/ws-sync/exposed/StructureDefinition/PractitionerRole-rass")));
        practitionerRole.setId(id);

        int roleIndex = new Random().nextInt(PRACTITIONER_ROLE_OCCUPATION.size());
        var occupation = PRACTITIONER_ROLE_OCCUPATION.get(roleIndex);
        var occupationDisplay = PRACTITIONER_ROLE_OCCUPATION_DISPLAY.get(roleIndex);
        int categoryIndex = new Random().nextInt(PRACTITIONER_ROLE_CATEGORY.size());
        var category = PRACTITIONER_ROLE_CATEGORY.get(categoryIndex);
        var categoryDisplay = PRACTITIONER_ROLE_CATEGORY_DISPLAY.get(categoryIndex);
        var code = practitionerRole.addCode();
        code.addCoding().setSystem("https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante").setCode(occupation).setDisplay(occupationDisplay);
        code.addCoding().setSystem("https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle").setCode(category).setDisplay(categoryDisplay);


        var specialtyType = PRACTITIONER_ROLE_SPECIALTIES.get(new Random().nextInt(PRACTITIONER_ROLE_SPECIALTIES.size()));
        int specialityIndex = new Random().nextInt(PRACTITIONER_ROLE_SPECIALTIES_CODE.size());
        var specialityCode = PRACTITIONER_ROLE_SPECIALTIES_CODE.get(specialityIndex);
        var specialityDisplay = PRACTITIONER_ROLE_SPECIALTIES_DISPLAY.get(specialityIndex);
        var s = practitionerRole.addSpecialty();
        s.addCoding().setCode(specialityCode).setSystem("urn:oid:1.2.250.1.213.2.28").setDisplay(specialityDisplay);
        s.addCoding().setCode(specialtyType).setSystem("https://mos.esante.gouv.fr/NOS/TRE_R04-TypeSavoirFaire/FHIR/TRE-R04-TypeSavoirFaire");

        practitionerRole.addIdentifier().setSystem("http://sample/pr/ids").setValue("prr-" + id);
        practitionerRole.addIdentifier().setSystem("http://otherid/pr/ids").setValue("prr-" + faker.code().isbnRegistrant());
        practitionerRole.setActive(true);

        Extension extension = new Extension();
        extension.setUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/practitionerRole-name");
        var humanName = new HumanName().addGiven(faker.name().firstName()).setFamily(faker.name().lastName()).addPrefix(faker.name().prefix());
        extension.setValue(humanName);
        practitionerRole.addExtension(extension);
        return practitionerRole;
    }

    static List<String> HEALTHCARE_SERVICE_CODES = List.of("07", "06", "11");
    static List<String> HEALTHCARE_SERVICE_DISPLAY = List.of("Chirurgie ambulatoire", "Anesthésie ambulatoire", "Hébergement complet internat");
    static List<String> HEALTHCARE_SERVICE_SYSTEM = List.of("https://apifhir.annuaire.sante.fr/wssync/exposed/structuredefinition/HealthcareService-HealthCareActivity-rass", "https://apifhir.annuaire.sante.fr/wssync/exposed/structuredefinition/HealthcareService-HealthCareActivity-rass", "https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/HealthcareService-SocialEquipment-rass");

    /**
     * Create a random {@link HealthcareService}
     * @param id the id
     * @return the generated HealthcareService
     */
    public static HealthcareService randomHealthcareService(String id) {
        var healthcareService = new HealthcareService();

        int healthcareServiceIndex = new Random().nextInt(HEALTHCARE_SERVICE_CODES.size());
        healthcareService.getMeta().setProfile(List.of(new CanonicalType(HEALTHCARE_SERVICE_SYSTEM.get(healthcareServiceIndex))));

        healthcareService.setId(id);
        Coding coding = new Coding()
                .setCode(HEALTHCARE_SERVICE_CODES.get(healthcareServiceIndex))
                .setDisplay(HEALTHCARE_SERVICE_DISPLAY.get(healthcareServiceIndex))
                .setSystem(HEALTHCARE_SERVICE_SYSTEM.get(healthcareServiceIndex));
        CodeableConcept codeableConcept = new CodeableConcept().addCoding(coding);
        healthcareService.addCharacteristic(codeableConcept);

        healthcareService.addIdentifier().setSystem("http://sample/pr/ids").setValue("hcs-" + id);
        healthcareService.addIdentifier().setSystem("http://otherid/pr/ids").setValue("hcs-" + faker.code().isbnRegistrant());
        healthcareService.setActive(true);
        return healthcareService;
    }


}
