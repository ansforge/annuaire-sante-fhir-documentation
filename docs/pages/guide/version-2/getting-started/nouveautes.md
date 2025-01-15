---
layout: menu-version-2
title: Les nouveautés de cette API
subTitle: Nouvelle API V2
---

### Quelles sont les différences entre l'API FHIR V1 et l'API FHIR V2 ?

| Ressources        | Description  |
| ---               | ---          |
| Respect du standard FHIR | L'API est désormais basée sur le nouveau guide d'implémentation qui respecte davantage au standard FHIR. Pour plus d'informations, n'hésitez pas à consulter le nouveau guide d'implémentation|
| Meilleure séparation de la partie FHIR | Dans la V1 : La ressource PractitionerRole contenait des informations liées aux professionnels ainsi que les activités. Désormais dans l'API FHIR V2 : il y a une meilleure distinction: les informations liées aux professionnels sont situées sur la ressource Practitioner, les informations liées à l'activité sont situées sur la ressource PractitionerRole |
| Identifier métier du PractitioneRRole| La ressource PractitionerRole contient désormais l'information "identifier" correspondant au numéro d'activité / identifiant métier de la situation d'exercice. C'est également un paramètre de recherche|

