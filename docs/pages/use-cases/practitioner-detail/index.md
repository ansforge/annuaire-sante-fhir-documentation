---
layout: default
title: "Fiche du professionel de santé"
subTitle: Cas d'utilisation
---

Dans ce cas d'utilisation, nous allons aborder la récupération d'éléments à la demande. Nous allons faire une application annuaire qui permet de trouver des professionnels de santé. 



### Qui est concerné ?

<div class="wysiwyg" markdown="1">
* Vous avez une application existante et vous souhaitez sur certains composants ajouter des données provenant de notre API
* Vous avez un système d'information et vous souhaitez enrichir certaines données bien précises
* Vous souhaitez construire une application sans stocker la donnée dans votre système
</div>



&nbsp;

### Ce dont vous aurez besoin

<div class="wysiwyg" markdown="1">
* Une API Key d'accès à l'API que vous pouvez récupérer en ligne à cette [adresse](https://portal.api.esante.gouv.fr/catalog/api/962f412b-e08e-4ee7-af41-2be08eeee7f6)
* Java 11+
* Un IDE (IntelliJ, Eclipse...)
* Maven 3+
</div>

&nbsp;


#### Initialisation du projet

Voir la section [Démarrage/Java]({{ '/pages/documentation/starters/java-starter.html' | relative_url }})


#### Trouver un professionnel de santé avec son numéro RPPS

Pour l'exemple, nous allons rechercher le PS ayant le numéro RPPS : 10000001111

Effectuer une recherche FHIR sur la ressource Practitioner avec le paramètre **identifier**. Cela va retourner un Bundle qui contiendra le résultat de recherche. Comme nous cherchons par numéro RPPS, s'il y a un résultat, il sera unique. 

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?identifier=10000001111&_pretty=true&_format=json"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// créez le client de l'api FHIR:
var ctx = FhirContext.forR4();
var client = ctx.newRestfulGenericClient("https://{{site.ans.api_url}}/fhir/v1/");
// ajoutez ensuite la clé de sécurité comme dans le starter java

var practitionerBundle = (Bundle) client.search().forResource(Practitioner.class).where(Practitioner.IDENTIFIER.exactly().codes("10000001111")).execute();
if(!practitionerBundle.hasEntry()){
logger.info("No results found with this rpps number");
return;
}

// get the practitioner:
var practitioner = (Practitioner) practitionerBundle.getEntry().get(0).getResource();

{% endhighlight %}
</div>
</div>





&nbsp;

Le Practitioner retourné contiendra différentes informations comme la civilité, tout autres identifiant connu, les diplômes, ...

```xml
Professionnel 10000001111:
    Id technique (champs id): 003-131111
	Identifier:
	Numéro urn:oid:1.2.250.1.71.4.2.1 : 810000001111
	Numéro http://rpps.fr : 10000001111

	Name:
	Civilité : [M]
	...


```

&nbsp;

#### Récupérer les PractitionerRole du Practitioner précédemment trouvé

Pour aller plus loin, nous allons chercher les exercices professionnels de ce practitioner pour obtenir plus d'informations. 

Nous faisons donc une requête sur la ressource PractitionerRole en précisant de trouver les PractitionerRole qui sont liés à ce Practitioner : 


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?practitioner=003-131111&_pretty=true&_format=json"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var practitionerRoleBundle = (Bundle) client.search().forResource(PractitionerRole.class).where(PractitionerRole.PRACTITIONER.hasId(practitioner.getIdElement().getIdPart())).execute();
var practitionerRoles = practitionerRoleBundle.getEntry().stream().map(pre -> pre.getResource()).map(PractitionerRole.class::cast).collect(Collectors.toList());
{% endhighlight %}
</div>
</div>


Le point important ici est de mettre la clause where sur le paramètre **practitioner** en spécifiant l'id du Practitioner précédemment trouvé. 

Cela va retourner un résultat de recherche avec tous les PractitionerRole liés à ce Practitioner : 

```xml
Exercices:
    Exercice 005-71111:
    Noms/Prénoms d'exercice :[JEAN] MARTIN
    Spécialités : SM26 S 
    ...


```

&nbsp;


#### Lier les practitionerRole avec Organization

Dans cette étape, nous allons récupérer les Organization rattachées aux PractitionerRole. 

Pour ce faire, nous modifions la requête de l'étape précédente pour demander de récupérer les PractitionerRole ainsi que leurs Organization rattachées dans une même requête. 
Cela se fait par le biais du paramètre **_include** :


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?practitioner=003-131111&_include=PractitionerRole:organization&_pretty=true&_format=json"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var practitionerRoleBundle = (Bundle) client.search().forResource(PractitionerRole.class).where(PractitionerRole.PRACTITIONER.hasId(practitioner.getIdElement().getIdPart())).include(new Include("PractitionerRole:organization")).execute();
var practitionerRolesAndOrganizations = practitionerRoleBundle.getEntry().stream().map(pre -> pre.getResource()).collect(Collectors.toList());
var practitionerRoles = practitionerRolesAndOrganizations.stream().filter(pre -> pre instanceof PractitionerRole).map(PractitionerRole.class::cast).collect(Collectors.toList());
var organizations = practitionerRolesAndOrganizations.stream().filter(pre -> pre instanceof Organization).map(Organization.class::cast).collect(Collectors.toList());
{% endhighlight %}
</div>
</div>

Cette requête va vous retourner un Bundle contenant à la fois les PractitionerRole et les Organization. Votre programme devra lui-même lier les PractitionerRole aux Organization grâce aux champs **PractitionerRole.organization** et **Organization.id**.
Dans l'exemple ci-dessous, nous voyons que le PractitionerRole est lié à la ressource Organization ayant l'id  `"organization": {"reference": "Organization/org-6922"}` et cette Organization est une autre entrée dans le Bundle 
qui a pour champs 'id' org-6922.

```json
{
  "resourceType": "Bundle",
  "total": 1,
  "entry": [
    {
      "fullUrl": "{{site.ans.api_url}}/fhir/v1/PractitionerRole/prarole-6922",
      "resource": {
        "resourceType": "PractitionerRole",
        "id": "prarole-6922",
        "organization": {
          "reference": "Organization/org-6922"
        },
        ...
      }
    },
    {
      "fullUrl": "{{site.ans.api_url}}/fhir/v1/Organization/org-6922",
      "resource": {
        "resourceType": "Organization",
        "id": "org-6922",
        ...
      }
    }
    ...
  
}


```
&nbsp;

### Aller plus loin

#### Récupération par lots

Vous pouvez tout à fait imaginer récupérer des Practitioner complets par lot. Cela nécessitera cependant à minima 2 requêtes.

La première requête va vous premettre de trouver les Practitioner, la seconde vous permettra de chercher des informations complémentaires dans des PractitionerRole ou Organization. 

Imaginons que vous cherchiez les fiches de 2 professionnels avec pour identifiants RPPS : 10000001111 et 10000001112.

Dans ce cas la première requête sera : 

```bash 
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/Practitioner?identifier=10000001111,10000001112&_pretty=true&_format=json"
```

Cette requête permet de demander tous les Practitioner ayant un identifier à 10000001111 ou 10000001112.

La liste retournée contiendra plusieurs Practitioner si les identifiants RPPS existent. 

Ensuite, vous pourrez là encore effectuer une unique requête pour aller chercher les ressources associées: 


```bash 
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/v1/PractitionerRole?practitioner=003-131111,003-131112&_pretty=true&_format=json"
```

#### Trouver le nom, le prénom, la civilité (ou le genre), l'adresse postale et les adresses MSS d'un Practitioner en partant de son identifiant ADELI/RPPS
Cliquez [ici](../../../pages/documentation/advanced/link.html#link-head-3) pour accéder à l'exemple.
<b />

#### Trouver l'identifiant ADELI/RPPS, les BAL MSS, les activités ainsi que les structures d'exercice d'un Practitien en partant de son nom et son prénom d'exercice
Cliquez [ici](../../../pages/documentation/advanced/link.html#link-head-5) pour accéder à l'exemple.
<br />





