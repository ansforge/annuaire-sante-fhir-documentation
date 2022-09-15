---
layout: documentation
title: Paramètres de recherche
subTitle: Ressources
---


## Paramètres de type texte

Les recherchers de type texte peuvent s'effectuer sur les différentes ressources disponibles.


### Recherche sans "modifier"

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Organization?name=Renard"
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


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: name=Renard et Renard
Organization found: name=Renard SCOP
Organization found: name=Renard EURL
```
<br/>


### Recherche avec le "modifier" "contains"

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Organization?name%3Acontains=EURL"
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


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: name=Perez EURL
Organization found: name=Gautier EURL
```
<br/>


### Recherche avec le "modifier" "exact"

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Organization?name%3Aexact=Gautier%20EURL"
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


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-183 name=Gautier EURL
Organization found: id=org-358 name=Gautier EURL
```

<br/>

## Paramètres de type token

Le serveur supporte la recherche par code, par système ou par système+code.

### Recherche par code

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Organization?identifier=org-org-148"
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
$response = $client->request('GET', '/fhir/v1/Organization?identifier=01604103000');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." name=".$organization->getName()."\n");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-148 name=Renard et Renard
```

<br/>

## Paramètres de type date

La recherche par date supporte les préfixes: gt, lt, le, ge, eq. Plusiseurs "précisions" sont supportées : yyyy par année, yyyy-MM-dd par jour, et par date complète

### Recherche par date

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04" 
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


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-148 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-149 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-144 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-386 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-145 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
```

<br/>

## Paramètres combinés

Les paramètres combinés permettent d'effectuer des recherches en les cumulant.
Ce cumul se fait de manière inclusive ou alternative.

### Paramètres ET (AND)

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H# "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Organization?name%3Acontains=Renard&name%3Acontains=et"
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


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-148 | name=Renard et Renard
Organization found: id=org-176 | name=Renard et Renard
Organization found: id=org-128 | name=Renard et Renard
```

<br/>

### Paramètres OU (OR)


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Organization?name%3Acontains=Renard%2Cet"
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


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-148 | name=Renard et Renard
Organization found: id=org-386 | name=Lopez et Lopez
Organization found: id=org-145 | name=Maillard et Maillard
```


NOTE | Pour aller plus loin, vous pouvez vous référer à la documentation de référence HL7 : (https://hl7.org/FHIR/search.html)
