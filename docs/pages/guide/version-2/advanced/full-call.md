---
layout: menu-version-2
title: "Appel FULL - Synchronisation complète des ressources"
subTitle: Cas d'utilisation
---


## <a id="one-header"></a>1) Appel FULL

Nous allons montrer comment réaliser un appel FULL pour synchroniser un système local avec les données de l'**Annuaire Santé**. 

&nbsp;

### Récupération d'une ressources "Practitioner"

En tant que client de l'API, je souhaite récupérer l'ensemble des professionnels intervenant dans le système de santé. 

**Exemples de requêtes :**

```sh
GET [base]/Practitioner
# récupère l'ensemble des exercices professionnels (incluant les actifs et les inactifs)

GET [base]/Practitioner?_revinclude=PractitionerRole:practitioner 
# récupère l'ensemble des exercices professionnels ainsi que les activités liées (Practitioner + PractitionerRole actifs et inactifs)

```
Si ce type d'appel est lancé, le code retourné contiendra dans la première pagination les 50 premiers éléments: 

```json
{
    "resourceType": "Bundle",
    "type": "searchset",
    "id": "387c9b7f-ee25-4735-a699-4e9397b98044",
    "total": 1825942,
    "entry": [
        {
            "fullUrl": "https://gateway.api.esante.gouv.fr/fhir/v2/Practitioner/003-3014698-3057235",
            "resource": {
                "resourceType": "Practitioner",
                "id": "003-3014698-3057235",
                "meta": {
                    "versionId": "1",
                    "lastUpdated": "2025-04-28T18:19:26.335+02:00",
                    "source": "https://annuaire.sante.fr",
                    "profile": [
                        "https://hl7.fr/ig/fhir/core/StructureDefinition/fr-core-practitioner",
                        "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-dp-practitioner"
                    ]
                },
                "language": "fr",
                "extension": [
                  ...
```
A la fin la réponse JSON, un lien "next" permet de consulter la prochaine pagination. Il suffit de récupérer l'url situé dans l'attribut link 

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












