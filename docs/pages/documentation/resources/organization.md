---
layout: documentation
title: Organization
subTitle: Ressources
---

Voici des exemples de requêtes sur les structures qui sont représentées dans le serveur FHIR par la ressource [Organization](https://hl7.org/FHIR/organization.html).

## Rechercher tout

Pour rechercher des structures, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();
var bundle = client.search().forResource(Organization.class).returnBundle(Bundle.class).execute();
for(var organizationEntry : bundle.getEntry()){
    // print Organization ids:
    var organization = (Organization) organizationEntry.getResource();
    logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-399 name=Weber, Weber and Weber
Organization found: id=org-158 name=Schaefer-Schaefer
Organization found: id=org-151 name=OReilly, OReilly and OReilly
Organization found: id=org-393 name=Volkman-Volkman
Organization found: id=org-152 name=Luettgen, Luettgen and Luettgen
Organization found: id=org-394 name=Gulgowski, Gulgowski and Gulgowski
Organization found: id=org-153 name=Wilkinson Group
```
  
<br>

## Rechercher selon une date de modification

Pour rechercher des structures selon la date de modification, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = FhirTestUtils.createClient();

// create the date search parameter :
var dateParam = new DateClientParam("_lastUpdated");

var bundle = client.search()
        .forResource(Organization.class)
        .where(dateParam.afterOrEquals().second("2022-08-05T14:51:04"))
        .returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print update date & id :
    logger.info("Organization found: id={} lastUpdate={}", organization.getIdElement().getIdPart(), organization.getMeta().getLastUpdated());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-148 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-149 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-144 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-386 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
Organization found: id=org-145 lastUpdate=Fri Aug 05 14:51:03 CEST 2022
```


<br>

## Rechercher selon un ou plusieurs identifiants

Pour rechercher des structures selon un identifiant, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization?identifier=org-org-148%2Corg-org-149%2Corg-85054-852 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the identifier search parameter :
var idParam = new StringClientParam("identifier");

var bundle = client.search()
.forResource(Organization.class)
.where(idParam.matches().values("org-org-148", "org-org-149", "org-85054-852"))
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print update date & id :
logger.info("Organization found: id={}", organization.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-org-148
Organization found: id=org-org-149
Organization found: id=org-org-144
```


<br>

## Rechercher selon un ou plusieurs numéro finess

Pour rechercher des structures selon un numéro finess, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization?identifier=http%3A%2F%2Ffiness.sante.gouv.fr%7C135-03-8573%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C697-57-5733%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C802-22-0946 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create finess where clause
var finessSearchClause = Organization.IDENTIFIER.exactly().systemAndValues(
"http://finess.sante.gouv.fr", "135-03-8573", "697-57-5733", "802-22-0946");

var bundle = client.search()
.forResource(Organization.class)
.where(finessSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print id :
logger.info("Organization found: id={}", organization.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: id=org-org-148
Organization found: id=org-org-149
Organization found: id=org-org-145
```


<br>

## Rechercher selon le type "GEOGRAPHICAL-ENTITY"

Pour rechercher des structures selon un numéro finess, il faut faire une recherche sur le endpoint FHIR Organization

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir//Orgnization?type=http%3A%2F%2Finteropsante.org%2Ffhir%2FCodeSystem%2Ffr-v2-3307%7CGEOGRAPHICAL-ENTITY 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the type search parameter :
var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
"http://interopsante.org/fhir/CodeSystem/fr-v2-3307", "GEOGRAPHICAL-ENTITY");

var bundle = client.search()
.forResource(Organization.class)
.where(typeSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
var organizationCodes = organization.getType().stream().map(type -> type.getCodingFirstRep().getCode()).collect(Collectors.joining(" - "));
logger.info("Organization found: id={} type={}", organization.getName(), organizationCodes);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: name=Haag Group type=SA08 - GEOGRAPHICAL-ENTITY - someorg
Organization found: name=Ward Inc type=SA08 - GEOGRAPHICAL-ENTITY - someorg
Organization found: name=Lubowitz-Lubowitz type=SA08 - GEOGRAPHICAL-ENTITY - some
Organization found: name=Schinner Group type=SA08 - GEOGRAPHICAL-ENTITY - someorg
```


<br>

## Rechercher par sous-classes de la Nomenclature d'Activités Française - INSEE

Pour rechercher des structures selon la sous-classe de la Nomenclature d'Activités Française, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5%7C82.19Z 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the search parameter :
var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
"https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5", "82.19Z");

var bundle = client.search()
.forResource(Organization.class)
.where(typeSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
var organizationCodes = organization.getType().stream().map(type -> type.getCodingFirstRep().getCode()).collect(Collectors.joining(" - "));
logger.info("Organization found: id={} type={}", organization.getName(), organizationCodes);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: name=Skiles, Skiles and Skiles type=SA29 - 82.19Z - LEGAL-ENTITY - someorg
Organization found: name=Terry, Terry and Terry type=SA29 - 82.19Z - LEGAL-ENTITY - someorg
Organization found: name=Mills Inc type=SA29 - 82.19Z - LEGAL-ENTITY - someorg
```


<br>

## Rechercher par secteur d'activité

Voici la liste des secteurs d'activités : <a href="https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite">https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite</a>

Pour rechercher des structures selon leur secteur d'activité, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA29 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the activity search parameter :
var activitySearchClause = Organization.TYPE.exactly().systemAndValues(
        "https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite", "SA29");

var bundle = client.search()
        .forResource(Organization.class)
        .where(activitySearchClause)
        .returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    var activityName = organization.getTypeFirstRep().getCodingFirstRep().getDisplay();
    logger.info("Organization found: id={} activity={}", organization.getName(), activityName);
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: name=Auer, Auer and Auer activity=Laboratoire d'analyses et de biologie médicale
Organization found: name=Erdman, Erdman and Erdman activity=Laboratoire d'analyses et de biologie médicale
Organization found: name=Stiedemann and Sons activity=Laboratoire d'analyses et de biologie médicale
```



<br>

## Rechercher par nom qui contient deux termes

Pour rechercher des structures par nom, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization?name%3Acontains=imagerie%2Ccentre 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var nameSearchClause = Organization.NAME.contains().values("imagerie", "centre");

var bundle = client.search()
.forResource(Organization.class)
.where(nameSearchClause)
.returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
logger.info("Organization found: name={}", organization.getName());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: name=Mills Inc centre
Organization found: name=Centre d'imagerie médicale - Selimed 63
Organization found: name=Imagerie médicale République
```



<br>

## Rechercher par code postal

Pour rechercher des structures par adresse, il faut faire une recherche sur le endpoint FHIR Organization.

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Organization?address-postalcode%3Aexact=91794%2C10228 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the postal code search parameter :
var nameSearchClause = Organization.ADDRESS_POSTALCODE.matchesExactly().values("91794", "10228");

var bundle = client.search()
        .forResource(Organization.class)
        .where(nameSearchClause)
        .returnBundle(Bundle.class).execute();

for(var organizationEntry : bundle.getEntry()){
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: name={} | zipCode={}", organization.getName(), organization.getAddressFirstRep().getPostalCode());
}
{% endhighlight %}
</div>

</div>

L'API devrait vous retourner une réponse de ce genre :

```bash
Organization found: name=Renard et Renard | zipCode=91794
Organization found: name=Maillard et Maillard | zipCode=10228
```

