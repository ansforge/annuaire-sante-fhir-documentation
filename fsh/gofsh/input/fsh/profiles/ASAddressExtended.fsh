Profile: ASAddressExtended
Parent: FrAddress
Id: as-address-extended
* ^meta.lastUpdated = "2022-07-25T17:40:40.0522362+00:00"
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-address-extended"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"

* meta.source = "https://annuaire.sante.fr" (exactly)
* meta.profile = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-address-extended"" (exactly)

* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0

* extension contains
    FrenchDepartment named frenchDepartment 0..1 and
    AdressCountryCode named countryCode 0..1

* line.extension ^slicing.discriminator.type = #value
* line.extension ^slicing.discriminator.path = "url"
* line.extension ^slicing.rules = #open

* line.extension contains
    iso21090-ADXP-careOf named careOf 0..* and 
    iso21090-ADXP-additionalLocator named additionalLocator 0..* and
    iso21090-ADXP-houseNumber named houseNumber 0..* and
    iso21090-ADXP-buildingNumberSuffix named buildingNumberSuffix 0..* and
    iso21090-ADXP-streetNameType named streetNameType 0..* and 
    iso21090-ADXP-streetNameBase named streetNamebase 0..* and
    iso21090-ADXP-postBox named postBox 0..*