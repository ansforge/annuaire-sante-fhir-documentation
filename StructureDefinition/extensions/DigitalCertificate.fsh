Extension: DigitalCertificate
Id: DigitalCertificate
Description: "Informations descriptives du moyen d’identification par certificat. Il s'agit des certificats utilisés par les professionnels et les structures"
* ^meta.lastUpdated = "2022-07-25T17:41:29.7781308+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context[0].type = #element
* ^context[=].expression = "PractitionerRole"
* ^context[+].type = #element
* ^context[=].expression = "Organization"
* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0
* extension contains
    serialNumber 0..1 and
    issuer 0..1 and
    subject 0..1 and
    validity 0..1 and
    domain 0..1 and
    keyUsage 0..1 and
    value 0..1
* extension[serialNumber] ^short = "Numéor de Série du Certificat"
* extension[serialNumber].value[x] only string
* extension[issuer] ^short = "DN (Distinguished Name - Nom distinctif) de l’autorité de certification émettrice du certificat"
* extension[issuer].value[x] only string
* extension[subject] ^short = "DN (Distinguished Name - Nom distinctif) du porteur du certificat"
* extension[subject].value[x] only string
* extension[validity].value[x] only Period
* extension[validity].value[x].start ^short = "Date de début de validité du certificat"
* extension[validity].value[x].end ^short = "Date de fin de validité du certificat"
* extension[domain] ^short = "CN du DNSujet"
* extension[domain] ^comment = "Renseigné uniquement pour les certificats logiciels de portée Structure<;"
* extension[domain].value[x] only string
* extension[keyUsage] ^short = "La clé d’usage (Keyusage) qui détermine l’usage autorisé du certificat"
* extension[keyUsage].value[x] only string
* extension[value] ^short = "Adresse mail du professionnel ou de la structure, si présente dans le certificat"
* extension[value].value[x] only Attachment
* value[x] only base64Binary or boolean or canonical or code or date or dateTime or decimal or id or instant or integer or markdown or oid or positiveInt or string or time or unsignedInt or uri or url or uuid or Address or Age or Annotation or Attachment or CodeableConcept or Coding or ContactPoint or Count or Distance or Duration or HumanName or Identifier or Money or Period or Quantity or Range or Ratio or Reference or SampledData or Signature or Timing or ContactDetail or Contributor or DataRequirement or Expression or ParameterDefinition or RelatedArtifact or TriggerDefinition or UsageContext or Dosage