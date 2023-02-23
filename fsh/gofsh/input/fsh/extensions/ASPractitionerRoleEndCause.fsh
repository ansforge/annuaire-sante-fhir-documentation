Extension: ASPractitionerRoleEndCause
Id: as-practitionerrole-endcause
Description: "Motif de fin d'activité"
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-endcause"
* ^meta.lastUpdated = "2022-07-08T13:18:13.46655+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-endcause" (exactly)
* value[x] only CodeableConcept
* value[x] from $JDV-J92-MotifFinActivite-RASS (required)
* value[x] ^binding.description = "Motifs de fin d'activité d'un professionnel du RASS,"
* value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R25-MotifFinActivite/FHIR/TRE-R25-MotifFinActivite" (exactly)