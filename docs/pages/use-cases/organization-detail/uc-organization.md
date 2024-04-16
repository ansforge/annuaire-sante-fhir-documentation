---
layout: default
title: "Fiche de structure"
subTitle: Cas d'utilisation
---
<p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Cette partie de la spécification est en cours de construction.
</p>

Dans ce cas d'utilisation, nous allons aborder la récupération d'éléments à la demande. Nous allons faire une application annuaire qui permet de trouver des structures (organization). 



## Qui est concerné ?

<div class="wysiwyg" markdown="1">
* Vous avez une application existante et vous souhaitez sur certains composants ajouter de la données provenant de notre API,
* Vous avez un système d'information et vous souhaitez enrichir certaines données bien précises,
* Vous souhaitez construire une application sans stocker la donnée dans votre système.
</div>



&nbsp;

## Ce dont vous aurez besoin

<div class="wysiwyg" markdown="1">
* Une API Key d'accès à l'API que vous pouvez récupérer en ligne à cette [adresse](https://portal.api.esante.gouv.fr/catalog/api/962f412b-e08e-4ee7-af41-2be08eeee7f6)
* Java 11+
* Un IDE (IntelliJ, Eclipse...)
* Maven 3+
</div>

&nbsp;


## Initialisation du projet

Voir la section [Démarrage/Java]({{ '/pages/documentation/starters/java-starter.html' | relative_url }})


#### Trouver une Organization avec son numéro FINESS

Pour l'exemple, nous allons rechercher une organization ayant le numéro FINESS : 010780914

Pour ce faire, nous allons effectuer une recherche sur la ressource Organization par **identifier**. Cela va retourner un Bundle qui contiendra le résultat de recherche. 

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" \
  "{{site.ans.api_url}}/fhir/v1/Organization?identifier=010780914&_pretty=true&_format=json"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// créez le client de l'api FHIR:
var ctx = FhirContext.forR4();
var client = ctx.newRestfulGenericClient("https://{{site.ans.api_url}}/fhir/v1/");
// ajoutez ensuite la clé de sécurité comme dans le starter java

var organizationBundle = (Bundle) client.search().forResource(Organization.class).where(Organization.IDENTIFIER.exactly().codes("010780914")).execute();
if(!organizationBundle.hasEntry()){
logger.info("No results found with this finess number");
return;
}

// get the organization:
var organization = (Organization) organizationBundle.getEntry().get(0).getResource();

{% endhighlight %}
</div>
</div>





&nbsp;

L'organization retournée contiendra différentes informations comme la raison sociale, l'adresse postale, le secteur d'activité, le type de la strcuture (juridique ou géographique), ...

```xml
organization 010780914:
    Id technique (champs id): 001-01-147
	Identifiants:
	Numéro urn:oid:1.2.250.1.71.4.2.2 : 1010780914
	Numéro https://finess.esante.gouv.fr : 010780914

	Raison sociale:
	name : "EHPAD RESIDENCE D'URFE BAGE LE CHATEL"
  
	Secteur d'activité:
	https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite : SA17
	...


```

&nbsp;

#### Récupérer les PractitionerRole rattachés à l'Organization précédemment trouvée

Pour aller plus loin, nous allons chercher les situations d'exercice rattachées à cette organization pour obtenir plus d'informations. 

Nous faisons donc une requête sur la ressource PractitionerRole en précisant de trouver les PractitionerRole qui sont liés à cette Organization : 


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" \
  "{{site.ans.api_url}}/fhir/v1/PractitionerRole?organization=001-01-147&_pretty=true&_format=json"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var practitionerRoleBundle = (Bundle) client.search().forResource(PractitionerRole.class).where(PractitionerRole.ORGANIZATION.hasId(organization.getIdElement().getIdPart())).execute();
var practitionerRoles = practitionerRoleBundle.getEntry().stream().map(pre -> pre.getResource()).map(PractitionerRole.class::cast).collect(Collectors.toList());
{% endhighlight %}
</div>
</div>


Le point important ici est de mettre la clause where sur le paramètre **organization** en spécifiant l'id de l'Organization précédemment trouvée. 

Cela va retourner un résultat de recherche avec tous les PractitionerRole liés à cette Organization : 

```xml
Exercices/Situations d'exercice:
    PractitionerRole 005-4902894-6549113:
    Noms :[COLETTE] ROUSSEAU
    Fonction : FON-33 
    ...


```

&nbsp;


#### Lier les PractitionerRole avec les Practitioner

Dans cette étape, nous allons récupérer les Practitioner rattachés aux PractitionerRole. 

Pour ce faire, nous modifions la requête de l'étape précédente pour demander de récupérer les PractitionerRole ainsi que leurs Practitioner attachés dans une même requête. 
Cela se fait par le biais du paramètre **_include** :


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" \
  "{{site.ans.api_url}}/fhir/v1/PractitionerRole?organization=001-01-147&_include=PractitionerRole:practitioner&_pretty=true&_format=json"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var practitionerRoleBundle = (Bundle) client.search().forResource(PractitionerRole.class).where(PractitionerRole.ORGANIZATION.hasId(organization.getIdElement().getIdPart())).include(new Include("PractitionerRole:practitioner")).execute();
var practitionerRolesAndPractitioners = practitionerRoleBundle.getEntry().stream().map(pre -> pre.getResource()).collect(Collectors.toList());
var practitionerRoles = practitionerRolesAndPractitioners.stream().filter(pre -> pre instanceof PractitionerRole).map(PractitionerRole.class::cast).collect(Collectors.toList());
var practitioners = practitionerRolesAndPractitioners.stream().filter(pre -> pre instanceof Practitioner).map(Practitioner.class::cast).collect(Collectors.toList());
{% endhighlight %}
</div>
</div>

Cette requête va vous retourner un Bundle contenant à la fois les PractitionerRole et les Practitioner. Votre programme devra lui-même lier les PractitionerRole aux Practitioner grâce aux champs **PractitionerRole.practitioner** et **Practitioner.id**.
Dans l'exemple ci-dessous nous voyons que le PractitionerRole est lié à la ressource Practitioner ayant l'id  `"practitioner": {"reference": "Practitioner/003-4745453"}` et ce practitioner est une autre entrée dans le Bundle  ayant pour champs 'id' 003-4745453.

```json
{003-4745453
  "resourceType": "Bundle",
  "total": 11,
  "entry": [
    {
      "fullUrl": "{{site.ans.api_url}}/fhir/v1/PractitionerRole/005-4902894-6549113",
      "resource": {
        "resourceType": "PractitionerRole",
        "id": "005-4902894-6549113",
        "practitioner": {
          "reference": "Practitioner/003-4745453"
        },
        ...
      }
    },
    {
      "fullUrl": "{{site.ans.api_url}}/fhir/v1/Practitioner/003-4745453",
      "resource": {
        "resourceType": "Practitioner",
        "id": "003-4745453",
        ...
      }
    }
    ...
  
}
```
&nbsp;


## Aller plus loin

#### Récupération par lots

Vous pouvez tout à fait imaginer récupérer des Organization par lot. Cela nécessitera cependant à minima 2 requêtes.

La première requête va vous premettre de trouver les Organization, la seconde vous permettra de chercher des informations complémentaires dans des PractitionerRole ou Practitioner. 

Imaginons que vous cherchiez les fiches de 2 structures avec pour leurs identifiants FINESS : 060016219 et 030782866

Dans ce cas la première requête sera : 

```json 
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" \
  "{{site.ans.api_url}}/fhir/v1/Organization?identifier=060016219,030782866&_pretty=true&_format=json"


```
<br />

Cette requête permet de récupérer toutes les Organizations ayant un identifier à : 060016219 OU 030782866.

La liste retournée contiendra plusieurs Organization si les identifiants FINESS existent. 

Ensuite, vous pourrez là encore effectuer une unique requête pour aller chercher les ressources associées: 


```json 
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" \
  "{{site.ans.api_url}}/fhir/v1/PractitionerRole?organization=001-01-1102727,001-01-1267408&_pretty=true&_format=json"


```
<br />

#### Trouver l’ensemble des EG d’un EJ

Cliquez [ici](../../../pages/documentation/advanced/link.html#link-head-4) pour accéder à l'exemple.
<br />





