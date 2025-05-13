---
layout: menu-version-2
title: Paramètres de recherche
subTitle: Ressources
---

*Lien vers la spécification FHIR : <https://hl7.org/FHIR/search.html>*


<div class="wysiwyg" markdown="1">
- [Paramètres de recherche disponibles](#one-header)
- [Paramètres de type texte](#two-header)
  - [Recherche sans modificateur](#21-header)
  - [Recherche avec modificateur](#22-header)
- [Paramètres de type token](#three-header)
- [Paramètres de type date](#four-header)
- [Paramètres combinés](#seven-header)
  - [Paramètre ET](#71-header)
  - [Paramètre OU](#72-header)

</div>
<br />


## <a id="one-header"></a>1) Paramètres de recherche disponibles
Pour afficher les paramètres de recherche pris en charge par l'API, il est possible d'interroger le CapabilityStatement.

**Requête :**

```sh
GET [base]/metadata
```

## <a id="two-header"></a>2) Paramètres de type texte ([string](https://www.hl7.org/fhir/search.html#string))

Les recherchers de type texte peuvent s'effectuer sur les différentes ressources disponibles.

#### <a id="21-header"></a>2.1) Recherche sans modificateur

**Requête :**

```sh
`GET [base]/Organization?name=Renard`
```

**Exemples de code :**  

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name=Renard"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var matchSearchClause = Organization.NAME.matches().value("Renard");

var bundle = client.search()
        .forResource(Organization.class)
        .where(matchSearchClause)
        .returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: name={}", organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("name=Renard")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: name={organization.Name}");
}
{% endhighlight %}
</div>
</div>
<br />

#### <a id="22-header"></a>2.2) Recherche avec les modificateurs "contains" et "exact"

**Requête :**

```sh
GET [base]/Organization?name%3Acontains=MIGNOT
# Le modificateur :contains retourne les résultats qui incluent la valeur MIGNOT n'importe où dans le champ recherché.

GET [base]/Organization?name%3Aexact=MIGNOT
# Le modificateur :exact retourne les résultats qui correspondent exactement à l'ensemble du paramètre fourni, y compris la casse et les accents.

```


**Exemples de code :** 

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=MIGNOT"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var containsSearchClause = Organization.NAME.contains().value("MIGNOT");

var bundle = client.search()
.forResource(Organization.class)
.where(containsSearchClause)
.returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
logger.info("Organization found: name={}", organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("name:contains=MIGNOT")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: name={organization.Name}");
}
{% endhighlight %}
</div>
</div>
<br />

## <a id="three-header"></a>3) Paramètres de type [token](https://www.hl7.org/fhir/search.html#token)

Le serveur supporte la recherche par code, par système ou par les deux.

### Recherche par code

**Requête :**

`GET [base]/Organization?identifier=org-org-148`

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?identifier=org-org-148"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var tokenSearchClause = Organization.IDENTIFIER.exactly().code("org-org-148");

var bundle = client.search()
.forResource(Organization.class)
.where(tokenSearchClause)
.returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
// cast entry :
var organization = (Organization) organizationEntry.getResource();
// print data :
logger.info("Organization found: id={} name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("identifier=org-org-148")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} name={organization.Name}");
}
{% endhighlight %}
</div>
</div>
<br />

## <a id="four-header"></a>4) Paramètres de type [date](https://www.hl7.org/fhir/search.html#date)

La recherche par date supporte les préfixes suivants:

| Préfix  | Nom               | Description                                                                         |
| ---     | ---               | ---                                                                                 |
| eq      | equal             | La valeur du paramètre dans la ressource est égale à la valeur saisie	              |
| ne      | not equal         | La valeur du paramètre dans la ressource n'est pas égale à la valeur saisie	        |
| gt      | greater           | La valeur du paramètre dans la ressource est supérieure à la valeur saisie	        |
| lt      | less              | La valeur du paramètre dans la ressource est inférieure à la valeur saisie	        |
| ge      | greater or equal  | La valeur du paramètre dans la ressource est supérieure ou égale à la valeur saisie	|
| le      | less or equal     | La valeur du paramètre dans la ressource est inférieure ou égale à la valeur saisie |
| sa      | starts after      | La valeur du paramètre dans la ressource démarre après la valeur saisie	            |
| eb      | ends before       | La valeur du paramètre dans la ressource termine avant la valeur saisie	            |
| ap      | approximately     | La valeur du paramètre dans la ressource est approximativement la même que la valeur fournie. Il est à noter que la valeur recommandée pour l'approximation est de 10% de la valeur indiquée |

Plus d'informations sur les [dates] (https://build.fhir.org/search.html#date)

Plusiseurs "précisions" sont supportées : yyyy par année, yyyy-MM-dd par jour, et par date complète.

**Quelques exemples :** 

<div class="wysiwyg" markdown="1">
* _lastUpdated=gt2020 : après 2020
* _lastUpdated=ge2020 : 2020 et après (2020 inclus)
* _lastUpdated=lt2022-12-15 : avant le 15 décembre 2022 exclus
* _lastUpdated=lt2022-12-15T15:00:00 : avant le 15 décembre 2022 15h (GMT)
* _lastUpdated=eq2021 : durant l'année 2021
</div>

&nbsp;


**Requête :**

`GET [base]/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04`

<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04" 
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
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
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("_lastUpdated=ge2022-08-05T14:51:04")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} lastUpdate={organization.Meta.LastUpdated.Value}");
}
{% endhighlight %}
</div>
</div>
<br />


## <a id="seven-header"></a>7) Paramètres combinés

Les paramètres combinés permettent d'effectuer des recherches en les cumulant.
Ce cumul se fait de manière inclusive ou alternative.

#### <a id="71-header"></a>7.1) Paramètres ET (AND)

**Requête :**

```sh
GET [base]/Organization?name:contains=ANDRE&name:contains=MIGNOT
```


**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H# "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=ANDRE&name%3Acontains=MIGNOT"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the two name search parameters :
var firstSearchClause = Organization.NAME.contains().value("ANDRE");
var secondSearchClause = Organization.NAME.contains().value("MIGNOT");

var bundle = client.search()
        .forResource(Organization.class)
        .where(firstSearchClause)
        .and(secondSearchClause)
        .returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: id={} | name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("name:contains=ANDRE").Add("name:contains", "MIGNOT")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
// print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} | name={organization.Name}");
}
{% endhighlight %}
</div>
</div>
<br />
  
#### <a id="72-header"></a>7.2) Paramètres OU (OR)

**Requête :**

```sh
GET [base]/Organization?name%3Acontains=ANDRE%2CMIGNOT
```
<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=ANDRE%2CMIGNOT"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the two name search parameters :
var orSearchClause = Organization.NAME.contains().values("ANDRE", "MIGNOT");

var bundle = client.search()
        .forResource(Organization.class)
        .where(orSearchClause)
        .returnBundle(Bundle.class).execute();

for (var organizationEntry : bundle.getEntry()) {
    // cast entry :
    var organization = (Organization) organizationEntry.getResource();
    // print data :
    logger.info("Organization found: id={} | name={}", organization.getIdElement().getIdPart(), organization.getName());
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}

// create the client:
var client = FhirTestUtils.CreateClient();
// create the name search parameter :
var q = new SearchParams()
  .Where("name:contains=ANDRE,MIGNOT")
  .LimitTo(50);
var bundle = client.Search<Organization>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var organization = be.Resource as Organization;
    Console.WriteLine($"Organization found: id={organization.IdElement.Value} | name={organization.Name}");
}
{% endhighlight %}
</div>
</div>
<br />
  
&nbsp;
