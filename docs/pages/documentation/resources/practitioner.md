---
layout: documentation
title: Practitioner
subTitle: Ressources
---


Voici des exemples de requêtes sur les praticiens qui sont représentés dans le serveur FHIR par la ressource [Practitioner.](https://hl7.org/FHIR/practitioner.html)


## Rechercher tout

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Practitioner"
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

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()."\n");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=003-137722 name=M
Practitioner found: id=003-138668 name=M
Practitioner found: id=003-138612 name=M
```

<br>


## Rechercher selon un ou plusieurs identifiants

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Practitioner?identifier=0012807590%2C810000005479"
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

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()."\n");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=0012807590 name=MME
Practitioner found: id=810000005479 name=MME
```

<br>

## Rechercher selon le préfixe du nom

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Practitioner?name=MME"
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

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()."\n");
}

{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=0102800000 name=MME
Practitioner found: id=0102800273 name=MME
```

<br>

## Rechercher les praticiens actifs

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Practitioner?active=true"
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

    echo("Practitioner found: name=".$practitioner->getName()." | active=". $practitioner->getActive(). "\n");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: name=M | active=true
Practitioner found: name=MME | active=true
Practitioner found: name=M | active=true
```

<br>

## Rechercher par date de modification

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Practitioner?_lastUpdated=ge2022-08-08T06%3A47%3A02"
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


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=003-852396 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022
Practitioner found: id=003-869607 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022
Practitioner found: id=003-139099 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022
Practitioner found: id=003-139084 | lastUpdate=Fri Sep 02 17:34:54 CEST 2022
```
