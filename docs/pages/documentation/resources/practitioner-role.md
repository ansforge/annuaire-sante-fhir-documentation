---
layout: documentation
title: Practitioner Role
subTitle: Ressources
---


Voici des exemples de requêtes sur les rôles des praticiens qui sont représentés dans le serveur FHIR par la ressource PractitionerRole.


## Rechercher tout

Appel de la ressource PractitionerRole pour restituer les données correspondant aux situation d'exercice et exercices professionnels des PS.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/PractitionerRole
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

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=prr-prarole-293 code=Audioprothésiste
Practitioner Role found: id=prr-prarole-495 code=Assistant dentaire
Practitioner Role found: id=prr-prarole-860 code=Pharmacien
```

<br>

## Recherche unitaire

La recherche unitaire permet de récupérer les données spécifiques d'une ressource à l'aide de son identifiant logique.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/PractitionerRole/prarole-981
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var practitionerRole = client.read()
.resource(PractitionerRole.class)
.withId("prarole-981")
.execute();

logger.info("Practitioner Role found: id={}", practitionerRole.getIdentifierFirstRep().getValue());
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=prr-prarole-981
```

<br>

## Recherche par rôles

Exemple de recherche pour le "chirurgien dentiste" (code 40) Etudiant (code E).

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/PractitionerRole?role=40&role=E
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var firstCodeClause = PractitionerRole.ROLE.exactly().code("40");
var secondCodeClause = PractitionerRole.ROLE.exactly().code("E");

var bundle = client.search()
.forResource(PractitionerRole.class)
.where(firstCodeClause)
.and(secondCodeClause)
.returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
// print PractitionerRole data:
var role = (PractitionerRole) roleEntry.getResource();
var roleCodes = role.getCode().stream().map(code ->
code.getCoding().stream().map(coding -> coding.getCode() + ":" + coding.getDisplay()).collect(Collectors.joining("|"))
).collect(Collectors.joining(" - "));

    logger.info("Practitioner Role found: id={} codes={}", role.getIdentifierFirstRep().getValue(), roleCodes);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=prr-prarole-501 codes=40:Chirurgien dentiste|E:Etudiant
```

<br>

## Recherche par spécialité

Exemple de recherche pour les "chirurgiens dentiste" (code 40) qui ont une spécialité ordinal "orthopédie dento-faciale" (code SCD01).

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/PractitionerRole?role=40&specialty=SCD01
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var codeClause = PractitionerRole.ROLE.exactly().code("40");
var specialityClause = PractitionerRole.SPECIALTY.exactly().code("SCD01");

var bundle = client.search()
.forResource(PractitionerRole.class)
.where(codeClause)
.and(specialityClause)
.returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
    // print PractitionerRole data:
    var role = (PractitionerRole) roleEntry.getResource();
    
    // concat roles
    var roleCodes = role.getCode().stream().map(code ->
            code.getCoding().stream().map(coding -> coding.getCode().concat(":").concat(coding.getDisplay())).collect(Collectors.joining("|"))
    ).collect(Collectors.joining(" - "));
    
    // concat speciality
    roleCodes = roleCodes.concat("|")
            .concat(role.getSpecialtyFirstRep().getCodingFirstRep().getCode())
            .concat(":")
            .concat(role.getSpecialtyFirstRep().getCodingFirstRep().getDisplay());
    
    logger.info("Practitioner Role found: id={} codes={}", role.getIdentifierFirstRep().getValue(), roleCodes);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=prr-prarole-345 codes=40:Chirurgien dentiste|M:Militaire|SCD01:Orthopédie dento-faciale
Practitioner Role found: id=prr-prarole-327 codes=40:Chirurgien dentiste|E:Etudiant|SCD01:Orthopédie dento-faciale
Practitioner Role found: id=prr-prarole-111 codes=40:Chirurgien dentiste|C:Civil|SCD01:Orthopédie dento-faciale
```

<br>

## Recherche par type de SmartCard

Exemple de recherche de toutes les cartes de type CPS.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/PractitionerRole?type-smartcard=CPS
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the date search parameter :
var smartCardClause = new StringClientParam("type-smartcard").matches().value("CPS");

var bundle = client.search()
.forResource(PractitionerRole.class)
.where(smartCardClause)
.returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
// cast entry :
var role = (PractitionerRole) roleEntry.getResource();
// print update date & id :
logger.info("Organization found: id={}", role.getIdElement().getIdPart());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
??? TODO ???
```

<br>

## Recherche par Practitioner

Exemple de recherche de toutes les situations d'exercice et exercice professionnel rattachées au professionel de santé pra-982.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/PractitionerRole?type-smartcard=CPS
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var pratictionerSearchClause = PractitionerRole.PRACTITIONER.hasId("pra-982");

var bundle = client.search()
        .forResource(PractitionerRole.class)
        .where(pratictionerSearchClause)
        .returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
    // print PractitionerRole data:
    var role = (PractitionerRole) roleEntry.getResource();
    logger.info("Practitioner Role found: id={} practitioner={}", role.getIdentifierFirstRep().getValue(), role.getPractitioner().getReference());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=prr-prarole-982 practitioner=Practitioner/pra-982
```

<br>


## Recherche selon le statut

Exemple de recherche pour toutes les données au statut "actif".

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/PractitionerRole?active=true
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

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=prr-prarole-946 active=true
Practitioner Role found: id=prr-prarole-256 active=true
Practitioner Role found: id=prr-prarole-899 active=true
```
