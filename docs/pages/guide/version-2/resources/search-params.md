---
layout: menu-version-2
title: Paramètres de recherche
subTitle: Ressources
---

*Lien vers la spécification FHIR : <https://hl7.org/FHIR/search.html>*


<div class="wysiwyg" markdown="1">
- [Paramètres de recherche disponibles](#one-header)
- [Paramètres de type texte](#two-header)
- [Paramètres de type token](#three-header)
- [Paramètres de type date](#four-header)
- [Paramètres combinés](#seven-header)
  - [Paramètre ET](#71-header)
  - [Paramètre OU](#72-header)
- [Paramètres des résultats](#eight-header)
  - [Paramètre _count](#81-header)
  - [Paramètre _total](#82-header)
  - [Paramètre _include](#83-header)
  - [Paramètre _revinclude](#84-header)
  - [Paramètre _elements](#85-header)

</div>
<br />


## <a id="one-header"></a>1) Paramètres de recherche disponibles
Pour afficher les paramètres de recherche pris en charge par l'API, vous pouvez interroger le CapabilityStatement.

**Requête :**

`GET [base]/metadata`


## <a id="two-header"></a>2) Paramètres de type texte ([string](https://www.hl7.org/fhir/search.html#string))

Les recherchers de type texte peuvent s'effectuer sur les différentes ressources disponibles.

#### 2.1) Recherche sans "modifier"

**Requête :**

`GET [base]/Organization?name=Renard`


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

#### 2.2) Recherche avec le "modifier" "contains"

**Requête :**

`GET [base]/Organization?name%3Acontains=EURL`


**Exemples de code :** 

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=EURL"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var containsSearchClause = Organization.NAME.contains().value("EURL");

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
  .Where("name:contains=EURL")
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

#### 2.3) Recherche avec le "modifier" "exact"

**Requête :**

`GET [base]/Organization?name%3Aexact=Gautier%20EURL`


**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Aexact=Gautier%20EURL"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the name search parameter :
var exactSearchClause = Organization.NAME.matchesExactly().value("Gautier EURL");

var bundle = client.search()
        .forResource(Organization.class)
        .where(exactSearchClause)
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
  .Where("name:exact=Gautier EURL")
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

`GET [base]/Organization?name%3Acontains=Renard&name%3Acontains=et`



**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H# "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=Renard&name%3Acontains=et"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the two name search parameters :
var firstSearchClause = Organization.NAME.contains().value("Renard");
var secondSearchClause = Organization.NAME.contains().value("et");

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
  .Where("name:contains=Renard").Add("name:contains", "et")
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

`GET [base]/Organization?name%3Acontains=Renard%2Cet`

<br />

**Exemples de code:**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Organization?name%3Acontains=Renard%2Cet"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the two name search parameters :
var orSearchClause = Organization.NAME.contains().values("Renard", "et");

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
  .Where("name:contains=Renard,et")
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
  
## <a id="eight-header"></a>8) Paramètres des résultats de la recherche

Il s'agit d'un ensemble de paramètres permettant de gérer les résultats retournés par une recherche. 
Vous trouverez ci-dessous la liste des paramètres de résultats de recherche pris en charge dans notre contexte.

#### <a id="81-header"></a>8.1) Paramètre ["_count"](https://www.hl7.org/fhir/search.html#count) 

Il permet de contrôler le nombre maximal de ressources retournées sur une page lorsqu'une réponse de l'API est paginée. Par exemple, _count=10 renvoie un maximum de 10 ressources. La valeur par défaut est 50.

**Requête:**

`GET [base]/Device?_count=200`

#### <a id="82-header"></a>8.2) Paramètre ["_total"](https://www.hl7.org/fhir/search.html#total) 

Comme son nom l'indique, ce paramètre indique le nombre total d'éléments (ressources) qui correspondent aux critères de recherche.
Ce paramètre peut prendre 3 valeurs : none, estimate ou accurate.

<div class="wysiwyg" markdown="1">
* none : le total n'est pas affiché
* estimate : le total affiché est une estimation
* accurate : le total affiché est précis
</div>

&nbsp;

Cet exemple montre comment utiliser le paramètre  _total=none pour ne pas afficher le total :

**Requête:**

`GET [base]/Device?_total=none`

Par défaut, l'affichage (ou pas) du total dépend principalement du temps nécessaire à son calcul. Ainsi, si le temps de calcul est trop important, le total ne sera pas inclus dans la réponse.
Dans la majorité des cas, le total est affiché sauf dans certains cas particuliers, comme les recherches textuelles (champs de type string) sur de gros volumes de données. Par exemple, rechercher tous les PractitionerRole ayant un nom d'exercice contenant « Martin ».   

#### <a id="83-header"></a>8.2) Paramètre ["include"](https://www.hl7.org/fhir/search.html#_include) 

Le paramètre **_include** permet d’afficher dans le résultat les ressources mères liées à la ressource fille. La/Les ressource(s) mère(s) es/sont récupérée(s) à partir de la ressource fille. 


**Exemples:**

`GET [base]/PractitionerRole?_include=PractitionerRole:organization`

Cette requête par exemple remonte les PractitionerRole ainsi que les Oganization auxquelles ils sont rattachés.
 

#### <a id="84-header"></a>8.2) Paramètre ["revinclude"](https://www.hl7.org/fhir/search.html#_revinclude) 

Le paramètre **_revinclude** permet d’afficher dans le résultat les ressources filles liées à la ressource mère. La/Les ressource(s) filles est/sont récupérée(s) à partir de la ressource mère. 


**Exemples:**

`GET [base]/Organization?_revinclude=Organization:partof`

Cette requête par exemple remonte les Entités Géographiques (EG) et les Entités Juridiques (EJ) de rattachement.

#### <a id="85-header"></a>8.2) Paramètre ["_elements"](https://hl7.org/fhir/search.html#_elements) 

Le paramètre **_elements** permet de préciser la liste d’attributs qui doit être retournée avec la ressource.  


**Exemples:**

`GET [base]/Practitioner?_elements=identifier,active`

Dans la requête ci-dessus, la réponse retournée contiendra un lot de practitioner, mais chaque entrée n’inclura que les attributs identifier et active du practitioner.

A noter que la réponse contient également une meta.tag valeur de SUBSETTED pour indiquer que tous les attributs de la ressource ne sont pas inclus.

```json

...
                    "tag": [
                        {
                            "system": "http://terminology.hl7.org/CodeSystem/v3-ObservationValue",
                            "code": "SUBSETTED",
                            "display": "Resource encoded in summary mode"
                        }
                    ]
                },
                "identifier": [
                    {
                        "use": "official",
                        "type": {
                            "coding": [
                                {
                                    "system": "http://interopsante.org/CodeSystem/v2-0203",
                                    "code": "IDNPS"
                                }
                            ]
                        },
                        "system": "urn:oid:1.2.250.1.71.4.2.1",
                        "value": "810000001916"
                    },
                    {
                        "use": "official",
                        "type": {
                            "coding": [
                                {
                                    "system": "http://interopsante.org/CodeSystem/v2-0203",
                                    "code": "RPPS"
                                }
                            ]
                        },
                        "system": "http://rpps.fr",
                        "value": "10000001916"
                    }
                ],
                "active": true
...

```

&nbsp;

{% include_relative _source-ref.md %}

