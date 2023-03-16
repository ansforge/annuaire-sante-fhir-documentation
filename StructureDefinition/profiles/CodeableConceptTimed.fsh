Profile: CodeableConceptTimed
Parent: CodeableConcept
Id: CodeableConcept-Timed
* ^meta.lastUpdated = "2022-07-26T15:54:49.2245079+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0
* extension contains CodeableConceptPeriod named period 0..*
* extension[period] ^min = 0