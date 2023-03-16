Extension: PractitionerRoleContracted
Id: PractitionerRole-Contracted
* ^meta.lastUpdated = "2022-07-25T17:23:18.1587036+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* value[x] only CodeableConcept
* value[x] from $JDV-J130-CNAMAmeliSecteurConventionnement-RASS (required)
* value[x] ^binding.description = "Secteur de conventionnement du professionnel libéral par la CNAM (extracts AMELI)"
* value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R282-CNAMAmeliSecteurConventionnement/FHIR/TRE-R282-CNAMAmeliSecteurConventionnement/" (exactly)