---
layout: menu-version-2
title: Organization
subTitle: Ressources
---

<div class="wysiwyg" markdown="1">
- [Présentation de la ressource](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche d'une structure](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par raison sociale](#42-header)
  - [Rechercher par identifiant structure](#43-header)
  - [Rechercher par date de mise à jour](#44-header)
  - [Rechercher par type](#45-header)
  - [Rechercher par code postal](#46-header)
</div>
<br />


## <a id="one-header"></a>1) Présentation de la ressource

Il s'agit d'une ressource qui regroupe  les données décrivant la [« structure »](https://mos.esante.gouv.fr/4.html#_f6152a96-2f8f-4f69-89f5-18f024d4b4d8) :
<div class="wysiwyg" markdown="1">
* numéros SIREN/ SIRET ou FINESS, type de structure (géographique ou juridique), activité , secteur d'activité santé, catégorie juridique, modalités de participation au service public hospitalier (SPH), 
* raison sociale, enseigne commerciale, coordonnées (adresse postale, adresses de messagerie électronique, téléphones), 
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
<p>{{site.ans.api_url}}/fhir/v2/Organization</p>
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

## <a id="three-header"></a>3) Paramètres de recherche (Search Parameter)

| Nom                               | Type      | Description                                               |
| ---                               | ---       | ---                                                       |
| _id                               | token     | Recherche sur l'ID technique de la ressource Organization |
| _lastUpdated                      | date      | Renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée (eq, ne, gt, lt, ge, le, ap)|
| active                            | token     | Recherche selon le statut de l'organisation     |
| address                           | string    | Recherche sur le champ adresse de l'organisation   |
| address-city                      | string    | Recherche sur la commune de l'organisation       |
| address-postalcode                | string    | Recherche sur le code postal de l'organisation    |
| data-information-system           | token     | Recherche sur le système d'information      |
| data-registration-authority       | token     | Recherche sur l'autorité d'enregistrement   |
| identifier                        | token     | Recherche sur l'identifiant de la structure       |
| identifier-type                   | token     | Recherche sur le type d'identifiant de la structure |
| mailbox-mss                       | string    | Recherche sur les messageries sécurisées de santé (MSS) rattachées aux organisations |
| name                              | string    | Recherche sur la raison sociale des structures            |
| partof                            | reference | Recherche tous les établissements géographiques rattachés à une même entité juridique |
| pharmacy-licence                  | string    | Recherche sur le numéro de licence des pharmacies officines |
| type                              | token     | Recherche sur le type de structure/ code APE/ catégorie juridique/ secteur d'activité/ catégorie d'établissement ou le code SPH de la structure |

## <a id="four-header"></a>4) Recherche de structure sur critères

Voici quelques exemples de requêtes sur les structures.

#### <a id="41-header"></a>4.1) Rechercher tout (sans critère)

**Récit utilisateur :** 
En tant que client de l'API, je souhaite récupérer l'ensemble des structures.

**Requêtes :**

```sh
GET [base]/Organization
# récupère l'ensemble des structures (incluant les actives et les inactives)

GET [base]/Organization?_include=Organization:partof 
# récupère les entités géographiques ainsi que leurs entités juridiques associées

GET [base]/Organization?_revinclude=Device:organization 
# récupère les entités ainsi que leurs équipements matériels lourds associés

GET [base]/Organization?_revinclude=HealthcareService:organization 
# récupère les entités ainsi que leurs activités de soins associés

GET [base]/Organization?_revinclude=PractitionerRole:organization 
# récupère les entités ainsi que toutes les activités des professionnels associées

GET [base]/Organization?_include=* 
# inclure toutes les ressources qui sont référencées par les Organization (Device, PractitionerRole, HealthCareService)
```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" {{site.ans.api_url}}/fhir/v2/Organization
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}

// Création du client
var client = FhirTestUtils.createClient();
var bundle = client.search().forResource(Organization.class).returnBundle(Bundle.class).execute();
for(var organizationEntry : bundle.getEntry()){
    // print les IDs des organisations
    var organization = (Organization) organizationEntry.getResource();
    logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// Création du client
var client = FhirTestUtils.CreateClient();

var bundle = client.Search<Organization>();
foreach (var be in bundle.Entry)
{
    // print les IDs:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} name={organization.Name}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="42-header"></a>4.2) Rechercher par raison sociale (name)

**Récit utilisateur :** 
En tant que client de l'API, je souhaite trouver une structure à partir de sa raison sociale.

**Requête :**

```sh
GET [base]/Organization?name%3Acontains=imagerie%2Ccentre
```

<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Organization?name%3Acontains=imagerie%2Ccentre"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// Création du client
var client = FhirTestUtils.createClient();

// Création du Search Parameter name
var nameSearchClause = Organization.NAME.contains().values("imagerie", "centre");

var bundle = client.search()
.forResource(Organization.class)
.where(nameSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){

// Convertir l'entrée
var organization = (Organization) organizationEntry.getResource();

// print data :
logger.info("Organization found: name={}", organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}

// Création du client
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("name:contains=imagerie").Add("name:contains", "centre")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
// Print IDs
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: name={organization.Name}");
}
{% endhighlight %}
</div>

</div>
<br />

#### <a id="43-header"></a>4.3) Rechercher par identifiant structure (identifier)

**Récit utilisateur :** 
En tant que client de l'API, je souhaite rechercher une structure à partir de son identifiant structure

**Requête :**

```sh
GET [base]/Organization?identifier=001604103000
# récupérer une structure dont l'identifiant est 001604103000

GET [base]/Organization?as-sp-data-information-system=FINESS&identifier=001604103000
# récupérer une structure qui proviennent du référentiel FINESS. Les trois valeurs disponibles sont RPPS, FINESS et CG.


```

<br />


**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Organization?identifier=001604103000"
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
.where(idParam.matches().values("001604103000"))
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print update date & id :
logger.info("Organization found: id={}", organization.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("identifier=001604103000")
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

#### <a id="44-header"></a>4.4) Rechercher par date de mise à jour (_lastUpdated)

**Récit utilisateur :** 
En tant que client de l'API, je souhaite rechercher toutes les structures mises à jour depuis une certaine date.


**Requête :**
```sh
`GET [base]/Organization?_lastUpdated=ge2022-08-05`
# Récupère toutes les structures mises à jour à partir du 05 août 2022 (inclus) jusqu'à aujourd'hui

```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = FhirTestUtils.createClient();

// Création du paramètre de recherche Date
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
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// Création du client
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("_lastUpdated=ge2022-08-05T14:51:04")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // Print les IDs
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} lastUpdate={organization.Meta.LastUpdated}");
}
{% endhighlight %}
</div>

</div>
<br />


#### <a id="45-header"></a>4.5) Rechercher par type (type)

Le champs "Type" de la ressource Organization peut contenir des informations différentes en fonction du code système renseigné. Le type permet de rechercher sur le secteur d'activité, la catégorie d'établissement, le type d'organisation (entité géographique, entité juridique), etc.

| Type de données           | Code système                                        |
| ---                       | ---                                                 |
| Type d'organisation       | https://hl7.fr/ig/fhir/core/2.1.0/ValueSet-fr-core-vs-organization-type.html  | 
| APE                       | https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5   |
| Catégorie Juridique       | https://mos.esante.gouv.fr/NOS/TRE_R72-FinessStatutJuridique/FHIR/TRE-R72-FinessStatutJuridique |
| Secteur d'activité        | https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite |
| Catégorie d'établissement | https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement |
| SPH                       | https://mos.esante.gouv.fr/NOS/TRE_R73-ESPIC/FHIR/TRE-R73-ESPIC |

Lorsque vous souhaitez rechercher sur un type de données particulier, utiliser les combinaisons suivantes : 
<div class="wysiwyg" markdown="1">
- Renseigner le code système concerné
- Renseigner le code fonctionnel de la valeur souhaité
</div>
</br>

**Exemples de requêtes :**

```sh
GET [base]/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R66-CategorieEtablissement%2FFHIR%2FTRE-R66-CategorieEtablissement%7C101
# récupère les organisations qui appartiennent à la catégorie d'établissement 101 - Centre Hospitalier Régional (C.H.R.)

GET [base]/Organization?type=GEOGRAPHICAL-ENTITY
# récupère les organisations qui sont uniquement des entités géographiques. Les deux types possibles sont GEOGRAPHICAL-ENTITY et LEGAL-ENTITY

GET [base]/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA02
# récupère les organisations qui font partie du secteur d'activité SA02

```
</br>

#### <a id="46-header"></a>4.6) Rechercher par code postal et ville (address-postalcode et address-city)

**Récit utilisateur :** 
En tant que client de l'API, je souhaite rechercher les structures d'un département (code postal).

**Requête :**

```sh
GET [base]//Organization?address-postalcode=75016&address-city=PARIS
# récupère les organisations qui sont dans la commune de Paris et qui ont un code postal 75016
```
<br />

**Exemples de code:**

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Organization?address-postalcode=75016&address-city=PARIS" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} 
// create the client: 
var client = FhirTestUtils.createClient();

// create the search parameters:
var postalCodeParam = Organization.ADDRESS_POSTALCODE.matchesExactly().values("75016");
var cityParam = Organization.ADDRESS_CITY.matchesExactly().values("PARIS");

var bundle = client.search()
.forResource(Organization.class)
.where(postalCodeParam)
.and(cityParam)
.returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {

// Convertir l'entrée:
var organization = (Organization) organizationEntry.getResource();
// print la data:
logger.info("Organization found: name={} | zipCode={} | city={}",
organization.getName(),
organization.getAddressFirstRep().getPostalCode(),
organization.getAddressFirstRep().getCity());
}
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import Bundle, Organization

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/Organization"
api_key = "{{site.ans.api_key}}"
postal_code = "75016"
city = "PARIS"

headers = {
"ESANTE-API-KEY": api_key,
"Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR avec un code postal et une ville
def fetch_organizations_by_postal_code_and_city():
response = requests.get(f"{api_url}?address-postalcode={postal_code}&address-city={city}", headers=headers)
if response.status_code == 200:
bundle = Bundle(**response.json())
for entry in bundle.entry:
organization = entry.resource
if isinstance(organization, Organization):
name = organization.name if organization.name else "Unknown"
zip_code = organization.address[0].postalCode if organization.address else "Unknown"
city_name = organization.address[0].city if organization.address else "Unknown"
print(f"Organization found: name={name} | zipCode={zip_code} | city={city_name}")
else:
response.raise_for_status()

 # Utilisation du client
fetch_organizations_by_postal_code_and_city()
{% endhighlight %}

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} 

// create the client: 
var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
.Where("address-postalcode=75016")
.And("address-city=PARIS")
.LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
// print ids:
var organization = be.Resource as Organization;
Console.WriteLine($"Organization found: name={organization.Name} | zipCode={organization.Address[0].PostalCode} | city={organization.Address[0].City}");
}
{% endhighlight %}

</div> </div> <br />

{% include_relative _source-ref.md %}
