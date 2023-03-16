Extension: PractitionerRoleSmartCard
Id: PractitionerRole-SmartCard
Description: "Informations descriptives du moyen d’identification des personnes physiques via une carte de professionnel."
* ^meta.lastUpdated = "2022-07-25T17:18:48.1178465+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0
* extension contains
    type 0..1 and
    number 0..1 and
    period 0..1 and
    cancellationDate 0..1 and
    date 0..*
* extension[type].value[x] only CodeableConcept
* extension[type].value[x].coding from $JDV-J128-TypeCarte-RASS (extensible)
* extension[type].value[x].coding ^binding.description = "Type de carte de professionnel (CPx)"
* extension[type].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R87-TypeCarte/FHIR/TRE-R87-TypeCarte" (exactly)
* extension[number].value[x] only string
* extension[number].value[x] ^short = "Numéro de la carte du professionnel."
* extension[period].value[x] only Period
* extension[period].value[x].start ^short = "Date de début de validité de la carte"
* extension[period].value[x].end ^short = "Date de finb de validité de la carte"
* extension[cancellationDate].value[x] only dateTime
* extension[cancellationDate].value[x] ^short = "Date de mise en opposition de la carte."
* extension[cancellationDate].value[x] ^comment = "Cette opposition implique la révocation des certifications embarquées dans la carte"
* extension[date] ^min = 0
* extension[date].value[x] only Meta
* extension[date].value[x].source = "https://annuaire.sante.fr" (exactly)
* extension[date].value[x].profile = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/PractitionerRole-SmartCard" (exactly)