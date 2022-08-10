---
layout: documentation
title: Healthcare Service
subTitle: Ressources
---


Voici des exemples de requêtes sur les services de santé qui sont représentées dans le serveur FHIR par la ressource HealthCareService.


## Rechercher tout

Pour rechercher des services de santé, il faut faire une recherche sur le endpoint FHIR HealthcareService

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/HealthcareService
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

Pour rechercher des services de santé, il faut faire une recherche sur le endpoint FHIR HealthcareService.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/HealthcareService?identifier=http%3A%2F%2Fsample%2Fpr%2Fids%7Chcs-hcs-413
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

## Rechercher le status actif

Pour rechercher des services de santé, il faut faire une recherche sur le endpoint FHIR HealthcareService.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/HealthcareService?active=true
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


