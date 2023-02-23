Extension: ASPractitionerRolePracticeRegion
Id: as-practitionerrole-practiceregion
Description: "Région d'exercice du professionnel"
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-practiceregion"
* ^meta.lastUpdated = "2022-07-25T17:20:08.6607218+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-practiceregion" (exactly)
* . ^short = "Code région de l'exercice du professionnel de santé"
* value[x] only CodeableConcept
* value[x] from $JDV-J93-RegionOM-RASS (required)
* value[x] ^binding.description = "Régions (outre-mer et Monaco inclus) dans le RASS https://mos.esante.gouv.fr/NOS/JDV_J93-RegionOM-RASS/FHIR/JDV-J93-RegionOM-RASS"
* value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R30-RegionOM/FHIR/TRE-R30-RegionOM" (exactly)