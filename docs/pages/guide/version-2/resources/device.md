---
layout: menu-version-2
title: Device
subTitle: Ressources
---

<div class="wysiwyg" markdown="1">
- [Présentation de la ressource](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche sur critères](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par date de mise à jour](#42-header)
  - [Rechercher par identifiant](#43-header)
  - [Recherche par type](#44-header)
  - [Recherche par statut](#45-header)
</div>
<br />


## <a id="one-header"></a>1. Présentation de la ressource

Il s'agit d'une ressource qui regroupe  les données complémentaires FINESS portant sur les « [équipements matériels lourds](https://mos.esante.gouv.fr/5.html#_1a21e9b8-d686-41ff-806d-38572f961ec6) :
<div class="wysiwyg" markdown="1">
* numéro d'autorisation ARGHOS, 
* période d'autorisation
* marque, numéro de série, statut du matériel
</div>
<br />

## <a id="two-header"></a>2. Caractéristiques techniques de la ressource

<table width="25%">
<tbody>
<tr>
<td width="30%">
<p><strong>Endpoint</strong></p>
</td>

<td width="54%">
<p>{{site.ans.api_url}}/fhir/v2/Device</p>
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

## <a id="three-header"></a>3. Paramètres de recherche

| Nom                           | Type      | Description                                       |
| ---                           | ---       | ---                                               |
| _id                           | token     | Rechercher sur l'ID technique de la ressource     |
| _lastUpdated                  | date      | renvoie uniquement les ressources selon la date de mises à jour et le "modifier" utilisé (eq, ne, gt, lt, ge, le, ap). Plus d'informations sur les [dates] (https://www.hl7.org/fhir/R4/search.html#date)  |
| data-information-system       | token     | Recherche sur le système d'informations  |
| identifier                    | token     | Recherche sur le numéro ARHGOS de l'équipement matériel lourd |
| manufacturer                  | string    | Recherche sur la marque de l'équipement matériel lourd |
| model                         | string    | Recherche sur le modèle de l'équipement matériel lourd |
| organization                  | reference | Référence l'ID technique de l'organisation associée à l'équipement |
| status                        | token     | Recherche sur le statut de l'équipement : active, inactive,  entered-in-error, unknown|
| type                          | token     | Recherche sur le type d'équipement matériel lourd |

## <a id="four-header"></a>4. Recherche d'équipement matériel lourd (EML) sur critères

Voici des exemples de requêtes sur les équipements matériels lourds.

## <a id="41-header"></a>4.1 Rechercher tout (sans critère)

En tant que client de l'API, je souhaite récupérer l'ensemble des équipements matériels lourds (EML).

**Exemples de requêtes :**

```sh
GET [base]/Device
# récupère l'ensemble des équipements matériels lourds (incluant les actifs et les inactifs)

GET [base]/Device?_include=Device:organization 
# inclure les Organization qui sont référencées par les Devices (Device + Organization)

GET [base]/Device?_include=* 
# inclure toutes les ressources qui sont référencées par les Devices (idem que la précédente requête)
```

<br />


#### <a id="42-header"></a>4.2 Rechercher par date de mise à jour (_lastUpdated)

*En tant que client de l'API, je souhaite rechercher tous les équipements mis à jour depuis une date donnée.

**Exemples de requêtes :**

```sh
GET [base]/Device?_lastUpdated=ge2025-08-07 
# Récupère tous les Practitioners mis à jour à partir du 07 août 2025 (inclus) jusqu'à aujourd'hui
```
<br />

#### <a id="43-header"></a>4.3 Rechercher un matériel par rapport à un identifiant (_id, identifier)

En tant que client de l'API, je souhaite rechercher un Equipement Materiel Lourd (EML) à partir de son numéro ARHGOS.

**Exemples de requêtes :**

```sh
GET [base]/Device?_id=002-4247117
# Rechercher les équipements en fonction de l'ID technique de la ressource

GET [base]/Device?identifier=93-93-67204
# Rechercher les équipements en fonction de son numéro ARHGOS 93-93-67204
```
<br />


#### <a id="44-header"></a>4.4 Rechercher par type d'équipement matériel lourd (type)

En tant que client de l'API, je souhaite rechercher tous les EML de type "Scanographe à utilisation médicale".

**Remarque :**

Les valeurs possibles du type EML sont disponibles dans le  référentiel [TRE_R272-EquipementMaterielLourd](https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd) des NOS.

**Exemples de requêtes :**

```sh
GET [base]/Device?type=https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd%7C05602 
# Rechercher les équipements en fonction du type d'équipement. L'exemple pris est de rechercher les scanographes dont le code métier est 05602.
```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Device?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R272-EquipementMaterielLourd%2FFHIR%2FTRE-R272-EquipementMaterielLourd%7C05602"
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

  
**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Device?v2/identifier=32-31-1156%2C93-93-4364"
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

#### <a id="45-header"></a>4.5 Rechercher par statut (status)

En tant que client de l'API, je souhaite rechercher les équipements en fonction de leur statut.

**Exemples de requêtes :**

```sh
GET [base]/Device?status=active 
# Rechercher les équipement dont le statut est actif

GET [base]/Device?status=inactive
# Rechercher les équipement dont le statut est inactif

```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Device?status=active"
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




