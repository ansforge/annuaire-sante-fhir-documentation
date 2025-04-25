---
layout: menu-version-2
title: Modificateurs des résultats de recherche
subTitle: Ressources
---

<div class="wysiwyg" markdown="1">

[Modificateurs des résultats de recherche](#0-header)
- [Paramètre _count](#1-header)
- [Paramètre _total](#2-header)
- [Paramètre _include](#3-header)
- [Paramètre _revinclude](#4-header)
- [Paramètre _elements](#5-header)


</div>
<br />

  
## <a id="0-header"></a>Modificateurs des résultats de la recherche

Il s'agit d'un ensemble de paramètres permettant de gérer les résultats retournés par une recherche. 
Vous trouverez ci-dessous la liste des paramètres de résultats de recherche pris en charge dans notre contexte.

### <a id="1-header"></a>1) Paramètre ["_count"](https://www.hl7.org/fhir/search.html#count) 

Il permet de contrôler le nombre maximal de ressources retournées sur une page lorsqu'une réponse de l'API est paginée. Par exemple, _count=10 renvoie un maximum de 10 ressources. La valeur par défaut est 50.

**Requête:**

```sh
`GET [base]/Device?_count=10`
```

&nbsp;


### <a id="2-header"></a>2) Paramètre ["_total"](https://www.hl7.org/fhir/search.html#total) 

Ce paramètre indique le nombre total d'éléments (ressources) qui correspondent aux critères de recherche. Ce paramètre peut prendre 3 valeurs :
<div class="wysiwyg" markdown="1">
- none : le total n'est pas affiché
- estimate : le total affiché est une estimation
- accurate : le total affiché est précis
</div>

&nbsp;

**Exemple de requête :**

```sh
GET [base]/Device?_total=none

```
<p>Par défaut, l'affichage (ou non) du total dépend principalement du temps nécessaire à son calcul. Ainsi, si le temps de calcul est trop important, le total ne sera pas inclus dans la réponse.
Dans la majorité des cas, le total est affiché sauf dans certains cas particuliers, comme les recherches textuelles (champs de type string) sur de gros volumes de données. Par exemple, rechercher tous les PractitionerRole ayant un nom d'exercice contenant « Martin ».</p>

### <a id="3-header"></a>3) Paramètre ["include"](https://www.hl7.org/fhir/search.html#_include) 

Le paramètre **_include** permet d’afficher dans le résultat les ressources mères liées à la ressource fille. Les ressources mères sont récupérées à partir de la ressource fille. 


**Exemple de requête :**

```sh
GET [base]/PractitionerRole?_include=PractitionerRole:organization
# Récupère les PractitionerRole ainsi que les organisations liées à ces PractitionerRole
``` 

#### <a id="4-header"></a>4) Paramètre ["revinclude"](https://www.hl7.org/fhir/search.html#_revinclude) 

Le paramètre **_revinclude** permet d’afficher dans le résultat les ressources filles liées à la ressource mère. Les ressources filles sont récupérées à partir de la ressource mère. 


**Exemples:**

```sh
GET [base]/Organization?_revinclude=Organization:partof
# Récupère les Entités géographiques (EG) et les Entités Juridiques (EJ) de rattachement
```


### <a id="5-header"></a>5) Paramètre ["_elements"](https://hl7.org/fhir/search.html#_elements) 

Le paramètre **_elements** permet de préciser la liste d’attributs qui doit être retournée avec la ressource.  


**Exemples:**

```sh
GET [base]/Practitioner?identifier=10001766673&_elements=name
# Récupère pour mon identifiant RPPS 10001766673 les informations liées à l'attribut name

GET [base]/PractitionerRole?_elements=telecom
# Récupère l'ensemble des PractitionerRole et n'affiche que les informations liées à l'attribut telecom (BAL MSS)

```
A noter que la réponse contient également une meta.tag valeur de SUBSETTED pour indiquer que tous les attributs de la ressource ne sont pas inclus.

```json

...
                    "tag": [
                        {
                            "system": "http://terminology.hl7.org/CodeSystem/v3-ObservationValue",
                            "code": "SUBSETTED",
                            "display": "Resource encoded in summary mode"
                        }
                    ]
                },
                "identifier": [
                    {
                        "use": "official",
                        "type": {
                            "coding": [
                                {
                                    "system": "http://interopsante.org/CodeSystem/v2-0203",
                                    "code": "IDNPS"
                                }
                            ]
                        },
                        "system": "urn:oid:1.2.250.1.71.4.2.1",
                        "value": "810000001916"
                    },
                    {
                        "use": "official",
                        "type": {
                            "coding": [
                                {
                                    "system": "http://interopsante.org/CodeSystem/v2-0203",
                                    "code": "RPPS"
                                }
                            ]
                        },
                        "system": "http://rpps.fr",
                        "value": "10000001916"
                    }
                ],
                "active": true
...

```

&nbsp;
