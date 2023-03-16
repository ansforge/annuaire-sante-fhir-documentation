Extension: PractitionerDeceasedDateTime
Id: Practitioner-DeceasedDateTime
Description: "Date of death of the practitioner if applicable."
* ^meta.lastUpdated = "2022-07-25T17:25:57.5901528+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Practitioner"
* value[x] ^slicing.discriminator.type = #type
* value[x] ^slicing.discriminator.path = "$this"
* value[x] ^slicing.rules = #open
* valueDateTime only dateTime
* valueDateTime ^sliceName = "valueDateTime"