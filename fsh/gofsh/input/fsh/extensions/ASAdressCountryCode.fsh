Extension: ASAdressCountryCode
Id: as-address-Country-Code
Description: "Code pays"
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-address-country-code"
* ^meta.lastUpdated = "2022-07-29T12:24:59.0761976+00:00"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Address"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-address-country-code" (exactly)
* value[x] only code
* value[x] from $JDV-J74-Pays-RASS (required)
* value[x] ^binding.description = "Pays (codes INSEE) du RASS; http://mos.asipsante.fr/NOS/TABS/JDV_J74-Pays-RASS.tabs"