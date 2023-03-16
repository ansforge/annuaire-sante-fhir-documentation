Extension: PractitionerRolePartOf
Id: PractitionerRole-PartOf
Description: "Permet de faire le lien avec un exercice professionnel. Cette extension est à utiliser dans le profil situation d'exercice de la ressource PractitionerRole."
* ^meta.lastUpdated = "2022-07-25T17:20:59.4408054+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* . ^short = "Référence vers l’exercice professionnel de rattachement (PractitionerRole)"
* value[x] 1..
* value[x] only Reference(PractitionerRole)
* value[x] ^short = "Référence vers l'id de la ressource PractionerRole-Professionalrole à laquelle est rattachée la situtation d'exercice"