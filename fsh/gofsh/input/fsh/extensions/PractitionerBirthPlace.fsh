Extension: PractitionerBirthPlace
Id: Practitioner-BirthPlace
* ^url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/Practitioner-BirthPlace"
* ^meta.lastUpdated = "2022-07-25T17:26:30.8116702+00:00"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Practitioner"
* url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/Practitioner-BirthPlace" (exactly)
* value[x] ^slicing.discriminator.type = #type
* value[x] ^slicing.discriminator.path = "$this"
* value[x] ^slicing.rules = #closed
* valueAddress only FrAddressExtended
* valueAddress ^sliceName = "valueAddress"