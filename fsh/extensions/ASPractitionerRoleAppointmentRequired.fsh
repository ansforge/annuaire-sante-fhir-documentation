Extension: ASPractitionerRoleAppointmentRequired
Id: as-practitionerrole-appointmentrequired
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-appointmentrequired"
* ^meta.lastUpdated = "2022-07-08T13:17:18.9970158+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole.availableTime"
* . ^short = "Type de consultation proposé par le professionnel de santé"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-appointmentrequired" (exactly)
* value[x] only string
* value[x] ^comment = "avc_rdv = avec rendez-vous; ss-rdv = sans rendez-vous, non_r = non renseigné"