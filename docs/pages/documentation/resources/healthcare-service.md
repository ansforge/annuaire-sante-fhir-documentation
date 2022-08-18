---
layout: documentation
title: Healthcare Service
subTitle: Ressources
---


Voici des exemples de requêtes sur les services de santé qui sont représentés dans le serveur FHIR par la ressource HealthCareService.


## Rechercher tout

Pour rechercher des services de santé, il faut faire une recherche sur le endpoint FHIR HealthcareService

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/HealthcareService
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
logger.info("Healthcare Service found: id={}", healthcareService.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=hcs-hcs-413
Healthcare Service found: id=hcs-hcs-655
Healthcare Service found: id=hcs-hcs-897
```

<br>

## Rechercher par identifiant

Pour rechercher un service de santé par son identifiant logique.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/HealthcareService?identifier=http%3A%2F%2Fsample%2Fpr%2Fids%7Chcs-hcs-413
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var typeSearchClause = HealthcareService.IDENTIFIER.exactly().systemAndValues("http://sample/pr/ids", "hcs-hcs-413");

var bundle = client.search()
.forResource(HealthcareService.class)
.where(typeSearchClause)
.returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
// print HealthcareService data:
var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
logger.info("Healthcare Service found: id={} | system={}", healthcareService.getIdentifierFirstRep().getValue(), healthcareService.getIdentifierFirstRep().getSystem());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=hcs-hcs-413 | system=http://sample/pr/ids
```

<br>

## Rechercher par activité de soins

Pour rechercher toutes les activités de soins qui ont comme forme d’activité la Chirurgie ambulatoire (code 07).

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/HealthcareService?characteristic=https%3A%2F%2Fapifhir.annuaire.sante.fr%2Fwssync%2Fexposed%2Fstructuredefinition%2FHealthcareService-HealthCareActivity-rass%7C07
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var characteristicSearchClause = HealthcareService.CHARACTERISTIC.exactly().systemAndValues(
    "https://apifhir.annuaire.sante.fr/wssync/exposed/structuredefinition/HealthcareService-HealthCareActivity-rass",
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
    String characteristicData = healthcareServiceCoding.getCode().concat("|").concat(healthcareServiceCoding.getSystem()).concat("|").concat(healthcareServiceCoding.getDisplay());
    logger.info("Healthcare Service found: id={} | characteristic={}", healthcareService.getIdentifierFirstRep().getValue(), characteristicData);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=hcs-hcs-655 | characteristic=07|https://apifhir.annuaire.sante.fr/wssync/exposed/structuredefinition/HealthcareService-HealthCareActivity-rass|Chirurgie ambulatoire
Healthcare Service found: id=hcs-hcs-412 | characteristic=07|https://apifhir.annuaire.sante.fr/wssync/exposed/structuredefinition/HealthcareService-HealthCareActivity-rass|Chirurgie ambulatoire
```

<br>

## Rechercher par équipements sociaux

Pour rechercher tous les équipements Sociaux qui ont comme type d’activité « Hébergement complet internat» (code 11).

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/HealthcareService?characteristic=https%3A%2F%2Fapifhir.annuaire.sante.fr%2Fws-sync%2Fexposed%2Fstructuredefinition%2FHealthcareService-SocialEquipment-rass%7C11
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
    logger.info("Healthcare Service found: id={} | characteristic={}", healthcareService.getIdentifierFirstRep().getValue(), characteristicData);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Healthcare Service found: id=hcs-hcs-413 | characteristic=11|https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/HealthcareService-SocialEquipment-rass|Hébergement complet internat
Healthcare Service found: id=hcs-hcs-897 | characteristic=11|https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/HealthcareService-SocialEquipment-rass|Hébergement complet internat
```

<br>


## Rechercher par status

Pour rechercher tous les services de santé qui sont actifs

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/HealthcareService?active=true
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
logger.info("Healthcare Service found: id={} | status={}", healthcareService.getIdentifierFirstRep().getValue(), healthcareService.getActive());
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


## Rechercher par date de mise à jour

Pour rechercher toutes les données complémentaires FINESS dont leurs données ont été mises à jour avant le 18/08/2022

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/HealthcareService?_lastUpdated=le2022-08-18
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
        .where(dateParam.beforeOrEquals().day("2022-08-18"))
        .returnBundle(Bundle.class).execute();

for (var healthcareServiceEntry : bundle.getEntry()) {
    // cast entry :
    var healthcareService = (HealthcareService) healthcareServiceEntry.getResource();
    // print update date & id :
    logger.info("HealthcarService found: id={} lastUpdate={}", healthcareService.getIdElement().getIdPart(), healthcareService.getMeta().getLastUpdated());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
HealthcarService found: id=hcs-413 lastUpdate=Thu Aug 11 15:10:24 CEST 2022
HealthcarService found: id=hcs-655 lastUpdate=Thu Aug 11 15:10:24 CEST 2022
HealthcarService found: id=hcs-897 lastUpdate=Thu Aug 11 15:10:24 CEST 2022
```
