Extension: PractitionerRoleHomeCare
Id: PractitionerRole-HomeCare
* ^url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/PractitionerRole-HomeCare"
* ^meta.lastUpdated = "2022-07-08T13:18:54.8596606+00:00"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole.availableTime"
* . ^short = "Consultation à domicile"
* url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/PractitionerRole-HomeCare" (exactly)
* value[x] only boolean
* value[x] ^comment = "Type consultation = a_dom => true"