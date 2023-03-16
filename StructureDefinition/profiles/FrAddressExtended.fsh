Profile: FrAddressExtended
Parent: $FrAddress
Id: Fr-Address-Extended
* ^meta.lastUpdated = "2022-07-25T17:40:40.0522362+00:00"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
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
* line.extension ^min = 0
* line.extension[careOf] only $iso21090-ADXP-careOf
* line.extension[careOf] ^sliceName = "careOf"
* line.extension[careOf] ^min = 0
* line.extension[additionalLocator] only $iso21090-ADXP-additionalLocator
* line.extension[additionalLocator] ^sliceName = "additionalLocator"
* line.extension[additionalLocator] ^min = 0
* line.extension[houseNumber] only $iso21090-ADXP-houseNumber
* line.extension[houseNumber] ^sliceName = "houseNumber"
* line.extension[houseNumber] ^min = 0
* line.extension[buildingNumberSuffix] only $iso21090-ADXP-buildingNumberSuffix
* line.extension[buildingNumberSuffix] ^sliceName = "buildingNumberSuffix"
* line.extension[buildingNumberSuffix] ^min = 0
* line.extension[streetNameType] only $iso21090-ADXP-streetNameType
* line.extension[streetNameType] ^sliceName = "streetNameType"
* line.extension[streetNameType] ^min = 0
* line.extension[streetNamebase] only $iso21090-ADXP-streetNameBase
* line.extension[streetNamebase] ^sliceName = "streetNamebase"
* line.extension[streetNamebase] ^min = 0
* line.extension[postBox] only $iso21090-ADXP-postBox
* line.extension[postBox] ^sliceName = "postBox"
* line.extension[postBox] ^min = 0