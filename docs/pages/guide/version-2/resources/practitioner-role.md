---
layout: menu-version-2
title: Practitioner Role
subTitle: Ressources
---

<div class="wysiwyg" markdown="1">
- [Présentation de la ressource](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche d'une situation d'exercice](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par identifiant technique](#42-header)
  - [Rechercher par identifiant de l'activité](#43-header)
  - [Rechercher par rôle](#44-header)
  - [Rechercher par statut](#45-header)
</div>
<br />


## <a id="one-header"></a>1. Présentation de la ressource PractitionerRole

Il s'agit d'une ressource qui regroupe  les données décrivant la [« situation d'exercice »](https://mos.esante.gouv.fr/2.html#_86e1685b-9e1d-47fb-bb66-d23ca0eb9679) (ou activité) du professionnel :
<div class="wysiwyg" markdown="1">
* Données sur l'activité professionnelle : identifiant de l'activité, genre d'activité, mode d'exercice, fonction, métier pharmacien, etc.
* Données de contact : Messageries Sécurisées de Santé (MSS), type de messagerie, etc.

</div>
<br />

## <a id="two-header"></a>2. Caractéristiques techniques de la ressource

<table width="25%">
<tbody>
<tr>
<td width="45%">
<p><strong>Endpoint</strong></p>
</td>

<td width="54%">
<p>{{site.ans.api_url}}/fhir/v2/PractitionerRole</p>
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
<p><strong>Paramètres de recherche</strong></p>
</td>
<td width="54%">
<p>_id, _lastUpdated, active, data-information-system, data-registration-authority, identifier, mailbox-mss, organization, practitioner, role</p>
</td>
</tr>
<tr>
<td width="45%">
<p><strong>Paramètres de requête</strong></p>
</td>
<td width="54%">
<p>_count, _include</p>
</td>
</tr>
</tbody>
</table>
<br />


## <a id="three-header"></a>3. Paramètres de recherche (Search Parameter)

| Nom                               | Type  | Description                 |
| ---                               | ---   | ---                         |
| _id                               | token | Recherche sur l'ID de la ressource     |
| _lastUpdated                      | date  | Renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée (eq, ne, gt, lt, ge, le, ap). Plus d'informations sur les [dates] (https://build.fhir.org/search.html#date)  |
| active                            | token | Recherche les ressources PractitionerRole actives |
| data-information-system           | token | Recherche sur le système d'information (RPPS, FINESS, MSS, CG) |
| data-registration-authority       | token | Recherche sur l'autorité d'enregistrement |
| identifier                        | token | Recherche sur l'identifiant métier du PractitionerRole |
| mailbox-mss                       | string    | Recherche sur la Messagerie Sécurisée de Santé (MSS) associée à la structure d'exercice du professionnel |
| organization                      | reference | Référence l'ID technique de l'organisation associée à l'activité du professionnel |
| practitioner                      | reference | Référence l'ID technique du Practitioner associée à l'activité du professionnel |
| role                              | token     | Recherche sur la fonction, le genre d'activité, le mode d'exercice ou la section Tableau des Pharmaciens |


## <a id="four-header"></a>4. Recherche d'une situation d'exercice

#### <a id="41-header"></a>4.1 Rechercher tout (sans critère)

En tant que client de l'API, je souhaite récupérer l'ensemble des données correspondant aux situations d'exercice des professionnels

**Requêtes :**

```sh
GET [base]/PractitionerRole
# récupère l'ensemble des PractitionersRoles (incluant les actifs et les inactifs)

GET [base]/PractitionerRole?_include=PractitionerRole%3Aorganization
# inclure les Organization qui sont référencées par les PractitionerRole (PractitionerRole + Organization)

GET [base]/PractitionerRole?_include=PractitionerRole%3Apractitioner
# inclure les Practitioner qui sont référencés par les PractitionerRole (PractitionerRole + Practitioner)

GET [base]/PractitionerRole?_include=*
# inclure toutes les ressources qui sont référencées les PractitionerRole (PractitionerRole + Practitioner + Organization)

```
<br />

**Exemples de code :**

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/PractitionerRole" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} 

// Création du client 
var client = FhirTestUtils.createClient();
var bundle = client.search().forResource(PractitionerRole.class).returnBundle(Bundle.class).execute();

for (var roleEntry : bundle.getEntry()) {
// Print les données du PractitionerRole
var role = (PractitionerRole) roleEntry.getResource();
logger.info("Practitioner Role found: id={} code={}", role.getIdElement().getIdPart(), role.getCodeFirstRep().getCodingFirstRep().getCode());
}
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import Bundle, PractitionerRole

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/PractitionerRole"
api_key = "{{site.ans.api_key}}"

headers = {
"ESANTE-API-KEY": api_key,
"Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR
def fetch_practitioner_roles():
response = requests.get(api_url, headers=headers)
if response.status_code == 200:
bundle = Bundle(**response.json())
for entry in bundle.entry:
practitioner_role = entry.resource
if isinstance(practitioner_role, PractitionerRole):
code = practitioner_role.code[0].coding[0].code if practitioner_role.code else "Unknown"
print(f"Practitioner Role found: id={practitioner_role.id} code={code}")
else:
response.raise_for_status()

# Utilisation du client
fetch_practitioner_roles()
{% endhighlight %}

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} // create the client: var client = FhirTestUtils.CreateClient();
var bundle = client.Search<PractitionerRole>();
foreach (var be in bundle.Entry)
{
// print ids:
var practitionerRole = be.Resource as PractitionerRole;
Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={practitionerRole.Code[0].Coding[0].Code}");
}
{% endhighlight %}

</div> </div> <br />

#### <a id="42-header"></a>4.2 Recherche par identifiant technique (_id)

En tant que client de l'API, je souhaite rechercher une ressource PractitionerRole par son identifiant technique. 

**Requête :**

```sh
GET [base]/PractitionerRole?_id=005-69329-7187020`
```
<br />


**Exemples de code :**

<div class="code-sample"> <div class="tab-content" data-name="curl"> {% highlight bash %} curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/PractitionerRole?_id=005-5087586-6923328" {% endhighlight %} </div> <div class="tab-content" data-name="java"> {% highlight java %} 

// Création du client 
var client = FhirTestUtils.createClient();
var practitionerRole = client.read()
.resource(PractitionerRole.class)
.withId("005-5087586-6923328")
.execute();

logger.info("Practitioner Role found: id={}", practitionerRole.getIdElement().getIdPart());
{% endhighlight %}

</div> <div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import PractitionerRole

# Configuration du client
api_url = "{{site.ans.api_url}}/fhir/v2/PractitionerRole?_id=005-5087586-6923328"
api_key = "{{site.ans.api_key}}"

headers = {
"ESANTE-API-KEY": api_key,
"Content-Type": "application/json"
}

# Fonction pour effectuer une requête FHIR par identifiant technique
def fetch_practitioner_role_by_id():
response = requests.get(api_url, headers=headers)
if response.status_code == 200:
practitioner_role = PractitionerRole(**response.json())
print(f"Practitioner Role found: id={practitioner_role.id}")
else:
response.raise_for_status()

# Utilisation du client
fetch_practitioner_role_by_id()
{% endhighlight %}

</div> <div class="tab-content" data-name="C#"> {% highlight csharp %} 

// Création du client 
var client = FhirTestUtils.CreateClient();
var practitionerRole = client.Read<PractitionerRole>("PractitionerRole?_id=005-5087586-6923328");
Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value}");
{% endhighlight %}

</div> </div> <br />


#### <a id="43-header"></a>4.3 Recherche par identifiant de l'activité (identifier)

En tant que client de l'API, je souhaite rechercher une ressource PractitionerRole par l'identifiant de son activité. 

**Requête :**

```sh
GET [base]/PractitionerRole?identifier=1013186038
```
<br />

#### <a id="44-header"></a>4.4 Recherche par rôle (role)

Le paramètre "role" permet de rechercher les PractitionerRole selon différents référentiels. Voici les différents codes systèmes disponibles : 

| Type de données       | Code système                                        |
| ---                   | ---                                                 |
| Type d'organisation   | http://interopsante.org/fhir/CodeSystem/fr-v2-3307  |
| Fonction              | https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction	|
| Role Prise Charge     | https://mos.esante.gouv.fr/NOS/TRE_R85-RolePriseCharge/FHIR/TRE-R85-RolePriseCharge |
| Autre Fonction Sanitaire | https://mos.esante.gouv.fr/NOS/TRE_R96-AutreFonctionSanitaire/FHIR/TRE-R96-AutreFonctionSanitaire	|
| Genre activité	    | https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite	|
| Mode d'exercice       | https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice	|
| Métier Pharmacien Section Tableau CNOP | https://mos.esante.gouv.fr/NOS/TRE_R06-SectionTableauCNOP/FHIR/TRE-R06-SectionTableauCNOP	|
| Metier Pharmacien Sous Section Tableau CNOP	 | https://mos.esante.gouv.fr/NOS/TRE_G05-SousSectionTableauCNOP/FHIR/TRE-G05-SousSectionTableauCNOP	 |


Lorsque vous souhaitez rechercher sur un type de données particulier, utiliser les combinaisons suivantes : 
<div class="wysiwyg" markdown="1">
- Renseigner le code système concerné
- Renseigner le code fonctionnel de la valeur souhaité
</div>
<br />

**Exemples de requêtes :**

```sh

GET [base]/PractitionerRole?role=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R85-RolePriseCharge%2FFHIR%2FTRE-R85-RolePriseCharge%7C318
# récupère dans la TRE Role Prise Charge le code 318 correspondant à la fonction "Auxiliaire de vie sociale"

GET [base]/PractitionerRole?role=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R06-SectionTableauCNOP%2FFHIR%2FTRE-R06-SectionTableauCNOP%7CA
# récupère dans la TRE SectionTableauCNOP le code A correspondant au Pharmacien titulaire d'officine "

GET [base]/PractitionerRole?mailbox-mss:contains=apycript.org
# récupère dans le PractitionerRole les adresses mail MSS qui contiennent la valeur apycript.org"

```
<br />

#### <a id="45-header"></a>4.5 Recherche par statut

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher toutes les situations d'exercices/activités actives

**Requête :**

```sh
GET [base]/PractitionerRole?active=true
```

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v2/PractitionerRole?active=true"
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
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
  .Where("active=true")
  .LimitTo(50);
var bundle = client.Search<PractitionerRole>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitionerRole = be.Resource as PractitionerRole;
    Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} active={practitionerRole.Active.Value}");
}
{% endhighlight %}
</div>


</div>
<br />

{% include_relative _source-ref.md %}

