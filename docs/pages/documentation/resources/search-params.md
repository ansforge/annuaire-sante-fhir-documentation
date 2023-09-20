---
layout: documentation
title: Paramètres de recherche
subTitle: Ressources
---
*Lien vers la spécification FHIR : <https://hl7.org/FHIR/search.htm>*

## Paramètres de recherche disponibles
Pour afficher les paramètres de recherche pris en charge par l'API, vous pouvez interroger le CapabilityStatement.
avec la requête suivante: GET [base]/metadata.

**Requête :**

`GET [BASE]/metadata`

## Paramètres de type texte ([string](https://www.hl7.org/fhir/search.html#string))

Les recherchers de type texte peuvent s'effectuer sur les différentes ressources disponibles.

### Recherche sans "modifier"

**Requête :**

`GET [BASE]/Organization?name=Renard`

**Réponse (simplifiée) :** 
  
```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: name=Renard et Renard
  Organization found: name=Renard SCOP
  Organization found: name=Renard EURL
```

**Exemples de code :**  
<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name=Renard"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var matchSearchClause = Organization.NAME.matches().value("Renard");

var bundle = client.search()
        .forResource(Organization.class)
        .where(matchSearchClause)
        .returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: name={}", organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?name=Renard');
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
// create the name search parameter :
var q = new SearchParams()
  .Where("name=Renard")
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

### Recherche avec le "modifier" "contains"

**Requête :**

`GET [BASE]/Organization?name%3Acontains=EURL`

**Réponse (simplifiée) :** 

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: name=Perez EURL
  Organization found: name=Gautier EURL
```

**Exemples de code :** 

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=EURL"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var containsSearchClause = Organization.NAME.contains().value("EURL");

var bundle = client.search()
.forResource(Organization.class)
.where(containsSearchClause)
.returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
logger.info("Organization found: name={}", organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?name%3Acontains=EURL');
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
// create the name search parameter :
var q = new SearchParams()
  .Where("name:contains=EURL")
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

### Recherche avec le "modifier" "exact"

**Requête :**

`GET [BASE]/Organization?name%3Aexact=Gautier%20EURL`

**Réponse (simplifiée) :** 

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: id=org-183 name=Gautier EURL
  Organization found: id=org-358 name=Gautier EURL
```

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Aexact=Gautier%20EURL"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var exactSearchClause = Organization.NAME.matchesExactly().value("Gautier EURL");

var bundle = client.search()
        .forResource(Organization.class)
        .where(exactSearchClause)
        .returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?name%3Aexact=Gautier%20EURL');
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
// create the name search parameter :
var q = new SearchParams()
  .Where("name:exact=Gautier EURL")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
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

## Paramètres de type [token](https://www.hl7.org/fhir/search.html#token)
Le serveur supporte la recherche par code, par système ou par les deux.

### Recherche par code

**Requête :**

`GET [BASE]/Organization?identifier=org-org-148`

**Réponse (simplifiée) :** 

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: id=org-148 name=Renard et Renard
```

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?identifier=org-org-148"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var tokenSearchClause = Organization.IDENTIFIER.exactly().code("org-org-148");

var bundle = client.search()
.forResource(Organization.class)
.where(tokenSearchClause)
.returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?identifier=org-org-148');
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
// create the name search parameter :
var q = new SearchParams()
  .Where("identifier=org-org-148")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
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

## Paramètres de type [date](https://www.hl7.org/fhir/search.html#date)
La recherche par date supporte les préfixes: gt, lt, le, ge, eq. 
Plusiseurs "précisions" sont supportées : yyyy par année, yyyy-MM-dd par jour, et par date complète.

**Quelques exemples :** 

<div class="wysiwyg" markdown="1">
* _lastUpdated=gt2020 : après 2020
* _lastUpdated=ge2020 : 2020 et après (2020 inclus)
* _lastUpdated=lt2022-12-15 : avant le 15 décembre 2022 exclus
* _lastUpdated=lt2022-12-15T15:00:00 : avant le 15 décembre 2022 15h (GMT)
* _lastUpdated=eq2021 : durant l'année 2021
</div>

&nbsp;


**Requête :**

`GET [BASE]/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04`

**Réponse (simplifiée) :** 

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: id=org-148 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-149 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-144 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-386 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
  Organization found: id=org-145 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
```

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04" 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
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
// create the name search parameter :
var q = new SearchParams()
  .Where("_lastUpdated=ge2022-08-05T14:51:04")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} lastUpdate={organization.Meta.LastUpdated.Value}");
}
{% endhighlight %}
</div>
</div>
<br />
  
## Paramètres de type référence  ([reference](https://www.hl7.org/fhir/search.html#reference))
<p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Cette partie de la spécification est en cours de construction.
</p>

## Paramètres de type [uri](https://www.hl7.org/fhir/search.html#uri)
<p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Cette partie de la spécification est en cours de construction.
</p>

## Paramètres combinés
Les paramètres combinés permettent d'effectuer des recherches en les cumulant.
Ce cumul se fait de manière inclusive ou alternative.

### Paramètres ET (AND)

**Requête :**

`GET [BASE]/Organization?name%3Acontains=Renard&name%3Acontains=et`

**Réponse (simplifiée) :** 

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: id=org-148 | name=Renard et Renard
  Organization found: id=org-176 | name=Renard et Renard
Organization found: id=org-128 | name=Renard et Renard
```

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H# "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=Renard&name%3Acontains=et"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the two name search parameters :
var firstSearchClause = Organization.NAME.contains().value("Renard");
var secondSearchClause = Organization.NAME.contains().value("et");

var bundle = client.search()
        .forResource(Organization.class)
        .where(firstSearchClause)
        .and(secondSearchClause)
        .returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: id={} | name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?name%3Acontains=Renard&name%3Acontains=et');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." | name=".$organization->getName()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("name:contains=Renard").Add("name:contains", "et")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} | name={organization.Name}");
}
{% endhighlight %}
</div>
</div>
<br />
  
### Paramètres OU (OR)

**Requête :**

`GET [BASE]/Organization?name%3Acontains=Renard%2Cet`

**Réponse (simplifiée) :** 

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Organization found: id=org-148 | name=Renard et Renard
  Organization found: id=org-386 | name=Lopez et Lopez
  Organization found: id=org-145 | name=Maillard et Maillard
```

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=Renard%2Cet"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the two name search parameters :
var orSearchClause = Organization.NAME.contains().values("Renard", "et");

var bundle = client.search()
        .forResource(Organization.class)
        .where(orSearchClause)
        .returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: id={} | name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Organization?name%3Acontains=Renard%2Cet');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." | name=".$organization->getName()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("name:contains=Renard,et")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} | name={organization.Name}");
}
{% endhighlight %}
</div>
</div>
<br />
  
### Paramètres des résultats de la recherche
Il s'agit d'un ensemble de paramètres permettant de gérer les résultats retournés par une recherche. 
Vous trouverez ci-dessous la liste des paramètres de résultats de recherche pris en charge dans notre contexte.

#### Paramètre ["_count"](https://www.hl7.org/fhir/search.html#count) :
Il permet de contrôler le nombre maximal de ressources retournées sur une page lorsqu'une réponse de l'API est paginée. Par exemple, _count=10 renvoie un maximum de 10 ressources. La valeur par défaut est 50.

**Requête:**

`GET [BASE]/Device?_count=200`

#### Paramètre ["_total"](https://www.hl7.org/fhir/search.html#total) :
Comme son nom l'indique, ce paramètre indique le nombre total d'éléments (ressources) qui correspondent aux critères de recherche.
Ce paramètre peut prendre 3 valeurs : none, estimate ou accurate.

<div class="wysiwyg" markdown="1">
* none : le total n'est pas affiché
* estimate : le total affiché est une estimation
* accurate : le total affiché est précis
</div>

&nbsp;

Cet exemple montre comment utiliser le paramètre  _total=none pour ne pas afficher le total :

**Requête:**

`GET [BASE]/Device?_total=none`

Par défaut, l'affichage (ou pas) du total dépend principalement du temps nécessaire à son calcul. Ainsi, si le temps de calcul est trop important, le total ne sera pas inclus dans la réponse.
Dans la majorité des cas, le total est affiché sauf dans certains cas particuliers, comme les recherches textuelles (champs de type string) sur de gros volumes de données. Par exemple, rechercher tous les PractitionerRole ayant un nom d'exercice contenant « Martin ».   

&nbsp;

{% include_relative _source-ref.md %}

