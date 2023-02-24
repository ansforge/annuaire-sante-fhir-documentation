Extension: ASPractitionerRoleHomeCare
Id: as-practitionerrole-homecare
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-homecare"
* ^meta.lastUpdated = "2022-07-08T13:18:54.8596606+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole.availableTime"
* . ^short = "Consultation à domicile"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-homecare" (exactly)
* value[x] only boolean
* value[x] ^comment = "Type consultation = a_dom => true"