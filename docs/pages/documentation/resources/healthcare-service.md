---
layout: documentation
title: Healthcare Service
subTitle: Ressources
---

## Description métier de la ressource
Il s'agit d'une ressource divisée en deux profils pour décrire les « [activités de soin](https://mos.esante.gouv.fr/5.html#_2f0d6658-e0f7-4486-a646-424b09f01f76) » et les « [équipements sociaux](https://mos.esante.gouv.fr/5.html#_def51d8f-2eb8-47f8-9c30-b03709096666) » :

<div class="wysiwyg" markdown="1">
* HealthcareService-SocialEquipment pour les équipements sociaux : date d'autorisation, date de première installation, code de discipline d'équipement, clientèle prise en charge, type d'activité.
* HealthcareService-HealthCareActivity pour les activités de soin : type et code d'activité de soin, numéro d'autorisation ARGHOS, prériode de validité d'autorisation, indicateur de suppression sur implantation, code de modalité, code de forme.
</div>
<br>

## Recherche de structure sur critères
Voici des exemples de requêtes sur les services de santé qui sont représentés dans le serveur FHIR par la ressource ["HealthCareService".](https://hl7.org/FHIR/healthcareservice.html)


### - Rechercher tout
Pour ce faire, il faut interroger l'endpoint FHIR HealthcareService.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/HealthcareService"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var bundle = client.search().forResource(HealthcareService.class).returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
// print HealthcareService data:
var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
logger.info("Healthcare Service found: id={}", healthcareService.getIdElement().getIdPart());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/HealthcareService');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    echo("Healthcare Service found: id=".$healthcareService->getId()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var bundle = client.Search<HealthcareService>();
foreach (var be in bundle.Entry)
{
    // print HealthcareService data:
    var healthcareService = be.Resource as HealthcareService;
    Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value}");
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=52-52-49883
Healthcare Service found: id=53-53-64147
Healthcare Service found: id=76-91-59118
```

<br>

### - Rechercher par identifiant 
Pour ce faire, il faut interroger l'endpoint FHIR HealthcareService.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/HealthcareService?identifier=52-52-49883"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var typeSearchClause = HealthcareService.IDENTIFIER.exactly().codes("52-52-49883");

var bundle = client.search()
.forResource(HealthcareService.class)
.where(typeSearchClause)
.returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
// print HealthcareService data:
var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
logger.info("Healthcare Service found: id={}", healthcareService.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/HealthcareService?identifier=52-52-49883');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    echo("Healthcare Service found: id=".$healthcareService->getId()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("identifier=52-52-49883")
  .LimitTo(50);
var bundle = client.Search<HealthcareService>(q);
foreach (var be in bundle.Entry)
{
    // print HealthcareService data:
    var healthcareService = be.Resource as HealthcareService;
    Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value}");
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=52-52-49883
```

<br>

### Rechercher par activité de soin
Pour rechercher toutes les activités de soins ayant comme forme d’activité la Chirurgie ambulatoire (code 07).

Vous pouvez trouvez les codes d'activité dans les référenciels MOS :
<div class="wysiwyg" markdown="1">
- [TRE-R276-FormeActivite](https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite)
- [TRE-R209-TypeActivite](https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite)
</div>
<br>
<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R276-FormeActivite%2FFHIR%2FTRE-R276-FormeActivite%7C07"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var characteristicSearchClause = HealthcareService.CHARACTERISTIC.exactly().codes(
               "https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite",
                "07"
);

var bundle = client.search()
    .forResource(HealthcareService.class)
    .where(characteristicSearchClause)
    .returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
    // print HealthcareService data:
    var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
    var healthcareServiceCoding = healthcareService.getCharacteristicFirstRep().getCodingFirstRep();
    String characteristicData = healthcareServiceCoding.getSystem().concat("|").concat(healthcareServiceCoding.getCode());
    logger.info("Healthcare Service found: id={} | characteristic={}", healthcareService.getIdElement().getIdPart(), characteristicData);
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R276-FormeActivite%2FFHIR%2FTRE-R276-FormeActivite%7C07');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    $characteristic = $healthcareService->getCharacteristic()[0]->getCoding()[0]->getSystem() . '|' . $healthcareService->getCharacteristic()[0]->getCoding()[0]->getCode();
    echo("Healthcare Service found: id=".$healthcareService->getId()." | characteristic=". $characteristic ."\n");
}

{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("characteristic=https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite|07")
  .LimitTo(50);
var bundle = client.Search<HealthcareService>(q);
foreach (var be in bundle.Entry)
{
    // print HealthcareService data:
    var healthcareService = be.Resource as HealthcareService;
    var healthcareServiceCoding = healthcareService.Characteristic[0].Coding[0];

    Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} | characteristic={healthcareServiceCoding.System}|{healthcareServiceCoding.Code}");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=04-04-62678 | characteristic=https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite|07
Healthcare Service found: id=53-53-50060 | characteristic=https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite|07
```

<br>

Voici un second exemple sur le référenciel TRE-R209-TypeActivite. 

    A noter que l'on spécifie le système pour chercher dans le bon référentiel: 

Pour rechercher tous les équipements sociaux ayant comme type d’activité « Hébergement complet internat» (code 11).

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R209-TypeActivite%2FFHIR%2FTRE-R209-TypeActivite%7C11"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var characteristicSearchClause = HealthcareService.CHARACTERISTIC.exactly().systemAndValues(
        "https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/HealthcareService-SocialEquipment-rass",
        "11"
);

var bundle = client.search()
        .forResource(HealthcareService.class)
        .where(characteristicSearchClause)
        .returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
    // print HealthcareService data:
    var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
    var healthcareServiceCoding = healthcareService.getCharacteristicFirstRep().getCodingFirstRep();
    String characteristicData = healthcareServiceCoding.getCode().concat("|").concat(healthcareServiceCoding.getSystem()).concat("|").concat(healthcareServiceCoding.getDisplay());
    logger.info("Healthcare Service found: id={} | characteristic={}", healthcareService.getIdElement().getIdPart(), characteristicData);
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R209-TypeActivite%2FFHIR%2FTRE-R209-TypeActivite%7C11');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    $characteristic = $healthcareService->getCharacteristic()[0]->getCoding()[0]->getSystem() . '|' . $healthcareService->getCharacteristic()[0]->getCoding()[0]->getCode();
    echo("Healthcare Service found: id=".$healthcareService->getId()." | characteristic=". $characteristic ."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("characteristic=https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite|11")
  .LimitTo(50);
var bundle = client.Search<HealthcareService>(q);
foreach (var be in bundle.Entry)
{
    // print HealthcareService data:
    var healthcareService = be.Resource as HealthcareService;
    var healthcareServiceCoding = healthcareService.Characteristic[0].Coding[0];

    Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} | characteristic={healthcareServiceCoding.System}|{healthcareServiceCoding.Code}");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=004-102455 | characteristic=https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite|11
Healthcare Service found: id=004-103009 | characteristic=https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite|11
```

<br>


### - Rechercher par status
Pour rechercher tous les services de santé actifs

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/HealthcareService?active=true"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var activeSearchClause = HealthcareService.ACTIVE.exactly().code("true");

var bundle = client.search()
.forResource(HealthcareService.class)
.where(activeSearchClause)
.returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
// print HealthcareService data:
var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
logger.info("Healthcare Service found: id={} | status={}", healthcareService.getIdElement().getIdPart(), healthcareService.getActive());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/HealthcareService?active=true');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    echo("Healthcare Service found: id=".$healthcareService->getId()." | status=". $healthcareService->getActive() ."\n");
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
var bundle = client.Search<HealthcareService>(q);
foreach (var be in bundle.Entry)
{
    // print HealthcareService data:
    var healthcareService = be.Resource as HealthcareService;
    Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} | status={healthcareService.Active.Value}");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=hcs-hcs-413 | status=true
Healthcare Service found: id=hcs-hcs-655 | status=true
Healthcare Service found: id=hcs-hcs-897 | status=true
Healthcare Service found: id=hcs-hcs-412 | status=true
```

<br>


### - Rechercher par date de mise à jour
Pour rechercher toutes les données complémentaires FINESS ayant été mises à jour depuis le 18/08/2022.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/HealthcareService?_lastUpdated=ge2022-08-18"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the date search parameter :
var dateParam = new DateClientParam("_lastUpdated");

var bundle = client.search()
        .forResource(HealthcareService.class)
        .where(dateParam.afterOrEquals().day("2022-08-18"))
        .returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
    // cast entry :
    var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
    // print update date & id :
    logger.info("HealthcarService found: id={} lastUpdate={}", healthcareService.getIdElement().getIdPart(), healthcareService.getMeta().getLastUpdated());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/HealthcareService?_lastUpdated=ge2022-08-18');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();
    echo("Healthcare Service found: id=".$healthcareService->getId()." lastUpdate=". $healthcareService->getMeta()->getLastUpdated() ."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("_lastUpdated=ge2022-08-18")
  .LimitTo(50);
var bundle = client.Search<HealthcareService>(q);
foreach (var be in bundle.Entry)
{
    // print HealthcareService data:
    var healthcareService = be.Resource as HealthcareService;
    Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} lastUpdate={healthcareService.Meta.LastUpdated.Value}");
}
{% endhighlight %}
</div>


</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
HealthcarService found: id=004-1014038 lastUpdate=Tue Sep 06 03:21:02 CEST 2022
HealthcarService found: id=004-1014044 lastUpdate=Tue Sep 06 03:21:02 CEST 2022
HealthcarService found: id=004-1014050 lastUpdate=Tue Sep 06 03:21:02 CEST 2022
```

<br>
{% include_relative _source-ref.md %}

