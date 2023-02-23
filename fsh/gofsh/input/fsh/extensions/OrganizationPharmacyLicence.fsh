Extension: OrganizationPharmacyLicence
Id: Organization-PharmacyLicence
Description: "Numéro de licence officine"
* ^url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/Organization-PharmacyLicence"
* ^meta.lastUpdated = "2022-07-25T17:28:38.8305088+00:00"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"

* ^context.type = #element
* ^context.expression = "Organization"
* . ^short = "Numéro licence officine"
* url = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/Organization-PharmacyLicence" (exactly)
* value[x] only string