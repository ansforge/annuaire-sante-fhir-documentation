---
layout: default
title: "Synchronisation complète des ressources"
subTitle: Cas d'utilisation
---

Au travers de ce cas d'utilisation, nous allons montrer comment synchroniser un système local avec les données de l'**Annuaire Santé**. 
Les données que nous allons chercher à récupérer sont : 

<div class="wysiwyg" markdown="1">
* Practitioner (Professionels de santé)
* PractitionerRole (Exercices professionels et situations d'exercice)
* Organization (Lieu d'exercice)
</div>

&nbsp;

![Schema](img/full-import-schema-01.png)

## Qui est concerné ?

<div class="wysiwyg" markdown="1">
* Vous avez un annuaire local que vous souhaitez actualiser périodiquement avec les données l'**Annuaire Santé**.
* Vous souhaitez avoir une image complète de l'**Annuaire Santé** 
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


## Etapes

<div class="wysiwyg" markdown="1">
* Nous allons récupérer toutes les ressources "PractitionerRole" de l'**Annuaire Santé** selon nos critères
* Nous allons transformer et stocker les éléments
* Nous allons modifier notre requête pour aller chercher des ressources liées à la première requête
* Enfin, nous verrons comment mettre à jour les données de notre annuaire
</div>

&nbsp;


#### Initialisation du projet 

Voir la section [Démarrage/Java]({{ '/pages/documentation/starters/java-starter.html' | relative_url }})

&nbsp;

#### Récupération des ressources "PractitionerRole"

Pour l'exemple, nous allons chercher uniquement les ressources "PractitionerRole" ayant une spécialité à "SM02" (correspond à Anesthésie-réanimation)

Créer un client FHIR avec la librairie Hapi en utilisant l'api Hapi:

```java
var ctx = FhirContext.forR4();
var client = ctx.newRestfulGenericClient("https://{{site.ans.api_url}}/fhir/v1/");


```
<br />

La requête de recherche que nous souhaitons effectuer en FHIR est du type: https://server_url/fhir/v1/PractitionerRole?specialty=SM02.

Vous pouvez par exemple la lancer avec un client curl : `curl -H "Accept: application/json" -H "ESANTE-API-KEY: XXXX-XXXX-XXXX"  https://server_url/fhir/v1/PractitionerRole?specialty=SM02`

En java+Hapi, cela va se traduire comme suit : 

```java
var client = createClient();
var fhirBundle = (Bundle) client.search().forResource(PractitionerRole.class)
        // we want to filter on the specialty:
        .where(PractitionerRole.SPECIALTY.exactly().codes("SM02"))
        .count(10)
        .execute();

        

```
<br />

Si cette requête est exécutée, le code retourné sera: 

```json
{
  "resourceType": "Bundle",
  "type": "searchset",
  "total": 1401,
  "link": [
    {
      "relation": "self",
      "url": "https://server-url/fhir/v1/PractitionerRole?_count=10&specialty=SM02"
    },
    {
      "relation": "next",
      "url": "https://server-url/fhir/v1?_getpages=ac89cc0f-5b0e-4fc0-abf3-fe00c24d20da&_pageId=8YIa-PgpJu7ZXeK8sBrbwSX-0wii42c5oOQjR_Uo4rUfGXE06RkRk3tiT4a1NewhvGzkYfMWevxbdI5vPBW60Cn7Wi6Vxlrty1JhxlREp9dqVC0WXAecWbGNSeoQBRoUzmAFPYo09FNltvsM786MhiOgUg7AVObnrWLZVfYwDNjhyQ-Wh6NNRp5PuQven8eyFVYbQyuXZDJ7FHsaRIqzGQxfYrZKY-_xwQNHwr7UZ-Mr4KNtg7jDgGVlf6QKxyg0VYsl0cplQBgksoFyQjzEzZ31CrmCro-qzCq2LIyywLRk1AqvscQAyfazpy_griE_3n-ruRT2Ge1riTR59V4nQGHPqpS_eg%3D%3D&_bundletype=searchset"
    }
  ],
  "entry": [
    {
      "fullUrl": "{{site.ans.api_url}}/fhir/v1/PractitionerRole/prarole-1089",
      "resource": {
        "resourceType": "PractitionerRole",
        "id": "prarole-1089"
        ...
      }
    },
    ...
  ]
}



```
<br />

Les PractitionerRole que nous recherchons se trouvent dans le champs "entry" de la réponse. On notera également le champs "total" avec le compte du nombre de ressources FHIR.

Si le nombre de résultat est supérieur au total demandé par le paramètre "_count", un lien va apparaitre dans la réponse FHIR au niveau du champs "link". Un appel à cette url nous donnera la page suivante. 

En java+Hapi, cela se fait comme suit : 

```java
do {
    var bundleContent = fhirBundle.getEntry();
    // store the bundleContent here
    deviceBundle = client.loadPage().byUrl(fhirBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
}while (fhirBundle.getLink("next") != null);


```

Le code ci-dessus permet d'aller chercher la page suivante d'un bundle et de répéter l'opération jusqu'à la fin de la pagination. 


Voici le code complet qui permet de récupérer tous les PractitionerRole de la spécialité recherchée: 

```java
var client = createClient();
var fhirBundle = (Bundle) client.search().forResource(PractitionerRole.class)
        // we want to filter on the specialty:
        .where(PractitionerRole.SPECIALTY.exactly().codes("SM02"))
        .count(10)
        .execute();

do {
    var bundleContent = fhirBundle.getEntry();
    // Store Practitioner roles:
    storageService.storePractitionerRole(bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).map(PractitionerRole.class::cast).collect(Collectors.toList()));
    // load the next page:
    fhirBundle = client.loadPage().byUrl(fhirBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
}while (fhirBundle.getLink("next") != null);


```

&nbsp;

#### Récupération des éléments liés : "Organization" et "Practitioner"

Désormais, nous allons modifier le code afin de récupérer également les Practitioner associés ainsi que les Organization. 

Pour ce faire, le standard FHIR propose le paramètre _include qui va inclure dans les résultats de réponse des éléments qui sont référencés dans la recherche principale. 



La requête de recherche que nous souhaitons effectuer en FHIR est du type: https://server.ans.fr/fhir/PractitionerRole?specialty=SM02&_include=PractionerRole:organization&_include=PractionerRole:practitioner.

Vous pouvez par exemple la lancer avec un client curl : `curl -H "Accept: application/json" -H "ESANTE-API-KEY: XXXX-XXXX-XXXX"  https://server.ans.fr/fhir/PractitionerRole?specialty=SM02&_include=PractionerRole:organization&_include=PractionerRole:practitioner`


Si cette requête fonctionne, alors la réponse sera un bundle de type fhir qui contiendra à la fois les PractitionerRole comme précédement 
ainsi que les Practitioner et les Organization liés aux Practitioner de la précédente requête : 

```json
{
  "resourceType": "Bundle",
  "total": 1401,
  "entry": [
    {
      "fullUrl": "{{site.ans.api_url}}/fhir/v1/PractitionerRole/prarole-6922",
      "resource": {
        "resourceType": "PractitionerRole",
        "id": "prarole-6922",
        "practitioner": {
          "reference": "Practitioner/pra-6922"
        },
        "organization": {
          "reference": "Organization/org-6922"
        },
        ...
      }
    },
    {
      "fullUrl": "{{site.ans.api_url}}/fhir/v1/Practitioner/pra-6922",
      "resource": {
        "resourceType": "Practitioner",
        "id": "pra-6922",
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
  ]
}


```

On remarquera que dans le champs entry, il y a désormais les PractitionerRole mais également les Organization et les Practitioner associés. 
Lors de la synchronisation, il conviendra donc de sauvegarder chacune des entrés en fonction de leurs types.


En java+Hapi, cela va se traduire par:

```java
var client = createClient();
var fhirBundle = (Bundle) client.search().forResource(PractitionerRole.class)
        // we want to filter on the specialty:
        .where(PractitionerRole.SPECIALTY.exactly().codes("SM02"))
        .include(PractitionerRole.INCLUDE_PRACTITIONER.asNonRecursive())
        .include(PractitionerRole.INCLUDE_ORGANIZATION.asNonRecursive())
        .count(10)
        .execute();
do {
    var bundleContent = fhirBundle.getEntry();
    // Store all resources:
    var practitionerRoles = bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "PractitionerRole".equals(e.fhirType())).map(PractitionerRole.class::cast).collect(Collectors.toList());
    var practitioners = bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Practitioner".equals(e.fhirType())).map(Practitioner.class::cast).collect(Collectors.toList());
    var organizations = bundleContent.stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Organization".equals(e.fhirType())).map(Organization.class::cast).collect(Collectors.toList());

    storageService.storePractitionerRole(practitionerRoles);
    storageService.storePractitioner(practitioners);
    storageService.storeOrganization(organizations);

    // load the next page:
    fhirBundle = client.loadPage().byUrl(fhirBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
}while (fhirBundle.getLink("next") != null);
```

&nbsp;

Note| Avec cette technique, vous pouvez recevoir un élément Organization plusieurs fois au fil des pages. En effet, vous recevrez la liste  des Organization référéncés par les éléments de la page courante. 


## Complément

#### L'exemple du filtre par département 

Parfois, vous souhaiterez faire des filtres sur des données qui ne sont pas présentes dans la ressource PractitionerRole. 

Dans ce cas, il ne sera pas possible de faire le cas d'utilisation.

Imaginons par exemple que vous souhaitez faire la même recherche mais uniquement sur les Practitioner qui ont au moins un PractitionerRole rattaché à une Organization dans le département 28. 

Dans ce cas, vous allez devoir faire la requête en plusieurs étapes.

<div class="wysiwyg" markdown="1">
1. Premièrement, il faut faire la requête sur la ressource qui contient le paramètre souhaité. Dans notre cas, c'est Organization. Vous pouvez avec cette requête récupérer les PractitionerRole associés grâce à la fonction "_revinclude".
2. Ensuite, afin de trouver les Practitioner associés à vos PractitionerRoles, vous avez plusiseurs possibilités. Dans cet exemple, nous allons parcourir nos PractitionerRoles et faire une requête par référence.
</div>

&nbsp;












