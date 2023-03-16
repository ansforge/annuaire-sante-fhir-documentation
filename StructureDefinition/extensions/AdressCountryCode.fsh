Extension: AdressCountryCode
Id: Address-Country-Code
Description: "Code pays"
* ^meta.lastUpdated = "2022-07-29T12:24:59.0761976+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Address"
* value[x] only code
* value[x] from $JDV-J74-Pays-RASS (required)
* value[x] ^binding.description = "Pays (codes INSEE) du RASS; http://mos.asipsante.fr/NOS/TABS/JDV_J74-Pays-RASS.tabs"