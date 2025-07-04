---
layout: menu-version-2
title: Healthcare Service
subTitle: Ressources
---

<div class="wysiwyg" markdown="1">
- [Présentation de la ressource](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche sur critères](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par identifiant](#42-header)
  - [Rechercher par type/forme d'activité](#43-header)
  - [Recherche par statut](#44-header)
  - [Recherche par date de mise à jour](#45-header)
</div>
<br />

## <a id="one-header"></a>1. Présentation de la ressource

Il s'agit d'une ressource divisée en deux profils pour décrire les « [activités de soins](https://mos.esante.gouv.fr/5.html#_2f0d6658-e0f7-4486-a646-424b09f01f76) » et les « [équipements sociaux](https://mos.esante.gouv.fr/5.html#_def51d8f-2eb8-47f8-9c30-b03709096666) » :

<div class="wysiwyg" markdown="1">
* HealthcareService-SocialEquipment pour les équipements sociaux : identifiant ARHGOS, date d'autorisation, date de première installation, active, référence à l'organisation, type d'équipement social, clientèle
* HealthcareService-HealthCareActivity pour les activités de soin : identifiant ARHGOS, date d'autorisation, période d'autorisation, active, modalité, forme, type
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
<p>{{site.ans.api_url}}/fhir/v2/HealthcareService</p>
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

| Nom                               | Type      | Description                                               |
| ---                               | ---       | ---                                                       |
| _has                              | string    |                                                           |
| _id                               | token     | Recherche sur l'ID de la ressource HealthCare Service     |
| _lastUpdated                      | date      | Renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée (eq, ne, gt, lt, ge, le, ap).|
| _profile| uri | Sélectionner le profil de la ressource Healthcare Service. Pour les activités de soins http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-healthcareservice-healthcare-activity / ; Pour les équipements sociaux http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-healthcareservice-social-equipment"  |
| _since                            | date      |                                                           |
| _total                            | string    |                                                           |
| active                            | token     | Recherche les ressources Healthcare Service actives       |
| as-sp-data-information-system     | token     | Recherche sur le système d'information                    |
| as-sp-data-registration-authority | token     | Recherche sur l'autorité d'enregistrement                 |
| characteristic                    | token     | Recherche sur le type d'activité des équipements sociaux ou sur la forme d'activité des activités de soins   |
| identifier                        | token     | Recherche sur l'identifiant des équipements sociaux ou des activités de soins        |
| organization                      | reference | Recherche tous les équipements sociaux ou activités de soins rattachés à une structure|
| service-category                  | token     | Recherche sur la modalité des activités de soins |
| service-type                      | token     | Recherche sur la discipline des équipements sociaux ou sur l'activité sanitaire régulée des activité de soins |


## <a id="four-header"></a>4. Recherche d'activité de soin et d'équipement social sur critères
Voici des exemples de requêtes sur les activités de soin et les équipements sociaux.


## <a id="41-header"></a>4.1 Rechercher tout

En tant que client de l'API, je souhaite récupérer l'ensemble des activités de soins et les équipements sociaux. On utilise ici le _profile pour récupérer exclusivement les activités de soins ou les équipements sociaux.

**Exemples de requêtes :**

```sh
GET [base]/HealthcareService
#récupère l'ensemble des HealthcareServices - activités de soins et équipements sociaux (actives et inactives)

GET [base]/HealthcareService?_profile=https%3A%2F%2Finterop.esante.gouv.fr%2Fig%2Ffhir%2Fannuaire%2FStructureDefinition%2Fas-dp-healthcareservice-social-equipment
# récupère l'ensemble des équipements sociaux (actives et inactives)

GET [base]/HealthcareService?_profile=https%3A%2F%2Finterop.esante.gouv.fr%2Fig%2Ffhir%2Fannuaire%2FStructureDefinition%2Fas-dp-healthcareservice-healthcare-activity
# récupère l'ensemble des activités de sooins (actives et inactives)


GET [base]/HealthcareService?_include=HealthcareService:organization 
# inclure les Organization qui sont référencées par les HealthcareService (HealthcareService + Organization)

GET [base]/HealthcareService?_include=* 
# inclure toutes les ressources qui sont référencées par les HealthcareService 


```
<br />


**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/HealthcareService"
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

<br />


#### <a id="42-header"></a>4.2 Rechercher par identifiant (_id ou identifier)

En tant que client de l'API, je souhaite rechercher un service à partir de son identifiant.

**Requête :**

```sh
`GET [base]/HealthcareService?identifier=52-52-49883`
# récupère le HealthcareService en fonction de son identifiant métier

`GET [base]/HealthcareService?_id=004-1014485-03
# récupère le HealthcareService en fonction de son identifiant technique

```

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/HealthcareService?identifier=52-52-49883"
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

<br />


#### <a id="43-header"></a>4.3 Rechercher par type/forme d'activité (characteristic)

En tant que client de l'API, je souhaite rechercher toutes les activités de soin ayant comme forme la Chirurgie ambulatoire (code 07).

**Remarque** : Les codes d'activité sont disponibles dans les référentiels suivants des NOS :
<div class="wysiwyg" markdown="1">
- [TRE-R276-FormeActivite](https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite)
- [TRE-R209-TypeActivite](https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite)
</div>
<br />

**Exemples de requêtes :**

```sh
GET [base]/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R276-FormeActivite%2FFHIR%2FTRE-R276-FormeActivite%7C07 
# Rechercher les HealthcareService selon la forme d'activité chirurgie ambulatoire (code: 07)
GET [base]/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R209-TypeActivite%2FFHIR%2FTRE-R209-TypeActivite%7C11 
# Rechercher les HealthcareService selon le type d'hébergement complet ou internat (code: 11)
```
<br />


**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R276-FormeActivite%2FFHIR%2FTRE-R276-FormeActivite%7C07"
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R209-TypeActivite%2FFHIR%2FTRE-R209-TypeActivite%7C11"
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

<br />


#### <a id="44-header"></a>4.4 Rechercher par statut (active)

En tant que client de l'API, je souhaite rechercher les activités de soins ou les équipements sociaux selon le statut Active

**Exemples de requêtes :**

```sh
GET [base]/HealthcareService?active=true 
# Recherche les HealthcareService qui sont actifs

GET [base]/HealthcareService?active=false 
# Recherche les HealthcareService qui sont inactifs

```
<br />


**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/HealthcareService?active=true"
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
<br />


#### <a id="45-header"></a>4.5 Rechercher par date de mise à jour (_lastUpdated)

En tant que client de l'API, je souhaite rechercher tous les services mis à jour depuis une certaine date ( >= '18/08/2022' dans l'exemple ).

**Exemples de requêtes :**

```sh
GET [base]/HealthcareService?_lastUpdated=ge2022-08-18 
# Rechercher les HealthcareService qui ont été mis à jour depuis le 18/08/2022 inclus
```
<br />


**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/HealthcareService?_lastUpdated=ge2022-08-18"
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

<br />


{% include_relative _source-ref.md %}

