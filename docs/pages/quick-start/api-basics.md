---
layout: documentation
title: Bases de l'API
---



### Erreur NET::ERR_CERT_AUTHORITY_INVALID sur l'IHM (https://portail.openfhir.annuaire.sante.fr)
L’erreur NET::ERR_CERT_AUTHORITY_INVALID est rencontrée car le certificat exposé sur le portail de démo de l’API FHIR est un certificat issu de l’IGC Santé de l’ANS, qui n’est pas une autorité de certification reconnue par les navigateurs du marché (a contrario des Thawte, DigiCert, etc).
Pour y remédier, il faut  ajouter l’AC IGC Santé dans le navigateur pour qu’elle soit reconnue par la suite. 

<p align="center">
  <img src="img/err_cert_authority_invalid.png" style="width:100%;">
</p>

### Paramètres d’entrée

Les paramètres et critères de recherche de l'API sont standard FHIR  :
<div class="wysiwyg"  markdown="1">
-	Paramètres : https://www.hl7.org/fhir/search.html
-	Critères de recherche : https://www.hl7.org/fhir/searchparameter-registry.html
-	Paramètres créés pour les recherches sur les champs inclus dans des extensions et autres : https://www.hl7.org/fhir/searchparameter.html
</div>
<br />
NOTE| Pour plus de détails sur les paramètres d’entrées de l'API, se référer au CapabilityStatement : [api-url]/metadata

### Paramètres et modificateurs de requêtes FHIR

Sont supportés les paramètres et le modificateurs suivants :
<div class="wysiwyg"  markdown="1">
- _revinclude, 
- _include
- _count : 50 est la valeur par défaut
- _total : none, estimate, accurate. le calcul du total par le service n'est pas systématique car ça dépend du temps nécessaire à son  calcul. si ce temps est important, le total ne sera pas affiché.
- Tous les préfixes de comparaison 
- Préfixes [date](https://hl7.org/FHIR/search.html#date): eq (equal), ge (greater equal), gt (greater than), lt (less than), le (less equal)
- _elements : permet de préciser la liste d’attributs qui doit être retournée avec la ressource.
</div>
<br />

### Pagination

Lorsqu’une réponse inclut de nombreux résultats, l'API les pagine et en retourne une partie. Par défaut, elle ne retourne que 50 
éléments .Quand une réponse est paginée, les réponses incluent des  liens permettant de récupérer les pages suivantes.
Seul le lien Next est supporté.

### Suppression d'une ressource (active=false ; status=inactive) 

De manière générale, la ressource n'est plus publiée dans l'API à partir du moment où elle devient inactive. Cependant, elle ne disparait pas complétement de l'API puisqu'elle reste présente dans le delta avec uniquement son id et le champs active = false (device : status inactive). Ce fonctionnement permet notamment aux consommateurs du delta d'isoler les ressources supprimées entre deux dates (synchronisations).

Par défaut, l'API remonte toutes les ressources (actives et inactives). 
Pour exlcure les ressources inactives du résulat, utilisez le paramètre active ou status.

<div class="wysiwyg"  markdown="1">
- active pour l'ensemble des ressources excepté Device (exemple : [api-url]/Practitioner?active=true)
- status pour Device (exemple : [api-url]/Device?status=active)
</div>
<br />

```json

{
    "resourceType": "Bundle",
    "id": "835a413a-d78e-4e80-8735-7a9e14b1b4e9",
    "meta": {
        "lastUpdated": "2023-11-02T10:02:24.909+00:00"
    },
    "type": "searchset",
    "total": 1,
    "link": [
        {
            "relation": "self",
            "url": "https://gateway.preprod.api.esante.gouv.fr/fhir/v1/Practitioner?_id=003-118475&active=false"
        }
    ],
    "entry": [
        {
            "fullUrl": "https://gateway.api.esante.gouv.fr/fhir/v1/Practitioner/003-118475",
            "resource": {
                "resourceType": "Practitioner",
                "id": "003-118475",
                "meta": {
                    "versionId": "2",
                    "lastUpdated": "2023-09-05T08:55:12.763+00:00",
                    "source": "https://annuaire.sante.fr",
                    "profile": [
                        "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-Practitioner"
                    ]
                },
                "active": false
            }
        }
    ]
}


```