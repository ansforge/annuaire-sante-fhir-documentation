---
layout: documentation
title: Practitioner Role
subTitle: Ressources
---


Voici des exemples de requêtes sur les rôles des praticiens qui sont représentés dans le serveur FHIR par la ressource PractitionerRole


## Rechercher tout

Pour rechercher des rôles de praticiens, il faut faire une recherche sur le endpoint FHIR PractitionerRole

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/PractitionerRole
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var bundle = client.search().forResource(PractitionerRole.class).returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
// print PractitionerRole data:
var role = (PractitionerRole) roleEntry.getResource();
logger.info("Practitioner Role found: id={} code={}", role.getIdentifierFirstRep().getValue(), role.getCodeFirstRep().getCodingFirstRep().getDisplay());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Practitioner Role found: id=prr-prarole-293 code=Audioprothésiste
Practitioner Role found: id=prr-prarole-495 code=Assistant dentaire
Practitioner Role found: id=prr-prarole-860 code=Pharmacien
```

<br>

## Rechercher ayant un status actif

Pour rechercher des rôles de praticiens, il faut faire une recherche sur le endpoint FHIR PractitionerRole

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/PractitionerRole?active=true
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var activeSearchClause = PractitionerRole.ACTIVE.exactly().code("true");

var bundle = client.search()
.forResource(PractitionerRole.class)
.where(activeSearchClause)
.returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
// print PractitionerRole data:
var role = (PractitionerRole) roleEntry.getResource();
logger.info("Practitioner Role found: id={} active={}", role.getIdentifierFirstRep().getValue(), role.getActive());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Practitioner Role found: id=prr-prarole-946 active=true
Practitioner Role found: id=prr-prarole-256 active=true
Practitioner Role found: id=prr-prarole-899 active=true
```

<br>

## Rechercher le rôle par code

Pour rechercher des rôles de praticiens, il faut faire une recherche sur le endpoint FHIR PractitionerRole

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/PractitionerRole?role=10
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var roleSearchClause = PractitionerRole.ROLE.exactly().code("10");

var bundle = client.search()
.forResource(PractitionerRole.class)
.where(roleSearchClause)
.returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
// print PractitionerRole data:
var role = (PractitionerRole) roleEntry.getResource();
logger.info("Practitioner Role found: id={} role={}", role.getIdentifierFirstRep().getValue(), role.getCodeFirstRep().getCodingFirstRep().getDisplay());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Practitioner Role found: id=prr-prarole-981 role=Médecin
```

<br>

## Rechercher un rôle par code de spécialité

Pour rechercher des rôles de praticiens, il faut faire une recherche sur le endpoint FHIR PractitionerRole

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/PractitionerRole?specialty=C
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var specialitySearchClause = PractitionerRole.SPECIALTY.exactly().code("C");

var bundle = client.search()
.forResource(PractitionerRole.class)
.where(specialitySearchClause)
.returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
// print PractitionerRole data:
var role = (PractitionerRole) roleEntry.getResource();
logger.info("Practitioner Role found: id={} speciality={}", role.getIdentifierFirstRep().getValue(), role.getSpecialtyFirstRep().getCodingFirstRep().getCode());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Practitioner Role found: id=prr-prarole-293 speciality=C
Practitioner Role found: id=prr-prarole-743 speciality=C
```

<br>
