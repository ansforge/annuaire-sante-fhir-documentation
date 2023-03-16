Extension: PractitionerRoleVitaleAccepted
Id: PractitionerRole-VitaleAccepted
* ^meta.lastUpdated = "2022-07-08T13:28:56.3272356+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "PractitionerRole"
* . ^short = "l’indicateur Carte Vitale acceptée précise si le professionnel, dans le cadre de cette situation opérationnelle, dispose des moyens techniques pour prendre en charge la carte vitale ou pas."
* value[x] only boolean
* value[x] ^comment = "0 : pas de prise en charge de la carte vitale.\r\n1 : prise en charge de la carte vitale."