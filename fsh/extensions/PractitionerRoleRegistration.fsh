Extension: PractitionerRoleRegistration
Id: PractitionerRole-Registration
Description: "Inscription/référencement du professionnel. Première inscription si \"isFirst = true\"."
* ^meta.lastUpdated = "2022-07-25T17:19:33.8929367+00:00"
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
    registeringOrganization 0..1 and
    registeringOrganizationDetail 0..1 and
    period 0..1 and
    status 0..1 and
    hostingDepartment 0..1 and
    isFirst 0..1
* extension[registeringOrganization].value[x] only CodeableConcept
* extension[registeringOrganization].value[x] from $JDV-J83-AutoriteEnregistrement-RASS (required)
* extension[registeringOrganization].value[x] ^binding.description = "Autorités d'enregistrement des acteurs de santé du RASS,"
* extension[registeringOrganization].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R60-AutoriteEnregistrement/FHIR/TRE-R60-AutoriteEnregistrement" (exactly)
* extension[registeringOrganizationDetail].value[x] only CodeableConcept
* extension[registeringOrganizationDetail].value[x] from $JDV-J84-DepartementOM-RASS (required)
* extension[registeringOrganizationDetail].value[x] ^binding.description = "Départements (outre-mer et Monaco inclus) pour le RASS,"
* extension[registeringOrganizationDetail].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_G09-DepartementOM/FHIR/TRE-G09-DepartementOM" (exactly)
* extension[period].value[x] only Period
* extension[status].value[x] only CodeableConcept
* extension[status].value[x] from $JDV-J85-StatutInscription-RASS (required)
* extension[status].value[x] ^binding.description = "Statut avancement dossier de référencement personne du RASS auprès d'un ordre"
* extension[status].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R33-StatutInscription/FHIR/TRE-R33-StatutInscription" (exactly)
* extension[hostingDepartment].value[x] only CodeableConcept
* extension[hostingDepartment].value[x] from $JDV-J84-DepartementOM-RASS (required)
* extension[hostingDepartment].value[x] ^binding.description = "Départements (outre-mer et Monaco inclus) pour le RASS,"
* extension[hostingDepartment].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_G09-DepartementOM/FHIR/TRE-G09-DepartementOM" (exactly)
* extension[isFirst].value[x] only boolean
* extension[isFirst].value[x] ^comment = "Si 1re insctription => practitionerRole-registration.isFirst= « true »\r\nSinon, practitionerRole-registration.isFirst= « false »"