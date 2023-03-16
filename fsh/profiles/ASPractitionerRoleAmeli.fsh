Profile: ASPractitionerRoleAmeli
Parent: PractitionerRole
Id: AS-PractitionerRole-Ameli
Description: "Profil créé à partir de la ressource PractitionerRole dans le cadre de l'annuaire santé - décrit les caractéristiques opérationnelles de l’exercice d’un professionnel au sein d’une organisation interne."
* ^meta.lastUpdated = "2022-07-26T16:47:14.6392724+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* meta.source = "https://annuaire.sante.fr" (exactly)
* meta.profile = "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-PractitionerRole-Ameli" (exactly)
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
    PractitionerRolePartOf named partOf 0..1 and
    PractitionerRoleHasCAS named hasCAS 0..0 and
    PractitionerRoleContracted named Contracted 0..0 and
    PractitionerRoleVitaleAccepted named vitalAccepted 0..0
* extension[partOf] ^short = "Référence vers la situation d’exercice de rattachement (PractitionerRole)"
* extension[Contracted] ^short = "Secteur de conventionnement du professionnel libéral auquel il a adhéré auprès de l'Assurance Maladie."
* extension[Contracted] ^isModifier = false
* extension[vitalAccepted] ^short = "L’indicateur Carte Vitale acceptée précise si le professionnel, dans le cadre de cette situation opérationnelle, dispose des moyens techniques pour prendre en charge la carte vitale ou pas."
* extension[vitalAccepted] ^isModifier = false
* identifier ..1
* identifier ^short = "Numéro AM"
* period ..0
* organization ..0
* code ..0
* specialty ..0
* location ..0
* healthcareService ..0
* telecom ..0
* telecom.id ..0
* telecom.use ..0
* telecom.rank ..0
* telecom.period ..0
* availableTime ..0
* availableTime.extension ^slicing.discriminator.type = #value
* availableTime.extension ^slicing.discriminator.path = "url"
* availableTime.extension ^slicing.rules = #open
* availableTime.extension ^min = 0
* availableTime.extension contains
    PractitionerRoleAppointmentRequired named appointmentRequired 0..* and
    PractitionerRoleHomeCare named homeCare 0..* and
    PractitionerRoleActivityType named activityType 0..*
* availableTime.extension[appointmentRequired] ^min = 0
* availableTime.extension[appointmentRequired] ^isModifier = false
* availableTime.extension[homeCare] ^min = 0
* availableTime.extension[homeCare] ^isModifier = false
* availableTime.extension[activityType] ^min = 0
* availableTime.extension[activityType] ^isModifier = false
* availableTime.daysOfWeek ^comment = "se referer à https://build.fhir.org/valueset-days-of-week.html"
* availableTime.allDay ..0
* availableTime.availableStartTime ^short = "Heure de début de la plage horaire"
* availableTime.availableEndTime ^short = "Heure de fin de la plage horaire."
* notAvailable ..0
* availabilityExceptions ..0
* endpoint ..0