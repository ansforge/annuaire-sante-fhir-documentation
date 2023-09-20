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

## Caractéristiques techniques de la ressource

<table width="25%">
<tbody>
<tr>
<td width="30%">
<p><strong>Endpoint</strong></p>
</td>

<td width="54%">
<p>{{site.ans.api_url}}/fhir/v1/Device</p>
</td>
</tr>
<tr>
<td width="30%">
<p><strong>Header</strong></p>
</td>
<td width="54%">
<p>ESANTE-API-KEY</p>
</td>
</tr>
<tr>
<td width="30%">
<p><strong>Méthodes HTTP associées</strong></p>
</td>

<td width="54%">
<p>GET, POST</p>
</td>
</tr>
<tr>
<td width="30%">
<p><strong>Paramètres de recherche</strong></p>
</td>
<td width="54%">
<p>_id, identifier, device-name, manufacturer, number-authorization-arhgos, status, type, _lastUpdated, organization, _total</p>
</td>
</tr>
<tr>
<td width="30%">
<p><strong>Paramètres de requête</strong></p>
</td>
<td width="54%">
<p>_count, _include</p>
</td>
</tr>
</tbody>
</table>
<br>

## Recherche d'équipement matériel lourd (EML) sur critères
Voici des exemples de requêtes sur les équipements matériels lourds.

### 1) Rechercher tout (sans critère)

-   **Récit utilisateur** : En tant que client de l'API, je souhaite récupérer l'ensemble des EML.

-   **Exemples de requêtes** :

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Device"
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

<br>

-   **Exemple de réponse (simplifiée)** :

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Device found: id=002-3405564 AuthorizationARHGOS=76-91-1096
  Device found: id=002-3405565 AuthorizationARHGOS=44-21-35510
  Device found: id=002-3405566 AuthorizationARHGOS=44-21-50847
```

<br>



### 2) Rechercher par date de mise à jour (_lastUpdated)

-   **Récit utilisateur** : En tant que client de l'API, je souhaite rechercher toutes les EML mise à jour depuis une certaine date.

-   **Exemples de requêtes** :

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Device?_lastUpdated=ge2022-08-07T14%3A51%3A04"
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

<br>

-   **Exemple de réponse (simplifiée)** :

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Device found: id=002-3401015 AuthorizationARHGOS=32-31-1156
  Device found: id=002-3122325 AuthorizationARHGOS=93-93-4364
  Device found: id=002-3122046 AuthorizationARHGOS=93-93-67204
```

<br>


### 3) Rechercher un matériel par son numéro ARHGOS (number-authorization-arhgos)

-   **Récit utilisateur** : En tant que client de l'API, je souhaite rechercher un EML à partir de son numéro ARHGOS.

-   **Exemples de requêtes** :
<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Device?number-authorization-arhgos=93-93-67204"
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

<br>

-   **Exemple de réponse (simplifiée)** :
  
```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
  Device found: id=002-3122046 type=05602
```

<br>



### 4) Rechercher par type EML (type)

-   **Récit utilisateur** : En tant que client de l'API, je souhaite rechercher tous les EML de type "Scanographe à utilisation médicale".

**Remarque** :
Les valeurs possibles du type EML sont disponibles dans le  référentiel [TRE_R272-EquipementMaterielLourd](https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd) des NOS.

-   **Exemples de requêtes** :
<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Device?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R272-EquipementMaterielLourd%2FFHIR%2FTRE-R272-EquipementMaterielLourd%7C05602"
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

-   **Exemple de réponse (simplifiée)** :

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Device found: id=002-3405555 | type=05602
  Device found: id=002-3405559 | type=05602
  Device found: id=002-3405562 | type=05602
```

<br>


### 5) Rechercher par son identifiant (identifier)
-   **Récit utilisateur** : En tant que client de l'API, je souhaite rechercher un EML à partir de son identifiant.

-   **Exemples de requêtes** :

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Device?v1/identifier=32-31-1156%2C93-93-4364"
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

<br>

-   **Exemple de réponse (simplifiée)** :
  
```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
  Device found: id=32-31-1156
```

<br>


### 6) Rechercher par status (status)

-   **Récit utilisateur** : En tant que client de l'API, je souhaite rechercher les EML actifs.

-   **Exemples de requêtes** :
<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Device?status=active"
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

-   **Exemple de réponse (simplifiée)** :

```bash
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Device found: id=device-147 | status=Active
  Device found: id=device-389 | status=Active
  Device found: id=device-146 | status=Active
```

&nbsp;



{% include_relative _source-ref.md %}




