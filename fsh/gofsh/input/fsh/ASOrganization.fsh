Profile: ASOrganization
Parent: FrOrganization
Id: as-organization
Description: "Profil créé à partir de la ressource FrOrganization dans le cadre de l'annuaire santé - décrit les organismes du domaine sanitaire, médico-social et social."
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-organization"
* ^meta.lastUpdated = "2022-10-28T09:41:33.609065+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"

* meta.source = "https://annuaire.sante.fr" (exactly)
* meta.profile = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-organization" (exactly)
* meta.security ..0
* meta.tag ..0

* extension ^slicing.discriminator.type = #value
* extension ^slicing.discriminator.path = "url"
* extension ^slicing.rules = #open
* extension ^min = 0
* extension[shortName] 0..0
* extension[description] 0..0
* extension[usePeriod] ^min = 0
* extension[usePeriod].valuePeriod ^sliceName = "valuePeriod"
* extension[usePeriod].valuePeriod.start ^short = "Date d'ouverture de la structure"
* extension[usePeriod].valuePeriod.end ^short = "Date de fermeture de la structure"
* extension contains
    DigitalCertificate named digitalCertificate 0..0 and
    MailboxMSS named mailboxMSS 0..* and
    OrganizationPharmacyLicence named pharmacyLicence 0..*
* extension[digitalCertificate] ^isModifier = false
* extension[mailboxMSS] ^min = 0
* extension[mailboxMSS] ^isModifier = false
* extension[mailboxMSS].extension ^slicing.discriminator.type = #value
* extension[mailboxMSS].extension ^slicing.discriminator.path = "url"
* extension[mailboxMSS].extension ^slicing.rules = #open
* extension[mailboxMSS].extension ^min = 0
* extension[mailboxMSS].extension contains
    responsible 0..0 and
    phone 0..0 and
    date 0..0
* extension[pharmacyLicence] ^min = 0
* extension[pharmacyLicence] ^isModifier = false

* identifier ^comment = "Une instance par identifiant (FINESS, SIREN, SIRET, idNat_Struct…)"
* identifier.use = #official (exactly)
* identifier.type ^comment = "Les codes FINEJ, FINEG, SIREN, SIRET, IDNST, INTRN proviennent du system  http://interopsante.org/CodeSystem/fr-v2-0203 ; Les codes 0,4 proviennent du system https://mos.esante.gouv.fr/NOS/TRE_G07-TypeIdentifiantStructure/FHIR/TRE-G07-TypeIdentifiantStructure"
* identifier.system ^comment = "« urn:oid:1.2.250.1.71.4.2.2 » si l’instance correspond à l’identification nationale des structures (idNat_Struct) ; « http://sirene.fr» si l’instance correspond à un identifiant SIREN ou SIRET ; « http://finess.sante.gouv.fr» si l’instance correspond à un identifiant FINESS EG ou EJ ; « urn:oid:1.2.250.1.213.1.6.4.3 » si l’instance correspond à un identifiant ADELI rang ou RPPS rang; « https://annuaire.sante.fr » si l’instance correspond à l’identifiant technique de la structure;"
* identifier.period ..0
* identifier.assigner ..0

* type[organizationType] ^short = "Type de strcuture"
* type[organizationType] ^comment = "Entitité Juridique : LEGAL-ENTITY; \r\nEntité Géographique : GEOGRAPHICAL-ENTITY"
* type[secteurActiviteRASS] ^binding.extension.url = "http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName"
* type[secteurActiviteRASS] ^binding.extension.valueString = "OrganizationType"
* type[secteurActiviteRASS] ^binding.strength = #required
* type[secteurActiviteRASS] ^binding.description = "Un secteur d'activité regroupe les établissements partageant la même activité de santé."
* type[categorieEtablissementRASS] ^binding.extension.url = "http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName"
* type[categorieEtablissementRASS] ^binding.extension.valueString = "OrganizationType"
* type[categorieEtablissementRASS] ^binding.strength = #required
* type[categorieEtablissementRASS] ^binding.description = "La catégorie d'établissement est le cadre réglementaire dans lequel s'exerce l'activité de l'entité géographique"
* type contains
    activiteINSEE 0..* and
    statutJuridiqueINSEE 0..* and
    SPH 0..*
* type[activiteINSEE] from $JDV-J99-InseeNAFrav2Niveau5-RASS (required)
* type[activiteINSEE] ^comment = "Toute entité juridique et chacun de ses établissements (EG) se voit attribuer par l'Insee, lors de son inscription au répertoire SIRENE, un code caractérisant son activité principale par référence à la nomenclature d'activités française (NAF rév. 2).\r\nPlus précisément, on distingue le code APET pour les EG."
* type[activiteINSEE] ^binding.extension.url = "http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName"
* type[activiteINSEE] ^binding.extension.valueString = "OrganizationType"
* type[activiteINSEE] ^binding.description = "Sous-classes de la Nomenclature d'Activités Française - INSEE"
* type[activiteINSEE].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5" (exactly)
* type[statutJuridiqueINSEE] from $JDV-J100-FinessStatutJuridique-RASS (required)
* type[statutJuridiqueINSEE] ^binding.extension.url = "http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName"
* type[statutJuridiqueINSEE] ^binding.extension.valueString = "OrganizationType"
* type[statutJuridiqueINSEE] ^binding.description = "Statut juridique FINESS qui caracterise la situation juridique de la personne morale"
* type[statutJuridiqueINSEE].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R72-FinessStatutJuridique/FHIR/TRE-R72-FinessStatutJuridique" (exactly)
* type[SPH] from $JDV-J162-ESPIC-RASS (required)
* type[SPH] ^binding.extension.url = "http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName"
* type[SPH] ^binding.extension.valueString = "OrganizationType"
* type[SPH] ^binding.description = "Modalités de participation au service public hospitalier"
* type[SPH].coding.system = "https://mos.esante.gouv.fr/NOS/TRE_R73-ESPIC/FHIR/TRE-R73-ESPIC" (exactly)
* name ^short = "Raison Sociale de la strcuture"
* alias ^short = "Enseigne commerciale de la structure"
* telecom ^comment = "Différentes instances pour les téléphones, la télécopie et l’adresse mail."
* telecom.system ^comment = "https://www.hl7.org/fhir/valueset-contact-point-system.html"
* telecom.use ^comment = "« old » si les coordonnées de structure ont une date de fin"
* telecom.period.id ..0
* telecom.period.start ..0
* address only FrAddressExtended
* partOf ^short = "Référence vers la structure de rattachement (lien EG/ EJ)"
* partOf ^comment = "Chaque entité geographique et ratachée à une entité juridique. C'est l'id de la ressource de l'entité juridique à laquelle est ratachée la structure qui est remontée dans l'element de référence partOf de l'entité géographique."
* contact ..0
* endpoint ..0