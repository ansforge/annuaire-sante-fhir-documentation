---
layout: menu-version-1
title: Practitioner Role
subTitle: Ressources
---

<div class="wysiwyg" markdown="1">
- [Description métier](#one-header)
- [Caractéristiques techniques](#two-header)
- [Paramètres de recherche](#three-header)
- [Recherche sur critères](#four-header)
  - [Rechercher tout](#41-header)
  - [Rechercher par identifiant](#42-header)
  - [Rechercher par rôle](#43-header)
    - [Recherche par profession et par catégorie professionnelle](#431-header)
  - [Recherche par spécialité](#44-header)
  - [Recherche par type de carte](#45-header)
  - [Recherche par professionnel](#46-header)
  - [Recherche par statut](#47-header)
</div>
<br />


## <a id="one-header"></a>1) Description métier de la ressource

Il s'agit d'une ressource qui regroupe  les données décrivant l' [« exercice »](https://mos.esante.gouv.fr/2.html#_5579aac4-b414-41f1-8569-2e99403e3af3) et la [« situation »](https://mos.esante.gouv.fr/2.html#_86e1685b-9e1d-47fb-bb66-d23ca0eb9679) d'exercice du professionnel :
<div class="wysiwyg" markdown="1">
* Données relatives à l'exercice professionnel : nom et prénom d'exercice, profession, civilité d'exercice, catégorie d'exercice, qualifications de spécialiste et disciplines et attributions particulières.
* Données sur l'activité professionnelle : fonction ou rôle du professionnel dans la structure d'activité, genre d'activité, mode d'exercice, type d'activité libérale pour les activités concernées, statut hospitalier pour les activités concernées, données de contact du professionnel pour l'activité concernée, données relatives à la structure d'activité.
* Données relatives à la carte d'un' professionnel intervenant dans le système de santé : type de carte, numéro, période de validité.
</div>
<br />

## <a id="two-header"></a>2) Caractéristiques techniques de la ressource

<table width="25%">
<tbody>
<tr>
<td width="45%">
<p><strong>Endpoint</strong></p>
</td>

<td width="54%">
<p>{{site.ans.api_url}}/fhir/v1/PractitionerRole</p>
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
<p>_id, name, given, family, mailbox-mss, number-smartcard, type-smartcard, active, role, speciality, _lastUpdated, _total, organization, practitioner</p>
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


## <a id="three-header"></a>3) Paramètres de recherche

| Nom | Type | Description |
| --- | --- | --- |
| _id | token | ID de la ressource |
| _lastUpdated | date | Renvoie uniquement les ressources qui ont été mises à jour pour la dernère fois comme spécifié par la période donnée (eq, ne, gt, lt, ge, le, ap). Plus d'informations sur les [dates] (https://build.fhir.org/search.html#date) |
| _since | date | |
| _total | string | |
| active | token | Recherche les ressources PractitionerRole actives |
| as-sp-data-information-system | token | Recherche sur le système d'information |
| as-sp-data-registration-authority | token | Recherche sur l'autorité d'enregistrement |
| family | Recherche sur le nom d'exercice des professionnels intervenant dans le système de santé |
| given | Recherche sur le prénom d'exercice des professionnels intervenant dans le système de santé |
| identifier| token | Recherche sur l'identifiant du PractitionerRole |
| mailbox-mss| string | La Messagerie Sécurisées de Santé (MSS) du Professionnel |
| name | string | Une recherche définie par le serveur qui peut correspondre à n'importe quel champ de HumanName, ici sur le préfix correspondant à la civilité des professionnels de santé|
| number-smartcard | string | Recherche sur le numéro de carte des professionnels |
| organization | reference | Recherche les exercices professionnels et les situation d'exercice rattachés à la structure sélectionnée |
| practitioner | reference | Recherche les exercices professionnels et les situation d'exercice rattachés aux professionnels intervenant dans le système de santé |
| role | token | Recherche sur la profession/ la catégorie professionnelle/ la fonction/ le genre d'activité/ le mode d'exercice ou la section tableau des pharmacien |
| specialty | token | Recherche sur le savoir-faire ou le type de savoir-faire |
| type-smartcard | token | Recherche sur le type de carte du professionnel  |


## <a id="four-header"></a>4) Recherche d'exercice et d'activité du professionnel sur des critères spécifiques

Voici des exemples de requêtes sur les exercices et les activités du professionnel de sante.

#### <a id="41-header"></a>4.1) Rechercher tout (sans critère)

**Récit utilisateur :** En tant que client de l'API, je souhaite récupérer l'ensemble des données correspondant aux situations d'exercice et exercices professionnels des PS.

**Requêtes :**

```sh
GET [base]/PractitionerRole
#récupère l'ensemble des PractitionersRoles (incluant les actifs et les inactifs)

GET [base]/Organization?_revinclude=PractitionerRole:organization #inclure les Organization qui sont référencées par les PractitionerRole (PractitionerRole + Organization)
GET [base]/Organization?_revinclude=PractitionerRole:practitioner #inclure les Practitioner qui sont référencés par les PractitionerRole (PractitionerRole + Practitioner)
GET [base]/Organization?_revinclude=PractitionerRole:* #inclure toutes les ressources qui sont réféencées les PractitionerRole (PractitionerRole + Practitioner + Oraganization)

```
<br />

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner Role found: id=005-5090000-6920000 code=FON-09
  Practitioner Role found: id=005-5070000-6900000 code=FON-09
  Practitioner Role found: id=005-5080000-6920000 code=FON-AU


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()." code=".$practitionerRole->getCode()[0]->getCoding()[0]->getCode()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var bundle = client.Search<PractitionerRole>();
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitionerRole = be.Resource as PractitionerRole;
    Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={practitionerRole.Code[0].Coding[0].Code}");
}
{% endhighlight %}
</div>

</div>
<br />


#### <a id="42-header"></a>4.2) Recherche par identifiant (_id)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher une ressource par son identifiant technique. 

**Requête :**

`GET [base]/PractitionerRole/005-5087586-6923328`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  total: 1
  Practitioner Role found: id=005-5087586-6923328


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole/005-5087586-6923328"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole/005-5087586-6923328');
/** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
$practitionerRole = $parser->parse((string) $response->getBody());
echo("Practitioner Role found: id=".$practitionerRole->getId()."\n");
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var practitionerRole = client.Read<PractitionerRole>("PractitionerRole/005-5087586-6923328");
Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value}");
{% endhighlight %}
</div>

</div>
<br />


#### <a id="43-header"></a>4.3) Recherche par rôle (role)

La recherche par le paramètre "role" permet de rechercher les PractitionerRole selon différents référentiels. Voici les différents référentiels disponibles : 

| Type                                        | Description                      | Système                                                                                               | Lien / Options                                                                                        |
|---------------------------------------------|----------------------------------|-------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| Profession                                  | Type d'organization              | http://interopsante.org/fhir/CodeSystem/fr-v2-3307                                                    | GEOGRAPHICAL-ENTITY ou LEGAL-ENTITY                                                                   |
| Catégorie professionnelle                   | JDV_J99-InseeNAFrav2Niveau5-RASS | https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle | https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle |
| Fonction TRE_R21-Fonction                   | TRE_R21-Fonction                 | https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction                                 | https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction                                 |
| Fonction TRE-R96-AutreFonctionSanitaire     | TRE-R96-AutreFonctionSanitaire   | https://mos.esante.gouv.fr/NOS/TRE_R96-AutreFonctionSanitaire/FHIR/TRE-R96-AutreFonctionSanitaire     | https://mos.esante.gouv.fr/NOS/TRE_R96-AutreFonctionSanitaire/FHIR/TRE-R96-AutreFonctionSanitaire     |
| Genre activité                              | JDV_J94-GenreActivite-RASS       | https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite                       | https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite                       |
| Mode d'exercice                             | JDV_J95-ModeExercice-RASS        | https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice                         | https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice                         |
| Metier Pharmacien Section Tableau CNOP      | TRE_R06-SectionTableauCNOP       | https://mos.esante.gouv.fr/NOS/TRE_R06-SectionTableauCNOP/FHIR/TRE-R06-SectionTableauCNOP             | https://mos.esante.gouv.fr/NOS/TRE_R06-SectionTableauCNOP/FHIR/TRE-R06-SectionTableauCNOP             |
| Metier Pharmacien Sous Section Tableau CNOP | TRE_G05-SousSectionTableauCNOP   | https://mos.esante.gouv.fr/NOS/TRE_G05-SousSectionTableauCNOP/FHIR/TRE-G05-SousSectionTableauCNOP     | https://mos.esante.gouv.fr/NOS/TRE_G05-SousSectionTableauCNOP/FHIR/TRE-G05-SousSectionTableauCNOP     |


Lorsque vous souhaitez rechercher sur un type particulier, utilisez la combinaison du système et du code souhaité :

`PractitionerRole?role=<system>%7C<code>`

**Quelques exemples :**

<div class="wysiwyg" markdown="1">
* `PractitionerRole?role=http://interopsante.org/fhir/CodeSystem/fr-v2-3307%7CLEGAL-ENTITY` Recherche par type d'organization LEGAL-ENTITY
* `PractitionerRole?role=https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle%7CC` Recherche par catégorie professionnelle TRE_R09-CategorieProfessionnelle avec le code C "Civil"
* `PractitionerRole?role=https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction%7CFON-02` Recherche par fonction TRE_R21-Fonction avec le code FON-02 "Associé dans une société d'exercice (SEL ou SCP)"
* `PractitionerRole?role=https://mos.esante.gouv.fr/NOS/TRE_R96-AutreFonctionSanitaire/FHIR/TRE-R96-AutreFonctionSanitaire%7CGENR03` Recherche par autres fonctions du domaine sanitaire TRE-R96-AutreFonctionSanitaire avec le code 400 "Aide-soignant"
* `PractitionerRole?role=https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite%7CGENR03` Recherche par genre d'activité JDV_J94-GenreActivite-RASS avec le code GENR03 "Remplacement dans une activité de soins"
* `PractitionerRole?role=https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice%7CS` Recherche par Mode d'exercice JDV_J95-ModeExercice-RASS avec le code S "Salarié"
* `PractitionerRole?role=https://mos.esante.gouv.fr/NOS/TRE_R06-SectionTableauCNOP/FHIR/TRE-R06-SectionTableauCNOP%7CS` Recherche par Section du tableau de l'Ordre des Pharmaciens TRE_R06-SectionTableauCNOP avec le code A "Pharmacien titulaire officine"
* `PractitionerRole?role=https://mos.esante.gouv.fr/NOS/TRE_G05-SousSectionTableauCNOP/FHIR/TRE-G05-SousSectionTableauCNOP%7CDA` Recherche par Sous-Section du tableau de l'Ordre des Pharmaciens TRE_G05-SousSectionTableauCNOP avec le code DA "Pharmacien adjoint"
</div>
<br />

##### <a id="431-header"></a>4.3.1) Recherche par profession et par catégorie professionnelle

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher tous les chirurgiens-dentistes (code profession= "40") en formation (code catégorie = "E").

**Requête :**

`GET [base]/PractitionerRole?role=40&role=E`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner Role found: id=005-480000-6510001 codes=https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction:FON-47|https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15- 
ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:E|https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite:GENR02|https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice:L
  Practitioner Role found: id=005-490000-6510000 codes=https://mos.esante.gouv.fr/NOS/TRE_R21-Fonction/FHIR/TRE-R21-Fonction:FON-47|https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15- 
ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:E|https://mos.esante.gouv.fr/NOS/TRE_R22-GenreActivite/FHIR/TRE-R22-GenreActivite:GENR02|https://mos.esante.gouv.fr/NOS/TRE_R23-ModeExercice/FHIR/TRE-R23-ModeExercice:



```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?role=40&role=E"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole?role=40&role=E');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    $codes = '';
    $index = 0;
    foreach ($practitionerRole->getCode() as $cc){
        /** @var $cc \DCarbone\PHPFHIRGenerated\R4\FHIRElement\FHIRCodeableConcept */
        foreach ($cc->getCoding() as $c) {
            if($index++>0){
                $codes .= '|';
            }
            $codes .= $c->getSystem().":".$c->getCode();
        }
    }
    echo("Practitioner Role found: id=".$practitionerRole->getId()." codes=".$codes."\n");
}

{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("role=40").Add("role","E")
  .LimitTo(50);

var bundle = client.Search<PractitionerRole>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitionerRole = be.Resource as PractitionerRole;

    var roleCodes = "";
    foreach(var c in practitionerRole.Code)
    {
        var codings = "";
        foreach(var code in c.Coding)
        {
            codings = codings + code.System + ":" + code.Code + "|";
        }
        roleCodes = roleCodes + " - " + codings;
    }

    Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={roleCodes}");
}
{% endhighlight %}
</div>

</div>
<br />


#### <a id="44-header"></a>4.4) Recherche par spécialité (specialty)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher tous les chirurgiens-dentistes (code profession= "40") ayant une spécialité ordinale "orthopédie dento-faciale" (code spécialité = "SCD01").

**Requête :**

`GET [base]/PractitionerRole?role=40&specialty=SCD01`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner Role found: id=005-400000 codes=https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:C|urn:oid:1.2.250.1.213.2.28:SCD01
  Practitioner Role found: id=005-390000 codes=https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:C|urn:oid:1.2.250.1.213.2.28:SCD01
  Practitioner Role found: id=005-380000 codes=https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante:40|https://mos.esante.gouv.fr/NOS/TRE_R09-CategorieProfessionnelle/FHIR/TRE-R09-CategorieProfessionnelle:C|urn:oid:1.2.250.1.213.2.28:SCD01



```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?role=40&specialty=SCD01"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole?role=40&specialty=SCD01');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    $codes = '';
    $index = 0;
    foreach ($practitionerRole->getCode() as $cc){
        /** @var $cc \DCarbone\PHPFHIRGenerated\R4\FHIRElement\FHIRCodeableConcept */
        foreach ($cc->getCoding() as $c) {
            if($index++>0){
                $codes .= '|';
            }
            $codes .= $c->getSystem().":".$c->getCode();
        }
    }
    echo("Practitioner Role found: id=".$practitionerRole->getId()." codes=".$codes."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();

var q = new SearchParams()
  .Where("role=40").Add("specialty", "SCD01")
  .LimitTo(50);

var bundle = client.Search<PractitionerRole>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitionerRole = be.Resource as PractitionerRole;

    var roleCodes = "";
    // concat roles
    foreach(var c in practitionerRole.Code)
    {
        var codings = "";
        foreach(var code in c.Coding)
        {
            codings = codings + code.System + ":" + code.Code + "|";
        }
        roleCodes = roleCodes + " - " + codings;
    }

    // concat specialty
    foreach (var c in practitionerRole.Specialty)
    {
        var codings = "";
        foreach (var code in c.Coding)
        {
            codings = codings + code.System + ":" + code.Code + "|";
        }
        roleCodes = roleCodes + " - " + codings;
    }

    Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={roleCodes}");
}
{% endhighlight %}
</div>

</div>
<br />


#### <a id="45-header"></a>4.5) Recherche par type de carte (type-smartcard)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher toutes les cartes de type CPS.

**Requête :**

`GET [base]/PractitionerRole?type-smartcard=CPS`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner Role found: id=005-54002-100000
  Practitioner Role found: id=005-54001
  Practitioner Role found: id=005-54000-100000


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?type-smartcard=CPS"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole?type-smartcard=CPS');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
  .Where("type-smartcard=CPS")
  .LimitTo(50);
var bundle = client.Search<PractitionerRole>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitionerRole = be.Resource as PractitionerRole;
    Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value}");
}
{% endhighlight %}
</div>

</div>
<br />


#### <a id="46-header"></a>4.6) Recherche par professionnel (practitioner)

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher toutes les situations d'exercice et exercices professionnels d'un PS en partant de son identifiant technique ( = "003-138020" dans l'exemple ).

**Requête :**

`GET [base]/PractitionerRole?practitioner=003-138020`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner Role found: id=005-109896 practitioner=Practitioner/003-138020


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?practitioner=003-138020"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole?practitioner=003-138020');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()." practitioner=". $practitionerRole->getPractitioner()->getReference() ."\n");
}
{% endhighlight %}
</div>
<div class="tab-content" data-name="C#">
{% highlight csharp %}
// create the client:
var client = FhirTestUtils.CreateClient();
var q = new SearchParams()
  .Where("practitioner=003-138020")
  .LimitTo(50);
var bundle = client.Search<PractitionerRole>(q);
foreach (var be in bundle.Entry)
{
    // print ids:
    var practitionerRole = be.Resource as PractitionerRole;
    Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} practitioner={practitionerRole.Practitioner.Reference}");
}
{% endhighlight %}
</div>

</div>
<br />


#### <a id="47-header"></a>4.7) Recherche par statut

**Récit utilisateur :** En tant que client de l'API, je souhaite rechercher toutes les ressources actives.

**Requête :**

`GET [base]/PractitionerRole??active=true`

**Réponse (simplifiée) :** 

```xml
HTTP 200 OK
  resourceType: Bundle
  type: searchset
  Practitioner Role found: id=prr-prarole-946 active=true
  Practitioner Role found: id=prr-prarole-256 active=true
  Practitioner Role found: id=prr-prarole-899 active=true


```
<br />

**Exemples de code :**

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?active=true"
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
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole?active=true');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()." active=". $practitionerRole->getActive() ."\n");
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

