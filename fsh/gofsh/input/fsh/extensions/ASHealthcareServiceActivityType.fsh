Extension: ASHealthcareServiceActivityType
Id: as-healthcareservice-activitytype
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-healthcareservice-activitytype"
* ^meta.lastUpdated = "2022-07-25T17:35:25.9463394+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "HealthcareService"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-healthcareservice-activitytype" (exactly)
* value[x] only CodeableConcept
* value[x] from $JDV-J131-CategorieActiviteSanitaireRegulee-RASS (required)
* value[x] ^binding.description = "Types d'activités autorisées"
* value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R277-CategorieActiviteSanitaireRegulee/FHIR/TRE-R277-CategorieActiviteSanitaireRegulee" (exactly)