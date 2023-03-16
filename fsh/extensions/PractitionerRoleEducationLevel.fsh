Extension: PractitionerRoleEducationLevel
Id: PractitionerRole-EducationLevel
Description: "Niveau de formation acquis de l'étudiant."
* ^meta.lastUpdated = "2022-07-25T17:22:37.3111839+00:00"
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
    qualification 0..1 and
    qualificationType 0..1 and
    academicDegree 0..1 and
    achievedLevel 0..1 and
    academicYear 0..1 and
    period 0..1 and
    issuer 0..1
* extension[qualification].value[x] only CodeableConcept
* extension[qualification].value[x] from $JDV-J105-EnsembleDiplome-RASS (required)
* extension[qualification].value[x] ^binding.description = "Ensemble des diplômes et qualifications du RASS,"
* extension[qualificationType].value[x] only CodeableConcept
* extension[qualificationType].value[x] from $JDV-J81-TypeDiplome-RASS (required)
* extension[qualificationType].value[x] ^binding.description = "Types de diplôme du RASS,"
* extension[qualificationType].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R14-TypeDiplome/FHIR/TRE-R14-TypeDiplome" (exactly)
* extension[academicDegree].value[x] only CodeableConcept
* extension[academicDegree].value[x] from $JDV-J86-NatCycleForm-RASS (required)
* extension[academicDegree].value[x] ^binding.description = "Natures du cycle de formation des professionnels du RASS,"
* extension[academicDegree].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R223-NatCycleForm/FHIR/TRE-R223-NatCycleForm" (exactly)
* extension[achievedLevel].value[x] only CodeableConcept
* extension[achievedLevel].value[x] from $JDV-J87-NiveauFormAcquis-RASS (required)
* extension[achievedLevel].value[x] ^binding.description = "Niveau de formation acquis dans le cycle de formation des professionnels du RASS"
* extension[achievedLevel].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R224-NiveauFormAcquis/FHIR/TRE-R224-NiveauFormAcquis" (exactly)
* extension[academicYear].value[x] only CodeableConcept
* extension[academicYear].value[x] from $JDV-J88-AnneeUniversitaire-RASS (required)
* extension[academicYear].value[x] ^binding.description = "Années universitaires des professionnels du RASS,"
* extension[academicYear].value[x].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R225-AnneeUniversitaire/FHIR/TRE-R225-AnneeUniversitaire" (exactly)
* extension[period].value[x] only Period
* extension[issuer].value[x] only Reference(Organization)
* extension[issuer].value[x].identifier ^short = "Lieu de formation"
* extension[issuer].value[x].identifier.system = "urn:oid:1.2.250.1.213.1.6.4.1" (exactly)