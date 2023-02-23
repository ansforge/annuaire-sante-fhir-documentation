Profile: ASPractitioner
Parent: Practitioner
Id: as-practitioner
Description: "Profil de l'Annuaire santé  de la ressource Practitioner. Ce profil décrit les données d'identification pérennes d’une personne physique, qui travaille en tant que professionnel (professionnel enregistré dans RPPS ou ADELI), personnel autorisé ou personnel d’établissement, dans les domaines sanitaire, médico-social et social."
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitioner"
* ^meta.lastUpdated = "2022-10-28T09:46:26.2219259+00:00"
* ^version = "0.2"
* ^status = #draft
* ^experimental = false
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* meta.source = "https://annuaire.sante.fr" (exactly)
* meta.profile = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitioner" (exactly)
* meta.security ..0
* meta.tag ..0
* implicitRules ..0
* text ..0
* contained ..0
* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0

* extension contains
    PractitionerNationality named nationality 0..0 and
    PractitionerAuthorization named authorization 0..* and
    PractitionerBirthPlace named birthPlace 0..0 and
    PractitionerDeceasedDateTime named deceasedDateTime 0..* and
    MailboxMSS named mailboxMSS 0..*
* extension[authorization] ^min = 0
* extension[authorization] ^isModifier = false
* extension[authorization].extension ^slicing.discriminator.type = #value
* extension[authorization].extension ^slicing.discriminator.path = "url"
* extension[authorization].extension ^slicing.rules = #open
* extension[authorization].extension ^min = 0
* extension[authorization].extension contains period 0..0
* extension[birthPlace] ^isModifier = false
* extension[deceasedDateTime] ^min = 0
* extension[deceasedDateTime] ^isModifier = false
* extension[mailboxMSS] ^definition = "Les BALs MSS de type PER rattachées seulement à l'identifiant du professionnel de Santé"
* extension[mailboxMSS] ^min = 0
* extension[mailboxMSS] ^isModifier = false
* extension[mailboxMSS].extension ^slicing.discriminator.type = #value
* extension[mailboxMSS].extension ^slicing.discriminator.path = "url"
* extension[mailboxMSS].extension ^slicing.rules = #open
* extension[mailboxMSS].extension ^min = 0
* extension[mailboxMSS].extension contains
    description 0..0 and
    responsible 0..0 and
    phone 0..0 and
    date 0..0
* extension[mailboxMSS].extension[publication] ^sliceName = "publication"
* extension[mailboxMSS].extension[publication] ^min = 0
* extension[mailboxMSS].extension[publication].value[x] ^short = "indicateur liste rouge"
* identifier ^short = "Une instance par identifiant (RPPS, ADELI, idNat_PS…)"
* identifier.type 1..
* identifier.type from $fr-practioner-identifier-type (extensible)
* identifier.type ^comment = "Les code ADELI, RPPS et IDNPS proviennent du system  http://interopsante.org/fhir/CodeSystem/fr-v2-0203 ; Les code 1, 3, 4, 5, 6 proviennent du system : https://mos.esante.gouv.fr/NOS/TRE_G08-TypeIdentifiantPersonne/FHIR/TRE-G08-TypeIdentifiantPersonne"
* identifier.type ^binding.extension[0].url = "http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName"
* identifier.type ^binding.extension[=].valueString = "IdentifierType"
* identifier.type ^binding.extension[+].url = "http://hl7.org/fhir/StructureDefinition/elementdefinition-isCommonBinding"
* identifier.type ^binding.extension[=].valueBoolean = true
* identifier.type ^binding.description = "ValueSet défini par Interop’Santé « fr-practioner-identifier-type"
* identifier.system 1..
* identifier.system ^short = "le system de l'identifiant dépend de la source d'où provient l'identifiant"
* identifier.system ^comment = "« http://rpps.fr» si l’instance correspond à un identifiant RPPS ; « http://adeli.fr» si l’instance correspond à un identifiant ADELI ; « urn:oid:1.2.250.1.71.4.2.1 » si l’instance correspond à l’identification nationale PP (idNat_PS) ; « urn:oid:1.2.250.1.213.1.6.4.2 » si l’instance correspond à une identification locale : Id Cabinet ADELI/N° de registre, FINESS/N° de registre, SIREN/N° de registre, SIRET/N° de registre ou Id Cabinet RPPS/N° de registre"
* identifier.value 1..
* identifier.value ^short = "la valeur de l'identifiant du PS"
* name only FrHumanName
* name ^short = "Une instance pour le nom d’usage et une instance pour le nom issu de l’état-civil"
* name.id ..0
* name.extension ^slicing.discriminator.type = #value
* name.extension ^slicing.discriminator.path = "url"
* name.extension ^slicing.rules = #open
* name.extension ^min = 0
* name.extension contains assemblyOrder 0..0
* name.extension[assemblyOrder].value[x] ^slicing.discriminator.type = #type
* name.extension[assemblyOrder].value[x] ^slicing.discriminator.path = "$this"
* name.extension[assemblyOrder].value[x] ^slicing.rules = #closed
* name.use ..0
* name.use ^comment = "« usual » pour nom et prénom d’usage (Personne) ; « official » pour nom de famille et prénoms (Etat-civil)"
* name.text ..0
* name.family ..0
* name.given ..0
* name.prefix ^binding.strength = #required
* name.suffix ..0
* name.period ..0
* telecom ..0
* telecom only FrContactPoint
* telecom ^comment = "Différentes instances pour les téléphones, la télécopie et l’adresse mail"
* telecom.system ^comment = "« phone » pour Téléphone et Téléphone 2 ; « fax » pour Télécopie ; « email » pour adresse e-mail"
* telecom.use ^comment = "« old » si les coordonnées de correspondance ont une date de fin"
* address ..0
* address only FrAddressExtended
* gender ..0
* birthDate ..0
* photo ..0
* qualification ^comment = "Une instance pour chaque diplôme ou autre diplôme obtenu"
* qualification.id ..0
* qualification.identifier ..0
* qualification.identifier ^short = "Numéro de diplôme"
* qualification.code.id ..0
* qualification.code.coding ^slicing.discriminator.type = #value
* qualification.code.coding ^slicing.discriminator.path = "system"
* qualification.code.coding ^slicing.description = "Two slices: one slice for the degree (diplôme obtenu) and one for its type (type diplôme obtenu)"
* qualification.code.coding ^slicing.ordered = false
* qualification.code.coding ^slicing.rules = #open
* qualification.code.coding contains
    degreeType 0..1 and
    degreeR48 0..1 and
    degreeR49 0..1 and
    degreeR50 0..1 and
    degreeR51 0..1 and
    degreeR52 0..1 and
    degreeR53 0..1 and
    degreeR54 0..1 and
    degreeR55 0..1 and
    degreeR56 0..1 and
    degreeR57 0..1 and
    degreeR58 0..1 and
    deegreeR36 0..1 and
    degreeR47 0..1 and
    degreeR226 0..1
* qualification.code.coding[degreeType] from $JDV-J81-TypeDiplome-RASS (required)
* qualification.code.coding[degreeType] ^binding.description = "Types de diplôme du RASS"
* qualification.code.coding[degreeType].system = "https://mos.esante.gouv.fr/NOS/TRE_R14-TypeDiplome/FHIR/TRE-R14-TypeDiplome" (exactly)
* qualification.code.coding[degreeR48] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR48] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR48].system = "https://mos.esante.gouv.fr/NOS/TRE_R48-DiplomeEtatFrancais/FHIR/TRE-R48-DiplomeEtatFrancais" (exactly)
* qualification.code.coding[degreeR48].system ^short = "Diplôme d'Etat français"
* qualification.code.coding[degreeR49] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR49] ^binding.description = "Ensemble des diplômes et qualifications du RASS;"
* qualification.code.coding[degreeR49].system = "https://mos.esante.gouv.fr/NOS/TRE_R49-DiplomeEtudeSpecialisee/FHIR/TRE-R49-DiplomeEtudeSpecialisee" (exactly)
* qualification.code.coding[degreeR49].system ^short = "Diplôme d'études spécialisées (DES"
* qualification.code.coding[degreeR50] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR50] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR50].system = "https://mos.esante.gouv.fr/NOS/TRE_R50-DESCGroupe1Diplome/FHIR/TRE-R50-DESCGroupe1Diplome" (exactly)
* qualification.code.coding[degreeR50].system ^short = "Diplôme d'études spécialisées complémentaires non qualifiants (DESC I)"
* qualification.code.coding[degreeR51] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR51] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR51].system = "https://mos.esante.gouv.fr/NOS/TRE_R51-DESCGroupe2Diplome/FHIR/TRE-R51-DESCGroupe2Diplome" (exactly)
* qualification.code.coding[degreeR51].system ^short = "Diplôme d'études spécialisées complémentaires qualifiants (DESC II)"
* qualification.code.coding[degreeR52] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR52] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR52].system = "https://mos.esante.gouv.fr/NOS/TRE_R52-CapaciteDiplome/FHIR/TRE-R52-CapaciteDiplome" (exactly)
* qualification.code.coding[degreeR52].system ^short = "Diplôme de capacité de médecine"
* qualification.code.coding[degreeR53] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR53] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR53].system = "https://mos.esante.gouv.fr/NOS/TRE_R53-DiplomePaysEEE/FHIR/TRE-R53-DiplomePaysEEE" (exactly)
* qualification.code.coding[degreeR53].system ^short = "Diplôme d'un pays de l'espace économique européen."
* qualification.code.coding[degreeR54] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR54] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR54].system = "https://mos.esante.gouv.fr/NOS/TRE_R54-DiplomeUniversiteInterUniversitaire/FHIR/TRE-R54-DiplomeUniversiteInterUniversitaire" (exactly)
* qualification.code.coding[degreeR54].system ^short = "Diplôme universitaire ou interuniversitaire"
* qualification.code.coding[degreeR55] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR55] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR55].system = "https://mos.esante.gouv.fr/NOS/TRE_R55-CertificatEtudeSpeciale/FHIR/TRE-R55-CertificatEtudeSpeciale" (exactly)
* qualification.code.coding[degreeR55].system ^short = "Certificat d'études spéciales (CES)"
* qualification.code.coding[degreeR56] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR56] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR56].system = "https://mos.esante.gouv.fr/NOS/TRE_R56-Attestation/FHIR/TRE-R56-Attestation" (exactly)
* qualification.code.coding[degreeR56].system ^short = "Attestation de formation."
* qualification.code.coding[degreeR57] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR57] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR57].system = "https://mos.esante.gouv.fr/NOS/TRE_R57-DiplomeEuropeenEtudeSpecialisee/FHIR/TRE-R57-DiplomeEuropeenEtudeSpecialisee" (exactly)
* qualification.code.coding[degreeR57].system ^short = "Diplôme européen d'études spécialisées"
* qualification.code.coding[degreeR58] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR58] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR58].system = "https://mos.esante.gouv.fr/NOS/TRE_R58-AutreTypeDiplome/FHIR/TRE-R58-AutreTypeDiplome" (exactly)
* qualification.code.coding[deegreeR36] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[deegreeR36] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[deegreeR36].system = "https://mos.esante.gouv.fr/NOS/TRE_R36-AutreDiplomeObtenu/FHIR/TRE-R36-AutreDiplomeObtenu" (exactly)
* qualification.code.coding[degreeR47] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR47] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR47].system = "https://mos.esante.gouv.fr/NOS/TRE_R47-CommissionQualification/FHIR/TRE-R47-CommissionQualification" (exactly)
* qualification.code.coding[degreeR47].system ^short = "Qualification attribuée par une commission"
* qualification.code.coding[degreeR226] from $JDV-J105-EnsembleDiplome-RASS (required)
* qualification.code.coding[degreeR226] ^binding.description = "Ensemble des diplômes et qualifications du RASS"
* qualification.code.coding[degreeR226].system = "https://mos.esante.gouv.fr/NOS/TRE_R226-Dip2iemeCycleNQ/FHIR/TRE-R226-Dip2iemeCycleNQ" (exactly)
* qualification.code.coding[degreeR226].system ^short = "Diplôme de deuxième cycle non qualifiant"
* qualification.code.text ..0
* qualification.period ..0
* qualification.issuer ..0
* qualification.issuer.reference ..0
* qualification.issuer.type ..0
* qualification.issuer.identifier ^short = "Code du lieu d'obtention du diplôme"
* qualification.issuer.identifier.use ..0
* qualification.issuer.identifier.type ..0
* qualification.issuer.identifier.system = "urn:oid:1.2.250.1.213.1.6.4.1" (exactly)
* qualification.issuer.identifier.period ..0
* qualification.issuer.identifier.assigner ..0
* qualification.issuer.display ..0
* communication ..0
* communication only CodeableConceptTimed
* communication.coding.system = "https://mos.esante.gouv.fr/NOS/TRE_G00-Langue/FHIR/TRE-G00-Langue" (exactly)