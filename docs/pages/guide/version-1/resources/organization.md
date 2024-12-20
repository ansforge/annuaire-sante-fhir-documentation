---
layout: menu-version-1
title: Organization
subTitle: Ressources
---
<!-- <p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Cette documentation concerne la version 1 de l'API qui sera prochainement décommissionnée. Nous vous invitons à migrer vers la version 2 de l'API FHIR Annuaire Santé.
</p> -->

<div class="wysiwyg" markdown="1">
- [Description métier](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche de structure sur critères](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par date de mise à jour](#42-header)
  - [Rechercher par identifiant](#43-header)
  - [Rechercher par numéro FINESS](#44-header)
  - [Rechercher par type GEOGRAPHICAL/LEGAL](#451-header)
  - [Rechercher sur la nomenclature d’activités française de l’Insee](#452-header)
  - [Rechercher par secteur d’activité](#453-header)
  - [Rechercher par nom](#46-header)
  - [Rechercher par code postal](#47-header)
</div>
<br />


## <a id="one-header"></a>1) Description métier de la ressource

Il s'agit d'une ressource qui regroupe  les données décrivant la [« structure »](https://mos.esante.gouv.fr/4.html#_f6152a96-2f8f-4f69-89f5-18f024d4b4d8) :
<div class="wysiwyg" markdown="1">
* numéros SIREN/ SIRET ou FINESS, type de structure (géographique ou juridique), activité , secteur d'activité santé, catégorie juridique, modalités de participation au service public hospitalier (SPH), 
* raison sociale, enseigne commerciale, coordonnées (adresse postale, adresses de messagerie électronique y compris MSS, téléphone, fax), 
* pour les structures géographiques, le numéros FINESS de la strcuture juridique de rattachement.
</div>
<br />

## <a id="two-header"></a>2) Caractéristiques techniques de la ressource

<table width="25%">
<tbody>
<tr>
<td width="45%">
<p><strong>Endpoint</strong></p>
</td>

<td width="54%">
<p>{{site.ans.api_url}}/fhir/v1/Organization</p>
</td>
</tr>
<tr>
<td width="45%">
<p><strong>Header</strong></p>
</td>
<td width="54%">
<p>ESANTE-API-KEY</p>
</td>
</tr>
<tr>
<td width="45%">
<p><strong>Méthodes HTTP associées</strong></p>
</td>

<td width="54%">
<p>GET, POST</p>
</td>
</tr>
<tr>
<td width="45%">
<p><strong>Paramètres de requête</strong></p>
</td>
<td width="54%">
<p>_count, _include, _revinclude</p>
</td>
</tr>
</tbody>
</table>

## <a id="three-header"></a>3) Paramètres de recherche

| Nom                               | Type      | Description                                               |
| ---                               | ---       | ---                                                       |
| _has                              | string    |                                                           |
| _id                               | token     | Recherche sur l'ID de la ressource Organization           |
| _lastUpdated                      | date      | Renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée (eq, ne, gt, lt, ge, le, ap).|
| _since                            | date      |                                                           |
| _total                            | string    |                                                           |
| active                            | token     | Recherche les ressources Organization actives             |
| address                           | string    | Recherche sur (une partie) de l'adresse de la structure   |
| address-city                      | string    | Recherche sur la commune spécifiée dans une adresse       |
| address-country                   | string    | Recherche sur le pays spécifiée dans une adresse          |
| address-postalcode                | string    | Recherche sur le code postal spécifié dans une adresse    |
| address-state                     | string    | Recherche un état specifiée dans une adresse              |
| address-use                       | string    | Recherche sur un code use spécifié dans adresse           |
| as-sp-data-information-system     | token     | Recherche sur le système d'information                    |
| as-sp-data-registration-authority | token     | Recherche sur l'autorité d'enregistrement                 |
| endpoint                          | reference | Endpoint technique fournissant des accès aux services exploités pour l'organisation |
| identifier                        | token     | Recherche sur tous les identifiants des structures        |
| identifier-type                   | token     | Recherche sur les types d'identifiers                     |
| mailbox-mss                       | string    | Recherche sur les messageries sécurisées de santé (MSS) rattachées aux Organizations |
| name                              | string    | Recherche sur la raison sociale des structures            |
| partof                            | reference | Recherche tous les établissements géographiques rattachés à une même entité juridique |
| pharmacy-licence                  | string    | Recherche sur le numéro de licence des officines          |
| type                              | token     | Recherche sur le type de structure/ code APE/ catégorie juridique/ secteur d'activité/ catégorie d'établissement ou le code SPH de la structure |

## <a id="four-header"></a>4) Recherche de structure sur critères

Voici quelques exemples de requêtes sur les structures.

#### <a id="41-header"></a>4.1) Rechercher tout (sans critère)

**Récit utilisateur :** En tant que client de l'API, je souhaite récupérer l'ensemble des structures.

**Requêtes :**

```sh
GET [base]/Organization
#récupère l'ensemble des Organizations (incluant les actives et les inactives)
GET [base]/Organization?_include=Organization:partof #inclure les entités juridiques auxquelles sont rattachées les entités géographiques
GET [base]/Organization?_revinclude=Device:organization #inclure les Device qui référencent les Organization (Organization + Device)
GET [base]/Organization?_revinclude=HealthcareService:organization #inclure les HealthcareService qui référencent les Organization (Organization + HealthcareService)
GET [base]/Organization?_revinclude=PractitionerRole:organization #inclure les PractitionerRole qui référencent les Organization (Organization + PractitionerRole)
GET [base]/Organization?_include=* #inclure toutes les ressources qui sont référencées par les Organization

```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: id=org-399 name=Weber, Weber and Weber
  Organization found: id=org-158 name=Schaefer-Schaefer
  Organization found: id=org-151 name=OReilly, OReilly and OReilly
  Organization found: id=org-393 name=Volkman-Volkman
  Organization found: id=org-152 name=Luettgen, Luettgen and Luettgen
  Organization found: id=org-394 name=Gulgowski, Gulgowski and Gulgowski
  Organization found: id=org-153 name=Wilkinson Group


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" {{site.ans.api_url}}/fhir/v1/Organization
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();
var bundle = client.search().forResource(Organization.class).returnBundle(Bundle.class).execute();
for(var organizationEntry : bundle.getEntry()){
    // print Organization ids:
    var organization = (Organization) organizationEntry.getResource();
    logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()." name=".$organization->getName()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var bundle = client.Search<Organization>();
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} name={organization.Name}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="42-header"></a>4.2) Rechercher par date de mise à jour (_lastUpdated)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher toutes les structures mises à jour depuis une certaine date.

**Requête :**

`GET [base]/Organization?_lastUpdated=ge2022-08-05`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: id=org-148 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-149 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-144 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-386 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-145 lastUpdate=Fri Aug 05 14:51:03 CEST 2022


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = FhirTestUtils.createClient();

// create the date search parameter :
var dateParam = new DateClientParam("_lastUpdated");

var bundle = client.search()
        .forResource(Organization.class)
        .where(dateParam.afterOrEquals().second("2022-08-05T14:51:04"))
        .returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print update date & id :
    logger.info("Organization found: id={} lastUpdate={}", organization.getIdElement().getIdPart(), organization.getMeta().getLastUpdated());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()." lastUpdate=".$organization->getMeta()->getLastUpdated()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("_lastUpdated=ge2022-08-05T14:51:04")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} lastUpdate={organization.Meta.LastUpdated}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="43-header"></a>4.3) Rechercher par identifiant (identifier)
**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher une structure à partir de l'un de ses identifiants.

**Requête :**

`GET [base]/Organization?identifier=001604103000`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
  Organization found: id=001604103000


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?identifier=001604103000%2C01603998400%2C001604252500"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the identifier search parameter :
var idParam = new StringClientParam("identifier");

var bundle = client.search()
.forResource(Organization.class)
.where(idParam.matches().values("001604103000", "01603998400", "001604252500"))
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print update date & id :
logger.info("Organization found: id={}", organization.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?identifier=001604103000%2C01603998400%2C001604252500');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("identifier=001604103000,01603998400,001604252500")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="44-header"></a>4.4) Rechercher par numéro FINESS (identifier)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher une structure à partir de son numéro FINESS.

**Requête :**

`GET [base]/Organization?identifier=http%3A%2F%2Ffiness.sante.gouv.fr%7C060016219`

**Réponse (simplifiée) :** 
  
```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
  Organization found: id=060016219


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?identifier=http%3A%2F%2Ffiness.sante.gouv.fr%7C010000602%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C010000628%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C010000735" 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create finess where clause
var finessSearchClause = Organization.IDENTIFIER.exactly().systemAndValues(
"http://finess.sante.gouv.fr", "010000602", "010000628", "010000735");

var bundle = client.search()
.forResource(Organization.class)
.where(finessSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print id :
logger.info("Organization found: id={}", organization.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?identifier=http%3A%2F%2Ffiness.sante.gouv.fr%7C010000602%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C010000628%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C010000735');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("identifier=http://finess.sante.gouv.fr|010000602,http://finess.sante.gouv.fr|010000628")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="45-header"></a>4.5) Recherches par types (type)

Le champs type de la ressource Organization peut contenir différentes informations en fonction du système.

| Type                    | Description                          | Système                                                                                           | Lien / Options                                                                                    |
|-------------------------|--------------------------------------|---------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| EJ/EG                   | Type d'organization                  | http://interopsante.org/fhir/CodeSystem/fr-v2-3307                                                | GEOGRAPHICAL-ENTITY ou LEGAL-ENTITY                                                               |
| APE                     | JDV_J99-InseeNAFrav2Niveau5-RASS     | https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5       | https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5       |
| Catégorie juridique     | JDV_J100-FinessStatutJuridique-RASS  | https://mos.esante.gouv.fr/NOS/TRE_R72-FinessStatutJuridique/FHIR/TRE-R72-FinessStatutJuridique   | https://mos.esante.gouv.fr/NOS/TRE_R72-FinessStatutJuridique/FHIR/TRE-R72-FinessStatutJuridique   |
| Secteur d’activité      | JDV_J101-SecteurActivite-RASS        | https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite               | https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite               |
| Catégorie Etablissement | JDV_J129-CategorieEtablissement-RASS | https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement | https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement |
| SPH                     | JDV_162-ESPIC-RASS                   | https://mos.esante.gouv.fr/NOS/TRE_R73-ESPIC/FHIR/TRE-R73-ESPIC                                   | https://mos.esante.gouv.fr/NOS/TRE_R73-ESPIC/FHIR/TRE-R73-ESPIC                                   |

Lorsque vous souhaitez rechercher sur un type particulier, utilisez la combinaison du système et du code souhaité : 

`Organization?type=<system>%7C<code>`

**Quelques exemples :** 

<div class="wysiwyg" markdown="1">
* `Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5%7C01.11Z` Recherche par code APE 01.11Z : "Culture de céréales (sf riz) légumineuses, graines oléagineuses" 
* `Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R72-FinessStatutJuridique/FHIR/TRE-R72-FinessStatutJuridique%7C02` Recherche par code Statuts juridiques provenant de FINESS, code 02 : "Département" 
* `Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement%7C101` Recherche par Catégorie d'établissements, code 101 "Centre Hospitalier Régional (C.H.R.)"
</div>

Ci-dessous, vous trouverez 3 exemples complets sur EJ/EG, Secteur d’activité et APE.


##### <a id="451-header"></a>4.5.1) Rechercher par type "GEOGRAPHICAL"/"LEGAL" 

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher les structures de type géographique.

**Remarque :**

Les deux types possibles sont : 
<div class="wysiwyg" markdown="1">
* GEOGRAPHICAL-ENTITY
* LEGAL-ENTITY
</div>
<br />

**Requête :**

`GET [base]/Organization?type=http%3A%2F%2Finteropsante.org%2Ffhir%2FCodeSystem%2Ffr-v2-3307%7CGEOGRAPHICAL-ENTITY`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: name=VILLAGE D'ENFANTS . ACTION ENFANCE type=GEOGRAPHICAL-ENTITY - 87.90A
  Organization found: name=LVA LABONDE LA FORESTIERE type=GEOGRAPHICAL-ENTITY - SA41 - 462
  Organization found: name=SERVICE D'ACTION EDUC EN MILIEU OUVERT type=GEOGRAPHICAL-ENTITY - SA20 
  Organization found: name=ESPACE ARTOIS SANTE - ARRAS type=GEOGRAPHICAL-ENTITY - SA04 - 698 - 9


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?type=http%3A%2F%2Finteropsante.org%2Ffhir%2FCodeSystem%2Ffr-v2-3307%7CGEOGRAPHICAL-ENTITY" 
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?type=GEOGRAPHICAL-ENTITY" 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the type search parameter :
var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
"http://interopsante.org/fhir/CodeSystem/fr-v2-3307", "GEOGRAPHICAL-ENTITY");

var bundle = client.search()
.forResource(Organization.class)
.where(typeSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
var organizationCodes = organization.getType().stream().map(type -> type.getCodingFirstRep().getCode()).collect(Collectors.joining(" - "));
logger.info("Organization found: name={} type={}", organization.getName(), organizationCodes);
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?type=http%3A%2F%2Finteropsante.org%2Ffhir%2FCodeSystem%2Ffr-v2-3307%7CGEOGRAPHICAL-ENTITY');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()." - type=".$organization->getType()[0]->getCoding()[0]->getCode()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("type=http://interopsante.org/fhir/CodeSystem/fr-v2-3307|GEOGRAPHICAL-ENTITY")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    var types = "";
    foreach(var type in organization.Type)
    {
        types = types + " - " +type.Coding[0].Code;
    }
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} type={types}");
}
{% endhighlight %}
</div>

</div>
<br />

##### <a id="452-header"></a>4.5.2) Rechercher sur la nomenclature d'activités française de l'Insee (code APE)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher les structures avec un code APE "**82.19Z**" qui correspond à "Photocopie, préparation de documents et autres activités spécialisées de soutien de bureau"

**Remarque :** 

Les codes APE sont disponibles dans le référenciel TRE-R75-InseeNAFrev2Niveau5 des NOS que vous trouverez ici : [TRE-R75-InseeNAFrev2Niveau5](https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5/)

**Requête :**

`GET [base]/Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5%7C82.19Z`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: name=Skiles, Skiles and Skiles type=SA29 - 82.19Z - LEGAL-ENTITY - someorg
  Organization found: name=Terry, Terry and Terry type=SA29 - 82.19Z - LEGAL-ENTITY - someorg
  Organization found: name=Mills Inc type=SA29 - 82.19Z - LEGAL-ENTITY - someorg


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5%7C82.19Z" 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the search parameter :
var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
"https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5", "82.19Z");

var bundle = client.search()
.forResource(Organization.class)
.where(typeSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
var organizationCodes = organization.getType().stream().map(type -> type.getCodingFirstRep().getCode()).collect(Collectors.joining(" - "));
logger.info("Organization found: id={} type={}", organization.getName(), organizationCodes);
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5%7C82.19Z');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()." - type=".$organization->getType()[0]->getCoding()[0]->getCode()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5|82.19Z")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    var types = "";
    foreach (var type in organization.Type)
    {
        types = types + " - " + type.Coding[0].Code;
    }
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} type={types}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="453-header"></a>4.5.3) Rechercher par secteur d'activité

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher les structures d'un secteur d'activité (SA29 par exemple, qui correspond à  "Laboratoires d'analyses et de biologie médicale").

**Remarque :**

La liste des secteurs d'activités se trouve dans le référenciel TRE_R02-SecteurActivite des NOS que vous trouverez ici : [TRE_R02-SecteurActivite](https://mos.esante.gouv.fr/NOS//FHIR/TRE-R02-SecteurActivite)

**Requête :**

`GET [base]/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA29`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: name=Auer, Auer and Auer activity=Laboratoire d'analyses et de biologie médicale
  Organization found: name=Erdman, Erdman and Erdman activity=Laboratoire d'analyses et de biologie médicale
  Organization found: name=Stiedemann and Sons activity=Laboratoire d'analyses et de biologie médicale


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA29" 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the activity search parameter :
var activitySearchClause = Organization.TYPE.exactly().systemAndValues(
        "https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite", "SA29");

var bundle = client.search()
        .forResource(Organization.class)
        .where(activitySearchClause)
        .returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    var activityName = organization.getTypeFirstRep().getCodingFirstRep().getDisplay();
    logger.info("Organization found: id={} activity={}", organization.getName(), activityName);
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA29');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()." - type=".$organization->getType()[0]->getCoding()[0]->getCode()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("type=https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite|SA29")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    var types = "";
    foreach (var type in organization.Type)
    {
        types = types + " - " + type.Coding[0].Code;
    }
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} activity={types}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="46-header"></a>4.6) Rechercher par nom (name)

**Récit utilisateur :** En tant que client de l'API, je souhaite trouver une structure à partir de son nom.

**Requête :**

`GET [base]/Organization?name%3Acontains=imagerie%2Ccentre`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: name=Mills Inc centre
  Organization found: name=Centre d'imagerie médicale - Selimed 63
  Organization found: name=Imagerie médicale République


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=imagerie%2Ccentre"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var nameSearchClause = Organization.NAME.contains().values("imagerie", "centre");

var bundle = client.search()
.forResource(Organization.class)
.where(nameSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
logger.info("Organization found: name={}", organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?name%3Acontains=imagerie%2Ccentre');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()."\n");
}

{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("name:contains=imagerie").Add("name:contains", "centre")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: name={organization.Name}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="47-header"></a>4.7) Rechercher par code postal (address-postalcode)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher les structures d'un département (code postal).

**Requête :**

`GET [base]/Organization?address-postalcode%3Aexact=13290%2C13321`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: name=Renard et Renard | zipCode=91794
  Organization found: name=Maillard et Maillard | zipCode=10228


```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?address-postalcode%3Aexact=13290%2C13321"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the postal code search parameter :
var nameSearchClause = Organization.ADDRESS_POSTALCODE.matchesExactly().values("13290", "13321");

var bundle = client.search()
        .forResource(Organization.class)
        .where(nameSearchClause)
        .returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: name={} | zipCode={}", organization.getName(), organization.getAddressFirstRep().getPostalCode());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?address-postalcode%3Aexact=13290%2C13321');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    $zip = count($organization->getAddress()) == 0 ? '-' : $organization->getAddress()[0]->getPostalCode()->getValue();
    echo("Organization found: name=".$organization->getName()." | zipCode=".$zip."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("address-postalcode=13290,13290")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: name={organization.Name} | zipCode={organization.Address[0].PostalCode}");
}
{% endhighlight %}
</div>

</div>
<br />

{% include_relative _source-ref.md %}
