---
layout: documentation
title: Practitioner
subTitle: Ressources
---

## Description métier de la ressource

Il s'agit d'une ressource qui regroupe  les données décrivant le [« professionnel »](https://mos.esante.gouv.fr/2.html#_9d79ff39-6b00-4aa6-ac03-7afb4a8aad2b) :

<div class="wysiwyg" markdown="1">
* Données d'identification : numéro RPPS (identifiant unique et pérenne de la personne dans le répertoire), numéro ADELI, civilité ou tout autre identifiant permettant, le cas échéant, d'assurer la transition des systèmes vers une identification par le numéro RPPS  
* Données de contact : adresse de messagerie électronique (MSS).
* Données relatives aux titres liés à l'exercice professionnel : intitulé de diplôme, attestation, certificat ou autre titre et autorisation d'exercice.

</div>

<p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Note : Le nom et le prénom d'exercice du professionnel sont restitués au niveau de la ressource « PractitionerRole ».
</p>
<br />

## Caractéristiques techniques de la ressource

<table width="25%">
<tbody>
<tr>
<td width="45%">
<p><strong>Endpoint</strong></p>
</td>

<td width="54%">
<p>{{site.ans.api_url}}/fhir/v1/Practitioner</p>
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
<p><strong>Paramètres de recherche</strong></p>
</td>
<td width="54%">
<p>_id, identifier, name, mailbox-mss, _lastUpdated, active, _total</p>
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
<br />

## Recherche de professionnel de santé sur critères

Voici des exemples de requêtes sur les professionnels de santé (PS).


### 1) Rechercher tout (sans critère)

**Récit utilisateur :** En tant que client de l'API, je souhaite récupérer l'ensemble des professionnels de santé.

**Exemples de requêtes :**

```sh
GET [base]/Practitioner 
GET [base]/Practitioner?_revinclude=PractitionerRole:practitioner #inclure les practitionerRole qui référencent les practitioner (Practitioner + PractitionerRole)


```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner found: id=003-137722 name=M
  Practitioner found: id=003-138668 name=M
  Practitioner found: id=003-138612 name=M


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var bundle = client.search().forResource(Practitioner.class).returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {
    // print Organization ids:
    var practitioner = (Practitioner) practitionerEntry.getResource();
    logger.info("Practitioner found: id={} name={}", practitioner.getIdElement().getIdPart(), practitioner.getNameFirstRep().getNameAsSingleString());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Practitioner');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()[0]->getPrefix()[0]->getValue()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var bundle = client.Search<Practitioner>();
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    var name = "";
    foreach (var n in practitioner.Name[0].Prefix) {
        name = name + " " + n;
    }
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}
</div>
</div>
<br />


### 2) Rechercher par identifiant (identifier)

**Récit utilisateur :** En tant que client de l'API, je souhaite vérifier l'identité d'un professionnel de santé à partir de son identifiant.

**Requête :**

`GET [base]/Practitioner?identifier=0012807590`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
  Practitioner found: id=0012807590 name=MME


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?identifier=0012807590%2C810000005479"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var identifierParams = Practitioner.IDENTIFIER.exactly().codes("0012807590", "810000005479");

var bundle = client.search()
.forResource(Practitioner.class)
.where(identifierParams)
.returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {
// print Organization ids:
var practitioner = (Practitioner) practitionerEntry.getResource();
logger.info("Practitioner found: id={} name={}", practitioner.getIdentifierFirstRep().getValue(), practitioner.getNameFirstRep().getNameAsSingleString());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Practitioner?identifier=0012807590%2C810000005479');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()[0]->getPrefix()[0]->getValue()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("identifier=0012807590,810000005479")
  .LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    var name = "";
    foreach (var n in practitioner.Name[0].Prefix)
    {
        name = name + " " + n;
    }
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}
</div>
</div>
<br />


### 3) Rechercher par civilité (name)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher tous les professionnels de santé ayant comme code civilité « MME ».

**Requête :**

`GET [base]/Practitioner?name=MME`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner found: id=0102800000 name=MME
  Practitioner found: id=0102800273 name=MME


```

<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?name=MME"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var nameSearchClause = Practitioner.NAME.matches().value("MME");

var bundle = client.search()
.forResource(Practitioner.class)
.where(nameSearchClause)
.returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {
// print Organization ids:
var practitioner = (Practitioner) practitionerEntry.getResource();
logger.info("Practitioner found: id={} name={}", practitioner.getIdentifierFirstRep().getValue(), practitioner.getNameFirstRep().getNameAsSingleString());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Practitioner?name=MME');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()[0]->getPrefix()[0]->getValue()."\n");
}

{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("name=MME")
  .LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    var name = "";
    foreach (var n in practitioner.Name[0].Prefix)
    {
        name = name + " " + n;
    }
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}
</div>
</div>
<br />


### 4) Rechercher par status (active)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher tous les professionnels de santé actifs.

**Requête :**

`GET [base]/Practitioner?active=true`

**Réponse (simplifiée) :** 
  
```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner found: name=M | active=true
  Practitioner found: name=MME | active=true
  Practitioner found: name=M | active=true


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?active=true"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var activeSearchClause = Practitioner.ACTIVE.exactly().code("true");

var bundle = client.search()
.forResource(Practitioner.class)
.where(activeSearchClause)
.returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {
// print Organization ids:
var practitioner = (Practitioner) practitionerEntry.getResource();
logger.info("Practitioner found: name={} | active={}", practitioner.getNameFirstRep().getNameAsSingleString(), practitioner.getActive());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Practitioner?active=true');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: name=".$practitioner->getName()[0]->getPrefix()[0]->getValue()." | active=". $practitioner->getActive(). "\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("active=true")
  .LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | active={practitioner.Active.Value}");
}
{% endhighlight %}
</div>
</div>
<br />


### 5) Rechercher par date de mise à jour (_lastUpdated)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher tous les professionnels de santé mis à jour depuis une certaine date.

**Requête :**

`GET [base]/Practitioner?_lastUpdated=ge2022-08-08`

**Réponse (simplifiée) :** 
  
```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner found: id=003-852396 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022
  Practitioner found: id=003-869607 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022
  Practitioner found: id=003-139099 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022
  Practitioner found: id=003-139084 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?_lastUpdated=ge2022-08-08T06%3A47%3A02"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the date search parameter :
var dateParam = new DateClientParam("_lastUpdated");

var bundle = client.search()
        .forResource(Practitioner.class)
        .where(dateParam.afterOrEquals().second("2022-08-08T06:47:02"))
        .returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {
    // cast entry :
    var practitioner = (Practitioner) practitionerEntry.getResource();
    // print update date & id :
    logger.info("Practitioner found: id={} | lastUpdate={}", practitioner.getIdElement().getIdPart(), practitioner.getMeta().getLastUpdated());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Practitioner?_lastUpdated=ge2022-08-08T06%3A47%3A02');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." | lastUpdate=".$practitioner->getMeta()->getLastUpdated()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("_lastUpdated=ge2022-08-08T06:47:02")
  .LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | lastUpdate={practitioner.Meta.LastUpdated}");
}
{% endhighlight %}
</div>
</div>
<br />


{% include_relative _source-ref.md %}

