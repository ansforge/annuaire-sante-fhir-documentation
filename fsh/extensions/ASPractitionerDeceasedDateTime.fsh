Extension: ASPractitionerDeceasedDateTime
Id: as-practitioner-deceaseddatetime
Description: "Date of death of the practitioner if applicable."
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitioner-deceaseddatetime"
* ^meta.lastUpdated = "2022-07-25T17:25:57.5901528+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Practitioner"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitioner-deceaseddatetime" (exactly)
* value[x] ^slicing.discriminator.type = #type
* value[x] ^slicing.discriminator.path = "$this"
* value[x] ^slicing.rules = #open
* valueDateTime only dateTime
* valueDateTime ^sliceName = "valueDateTime"