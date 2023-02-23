Extension: ASPractitionerRolePartOf
Id: as-practitionerrole-partof
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-partof"
Description: "Permet de faire le lien avec un exercice professionnel. Cette extension est à utiliser dans le profil situation d'exercice de la ressource PractitionerRole."
* ^meta.lastUpdated = "2022-07-25T17:20:59.4408054+00:00"
* ^version = "1.0"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-practitionerrole-partof" (exactly)
* . ^short = "Référence vers l’exercice professionnel de rattachement (PractitionerRole)"
* value[x] 1..
* value[x] only Reference(PractitionerRole)
* value[x] ^short = "Référence vers l'id de la ressource PractionerRole-Professionalrole à laquelle est rattachée la situtation d'exercice"