Extension: ASOrganizationPharmacyLicence
Id: as-organization-pharmacylicence
Description: "Numéro de licence officine"
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-organization-pharmacylicence"
* ^meta.lastUpdated = "2022-07-25T17:28:38.8305088+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"

* ^context.type = #element
* ^context.expression = "Organization"
* . ^short = "Numéro licence officine"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-organization-pharmacylicence" (exactly)
* value[x] only string