Extension: PractitionerAuthorization
Id: Practitioner-Authorization
Description: "Autorisation d'exercice"
* ^meta.lastUpdated = "2022-07-25T17:28:43.7645544+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Practitioner"
* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0
* extension contains
    type 0..1 and
    period 0..1 and
    field 0..1 and
    profession 0..1
* extension[type].value[x] only CodeableConcept
* extension[type].value[x] from $JDV-J75-TypeAutorisation-RASS (required)
* extension[type].value[x] ^binding.description = "Type d'autorisation d'exercice pour accès à l'exercice de la profession du RASS;https://mos.esante.gouv.fr/NOS/JDV_J75-TypeAutorisation-RASS/FHIR/JDV-J75-TypeAutorisation-RASS"
* extension[type].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R17-TypeAutorisation/FHIR/TRE-R17-TypeAutorisation" (exactly)
* extension[period].value[x] only Period
* extension[period].value[x].start ^short = "Date effet autorisation"
* extension[period].value[x].end ^short = "Date fin autorisation"
* extension[field].value[x] only CodeableConcept
* extension[field].value[x] from $JDV-J76-DisciplineAutorisation-RASS (required)
* extension[field].value[x] ^binding.description = "Discipline à laquelle l'autorisation d'exercice est restreinte dans le RASS"
* extension[field].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R18-DisciplineAutorisation/FHIR/TRE-R18-DisciplineAutorisation" (exactly)
* extension[profession].value[x] only CodeableConcept
* extension[profession].value[x] from $JDV-J106-EnsembleProfession-RASS (required)
* extension[profession].value[x] ^binding.description = "Profession pour laquelle l'autorisation est délivrée"
* extension[profession].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante/" (exactly)