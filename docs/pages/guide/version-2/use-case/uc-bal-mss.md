---
layout: menu-version-2
title: "Extraction des BAL MSSanté"
subTitle: Cas d'utilisation
---

<div class="wysiwyg" markdown="1">
- [Récupérer les BAL sur chaque ressource](#1-header)
    - [Interroger la ressource Practitioner](#11-header)
    - [Interroger la ressource PractitionerRole](#12-header)
    - [Interroger la ressource Organization](#13-header)
- [Zoom sur les BAL selon la structure](#2-header)
</div>
<br />

L’ANS, en tant que régulateur de l’espace national de confiance MSSanté, tient à jour l’annuaire national MSSanté, qui rassemble toutes les Boîtes Aux Lettres (BAL) de Messageries Sécurisées de Santé (MSS) transmises par les opérateurs de l’espace de confiance.
L’opérateur transmet, pour chaque BAL du périmètre cité, un identifiant de la personne (Identifiant National ou identifiant RPPS) et/ou un identifiant de structure.

Il existe plusieurs types de BAL MSS :

| Type de BAL       | Description |
| ---               | ---         |
| Personnelle       | Il existe deux types de BAL personnelles : les BAL des personnes physiques disposant d’un identifiant RPPS, dont l’usage est sous la responsabilité exclusive du porteur de l’adresse. Et les BAL personnelles qui sont attribuées à des personnes dont l'identité RPPS est enregistrée par une personne morale (établissement de santé, éditeur de logiciels) responsable de l'accès et de l'usage de la BAL |
| Cabinet           | Les BAL Cabinet correspondent aux BAL des cabinets libéraux |
| Organisationelle  | les BAL organisationnelles sont associées à un service ou à une équipe, rattachées à une personne morale responsable de l’accès et de l’usage de la BAL (ex: service de cardiologie) |
| Applicative       | les BAL Applicatives associées à un logiciel métier ou à une machine sont utilisées à des fins d’envois ou de réception automatisés, rattachées à une personne morale responsable de l’accès et de l’usage de la BAL|

&nbsp;

NOTE | Pour plus d'informations sur la MSSanté, [cliquez ici](https://mailiz.mssante.fr/home). 


## <a id="one-header"></a>1. Récupérer les BAL sur chaque ressource
Les types de BAL se retrouvent principalement principalement sur les ressources suivantes:
<div class="wysiwyg" markdown="1">
* Practitioner: on retrouve les BAL Personnelles et Cabinet qui sont associées uniquement à un identifiant RPPS ;
* PractitionerRole : on retrouve les BAL Personnelles et Cabinet qui sont associées à un identifiant RPPS et à une structure.
* Organization : on retrouve les BAL Organisationnelles et Applicatives. Elles sont associées exclusivement à une structure. On retrouve également les adresses mails servant au contact standard de la structure et qui ne sont pas des BAL MSS.
</div>

<br />

#### <a id="11-header"></a>1.1 Interroger la ressource Practitioner

En tant que client de l'API, je souhaite rechercher l'ensemble des boîtes aux lettres de messagerie sécurisée sur la ressource Practitioner

**Requêtes :**

```sh
GET [base]/Practitioner?mailbox-mss:contains=%40
# récupère les professionnels qu disposent au minimum d'une BAL MSS

GET [base]/Practitioner?mailbox-mss:contains=@medecin.mssante.fr
# récupère les professionnels qu disposent d'une BAL MSS contenant "@medecin.mssante.fr"
```
<br />

#### <a id="12-header"></a>1.2 Interroger la ressource PractitionerRole

En tant que client de l'API, je souhaite rechercher l'ensemble des boîtes aux lettres de messagerie sécurisée sur la ressource PractitionerRole.

**Requêtes :**

```sh
GET [base]/PractitionerRole?mailbox-mss:contains=%40
# récupère les activités des professionnels qu disposent au minimum d'une BAL MSS

GET [base]/PractitionerRole?mailbox-mss:contains=@medecin.mssante.fr
# récupère les activités des professionnels qu disposent d'une BAL MSS contenant "@medecin.mssante.fr"
```
<br />

#### <a id="13-header"></a>1.3 Interroger la ressource Organization

La ressource Organization peut contenir :
<div class="wysiwyg" markdown="1">
- des adresses mail MSS qui correspondent à des BAL Organisationnels ou Applicatives
- des adresses mail correspondant au contact de la structure (ex: l'adresse mail du contact standard d'un cabinet libéral). Ces adresses mails ne sont pas des BAL MSS.
</div>
En tant que client de l'API, je souhaite rechercher l'ensemble des boîtes aux lettres de messagerie sécurisée sur la ressource Organization.

**Requêtes :**

```sh
GET [base]/Organization?mailbox-mss:contains=%40
# récupère les BAL qu disposent au minimum d'une BAL

GET [base]/Organization?mailbox-mss:contains=@ch-dax.mssante.fr
# récupère les activités des professionnels qu disposent d'une BAL MSS contenant @louispasteursante.mssante.fr
```
<br />


 ## <a id="two-header"></a>2. Zoom sur les BAL selon le secteur d'activité

Pour récupérer les BAL sur les Centres de Santé, il faut interroger la ressource "Organization" et appliquer les deux critères suivants:
<div class="wysiwyg" markdown="1">
- Filter sur les organisations appartenant au secteur d'activité SA05 correspondant aux Centres de Santé
- Inclure uniquement les organisations qui disposent au moins d'une BAL MSS (mailbox-mss:contains=%40)
</div>
<br />

Ci-dessous un tableau contenant quelques exemples de secteurs d'activités liés à chaque structure :

| Structures                | Secteurs d'activités      |
| ---                       | ---                       |
| Centres de santé          | SA05                      |
| Laboratoires              | SA25, SA29                |
| Pharmacies d'officines    | SA33, SA38, SA39, SA65    |
| EPHAD                     | SA17                      |


**Requêtes :**

```sh
GET [base]/Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite%7CSA05&mailbox-mss:contains=%40
# récupère les centres de santé (SA05) et qui disposent au moins d'une BAL MSS

GET [base]/Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite%7CSA25,https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite%7CSA29&mailbox-mss:contains=%40
# récupère les laboratoires (SA25, SA29) et qui disposent au moins d'une BAL MSS
```
<br />

Le résultat retourné est un Bundle contenant la première page de résultat. Ci-dessous un exemple de code qui peut donner un résultat équivalent :
<br/>

```sh
        ...
                "type": [
                    {
                        "extension": [
                            {
                                "url": "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-ext-organization-types",
                                "valueCode": "organizationType"
                            }
                        ],
                        "coding": [
                            {
                                "system": "https://hl7.fr/ig/fhir/core/CodeSystem/fr-core-cs-v2-3307",
                                "code": "GEOGRAPHICAL-ENTITY"
                            }
                        ]
                    },
                    {
                        "extension": [
                            {
                                "url": "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-ext-organization-types",
                                "valueCode": "secteurActiviteRASS"
                            }
                        ],
                        "coding": [
                            {
                                "system": "https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite",
                                "code": "SA05"
                            }
                        ]
                    },
                    {
                        "coding": [
                            {
                                "system": "https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement",
                                "code": "124"
                            }
                        ]
                    },
                    {
                        "extension": [
                            {
                                "url": "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-ext-organization-types",
                                "valueCode": "activiteINSEE"
                            }
                        ],
                        "coding": [
                            {
                                "system": "https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5",
                                "code": "86.23Z"
                            }
                        ]
                    }
                ],
                "name": "CENTRE DE SANTE DR HOUSE",
                "telecom": [
                    {
                        "extension": [
                            {
                                "url": "https://hl7.fr/ig/fhir/core/StructureDefinition/fr-core-contact-point-email-type",
                                "valueCoding": {
                                    "system": "https://mos.esante.gouv.fr/NOS/TRE_R256-TypeMessagerie/FHIR/TRE-R256-TypeMessagerie",
                                    "code": "MSSANTE"
                                }
                            },
                            {
                                "url": "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-ext-mailbox-mss-metadata",
                                "extension": [
                                    {
                                        "url": "type",
                                        "valueCodeableConcept": {
                                            "coding": [
                                                {
                                                    "system": "https://mos.esante.gouv.fr/NOS/TRE_R257-TypeBAL/FHIR/TRE-R257-TypeBAL",
                                                    "code": "ORG"
                                                }
                                            ]
                                        }
                                    },
                                    {
                                        "url": "description",
                                        "valueString": "DR HOUSE Secrétariat "
                                    },
                                    {
                                        "url": "digitization",
                                        "valueBoolean": true
                                    }
                                ]
                            }
                        ],
                        "system": "email",
                        "value": "secretariat.drhouse@aura.mssante.fr"
                    }
                ]
        ...
```
<br />

Pour récupérer les BAL MSS Personnelles des professionnels ayant une activité dans une de ces structures, il faut interroger la ressource PractitionerRole.
<div class="wysiwyg" markdown="1">
- Dans un premier appel : filtrer sur les structures appartenant au secteur d'activité SA05 (correspondant aux Centres de Santé). Inclure également les PractitionerRole liés aux Organizations en utilisant le _revinclude
- Dans un second appel : pour chacun des ID techniques des PractitionerRole récupérés dans le premier appel, réaliser un second appel pour appeler les ID techniques du Practitioner et faire un _revinclude pour récupérer le Practitioner
</div> 
<br />

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %} 
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA05&_revinclude=PractitionerRole:organization" 
{% endhighlight %}
 </div>
</div>
<br />

