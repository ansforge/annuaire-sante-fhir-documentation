Extension: FrenchDepartment
Id: French-Department
Description: "Département français"
* ^url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/French-Department"
* ^meta.lastUpdated = "2022-07-25T17:39:24.3766484+00:00"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context[0].type = #element
* ^context[=].expression = "Address"
* ^context[+].type = #element
* ^context[=].expression = "Element"
* url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/French-Department" (exactly)
* value[x] only CodeableConcept
* value[x] from $JDV-J84-DepartementOM-RASS (required)
* value[x] ^binding.description = "Départements (outre-mer et Monaco inclus) pour le RASS;https://mos.esante.gouv.fr/NOS/JDV_J84-DepartementOM-RASS/FHIR/JDV-J84-DepartementOM-RASS"