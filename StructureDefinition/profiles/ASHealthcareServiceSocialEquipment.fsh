Profile: ASHealthcareServiceSocialEquipment
Parent: HealthcareService
Id: AS-HealthcareService-SocialEquipment
Description: "Profil créé à partir de la ressource HealthcareService dans le cadre de l'annuaire santé - décrit les équipements sociaux représentant les activités des établissements du domaine social et médico-social enregistrés dans FINESS."
* ^meta.lastUpdated = "2022-07-26T16:54:05.82027+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* meta.source = "https://annuaire.sante.fr" (exactly)
* meta.profile = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-HealthcareService-SocialEquipment" (exactly)
* meta.security ..0
* meta.tag ..0
* implicitRules ..0
* text ..0
* contained ..0
* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0
* extension contains
    HealthcareServiceAuthorizationDate named authorizationDate 0..1 and
    HealthcareServiceImplementationPeriod named installationDate 0..1
* extension[installationDate] ^isModifier = false
* identifier ..0
* providedBy ^short = "Référence vers la structure FINESS ET"
* providedBy ^definition = "Reference vers l'id de la ressource de la structure FINESS ET à laquelle est rattaché cet equipement social"
* category ..0
* type from $JDV-J136-DisciplineEquipementSocial-RASS (required)
* type ^binding.description = "La discipline détermine la nature de l’activité"
* type.coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R280-DisciplineEquipementSocial/FHIR/TRE-R280-DisciplineEquipementSocial" (exactly)
* specialty ..0
* location ..0
* name ..0
* comment ..0
* extraDetails ..0
* photo ..0
* telecom ..0
* coverageArea ..0
* serviceProvisionCode ..0
* eligibility.code from $JDV-J137-Clientele-RASS (extensible)
* eligibility.code ^binding.description = "Population prise en charge par l’établissement dans le cadre de l’activité associée à la discipline"
* eligibility.code.coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R279-Clientele/FHIR/TRE-R279-Clientele" (exactly)
* eligibility.comment ..0
* program ..0
* characteristic from $JDV-J138-TypeActivite-RASS (extensible)
* characteristic ^binding.description = "Le mode de fonctionnement précise la modalité d’accueil, d’hébergement et/ou d’ouverture de l’activité associée à la discipline"
* characteristic.coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite" (exactly)
* communication ..0
* referralMethod ..0
* appointmentRequired ..0
* availableTime ..0
* notAvailable ..0
* availabilityExceptions ..0
* endpoint ..0