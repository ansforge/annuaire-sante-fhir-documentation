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
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" https://ans.com/fhir/Practitioner
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

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=pra-978 name=Prof Noémie Bonnet Gabriel
Practitioner found: id=pra-737 name=Mme Carla Garnier Mathéo
Practitioner found: id=pra-979 name=Mme Alexandre Prof Lucie Moreau
Practitioner found: id=pra-734 name=Dr Alexis Charpentier Sacha
```

<br>


## Rechercher selon un ou plusieurs identifiants

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" https://ans.com/fhir/Practitioner?identifier=p-pra-738%2Cp-pra-978
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var identifierParams = Practitioner.IDENTIFIER.exactly().codes("p-pra-738", "p-pra-978");

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

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=p-pra-738 name=Mlle Baptiste Philippe Mael
Practitioner found: id=p-pra-978 name=Prof Noémie Bonnet Gabriel
```

<br>

## Rechercher selon le préfixe du nom

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" https://ans.com/fhir/Practitioner?name=Dr
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var nameSearchClause = Practitioner.NAME.matches().value("Dr");

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

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=p-pra-734 name=Dr Alexis Charpentier Sacha
Practitioner found: id=p-pra-732 name=Dr Nathan Prof Jade Marchal
Practitioner found: id=p-pra-974 name=Dr Elisa Mattéo Dupuy
Practitioner found: id=p-pra-975 name=Dr Romane Mathis Menard
Practitioner found: id=p-pra-971 name=Dr Charlotte Prof Lina Marty
```

<br>

## Rechercher les praticiens actifs

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" https://ans.com/fhir/Practitioner?active=true
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

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: name=Mlle Baptiste Philippe Mael | active=true
Practitioner found: name=Prof Pierre Prof Alexandre Le gall | active=true
Practitioner found: name=Prof Sarah Lefebvre Romain | active=true
Practitioner found: name=Prof Noémie Bonnet Gabriel | active=true
```

<br>

## Rechercher par date de modification

Pour rechercher des praticiens, il faut faire une recherche sur le endpoint FHIR Practitioner.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" https://ans.com/fhir/Practitioner?_lastUpdated=ge2022-08-08T06%3A47%3A02
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

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner found: id=pra-738 | lastUpdate=Mon Aug 08 15:55:53 CEST 2022
Practitioner found: id=pra-739 | lastUpdate=Mon Aug 08 15:55:53 CEST 2022
Practitioner found: id=pra-736 | lastUpdate=Mon Aug 08 15:55:53 CEST 2022
Practitioner found: id=pra-978 | lastUpdate=Mon Aug 08 15:55:53 CEST 2022
```
