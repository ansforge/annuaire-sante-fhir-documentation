Extension: ASPractitionerBirthPlace
Id: as-practitioner-birthplace
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitioner-birthplace"
* ^meta.lastUpdated = "2022-07-25T17:26:30.8116702+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Practitioner"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitioner-birthplace" (exactly)
* value[x] ^slicing.discriminator.type = #type
* value[x] ^slicing.discriminator.path = "$this"
* value[x] ^slicing.rules = #closed
* valueAddress only FrAddressExtended
* valueAddress ^sliceName = "valueAddress"