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
- [Zoom sur les BAL selon le secteur d'activité](#2-header)
    - [BAL des centres de santé](#21-header)
    - [BAL des laboratoires](#22-header)
    - [BAL des officines](#23-header)
    - [Rechercher les BAL des EPHAD](#24-header)
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

#### <a id="21-header"></a>2.1 Centres de santé (type=SA05) 

Pour récupérer les BAL sur les Centres de Santé, il faut interroger la ressource "Organization" et appliquer les deux critères suivants:
<div class="wysiwyg" markdown="1">
- Filter sur les organisations appartenant au secteur d'activité SA05 correspondant aux Centres de Santé
- Inclure uniquement les organisations qui disposent au moins d'une BAL MSS (mailbox-mss:contains=%40)
</div>
<br />

**Requêtes :**

```sh
GET [base]/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA05&mailbox-mss:contains=%40
# récupère les organisations qui ont un secteur d'activité SA05 et qui disposent au moins d'une BAL MSS
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
                "name": "CENTRE DE SANTE DARWING",
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
                                        "valueString": "Darwin Secrétariat "
                                    },
                                    {
                                        "url": "digitization",
                                        "valueBoolean": true
                                    }
                                ]
                            }
                        ],
                        "system": "email",
                        "value": "secretariat.darwin@hopitaldarwin.mssante.fr"
                    }
                ],
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

L’exécution de l’exemple de code peut donner un résultat équivalent :
<br />

<div class="wysiwyg" markdown="1">
 * Schéma montrant le champs practitioner du PractitionerRole : 
<img src="focus_postman_irisdp_bal_mss_per_centre_de_sante_1.png" alt="practitioner-ref">
</div>

<div class="wysiwyg" markdown="1">
 * Schéma montrant les champs BAL MSS, Type de BAL et Identifiant personne : 
<img src="focus_postman_irisdp_bal_mss_per_centre_de_sante_2.png" alt="mailbox, mailbox-type, id-practitioner">
</div>


<br />

<!-- ## <a id="lab-header"></a>2) Laboratoires
Le process d'extraction des BAL est similaire à celui appliqué précédemment pour les centres de santé.

Afin de récupérer les établissements de biologie , nous devons interroger l’endpoint Organization :
<div class="wysiwyg" markdown="1">
* En filtrant sur le système et les types d’établissements : https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite, SA25, SA29
</div>
<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %} 
Faire un appel sur l'endpoint Organization en filtrant sur les Organizations :
  * de type SA25, SA29
  * et ayant au moins un mailbox 
 {% endhighlight %}
</div>
<div class="tab-content" data-name="curl">
{% highlight bash %} 
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA25%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA29&mailbox-mss:contains=%40" 
{% endhighlight %}
</div>
</div>

 <br />

## <a id="ph-header"></a>3) Officines
Le process d'extraction des BAL est similaire à celui appliqué précédemment pour les centres de santé.

Afin de récupérer les officines de pharmacie, nous devons interroger l’endpoint Organization :
<div class="wysiwyg" markdown="1">
* En filtrant sur le système et les types d’établissements : https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite, SA33, SA38, SA39
</div>
<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %} 
Faire un appel sur l'endpoint Organization en filtrant sur les Organizations :
  * de type SA33, SA38, SA39, SA65
  * et ayant au moins un mailbox 
 {% endhighlight %}
</div>
<div class="tab-content" data-name="curl">
{% highlight bash %} 
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organizationt?ype=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA33%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA38%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA39&mailbox-mss:contains=%40" 
{% endhighlight %}
</div>
</div>

 <br />

## <a id="ep-header"></a>4) EPHAD

#### 4.1) Liste des EPHAD
Afin de récupérer la liste des EPHAD, nous devons interroger l’endpoint Organization :
<div class="wysiwyg" markdown="1">
* En filtrant sur le système et le type d’établissement : https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite, SA17
* En incluant les entités juridiques de rattachement : Organization.partof
</div>
<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %} 
Faire un appel sur l'endpoint Organization en filtrant sur les Organizations dont le secteur d'activité est SA17.  Cet appel devra inclure les établissements pères dits juridiques auxquels sont rattachés les établissements géographiques (&_include=Organization:partof):
{% endhighlight %}
</div>

<div class="tab-content" data-name="curl">
{% highlight bash %} 
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organizationt?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA17&_include=Organization:partof" 
{% endhighlight %}
</div>
</div>
L’exécution de l’exemple de code peut donner un résultat équivalent :
<br />

<div class="wysiwyg" markdown="1">
 * Schéma montrant les champs FINESS EG et Type d'établissement (géographique ou juridique) : 
<img src="focus_postman_irisdp_liste_ephad_1.png" alt="Schéma montrant les champs FINESS EG et Type d'établissement (géographique ou juridique)">
</div>
<br/>
<div class="wysiwyg" markdown="1">
 * Schéma montrant les champs Raison sociale, Code postal (département/région) et Id technique de l'entité juridique de rattachement : 
<img src="focus_postman_irisdp_liste_ephad_2.png" alt="Schéma montrant Raison sociale + Code postal (département/région) + Id technique de l'entité juridique de rattachement">
</div>
<br/>
<div class="wysiwyg" markdown="1">
 * Schéma montrant les champs Organization.partof (lien vers l'entité juridique) et FINESS EJ : 
<img src="focus_postman_irisdp_liste_ephad_3.png" alt="Schéma montrant Organization.partof + FINESS EJ">
</div>
<br />

#### 4.2) Liste des BAL rattachées
##### 4.2.1) BAL ORG
 Afin d'extraire les BAL MSSanté organisationnelles , il faut interroger l’endpoint Organization.

Nous appliquerons deux filtres à la requête afin d’obtenir le résultat attendu :
<div class="wysiwyg" markdown="1">
 * le type d'Organization : SA17 (secteur d'activité) 
 * Et en n'incluant que les Organizations ayant au moins d'une BAL MSS
</div>
<br/>
<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %} 
Faire un appel sur l'endpoint Organization en filtrant sur les Organizations :
  * de type SA17 (type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite)
  * et ayant au moins un mailbox (mailbox-mss:contains=%40 )
 {% endhighlight %}
</div>
<div class="tab-content" data-name="curl">
{% highlight bash %} 
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA17&mailbox-mss:contains=%40" 
{% endhighlight %}
</div>
</div>

 -->
