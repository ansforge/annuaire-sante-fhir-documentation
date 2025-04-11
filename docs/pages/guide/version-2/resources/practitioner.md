---
layout: menu-version-2
title: Practitioner
subTitle: Ressources
---

<div class="wysiwyg" markdown="1">
- [Présentation de la ressource](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche d'un professionnel](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par identifiant](#42-header)
  - [Rechercher par statut](#44-header)
  - [Rechercher par date de mise à jour](#45-header)
</div>
<br />


## <a id="one-header"></a>1) Présentation de la ressource Practitioner

Il s'agit d'une ressource qui regroupe  les données décrivant l'[« exercice professionnel »](https://mos.esante.gouv.fr/2.html#_9d79ff39-6b00-4aa6-ac03-7afb4a8aad2b). Les informations disponibles sont :

<div class="wysiwyg" markdown="1">
* Données d'identification : identifiant RPPS - identifiant unique et pérenne de la personne dans le répertoire RPPS -, civilité d'exercice, civilité, etc. 
* Données de contact : Messageries Sécurisées de Santé (MSS), type de messagerie, etc.
* Données relatives aux titres liés à l'exercice professionnel : diplôme, type de diplôme, attestation, certificat ou autre titres et autorisation d'exercice.
</div>
&nbsp;


## <a id="two-header"></a>2) Caractéristiques techniques de la ressource

<table width="25%">
<tbody>
<tr>
<td width="45%">
<p><strong>Endpoint</strong></p>
</td>

<td width="54%">
<p>{{site.ans.api_url}}/fhir/v2/Practitioner</p>
</td>
</tr>
<tr>
<td width="45%">
<p><strong>Header</strong></p>
</td>
<td width="54%">
<p>ESANTE-API-KEY</p>
</td>
</tr>
<tr>
<td width="45%">
<p><strong>Méthodes HTTP associées</strong></p>
</td>

<td width="54%">
<p>GET, POST</p>
</td>
</tr>
<tr>
<td width="45%">
<p><strong>Paramètres de requête</strong></p>
</td>
<td width="54%">
<p>_count, _include, _revinclude</p>
</td>
</tr>
</tbody>
</table>

## <a id="three-header"></a>3) Paramètres de recherche

| Nom             | Type    | Description         |
| ---             | ---     | ---                 |
| _id             | token   | ID de la ressource  |
| _lastUpdated    | date    | renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée (eq, ne, gt, lt, ge, le, ap). Plus d'informations sur les [dates] (https://build.fhir.org/search.html#date) |
| _since          | date    |                     |
| _total          | string  |                     |
| active          | token   | Recherche les ressources Practitioner actives |
| identifier      | token   | Recherche sur tous les identifiants des professionnels intervenant dans le système de santé|
| identifier-type | token   | Recherche sur les types d'identifiants (ADELI, RPPS, IDNPS - IDentifiant National du Professionnel intervenant dans le système de Santé |
| mailbox-mss     | string  | La Messagerie Sécurisées de Santé du Professionnel|
| name            | string  | Une recherche définie par le serveur qui peut correspondre à n'importe quel champ de HumanName, ici sur le préfix correspondant à la civilité des professionnels de santé|


## <a id="four-header"></a>4) Recherche d'un professionnel sur des critères spécifiques

Voici des exemples de requêtes sur la recherche de professionnels intervenant dans le système de santé.


#### <a id="41-header"></a>4.1) Rechercher tout (sans critère)

**Récit utilisateur :** 
En tant que client de l'API, je souhaite récupérer l'ensemble des professionnels intervenant dans le système de santé. 

**Exemples de requêtes :**

```sh
GET [base]/Practitioner
# récupère l'ensemble des exercices professionnels (incluant les actifs et les inactifs)

GET [base]/Practitioner?_revinclude=PractitionerRole:practitioner 
# récupère l'ensemble des exercices professionnels ainsi que les activités liées (Practitioner + PractitionerRole actifs et inactifs)

```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner"
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
<div class="tab-content" data-name="python">
{% highlight python %}
import requests
from fhir.resources.fhirtypes import Bundle, Practitioner

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/Practitioner"
api_key = "{{site.ans.api_key}}"

headers = {
    "ESANTE-API-KEY": api_key,
    "Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR
def fetch_practitioners():
    response = requests.get(api_url, headers=headers)
    if response.status_code == 200:
        bundle = Bundle(**response.json())
        for entry in bundle.entry:
            practitioner = entry.resource
            if isinstance(practitioner, Practitioner):
                name = practitioner.name[0].text if practitioner.name else "Unknown"
                print(f"Practitioner found: id={practitioner.id} name={name}")
    else:
        response.raise_for_status()

# Utilisation du client
fetch_practitioners()
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var bundle = client.Search<Practitioner>();
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    var name = "";
    foreach (var n in practitioner.Name[0].Prefix) {
        name = name + " " + n;
    }
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}
</div>

</div>
<br />


#### <a id="42-header"></a>4.2) Rechercher par identifiant (identifier)

**Contexte :** 
En tant que client de l'API, je souhaite vérifier l'identité d'un professionnel à partir de son identifiant.

**Requête :**

```sh
`GET [base]/Practitioner?identifier=10001234567`
# récupère le Practitioner par rapport à l'identifiant RPPS du professionnel

```

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner?identifier=0012807590%2C810000005479"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var identifierParams = Practitioner.IDENTIFIER.exactly().codes("0012807590", "810000005479");

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
<div class="tab-content" data-name="python">
{% highlight python %}
import requests
from fhir.resources.fhirtypes import Bundle, Practitioner

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/Practitioner"
api_key = "{{site.ans.api_key}}"
identifiers = "0012807590,810000005479"

headers = {
    "ESANTE-API-KEY": api_key,
    "Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR avec des identifiants spécifiques
def fetch_practitioners_by_identifier():
    response = requests.get(f"{api_url}?identifier={identifiers}", headers=headers)
    if response.status_code == 200:
        bundle = Bundle(**response.json())
        for entry in bundle.entry:
            practitioner = entry.resource
            if isinstance(practitioner, Practitioner):
                name = practitioner.name[0].text if practitioner.name else "Unknown"
                print(f"Practitioner found: id={practitioner.id} name={name}")
    else:
        response.raise_for_status()

# Utilisation du client
fetch_practitioners_by_identifier()
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("identifier=0012807590,810000005479")
  .LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    var name = "";
    foreach (var n in practitioner.Name[0].Prefix)
    {
        name = name + " " + n;
    }
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}
</div>
</div>
<br />

#### <a id="43-header"></a>4.4) Rechercher par prénom et nom d'exercice

**Récit utilisateur :** 
En tant que client de l'API, je souhaite rechercher tous les professionnels par rapport au prénom et nom d'exercice .

**Requête :**

```sh
`GET [base]/Practitioner?name:family=MASI&name:given=BRUNO
# Récupère l'ensemble des Practitioners dont le nom d'exercice est MASI et le prénom d'exercice est Bruno
```

<br />

#### <a id="43-header"></a>4.4) Rechercher par statut (active)

**Récit utilisateur :** 
En tant que client de l'API, je souhaite rechercher tous les professionnels de santé actifs.

**Requête :**

```sh
`GET [base]/Practitioner?active=true`
# récupère l'ensemble des Practitioners qui ont un exercice professionnel actif
```

Exemples de code :

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner?name\:family=MASI&name\:given=BRUNO" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} // create the client: var client = FhirTestUtils.createClient();
// create the name search parameters:
var familyNameParam = Practitioner.NAME.exactly().family("MASI");
var givenNameParam = Practitioner.NAME.exactly().given("BRUNO");

var bundle = client.search()
.forResource(Practitioner.class)
.where(familyNameParam)
.and(givenNameParam)
.returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {
// cast entry :
var practitioner = (Practitioner) practitionerEntry.getResource();
// print name and id :
logger.info("Practitioner found: id={} name={}", practitioner.getIdElement().getIdPart(), practitioner.getNameFirstRep().getNameAsSingleString());
}
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import Bundle, Practitioner
Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/Practitioner"
api_key = "{{site.ans.api_key}}"
family_name = "MASI"
given_name = "BRUNO"

headers = {
"ESANTE-API-KEY": api_key,
"Content-Type": "application/json"
}

Fonction pour effectuer une requête FHIR avec un prénom et un nom d'exercice
def fetch_practitioners_by_name():
response = requests.get(f"{api_url}?name:family={family_name}&name:given={given_name}", headers=headers)
if response.status_code == 200:
bundle = Bundle(**response.json())
for entry in bundle.entry:
practitioner = entry.resource
if isinstance(practitioner, Practitioner):
name = practitioner.name[0].text if practitioner.name else "Unknown"
print(f"Practitioner found: id={practitioner.id} name={name}")
else:
response.raise_for_status()

Utilisation du client
fetch_practitioners_by_name()
{% endhighlight %}

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} // create the client: var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
.Where("name:family=MASI")
.And("name:given=BRUNO")
.LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
// print ids:
var practitioner = be.Resource as Practitioner;
var name = practitioner.Name[0].Given.FirstOrDefault() + " " + practitioner.Name[0].Family;
Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}

</div> </div> <br />

<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner?active=true"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v2/Practitioner?active=true');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: name=".$practitioner->getName()[0]->getPrefix()[0]->getValue()." | active=". $practitioner->getActive(). "\n");
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
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | active={practitioner.Active.Value}");
}
{% endhighlight %}
</div>
</div>
<br />


#### <a id="44-header"></a>4.5) Rechercher par date de mise à jour (_lastUpdated)

En tant que client de l'API, je souhaite rechercher tous les exercices professionnels mis à jour depuis une certaine date.

**Requête :**
```sh
`GET [base]/Practitioner?_lastUpdated=ge2024-08-30`
# Récupère tous les Practitioners mis à jour à partir du 30 août 2024 (inclus) jusqu'à aujourd'hui

```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner?_lastUpdated=ge2022-08-08T06%3A47%3A02"
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
<div class="tab-content" data-name="python">
{% highlight python %}
import requests
from fhir.resources.fhirtypes import Bundle, Practitioner

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/Practitioner"
api_key = "{{site.ans.api_key}}"
last_updated = "ge2022-08-08T06:47:02"

headers = {
    "ESANTE-API-KEY": api_key,
    "Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR avec une date de mise à jour
def fetch_practitioners_by_last_updated():
    response = requests.get(f"{api_url}?_lastUpdated={last_updated}", headers=headers)
    if response.status_code == 200:
        bundle = Bundle(**response.json())
        for entry in bundle.entry:
            practitioner = entry.resource
            if isinstance(practitioner, Practitioner):
                last_update = practitioner.meta.lastUpdated if practitioner.meta else "Unknown"
                print(f"Practitioner found: id={practitioner.id} | lastUpdate={last_update}")
    else:
        response.raise_for_status()

# Utilisation du client
fetch_practitioners_by_last_updated()
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("_lastUpdated=ge2022-08-08T06:47:02")
  .LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitioner = be.Resource as Practitioner;
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | lastUpdate={practitioner.Meta.LastUpdated}");
}
{% endhighlight %}
</div>
</div>
<br />


{% include_relative _source-ref.md %}

