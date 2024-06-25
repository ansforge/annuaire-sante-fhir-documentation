---
layout: menu-version-1
title: Device
subTitle: Ressources
---


<div class="wysiwyg" markdown="1">
- [Description métier](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche sur critères](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par date de mise à jour](#42-header)
  - [Rechercher par numéro ARHGOS](#43-header)
  - [Recherche par type](#44-header)
  - [Recherche par identifiant](#45-header)
  - [Recherche par statut](#46-header)
</div>
<br />


## <a id="one-header"></a>1) Description métier de la ressource

Il s'agit d'une ressource qui regroupe  les données complémentaires FINESS portant sur les « [équipements matériels lourds](https://mos.esante.gouv.fr/5.html#_1a21e9b8-d686-41ff-806d-38572f961ec6) :
<div class="wysiwyg" markdown="1">
* numéro d'autorisation ARGHOS, période de validité, marque, numéro de série, code EML 
</div>
<br />

## <a id="two-header"></a>2) Caractéristiques techniques de la ressource

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
<p><strong>Paramètres de requête</strong></p>
</td>
<td width="54%">
<p>_count, _include</p>
</td>
</tr>
</tbody>
</table>
<br />

## <a id="three-header"></a>3) Paramètres de recherche

| Nom                               | Type      | Description                                                                       |
| ---                               | ---       | ---                                                                               |
| _id                               | token     | Recherche sur l'ID de la ressource HealthCare Service                             |
| _lastUpdated                      | date      | Renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée |
| _since                            | date      |                                                                                   |
| _total                            | string    |                                                                                   |
| as-sp-data-information-system     | token     | Recherche sur le système d'information                                            |
| as-sp-data-registration-authority | token     | Recherche sur l'autorité d'enregistrement                                         |
| device-name                       | string    | Le nom de l'équipement                                                            |
| identifier                        | token     | Recherche sur l'identifiant de l'équipement matériel lourd                        |
| manufacturer                      | string    | Recherche sur la marque des équipements matériels lourds                          |
| model                             | string    | Recherche sur le modèle des équipements matériels lourds                          |
| number-authorization-arhgos       | string    | Recherche sur le numéro d'autorisation ARHGOS                                     |
| organization                      | reference | Recherche les équipements matériels lourds rattachés à la structure sélectionnée  |
| status                            | token     | active, inactive,  entered-in-error, unknown                                      |
| type                              | token     | Recherche sur le type de l'équipement matériel lourd                              |

## <a id="four-header"></a>4) Recherche d'équipement matériel lourd (EML) sur critères

Voici des exemples de requêtes sur les équipements matériels lourds.

## <a id="41-header"></a>4.1) Rechercher tout (sans critère)

**Récit utilisateur :** En tant que client de l'API, je souhaite récupérer l'ensemble des EML.

**Exemples de requêtes :**

```sh
GET [base]/Device
GET [base]/Device?_include=Device:organization #inclure les Organization qui sont référencées par les Device (Device + Organization)
GET [base]/Device?_include=* #inclure toutes les ressources qui sont référencées par les Device 


```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Device found: id=002-3405564 AuthorizationARHGOS=76-91-1096
  Device found: id=002-3405565 AuthorizationARHGOS=44-21-35510
  Device found: id=002-3405566 AuthorizationARHGOS=44-21-50847


```

<br />

**Exemples de code :**


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

<br />


#### <a id="42-header"></a>4.2) Rechercher par date de mise à jour (_lastUpdated)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher toutes les EML mise à jour depuis une certaine date.

**Exemples de requêtes :**

```sh
GET [base]/Device?_lastUpdated=ge2022-08-07 #Les Device ayant été mis à jour depuis le 07/08/2022 inclus


```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Device found: id=002-3401015 AuthorizationARHGOS=32-31-1156
  Device found: id=002-3122325 AuthorizationARHGOS=93-93-4364
  Device found: id=002-3122046 AuthorizationARHGOS=93-93-67204


```

<br />

**Exemples de code :**

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

<br />


#### <a id="43-header"></a>4.3) Rechercher un matériel par son numéro ARHGOS (number-authorization-arhgos)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher un EML à partir de son numéro ARHGOS.

**Exemples de requêtes :**

```sh
GET [base]/Device?number-authorization-arhgos=93-93-67204 #Les device ayant le numéro ARHGOS = 93-93-67204


```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
    Device found: id=002-3122046 type=05602


```
<br />

**Exemples de code :**

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

<br />


#### <a id="44-header"></a>4.4) Rechercher par type EML (type)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher tous les EML de type "Scanographe à utilisation médicale".

**Remarque :**

Les valeurs possibles du type EML sont disponibles dans le  référentiel [TRE_R272-EquipementMaterielLourd](https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd) des NOS.

**Exemples de requêtes :**

```sh
GET [base]/Device?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R272-EquipementMaterielLourd%2FFHIR%2FTRE-R272-EquipementMaterielLourd%7C05602 #Critère de recherche sur le type EML


```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Device found: id=002-3405555 | type=05602
  Device found: id=002-3405559 | type=05602
  Device found: id=002-3405562 | type=05602

  
```

<br />

**Exemples de code :**

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

<br />


#### <a id="45-header"></a>4.5) Rechercher par son identifiant (identifier)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher un EML à partir de son identifiant.

**Exemples de requêtes :**

```sh
GET [base]/Device?v1/identifier=32-31-1156%2C93-93-4364 #Critère de recherche sur l'identifiant


```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
    Device found: id=32-31-1156

  
```

<br />
  
**Exemples de code :**

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
<br />

#### <a id="46-header"></a>4.6) Rechercher par statut (status)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher les EML actifs.

**Exemples de requêtes :**

```sh
GET [base]/Device?status=active #actif
GET [base]/Device?status=inactive #inactif


```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
    Device found: id=device-147 | status=Active
    Device found: id=device-389 | status=Active
    Device found: id=device-146 | status=Active


```
<br />

**Exemples de code :**

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
<br />



&nbsp;



{% include_relative _source-ref.md %}




