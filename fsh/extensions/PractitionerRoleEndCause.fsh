Extension: PractitionerRoleEndCause
Id: PractitionerRole-EndCause
Description: "Motif de fin d'activité"
* ^meta.lastUpdated = "2022-07-08T13:18:13.46655+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* value[x] only CodeableConcept
* value[x] from $JDV-J92-MotifFinActivite-RASS (required)
* value[x] ^binding.description = "Motifs de fin d'activité d'un professionnel du RASS,"
* value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R25-MotifFinActivite/FHIR/TRE-R25-MotifFinActivite" (exactly)