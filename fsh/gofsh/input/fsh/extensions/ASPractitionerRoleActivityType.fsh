Extension: ASPractitionerRoleActivityType
Id: as-practitionerrole-activitytype
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-activitytype"
* ^meta.lastUpdated = "2022-07-25T17:24:45.7871829+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole.availableTime"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-activitytype" (exactly)
* value[x] only code
* value[x] ^comment = "”co” = plage horaire de consultation; ”ca” = plage horaire d’activité du cabinet; Si non renseigné = pas de plages horaire de consultation ou d’activité renseignée"