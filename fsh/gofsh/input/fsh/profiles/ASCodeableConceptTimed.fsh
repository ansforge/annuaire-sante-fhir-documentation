Profile: ASCodeableConceptTimed
Parent: CodeableConcept
Id: as-codeableconcept-timed

* ^meta.lastUpdated = "2022-07-26T15:54:49.2245079+00:00"
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-codeableconcept-timed"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"

* meta.source = "https://annuaire.sante.fr" (exactly)
* meta.profile = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-codeableconcept-timed" (exactly)

* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open

* extension contains CodeableConceptPeriod named period 0..*
