Extension: PractitionerBirthPlace
Id: Practitioner-BirthPlace
* ^meta.lastUpdated = "2022-07-25T17:26:30.8116702+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Practitioner"
* value[x] ^slicing.discriminator.type = #type
* value[x] ^slicing.discriminator.path = "$this"
* value[x] ^slicing.rules = #closed
* valueAddress only FrAddressExtended
* valueAddress ^sliceName = "valueAddress"