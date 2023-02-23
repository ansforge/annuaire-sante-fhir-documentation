Extension: HealthcareServiceImplementationPeriod
Id: HealthcareService-ImplementationPeriod
* ^url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/HealthcareService-ImplementationPeriod"
* ^meta.lastUpdated = "2022-07-25T17:31:59.3499615+00:00"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "HealthcareService"
* url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/HealthcareService-ImplementationPeriod" (exactly)
* value[x] only Period
* value[x].start ^short = "Date mise en œuvre"
* value[x].end ^short = "Date fin de mise en oeuvre"