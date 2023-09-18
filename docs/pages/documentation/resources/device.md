---
layout: documentation
title: Device
subTitle: Ressources
---

## Description métier de la ressource
Il s'agit d'une ressource qui regroupe  les données complémentaires FINESS portant sur les « [équipements matériels lourds](https://mos.esante.gouv.fr/5.html#_1a21e9b8-d686-41ff-806d-38572f961ec6) :
<div class="wysiwyg" markdown="1">
* numéro d'autorisation ARGHOS, période de validité, marque, numéro de série, code EML 
</div>
<br>

## Recherche d'équipement matériel lourd (EML) sur critères
Voici des exemples de requêtes sur les équipements matériels lourds qui sont représentés dans le serveur FHIR par la ressource ["Device".](https://hl7.org/FHIR/device.html)

### - Rechercher tout
Pour ce faire, il faut interroger l'endpoint FHIR Device.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Device"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var bundle = client.search().forResource(Device.class).returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data:
    var device = (Device) deviceEntry.getResource();
logger.info("Device found: id={} AuthorizationARHGOS={}", device.getIdElement().getIdPart(), device.getExtensionByUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS").getValue());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Device');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    $extArhgos = getExtensionByUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS", $device->getExtension());
    echo("Device found: id=".$device->getId()." AuthorizationARHGOS=".$extArhgos->getValueString()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
var client = FhirTestUtils.CreateClient();

var bundle = client.Search<Device>();
foreach (var be in bundle.Entry)
{
    // print ids:
    var device = be.Resource as Device;
    Console.WriteLine($"Device found: id={device.IdElement.Value} AuthorizationARHGOS={device.Extension.FindLast(e => e.Url.Equals("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS")).Value}");
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Device found: id=002-3405564 AuthorizationARHGOS=76-91-1096
Device found: id=002-3405565 AuthorizationARHGOS=44-21-35510
Device found: id=002-3405566 AuthorizationARHGOS=44-21-50847
```

<br>



### - Rechercher par date de mise à jour
Pour rechercher les matériels ayant été mis à jour depuis une date donnée.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Device?_lastUpdated=ge2022-08-07T14%3A51%3A04"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the date search parameter :
var dateParam = new DateClientParam("_lastUpdated");

var bundle = client.search()
.forResource(Device.class)
.where(dateParam.afterOrEquals().second("2022-08-07T14:51:04"))
.returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data:
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} AuthorizationARHGOS={}", device.getIdElement().getIdPart(), device.getExtensionByUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS").getValue());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Device?_lastUpdated=ge2022-08-07T14%3A51%3A04');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    $extArhgos = getExtensionByUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS", $device->getExtension());
    echo("Device found: id=".$device->getId()." AuthorizationARHGOS=".$extArhgos->getValueString()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}

// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("_lastUpdated=ge2022-08-07T14:51:04")
  .LimitTo(50);
var bundle = client.Search<Device>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var device = be.Resource as Device;
    Console.WriteLine($"Device found: id={device.IdElement.Value} AuthorizationARHGOS={device.Extension.FindLast(e => e.Url.Equals("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS")).Value}");
}

{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Device found: id=002-3401015 AuthorizationARHGOS=32-31-1156
Device found: id=002-3122325 AuthorizationARHGOS=93-93-4364
Device found: id=002-3122046 AuthorizationARHGOS=93-93-67204
```

<br>


### - Rechercher un matériel par son numéro ARHGOS
Pour ce faire, il faut interroger l'endpoint Device avec le paramètre  "number-authorization-arhgos".


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Device?number-authorization-arhgos=93-93-67204"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the type search parameter :
var arhgosParam = new StringClientParam("number-authorization-arhgos");

var bundle = client.search()
        .forResource(Device.class)
        .where(arhgosParam.contains().value("93-93-67204"))
        .returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Organization ids:
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} type={}", device.getIdElement().getIdPart(), device.getType().getCodingFirstRep().getCode());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Device?number-authorization-arhgos=93-93-67204');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()." type=".$device->getType()->getCoding()[0]->getCode()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}

// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("number-authorization-arhgos=93-93-67204")
  .LimitTo(50);
var bundle = client.Search<Device>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var device = be.Resource as Device;
    Console.WriteLine($"Device found: id={device.IdElement.Value} AuthorizationARHGOS={device.Extension.FindLast(e => e.Url.Equals("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS")).Value}");
}

{% endhighlight %}
</div>

</div>



L'API devrait vous retourner une réponse de ce genre :

```bash
Device found: id=002-3122046 type=05602
```

<br>



### - Rechercher les matériels de type "Scanographe à utilisation médicale"
Il est possible de rechercher un équipement lourd avec son type en utilisant le paramètre fhir "type". Voici un exemple avec un "Scanographe à utilisation médicale". 

Vous pouvez trouver l'enssemble des valeurs possibles dans le [référenciel du MOS](https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd)

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Device?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R272-EquipementMaterielLourd%2FFHIR%2FTRE-R272-EquipementMaterielLourd%7C05602"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the type search parameter :
var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
"https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd", "05602");

var bundle = client.search()
    .forResource(Device.class)
    .where(typeSearchClause)
    .returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data:
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} | type={}", device.getIdElement().getIdPart(), device.getType().getCodingFirstRep().getCode());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Device?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R272-EquipementMaterielLourd%2FFHIR%2FTRE-R272-EquipementMaterielLourd%7C05602');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()." type=".$device->getType()->getCoding()[0]->getCode()."\n");
}

{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("type=https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd|05602")
  .LimitTo(50);
var bundle = client.Search<Device>(q);
foreach (var be in bundle.Entry)
{
    // print Device data
    var device = be.Resource as Device;
    Console.WriteLine($"Device found: id={device.IdElement.Value}  | type={device.Type.Coding[0].Code}");
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Device found: id=002-3405555 | type=05602
Device found: id=002-3405559 | type=05602
Device found: id=002-3405562 | type=05602
```

<br>


### - Rechercher un matériel par son identifiant
Pour ce faire, il faut interroger l'endpoint FHIR Device.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Device?identifier=32-31-1156%2C93-93-4364"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the identifier search parameters :
var identifierParams = Device.IDENTIFIER.exactly().codes("32-31-1156", "93-93-4364");

var bundle = client.search()
    .forResource(Device.class)
    .where(identifierParams)
    .returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={}", device.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Device?identifier=32-31-1156%2C93-93-4364');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("identifier=32-31-1156,93-93-4364")
  .LimitTo(50);
var bundle = client.Search<Device>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var device = be.Resource as Device;
    Console.WriteLine($"Device found: id={device.IdElement.Value}");
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Device found: id=32-31-1156
Device found: id=93-93-4364
```

<br>


### - Rechercher les matériels actifs
Pour ce faire, il faut faire interroger l'endpoint FHIR Device avec le paramètre "status".

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Device?status=active"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the active search parameters :
var activeParams = Device.STATUS.exactly().code(Device.FHIRDeviceStatus.ACTIVE.toCode());

var bundle = client.search()
        .forResource(Device.class)
        .where(activeParams)
        .returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} | status={}", device.getIdElement().getIdPart(), device.getStatus().getDisplay());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/Device?status=active');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()." | status=".$device->getStatus()->getValue()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
   .Where("status=active")
  .LimitTo(50);
var bundle = client.Search<Device>(q);
foreach (var be in bundle.Entry)
{
    // print Device data
    var device = be.Resource as Device;
    Console.WriteLine($"Device found: id={device.IdElement.Value}  | status={device.Status.Value}");
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Device found: id=device-147 | status=Active
Device found: id=device-389 | status=Active
Device found: id=device-146 | status=Active
```

&nbsp;



{% include_relative _source-ref.md %}




