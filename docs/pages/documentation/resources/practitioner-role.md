---
layout: documentation
title: Practitioner Role
subTitle: Ressources
---


Voici des exemples de requêtes sur les rôles des praticiens qui sont représentés dans le serveur FHIR par la ressource [PractitionerRole.](https://hl7.org/FHIR/practitionerrole.html)


## Rechercher tout

Appel de la ressource PractitionerRole pour restituer les données correspondant aux situation d'exercice et exercices professionnels des PS.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole"
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
logger.info("Practitioner Role found: id={} code={}", role.getIdElement().getIdPart(), role.getCodeFirstRep().getCodingFirstRep().getCode());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=005-5090000-6920000 code=FON-09
Practitioner Role found: id=005-5070000-6900000 code=FON-09
Practitioner Role found: id=005-5080000-6920000 code=FON-AU
```

<br>

## Recherche unitaire

La recherche unitaire permet de récupérer les données spécifiques d'une ressource à l'aide de son identifiant logique.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole/005-5087586-6923328"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var practitionerRole = client.read()
.resource(PractitionerRole.class)
.withId("005-5087586-6923328")
.execute();

logger.info("Practitioner Role found: id={}", practitionerRole.getIdElement().getIdPart());
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=005-5087586-6923328
```

<br>

## Recherche par rôles

Exemple de recherche pour le "chirurgien dentiste" (code 40) Etudiant (code E).

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole?role=40&role=E"
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
code.getCoding().stream().map(coding -> coding.getSystem() + ":" + coding.getCode()).collect(Collectors.joining("|"))
).collect(Collectors.joining(" - "));

    logger.info("Practitioner Role found: id={} codes={}", role.getIdElement().getIdPart(), roleCodes);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=005-480000-6510001 codes=https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction:FON-47|https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:E|https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite:GENR02|https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice:L
Practitioner Role found: id=005-490000-6510000 codes=https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction:FON-47|https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:E|https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite:GENR02|https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice:L
```

<br>

## Recherche par spécialité

Exemple de recherche pour les "chirurgiens dentiste" (code 40) qui ont une spécialité ordinal "orthopédie dento-faciale" (code SCD01).

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole?role=40&specialty=SCD01"
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
                    code.getCoding().stream().map(coding -> coding.getSystem().concat(":").concat(coding.getCode())).collect(Collectors.joining("|"))
            ).collect(Collectors.joining(" - "));
    
    // concat speciality
    roleCodes = roleCodes.concat("|")
                    .concat(role.getSpecialtyFirstRep().getCodingFirstRep().getSystem())
                    .concat(":")
                    .concat(role.getSpecialtyFirstRep().getCodingFirstRep().getCode());
    
    logger.info("Practitioner Role found: id={} codes={}", role.getIdElement().getIdPart(), roleCodes);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=005-400000 codes=https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:C|urn:oid:1.2.250.1.213.2.28:SCD01
Practitioner Role found: id=005-390000 codes=https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:C|urn:oid:1.2.250.1.213.2.28:SCD01
Practitioner Role found: id=005-380000 codes=https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:C|urn:oid:1.2.250.1.213.2.28:SCD01
```

<br>

## Recherche par type de SmartCard

Exemple de recherche de toutes les cartes de type CPS.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole?type-smartcard=CPS"
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
Practitioner Role found: id=005-54002-100000
Practitioner Role found: id=005-54001
Practitioner Role found: id=005-54000-100000
```

<br>

## Recherche par Practitioner

Exemple de recherche de toutes les situations d'exercice et exercice professionnel rattachées au professionel de santé 003-138020.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole?practitioner=003-138020"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var pratictionerSearchClause = PractitionerRole.PRACTITIONER.hasId("003-138020");

var bundle = client.search()
        .forResource(PractitionerRole.class)
        .where(pratictionerSearchClause)
        .returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
    // print PractitionerRole data:
    var role = (PractitionerRole) roleEntry.getResource();
    logger.info("Practitioner Role found: id={} practitioner={}", role.getIdElement().getIdPart(), role.getPractitioner().getReference());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Practitioner Role found: id=005-109896 practitioner=Practitioner/003-138020
```

<br>


## Recherche selon le statut

Exemple de recherche pour toutes les données au statut "actif".

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole?active=true"
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
logger.info("Practitioner Role found: id={} active={}", role.getIdElement().getIdPart(), role.getActive());
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
