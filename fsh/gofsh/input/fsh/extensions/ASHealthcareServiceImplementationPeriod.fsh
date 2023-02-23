Extension: ASHealthcareServiceImplementationPeriod
Id: as-healthcareservice-implementationperiod
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-healthcareservice-implementationperiod"
* ^meta.lastUpdated = "2022-07-25T17:31:59.3499615+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "HealthcareService"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-healthcareservice-implementationperiod" (exactly)
* value[x] only Period
* value[x].start ^short = "Date mise en œuvre"
* value[x].end ^short = "Date fin de mise en oeuvre"