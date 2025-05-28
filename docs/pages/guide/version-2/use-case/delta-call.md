---
layout: menu-version-2
title: "Appels Delta - Mise à jour des données"
subTitle: Cas d'utilisation
---

### Introduction

On peut distinguer trois types d'appels disponibles dans l'API FHIR Annuaire Santé : 

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3>Appels Full</h3>
        <hr aria-hidden="true">
        <div>
            <ul>
                <li>Récupérer l’ensemble des données en libre accès provenant de l’Annuaire Santé</li>
            </ul>
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Appels Delta</h3>
        <hr aria-hidden="true">
        <div>
            <ul>
                <li>Faciliter la mise à jour de la base de données client via des rafraichissements deltas depuis une date souhaitée.</li>
            </ul>
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Appels unitaires</h3>
        <hr aria-hidden="true">
        <div>
            <ul>
                <li>Obtenir les informations détaillées d’un professionnel ou d’une structure.</li>
                <li>Possibilité de récupérer les données selon des critères spécifiques choisis par l’utilisateur (par profession, par département, etc.)</li>
            </ul>
        </div>
    </div>
</div>


### Appels Delta - Mise à jour des données

Nous allons montrer comment réaliser un appel delta d'une ressource pour réaliser la mise à jour des données de l'Annuaire Santé.

Prenons l'exemple d'un client de l'API qui souhait mettre à jour l'ensemble des professionnels actifs et dont la profession est Médecin. Il devra interroger la ressource Practitioner.

```sh
GET [base]/Practitioner?qualification-code=10&_lastUpdated=ge2025-05-27
# récupère l'ensemble des professionnels dont la profession est médecin qui ont été mis à jour entre le 27 mai 2025 et aujourd'hui.
```

Cet appel delta vous permettra de remonter l'ensemble des professions qui ont été mis à jour entre la date définie et aujourd'hui. Vous pourrez ainsi constater des ressources qui vont voir des changements de statut "active = false"

Note| Les appels delta ne peuvent pas excéder plus de 30 jours. Le serveur ne garde pas les mises à jour réalisées au-delà de 30 jours.

Si ce type d'appel est lancé, l'appel delta remontera près de 9903 entrées qui ont été mises à jour depuis le 27 mai 2025. Le code retourné contiendra dans la première pagination les 50 premiers éléments :

```json
{
    "resourceType": "Bundle",
    "type": "searchset",
    "id": "05978e52-41ca-4a10-a204-ebdaaf0e2230",
    "total": 9903,
    "entry": [
        {
            "fullUrl": "https://gateway.api.esante.gouv.fr/fhir/v2/Practitioner/003-3422245-5139408",
            "resource": {
                "resourceType": "Practitioner",
                "id": "003-3422245-5139408",
                "meta": {
                    "versionId": 2,
                    "lastUpdated": "2025-05-27T10:16:30.021+02:00",
                    "source": "https://annuaire.sante.fr",
                    "profile": [
                        "https://hl7.fr/ig/fhir/core/StructureDefinition/fr-core-practitioner",
                        "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-dp-practitioner"
                    ]
                },
                "language": "fr",
                "extension": [
                    {
```

A la fin la réponse JSON, un lien "next" permet de consulter la prochaine pagination. Il suffit de préparer un script qui va permettre de récupérer l'url situé dans l'attribut link et d'appeler jusqu'à la dernière pagination pour récupérer l'ensemble de vos deltas.

<br />

```json
 "link": [
        {
            "relation": "next",
            "url": "https://gateway.api.esante.gouv.fr/fhir/v2/_page?id=u0AmXEZS9W7Bbdpqas6XBPLbNGQlIioFziyluVFRcw6zn5FNdpclx0zQDcJYMMwqFdh_sgoWYtG_iHEbgDKx_iyXjemetsvolontairementquelquechosedefauxG4nJWrE02uwuSQfBi0thMLxvb4mvtlpBvUuVF49yTCLmxZXbZy3u4Zchy-Pt3KnI0A2v-u-Aw8wXDFU9blcx1J51QuXCIBX_jSnw5QfFEjhhOQ8t0WJVdAuace64TVjP2y6g0Ed8BoZrqWpDpb-2OFb_4q7yPkN2Oib8U3tJ1rWe719OBsfnyOI_Y4dTCW5nJxOG-nuCCObru_JJZKnFSs2cJEgBk8pNUKb-7vuv9W8haT0bcHA-X7U2RIE1QNydAON7M9eF1vGslrJtqS1J1RFLXmY3i7WWWdxw2bC_QRP2DEV_p507gguZOzSqxSe73RSp7v2FG_hBGsxyt8vkHwVIt1cyONl_qSVI06SfqOb9YOHKnt_HCU_6lH8OzTum6oabtlGzdok7OuHJ2icZYAkQ_iIc4vLHaHyonB6uqbHberRc7S2UtdCGb"
        },
        {
            "relation": "self",
            "url": "https://gateway.api.esante.gouv.fr/fhir/v2/Practitioner?qualification-code=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_G15-ProfessionSante%2FFHIR%2FTRE-G15-ProfessionSante%7C10&active=true"
        }
    ]
}
```


<!-- #### Récupération des éléments liés : "Organization" et "Practitioner"

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











 -->
