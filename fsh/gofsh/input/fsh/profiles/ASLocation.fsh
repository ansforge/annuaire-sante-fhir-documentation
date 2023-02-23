Profile: ASLocation
Parent: Location
Id: as-location
Description: "Profil créé à partir de la ressource Location dans le cadre de l'annuaire santé - Location contained dans le profil Situation d'exercice de PractitionerRole -  contient l'adresse d'activité du professionnel."
* ^url = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-location"
* ^meta.lastUpdated = "2022-07-08T13:11:25.3167142+00:00"
* ^version = "0.2"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"

* meta.source = "https://annuaire.sante.fr" (exactly)
* meta.profile = "http://interop.esante.gouv.fr/fhir/ig/as10/StructureDefinition/as-location" (exactly)

* address only FrAddressExtended