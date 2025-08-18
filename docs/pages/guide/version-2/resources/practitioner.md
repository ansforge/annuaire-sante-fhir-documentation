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
  - [Rechercher par prénom et nom d'exercice](#43-header)
  - [Rechercher par statut](#44-header)
  - [Rechercher par date de mise à jour](#45-header)
  - [Rechercher par profession](#46-header)
  - [Rechercher par spécialité](#47-header)
  - [Rechercher par catégorie professionnelle](#48-header)

</div>
<br />


## <a id="one-header"></a>1. Présentation de la ressource Practitioner

Il s'agit d'une ressource qui regroupe  les données décrivant l'[« exercice professionnel »](https://mos.esante.gouv.fr/2.html#_9d79ff39-6b00-4aa6-ac03-7afb4a8aad2b). Les informations disponibles sont :

<div class="wysiwyg" markdown="1">
* Données d'identification : identifiant RPPS - identifiant unique et pérenne de la personne dans le répertoire RPPS -, civilité d'exercice, civilité, etc. 
* Données de contact : Messageries Sécurisées de Santé (MSS), type de messagerie, etc.
* Données relatives aux titres liés à l'exercice professionnel : diplôme, type de diplôme, attestation, certificat ou autre titres et autorisation d'exercice.
</div>
&nbsp;


## <a id="two-header"></a>2. Caractéristiques techniques de la ressource

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

## <a id="three-header"></a>3. Paramètres de recherche (Search Parameter)

| Nom                           | Type    | Description         |
| ---                           | ---     | ---                 |
| _id                           | token   | Recherche sur l'ID technique de la ressource  |
| _lastUpdated                  | date    |  renvoie uniquement les ressources selon la date de mises à jour et le "modifier" utilisé (eq, ne, gt, lt, ge, le, ap). Plus d'informations sur les [dates] (https://www.hl7.org/fhir/R4/search.html#date)   |
| active                        | token   | Recherche sur le statut de l'exercice professionnel (ouvert ou fermé)  |
| data-information-system       | token   | Recherche sur le système d'informations  |
| data-registration-authority   | token   | Recherche sur l'autorité d'enregistrement |
| family                        | string  | Recherche sur le nom d'exercice du professionnel |
| given                         | string  | Recherche sur le prénom d'exercice du professionnel |
| identifier                    | token   | Recherche sur l'identifiant métier du professionnel (RPPS, IDNPS - IDentifiant National du Professionnel de Santé)
| identifier-type               | type    | Recherche sur le type d'identifiant du professionnel (soit RPPS, soit IDNPS)
| mailbox-mss                   | string  | Recherche sur la Messagerie Sécurisée de Santé (MSS) du Professionnel |
| name                          | string  | Recherche sur le nom complet. Il peut contenir le préfixe correspondant à civilité du professionnel (ex: MME) ou le suffixe qui correspond à la civilité d'exercice (ex: DR) |
| number-smartcard              | string  | Recherche sur le numéro de carte des professionnels |
| qualification-code            | token   | Recherche sur le code des éléments suivants: diplôme, exercice professionnel, savoir-faire et type de savoir-faire |


## <a id="four-header"></a>4. Recherche d'un professionnel sur des critères spécifiques

Voici des exemples de requêtes sur la recherche de professionnels intervenant dans le système de santé.


#### <a id="41-header"></a>4.1 Rechercher tout (sans critère)

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

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} 

// Configuration du client
var client = FhirTestUtils.createClient();
var bundle = client.search().forResource(Practitioner.class).returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {

//  print les IDs Organizations:
var practitioner = (Practitioner) practitionerEntry.getResource();
logger.info("Practitioner found: id={} name={}", practitioner.getIdElement().getIdPart(), practitioner.getNameFirstRep().getNameAsSingleString());
}
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import Bundle, Practitioner

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

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} 

// Configuration du client
var client = FhirTestUtils.CreateClient();
var bundle = client.Search<Practitioner>();
foreach (var be in bundle.Entry)
{

// Print les IDs:
var practitioner = be.Resource as Practitioner;
var name = "";
foreach (var n in practitioner.Name[0].Prefix) {
name = name + " " + n;
}
Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}

</div> </div> <br />

#### <a id="42-header"></a>4.2 Rechercher par identifiant (identifier ou _id)

En tant que client de l'API, je souhaite vérifier l'identité d'un professionnel à partir de son identifiant.

**Requête :**

```sh
GET [base]/Practitioner?identifier=10001234567
# récupère le Practitioner par rapport à l'identifiant RPPS du professionnel

GET [base]/Practitioner?_id=003-32783-88722
# récupère le Practitioner par rapport à son identifiant technique
```

**Exemples de code :**

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner?identifier=0012807590%2C810000005479" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} 

// Configuration du client
var client = FhirTestUtils.createClient();
var identifierParams = Practitioner.IDENTIFIER.exactly().codes("0012807590", "810000005479");

var bundle = client.search()
.forResource(Practitioner.class)
.where(identifierParams)
.returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {

// Print les IDs Organization:
var practitioner = (Practitioner) practitionerEntry.getResource();
logger.info("Practitioner found: id={} name={}", practitioner.getIdentifierFirstRep().getValue(), practitioner.getNameFirstRep().getNameAsSingleString());
}
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import Bundle, Practitioner

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

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} 

// Configuration du client
var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
.Where("identifier=0012807590,810000005479")
.LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{
// Print les IDs:
var practitioner = be.Resource as Practitioner;
var name = "";
foreach (var n in practitioner.Name[0].Prefix)
{
name = name + " " + n;
}
Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}

</div> </div> <br />

#### <a id="43-header"></a>4.3. Rechercher par prénom et nom d'exercice

En tant que client de l'API, je souhaite rechercher tous les professionnels par rapport au prénom et nom d'exercice .

**Requête :**

```sh
GET [base]/Practitioner?name:family=MASI&name:given=BRUNO
# Récupère l'ensemble des Practitioners dont le nom d'exercice est MASI et le prénom d'exercice est Bruno
```

**Exemples de code :**

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/Practitioner?name:family=MASI&name:given=BRUNO" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} 

// Configuration du client

var client = FhirTestUtils.createClient();

// Configurer le paramètre de recherche:
var familyNameParam = Practitioner.NAME.exactly().family("MASI");
var givenNameParam = Practitioner.NAME.exactly().given("BRUNO");

var bundle = client.search()
        .forResource(Practitioner.class)
        .where(familyNameParam)
        .and(givenNameParam)
        .returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {

// Convertir l'entrée
    var practitioner = (Practitioner) practitionerEntry.getResource();

// Enregistrer le message d'information
    logger.info("Practitioner found: id={} name={}", practitioner.getIdElement().getIdPart(), practitioner.getNameFirstRep().getNameAsSingleString());
}
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import Bundle, Practitioner

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/Practitioner"
api_key = "{{site.ans.api_key}}"
family_name = "MASI"
given_name = "BRUNO"

headers = {
"ESANTE-API-KEY": api_key,
"Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR avec un prénom et un nom d'exercice
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

# Utilisation du client
fetch_practitioners_by_name()
{% endhighlight %}

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} 

// Configuration du client 
var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
.Where("name:family=MASI")
.And("name:given=BRUNO")
.LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{

// Enregistrer les IDs
var practitioner = be.Resource as Practitioner;
var name = practitioner.Name[0].Given.FirstOrDefault() + " " + practitioner.Name[0].Family;
Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
}
{% endhighlight %}

</div> </div> <br />

<br />

#### <a id="44-header"></a>4.4 Rechercher par statut (active)

En tant que client de l'API, je souhaite rechercher tous les professionnels de santé actifs.

**Requête :**

```sh
GET [base]/Practitioner?active=true
# récupère l'ensemble des Practitioners qui ont un exercice professionnel actif
```

**Exemples de code :**

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?active=true" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} 

// Configuration du client
var client = FhirTestUtils.createClient();
var activeSearchClause = Practitioner.ACTIVE.exactly().code("true");

var bundle = client.search()
.forResource(Practitioner.class)
.where(activeSearchClause)
.returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {

// Enregistrer les IDs Organization
var practitioner = (Practitioner) practitionerEntry.getResource();
logger.info("Practitioner found: name={} | active={}", practitioner.getNameFirstRep().getNameAsSingleString(), practitioner.getActive());
}
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import Bundle, Practitioner

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v1/Practitioner"
api_key = "{{site.ans.api_key}}"

headers = {
"ESANTE-API-KEY": api_key,
"Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR pour les professionnels actifs
def fetch_active_practitioners():
response = requests.get(f"{api_url}?active=true", headers=headers)
if response.status_code == 200:
bundle = Bundle(**response.json())
for entry in bundle.entry:
practitioner = entry.resource
if isinstance(practitioner, Practitioner):
name = practitioner.name[0].text if practitioner.name else "Unknown"
active_status = practitioner.active
print(f"Practitioner found: name={name} | active={active_status}")
else:
response.raise_for_status()

# Utilisation du client
fetch_active_practitioners()
{% endhighlight %}

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} 

// Configuration du client
var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
.Where("active=true")
.LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{

// Enregistrer les IDs
var practitioner = be.Resource as Practitioner;
Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | active={practitioner.Active.Value}");
}
{% endhighlight %}

</div> </div> <br />

#### <a id="45-header"></a>4.5 Rechercher par date de mise à jour (_lastUpdated)

En tant que client de l'API, je souhaite rechercher tous les exercices professionnels mis à jour depuis une certaine date.

**Requête :**
```sh
GET [base]/Practitioner?_lastUpdated=ge2024-08-30
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

// Configuration du client
var client = FhirTestUtils.createClient();

// Enregistrement des paramètres de recherche Date
var dateParam = new DateClientParam("_lastUpdated");

var bundle = client.search()
        .forResource(Practitioner.class)
        .where(dateParam.afterOrEquals().second("2022-08-08T06:47:02"))
        .returnBundle(Bundle.class).execute();

for (var practitionerEntry : bundle.getEntry()) {
// Convertir les entrées
    var practitioner = (Practitioner) practitionerEntry.getResource();

//  Print les dates de mises à jour et ID
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

// Configuration du client
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("_lastUpdated=ge2022-08-08T06:47:02")
  .LimitTo(50);
var bundle = client.Search<Practitioner>(q);
foreach (var be in bundle.Entry)
{

// Print les IDs
    var practitioner = be.Resource as Practitioner;
    Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | lastUpdate={practitioner.Meta.LastUpdated}");
}
{% endhighlight %}
</div>
</div>
<br />



#### <a id="46-header"></a>4.6 Rechercher par profession
En tant que client de l'API, je souhaite rechercher tous les professionnels dont la profession est Pharmacien

**Requête :**

```sh
GET [base]/Practitioner?qualification-code=21
# Récupère tous les professionnels dont la profession est pharmacien (code 21)

GET [base]/Practitioner?qualification-code=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_G15-ProfessionSante%2FFHIR%2FTRE-G15-ProfessionSante%7C21
# Récupère tous les professionnels dont la profession est pharmacien en indiquant la TRE (Table de REférence)
```
<br />

#### <a id="47-header"></a>4.7 Rechercher par spécialité
En tant que client de l'API, je souhaite rechercher tous les professionnels dont la spécialité est Pneumologie (SM41)

**Requête :**

```sh
GET [base]/Practitioner?qualification-code=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R38-SpecialiteOrdinale%2FFHIR%2FTRE-R38-SpecialiteOrdinale%7CSM41
# Récupère tous les professionnels dont la spécialité est Pneumologie (SM41)
```
<br />

#### <a id="48-header"></a>4.8 Rechercher par catégorie professionnelle
En tant que client de l'API, je souhaite rechercher tous les professionnels selon la catégorie professionnelle. Pour plus d'informations, consulter le 
[lien suivant](https://interop.esante.gouv.fr/ig/nos/1.5.0/ValueSet-JDV-J89-CategorieProfessionnelle-RASS.html){:target="_blank"}.

**Requête :**
```sh
GET [base]/Practitioner?qualification-code=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R09-CategorieProfessionnelle%2FFHIR%2FTRE-R09-CategorieProfessionnelle%7CC
# Récupère tous les professionnels dont la catégorie professionnelle est Civil (code: C)
```
<br />

{% include_relative _source-ref.md %}
