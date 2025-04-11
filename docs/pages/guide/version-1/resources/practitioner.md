---
layout: menu-version-1
title: Practitioner
subTitle: Ressources
---
<!-- <p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Cette documentation concerne la version 1 de l'API qui sera prochainement décommissionnée. Nous vous invitons à migrer vers la version 2 de l'API FHIR Annuaire Santé.
</p> -->

<div class="wysiwyg" markdown="1">
- [Description métier](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche d'un professionnel](#four-header)
  - [Rechercher tout](#31-header)
  - [Rechercher par identifiant](#32-header)
  - [Rechercher par statut](#33-header)
  - [Rechercher par date de mise à jour](#34-header)
</div>
<br />


## <a id="one-header"></a>1) Description métier de la ressource

Il s'agit d'une ressource qui regroupe  les données décrivant l'[« exercice professionnel »](https://mos.esante.gouv.fr/2.html#_9d79ff39-6b00-4aa6-ac03-7afb4a8aad2b). Les informations disponibles sont :

<div class="wysiwyg" markdown="1">
* Données d'identification : numéro RPPS (identifiant unique et pérenne de la personne dans le répertoire), numéro ADELI, civilité ou tout autre identifiant permettant, le cas échéant, d'assurer la transition des systèmes vers une identification par le numéro RPPS  
* Données de contact : adresse de messagerie électronique (MSS).
* Données relatives aux titres liés à l’exercice professionnel : intitulé de diplôme, attestation, certificat ou autre titre et autorisation d’exercice.

</div>
<br />

<p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Note : Le nom et le prénom d'exercice du professionnel sont restitués au niveau de la ressource « PractitionerRole ».
</p>
<br />

## <a id="two-header"></a>2) Caractéristiques techniques de la ressource

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
<p><strong>Paramètres de requête</strong></p>
</td>
<td width="54%">
<p>_count, _include, _revinclude</p>
</td>
</tr>
</tbody>
</table>
<br />

## <a id="three-header"></a>3) Paramètres de recherche

| Nom | Type | Description |
| --- | --- | --- |
| _id | token | ID de la ressource |
| _lastUpdated | date | renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée (eq, ne, gt, lt, ge, le, ap). Plus d'informations sur les [dates] (https://build.fhir.org/search.html#date) |
| _since | date | |
| _total | string | |
| active | token | Recherche les ressources Practitioner actives |
| as-sp-data-information-system | token | Recherche sur le système d'information |
| as-sp-data-registration-authority | token | Recherche sur l'autorité d'enregistrement |
| identifier| token | Recherche sur tous les identifiants des professionnels intervenant dans le système de santé|
| identifier-type| token | Recherche sur les types d'identifiants (RPPS, IDNPS - IDentifiant National du Professionnel intervenant dans le système de Santé |
| mailbox-mss| string | La Messagerie Sécurisées de Santé du Professionnel|
| name | string | Une recherche définie par le serveur qui peut correspondre à n'importe quel champ de HumanName, ici sur le préfix correspondant à la civilité des professionnels de santé|


## <a id="four-header"></a>4) Recherche d'un professionnel sur des critères spécifiques

Voici des exemples de requêtes sur la recherche de professionnels intervenant dans le système de santé.


#### <a id="41-header"></a>4.1) Rechercher tout (sans critère)

**Contexte:** 
En tant que client de l'API, je souhaite récupérer l'ensemble des professionnels intervenant dans le système de santé. 

**Exemples de requêtes :**

```sh
GET [base]/Practitioner
#récupère l'ensemble des practitioners (incluant les actifs et les inactifs)

GET [base]/Practitioner?_revinclude=PractitionerRole:practitioner 
#inclure les practitionerRole qui référencent les practitioners (Practitioner + PractitionerRole)

```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner"
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


#### <a id="42-header"></a>4.2) Rechercher par identifiant (identifier)

**Récit utilisateur :** En tant que client de l'API, je souhaite vérifier l'identité d'un professionnel à partir de son identifiant.

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
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?identifier=0012807590%2C810000005479"
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



#### <a id="43-header"></a>4.3) Rechercher par statut (active)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher tous les professionnels de santé actifs.

**Requête :**

`GET [base]/Practitioner?active=true`


**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?active=true"
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


#### <a id="44-header"></a>4.4) Rechercher par date de mise à jour (_lastUpdated)

En tant que client de l'API, je souhaite rechercher tous les professionnels de santé mis à jour depuis une certaine date.

**Requête :**

`GET [base]/Practitioner?_lastUpdated=ge2025-08-08`

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?_lastUpdated=ge2022-08-08T06%3A47%3A02"
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

