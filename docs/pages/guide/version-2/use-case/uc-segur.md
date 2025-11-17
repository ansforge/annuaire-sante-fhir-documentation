---
layout: menu-version-2
title: "Extraction des acteurs des couloirs du Ségur du Numérique en Santé"
subTitle: Cas d'utilisation
---

Le Ségur du Numérique en Santé s’articule autour de 6 types de d'acteurs appelés couloirs. 
<div class="wysiwyg" markdown="1">
- [Couloir Médico-social](#one-header)
- [Couloir Hôpital](#two-header)
- [Couloir Médecin de ville](#three-header)
- [Couloir Biologie médicale](#four-header)
- [Couloir Radiologie](#five-header)
- [Couloir Officine](#six-header)
</div>
<br />

Le Ségur du numérique vise à développer le partage des données de santé entre les différents acteurs de la prise en charge et les patients, par le financement d’une mise à jour logicielle prise en charge par l’Etat pour favoriser l’augmentation des usages numériques dans les établissements. 

Les établissements de santé peuvent être accompagnés par les acteurs régionaux (ARS, GRADeS) dans leur déploiement, le pilotage de services socles nationaux et leur mise en conformité réglementaire.

Nous allons vous détailler ci-dessous comment interroger l'API pour identifier les acteurs présents dans chaque couloir : [segurnumerique.sante-idf.fr](https://segurnumerique.sante-idf.fr/segur-et-services-socles/les-couloirs/).

### <a id="one-header"></a>1. Le couloir Médico-Social

En tant que client de l'API, je souhaite rechercher l'ensemble des établissements médico-sociaux. Pour cela, il faut interroger la ressource Organization en fonction des deux critères suivants:
<div class="wysiwyg" markdown="1">
 * l'identifier doit contenir le code système https://finess.esante.gouv.fr
 * l'établissement doit appartenir aux catégories d'établissements suivantes : 159, 165, 166, 172, 175, 176, 177, 178, 180, 182, 183, 186, 188, 189, 190, 192, 194, 195, 196, 197, 198, 202, 207, 209, 213, 221, 236, 238, 241, 246, 247, 249, 252, 253, 255, 286, 295, 344, 354, 370, 377, 378, 379, 381, 382, 390, 395, 396, 402, 411, 418, 427, 437, 440, 441, 445, 446, 448, 449, 453, 460, 462, 500, 501, 502, 608
</div>

```sh
GET [base]/Organization?identifier=https%3A%2F%2Ffiness.esante.gouv.fr%7C&type=159,165,166,172,175,176,177,178,180,182,183,186,188,189,190,192,194,195,196,197,198,202,207,209,213,221,236,238,241,246,247,249,252,253,255,286,295,344,354,370,377,378,379,381,382,390,395,396,402,411,418,427,437,440,441,445,446,448,449,453,460,462,500,501,502,608
# récupère les organisations qui ont un code système finess.esante.gouv.fr et qui appartiennent aux différents catégories d'établissements de la requête (159,165,166, etc.)
```

Pour information, ces codes font partie de la TRE [TRE-R66-CategorieEtablissement](https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement).



<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}

curl -H "ESANTE-API-KEY: d5aa2278-61af-43c1-8167-feb7c695d1e1" -X POST "https://gateway.api.esante.gouv.fr/fhir/v2/Organization/_search" -d type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R66-CategorieEtablissement%2FFHIR%2FTRE-R66-CategorieEtablissement%7C159%2C165%2C166%2C172%2C175%2C176%2C177%2C178%2C180%2C182%2C183%2C186%2C188%2C189%2C190%2C192%2C194%2C195%2C196%2C197%2C198%2C202%2C207%2C209%2C213%2C221%2C236%2C238%2C241%2C246%2C247%2C249%2C252%2C253%2C255%2C286%2C295%2C344%2C354%2C370%2C377%2C378%2C379%2C381%2C382%2C390%2C395%2C396%2C402%2C411%2C418%2C427%2C437%2C440%2C441%2C445%2C446%2C448%2C449%2C453%2C460%2C462%2C500%2C501%2C502%2C608&identifier=https%3A%2F%2Ffiness.esante.gouv.fr%7C'

curl -H "ESANTE-API-KEY: d5aa2278-61af-43c1-8167-feb7c695d1e1" -X POST https://gateway.api.esante.gouv.fr/fhir/v2/Organization/_search -d 'type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R66-CategorieEtablissement%2FFHIR%2FTRE-R66-CategorieEtablissement%7C159%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R66-CategorieEtablissement%2FFHIR%2FTRE-R66-CategorieEtablissement%7C159%2C165%2C166%2C172%2C175%2C176%2C178%2C180%2C182%2C183%2C186%2C188%2C189%2C190%2C192%2C194%2C195%2C196%2C197%2C198%2C202%2C207%2C209%2C213&identifier=https%3A%2F%2Ffiness.esante.gouv.fr%7C'
      
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// Créer un client FHIR
var clientFhir = createClient();

// Construire la clause de recherche pour les catégories d'établissements
var codesCategories = Arrays.asList("159", "165", "166", "172", "175", "176", "177", "178", "180", "182", "183", "186", "188", "189", "190", "192", "194", "195", "196", "197", "198", "202", "207", "209", "213", "221", "236", "238", "241", "246", "247", "249", "252", "253", "255", "286", "295", "344", "354", "370", "377", "378", "379", "381", "382", "390", "395", "396", "402", "411", "418", "427", "437", "440", "441", "445", "446", "448", "449", "453", "460", "462", "500", "501", "502", "608");
var clauseActivite = Organization.TYPE.exactly()
        .systemAndValues("https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement", codesCategories);

// Construire la clause de recherche pour les identifiants
var clauseIdentifiant = Organization.IDENTIFIER.hasSystemWithAnyCode("https://finess.esante.gouv.fr");

// Créer et exécuter la requête
var bundle = clientFhir
        .search()
        .forResource(Organization.class)
        .where(clauseActivite)
        .and(clauseIdentifiant)
        .returnBundle(Bundle.class)
        .execute();

var aPageSuivante = true;
var organisationsFiness = new LinkedList<>();

// Pour chaque page
do {
    logger.info("Nombre total de résultats {}", bundle.getTotal());

    // Extraire les données du bundle
    organisationsFiness.addAll(bundle.getEntry());

    if (bundle.getLink("next") != null) {
        // Obtenir la page suivante
        bundle = clientFhir
                .loadPage()
                .byUrl(bundle.getLink("next").getUrl())
                .andReturnBundle(Bundle.class)
                .execute();
    } else {
        aPageSuivante = false;
    }
} while (aPageSuivante);

{% endhighlight %}
</div>

</div>

Vous trouverez ainsi des réponses JSON de ce type avec le système https://finess.esante.gouv.fr et le coding avec la catégorie d'établissement (dans l'exemple ci-dessous le code catégorie d'établissement est 182)  :

```sh
                "identifier": [
                    {
                        "use": "official",
                        "type": {
                            "coding": [
                                {
                                    "system": "https://hl7.fr/ig/fhir/core/CodeSystem/fr-core-cs-v2-0203",
                                    "code": "IDNST"
                                }
                            ]
                        },
                        "system": "urn:oid:1.2.250.1.71.4.2.2",
                        "value": "1770816478"
                    },
                    {
                        "use": "official",
                        "type": {
                            "coding": [
                                {
                                    "system": "https://hl7.fr/ig/fhir/core/CodeSystem/fr-core-cs-v2-0203",
                                    "code": "FINEG"
                                }
                            ]
                        },
                        "system": "https://finess.esante.gouv.fr",
                        "value": "770816478"
                    }
                ],
                "active": true,
                "type": [
                    {
                        "extension": [
                            {
                                "url": "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-ext-organization-types",
                                "valueCode": "organizationType"
                            }
                        ],
                        "coding": [
                            {
                                "system": "https://hl7.fr/ig/fhir/core/CodeSystem/fr-core-cs-v2-3307",
                                "code": "GEOGRAPHICAL-ENTITY"
                            }
                        ]
                    },
                    {
                        "extension": [
                            {
                                "url": "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-ext-organization-types",
                                "valueCode": "secteurActiviteRASS"
                            }
                        ],
                        "coding": [
                            {
                                "system": "https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite",
                                "code": "SA16"
                            }
                        ]
                    },
                    {
                        "coding": [
                            {
                                "system": "https://mos.esante.gouv.fr/NOS/TRE_R66-CategorieEtablissement/FHIR/TRE-R66-CategorieEtablissement",
                                "code": "182"
                            }
                        ]
                    },

```


### <a id="two-header"></a>2. Le couloir Hôpital

En tant que client de l'API, je souhaite rechercher l'ensemble des établissements sanitaires. Pour s'assurer que l'établissement ne correspond pas à un établissement médico-social:
<div class="wysiwyg" markdown="1">
- l'appel doit être réalisé en filtrant sur le code système de la [TRE-R02-SecteurActivite](https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite/) 
- Vérifier que chaque structure ne FASSE PAS PARTIE des catégories d'établissements "médico-sociaux" (voir la liste des catégories d'établisssements dans le cas d'utilisation précédent). 
- l'appel doit être réalisé sur les secteurs d'activités : 
</div>


| Code  | Libellé Secteur d'activité                        |
| ---   | ---                                               |
| SA01  | Etablissement public de santé                     |
| SA02  | Hôpital militaire du Service de santé des armées  |
| SA03  | Etablissement privé PSPH                          |
| SA04  | Etablissement privé non PSPH                      |
| SA30  | Autre établissement sanitaire                     |
| SA34  | Centre de dialyse                                 |
| SA36  | Centre anti-cancer                                |

**Requêtes :**
```sh
GET [base]/Organization?identifier=https%3A%2F%2Ffiness.esante.gouv.fr%7C&type=SA01,SA02,SA03,SA04,SA30,SA34,SA36&type:not=159,165,166,172,175,176,177,178,180,182,183,186,188,189,190,192,194,195,196,197,198,202,207,209,213,221,236,238,241,246,247,249,252,253,255,286,295,344,354,370,377,378,379,381,382,390,395,396,402,411,418,427,437,440,441,445,446,448,449,453,460,462,500,501,502,608
# Récupère toutes les organisations provenant du SI FINESS, qui sont dans les secteurs d'activités suivants : SA01, SA02, SA03, SA04, SA30, SA34, SA36 et qui n'appartiennent pas aux catégories d'établissement du secteur médico-social
```

### <a id="three-header"></a>3. Le couloir Hôpital

En tant que client de l'API, je souhaite rechercher l'ensemble des officines de pharmacie. Il faut interroger la ressource Organization :
<div class="wysiwyg" markdown="1">
 * En filtrant sur le système et le secteur d'activité de l'organisation : https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite, SA33, SA38, SA39, SA65
</div>

<br />
La liste des codes des pharmacies (ex: SA33, etc...) se trouve dans le référentiel : [TRE-R02-SecteurActivite](https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite/)


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA33%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA38%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA39%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%SA65"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = createClient();
var hasNext = true;
Bundle orgBundle = null;
var totalElements = 0;
var treated = 0;
var goodElements = new ArrayList<>();

// construct radiology facility request
try {
    var pharmacyCodesList = Arrays.asList("SA33", "SA38", "SA39", "SA65");
    orgBundle = client.search().forResource(Organization.class)
            .where(Organization.TYPE.exactly().systemAndValues("https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite", pharmacyCodesList))
            .returnBundle(Bundle.class).execute();

    totalElements = orgBundle.getTotal();
} catch (Exception e) {
    e.printStackTrace();
    hasNext = false;
}

logger.info("Total results - {}", totalElements);

do {
    var bundleContent = orgBundle.getEntry();

    for (var e : bundleContent) {
        // store the organization inside a map
        if(e.getResource() instanceof Organization) {
            var org = (Organization) e.getResource();
            goodElements.add(org);
            treated++;
        }
    }

    // check if result has a next page
    if (orgBundle.getLink("next")!=null) {
        try {
            orgBundle = client.loadPage().byUrl(orgBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
        } catch (Exception e) {
            logger.error("Error getting next page");
            e.printStackTrace();
            hasNext = false;
        }
    } else {
        hasNext = false;
    }

    logger.info("Progress treated - {} / {}", treated, totalElements);
} while (hasNext);

logger.info("Total organization - {}", goodElements.size());
{% endhighlight %}
</div>
</div>
<br />


<!-- # TODO

### 4. Le couloir Biologie Médicale

Afin de récupérer les établissements de biologie ayant des médecins ou pharmaciens exerçants, nous devons interroger l'endpoint Organization :
<div class="wysiwyg" markdown="1">
 * En filtrant sur les types d'établissements : SA25, SA29
 * En incluant les PractitionerRoles liés aux Organizations afin de pouvoir filtrer sur le code profession de l'activité du professionel (PractitionerRole)
</div>

<br />

Une fois l'ensemble des données récupéré, il faut regrouper les PractitionerRoles pour les lier aux bonnes Organizations (champ organization du PractitionerRole comme le montre le point 1 sur l'image ci-dessous)

Nous pouvons finalement ne récupérer que les Organizations contenant des PractitionerRoles qui correspondent aux filtres Secteur d'activité et Profession santé (champs montré sur le point 2 de l'image ci-dessous).


![Schéma montrant comment relier les Organization et les PractitionerRole](img/focus-json-couloir-bio-med.png){:style="max-width:670px"}


Note : La liste des codes des établissements sanitaires (ex: SA25, etc...) se situe dans le référentiel : [TRE-R02-SecteurActivite](https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite/). Les Roles souhaités (10 et 21) sont disponibles dans le référentiel [TRE-G15-ProfessionSante](https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante)



<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %}
1) Faire un appel sur le endpoint Organization en filtrant sur les Organization qui ont un type SA25 ou SA29 (&type=SA25,SA29). Cet appel devra inclure les PractitionerRoles attachés (&_revinclude=PractitionerRole:organization)
2) Pour chacun des PractitionerRole retourné, vérifier il y a au moins 1 role (champs code) avec pour système "https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante" et un code associé à 10 (médecin) ou 21 (pharmacien)
3) Pour chacun des PractitionerRole trouvé, récupérer les Organization qui ont le même id que le champs organization du PractitionerRole
4) Répeter l'opération sur toutes les pages 
{% endhighlight %}
</div>
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/Organization?type=SA25%2CSA29&_revinclude=PractitionerRole%3Aorganization"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = createClient();

// construct biology facility request
var orgBundle = client.search().forResource(Organization.class)
        .where(Organization.TYPE.exactly().codes("SA25","SA29"))
        .revInclude(PractitionerRole.INCLUDE_ORGANIZATION)
        .returnBundle(Bundle.class).execute();

var totalElements = orgBundle.getTotal();
var goodElements = new ArrayList<>();
var hasNext = true;
var treated = 0;

logger.info("Total results - {}", orgBundle.getTotal());

do {
    var bundleContent = orgBundle.getEntry();
    var organizationMap = new LinkedHashMap<String, Organization>();

    for (var e : bundleContent) {
        // store the organization inside a map
        if(e.getResource() instanceof Organization) {
            var org = (Organization) e.getResource();
            organizationMap.put(org.getId(), org);
        }

        if(e.getResource() instanceof PractitionerRole) {
            var role = (PractitionerRole) e.getResource();
            var medicOrPharmacist = false;

            // check if the Role contains a medic or pharmacist code
            for(var code : role.getCode()) {
                for(var coding : code.getCoding()) {
                    if (coding.getSystem().equals("https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante") &&
                            (coding.getCode().equals("10") || coding.getCode().equals("21"))) {
                        medicOrPharmacist = true;
                        break;
                    }
                }
            }

            // if the Role is right and still practicing, link it to the right Organization
            if(medicOrPharmacist && !role.getPeriod().hasEnd() && role.getOrganization() != null) {
                var org = (Organization) role.getOrganization().getResource();

                if(organizationMap.containsKey(org.getId())) {
                    organizationMap.get(org.getId()).addContained(role);
                }
            }
        }
    }

    // loop over Organization and keep only those with roles
    for(var org : organizationMap.values()) {
        logger.info("Organization has {} roles", org.getContained().size());
        if(org.getContained().size() > 0) {
            goodElements.add(org);
        }
    }

    // check if result has a next page
    if (orgBundle.getLink("next")!=null) {
        orgBundle = client.loadPage().byUrl(orgBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
    } else {
        hasNext = false;
    }

    treated += bundleContent.size();
} while (hasNext);

logger.info("Total global - {}", totalElements);
logger.info("Total filtered - {}", goodElements.size());
{% endhighlight %}
</div>

</div>

<br />


### 4. Le couloir Radiologie

Afin de récupérer les établissements de radiologie, nous devons interroger l'endpoint Organization :
<div class="wysiwyg" markdown="1">
 * En filtrant sur les types d'établissements : SA07, SA08, SA09
 * En incluant les PractitionerRole liés aux Organizations afin de pouvoir filtrer ensuite sur le savoir-faire des PractitionerRole
</div>

<br />


Une fois l’ensemble des données récupéré, procédez aux filtres suivant : 

Les PractitionerRoles doivent correspondre à la profession de santé "Médecin" (10) (champs code du PractitionerRole comme le montre le point 3 sur l'image ci-dessous).

Ensuite les PractitionerRoles souhaités doivent correspondre à certaines spécialités : SM28, SM44, SM45, SM55 (champs specialty du PractitionerRole comme le montre le point 2 sur l'image ci-dessous).

Enfin, faut regrouper les PractitionerRoles pour les lier aux bonnes Organizations (champ organization du PractitionerRole comme le montre le point 1 sur l’image ci-dessous)


Nous pouvons finalement ne récupérer que les Organizations contenant des PractitionerRoleRoles selon les filtres que nous avons appliqués.



![Schéma montrant comment relier et filter les Organization et les PractitionerRole](img/focus-json-couloir-radio.png){:style="max-width:670px"}



Note: la liste des codes des établissements sanitaires (ex: SA07, etc...) se situe dans le référentiel : [TRE-R02-SecteurActivite](https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite/). La liste des profession est disponible dans le référentiel [TRE-G15-ProfessionSante](https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante). La liste des code spécialités est disponibles dans le référentiel [TRE_R38-SpecialiteOrdinale](https://mos.esante.gouv.fr/NOS/TRE_R38-SpecialiteOrdinale/FHIR/TRE-R38-SpecialiteOrdinale)


<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %}
1) Faire un appel sur l'endpoint Organization en filtrant sur les Organization qui ont un type SA07, SA08 ou SA0 (&type=SA07,SA08,SA09). Cet appel devra inclure les PractitionerRoles attachés (&_revinclude=PractitionerRole:organization)
2) Pour chacun des PractitionerRole retournés, vérifier qu'il y a au moins 1 Role (champs role) avec pour système "https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante" et un code associé à 10 (médecin)
3) Pour chacun des PractitionerRole filtrés, vérifier qu'il y a au moins 1 Spécialité (champs speciality) avec pour système "https://mos.esante.gouv.fr/NOS/TRE_R38-SpecialiteOrdinale/FHIR/TRE-R38-SpecialiteOrdinale" et un code associé SM28, SM44, SM45 ou SM55
4) Pour chacun des PractitionerRole trouvés et filtrés, récupérer les Organization qui ont le même id que le champs organization du PractitionerRole
5) Répeter l'opération sur toutes les pages 
{% endhighlight %}
</div>
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/Organization?type=SA07%2CSA08%2CSA09&_revinclude=PractitionerRole%3Aorganization"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = createClient();
var hasNext = true;
Bundle orgBundle = null;
var totalElements = 0;
var goodElements = new ArrayList<>();
var treated = 0;
var nbRoles = 0;

// construct radiology facility request
try {
orgBundle = client.search().forResource(Organization.class)
.where(Organization.TYPE.exactly().codes("SA07", "SA08", "SA09"))
.revInclude(PractitionerRole.INCLUDE_ORGANIZATION)
.returnBundle(Bundle.class).execute();

    totalElements = orgBundle.getTotal();
} catch (Exception e) {
e.printStackTrace();
hasNext = false;
}

logger.info("Total results - {}", orgBundle.getTotal());

do {
var bundleContent = orgBundle.getEntry();
var organizationMap = new LinkedHashMap<String, Organization>();

    for (var e : bundleContent) {
        // store the organization inside a map
        if(e.getResource() instanceof Organization) {
            var org = (Organization) e.getResource();
            organizationMap.put(org.getId(), org);
            treated++;
        }

        if(e.getResource() instanceof PractitionerRole) {
            var role = (PractitionerRole) e.getResource();
            var medic = false;
            var radiologist = false;

            nbRoles++;

            // check if the Role contains a medic
            for(var code : role.getCode()) {
                for(var coding : code.getCoding()) {
                    if (coding.getSystem().equals("https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante") &&
                            coding.getCode().equals("10")) {
                        medic = true;
                        break;
                    }
                }
            }

            if(medic) {
                var specialtyStringList = Arrays.asList("SM28", "SM44", "SM45", "SM55");

                // if a medic was found, check its specialties to filter only radiologist
                for (var code : role.getSpecialty()) {
                    for (var coding : code.getCoding()) {
                        logger.info("Specialty Coding {} - {}", coding.getSystem(), coding.getCode());
                        if (coding.getSystem().equals("https://mos.esante.gouv.fr/NOS/TRE_R38-SpecialiteOrdinale/FHIR/TRE-R38-SpecialiteOrdinale") &&
                                specialtyStringList.contains(coding.getCode())) {
                            radiologist = true;
                            break;
                        }
                    }
                }
            }

            // if the Role is right and still practicing, link it to the right Organization
            if(radiologist && !role.getPeriod().hasEnd() && role.getOrganization() != null) {
                var org = (Organization) role.getOrganization().getResource();

                if(organizationMap.containsKey(org.getId())) {
                    organizationMap.get(org.getId()).addContained(role);
                }
            }
        }
    }

    // loop over Organization and keep only those with roles
    for(var org : organizationMap.values()) {
        if(!org.getContained().isEmpty()) {
            goodElements.add(org);
        }
    }

    // check if result has a next page
    if (orgBundle.getLink("next")!=null) {
        try {
            orgBundle = client.loadPage().byUrl(orgBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
        } catch (Exception e) {
            logger.error("Error getting next page");
            e.printStackTrace();
            hasNext = false;
        }
    } else {
        hasNext = false;
    }

    logger.info("Progress treated - {} / {} / {} role(s)", treated, orgBundle.getTotal(), nbRoles);
} while (hasNext);

logger.info("Total organization - {}", totalElements);
logger.info("Total radiology  - {}", goodElements.size());
{% endhighlight %}
</div>

</div>

L'exécution de l'exemple de code peut donner un résultat équivalent :


<br />


### 5. Le couloir Médecine de ville

Afin de récupérer les établissements de médecine de ville qui ne sont pas des cabinets de radiologie, nous devons interroger l'endpoint Organization :
<div class="wysiwyg" markdown="1">
 * En filtrant sur les types d'établissements : SA05, SA07, SA08, SA09, SA52
 * En incluant les PractitionerRole liés aux Organizations afin de pouvoir filtrer ensuite sur le savoir-faire des Practitioner
</div>

<br />

Une fois l’ensemble des données récupéré, procédez aux filtres suivant : 

Les PractitionerRoles doivent correspondre à la profession de santé "Médecin" (10) (champs code du PractitionerRole comme le montre le point 3 sur l'image ci-dessous).

Ensuite les PractitionerRoles souhaités ***NE DOIVENT PAS correspondre*** à certaines spécialités : SM28, SM44, SM45, SM55 (champs specialty du PractitionerRole comme le montre le point 2 sur l'image ci-dessous)

Enfin, faut regrouper les PractitionerRoles pour les lier aux bonnes Organizations (champ organization du PractitionerRole comme le montre le point 1 sur l’image ci-dessous)



Puis les Roles souhaités ne doivent pas correspondre à certaines spécialités (SM28, SM44, SM45, SM55), disponibles dans le référentiel [TRE_R38-SpecialiteOrdinale](https://mos.esante.gouv.fr/NOS/TRE_R38-SpecialiteOrdinale/FHIR/TRE-R38-SpecialiteOrdinale)


Nous pouvons finalement ne récupérer que les Organizations contenant des PractitionerRoleRoles selon les filtres que nous avons appliqués.


![Schéma montrant comment relier et filter les Organization et les PractitionerRole](img/focus-json-couloir-med-ville.png){:style="max-width:670px"}



<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %}
1) Faire un appel sur l'endpoint Organization en filtrant sur les Organization qui ont un type SA07, SA08 ou SA0 (&type=SA07,SA08,SA09). Cet appel devra inclure les PractitionerRoles attachés (&_revinclude=PractitionerRole:organization)
2) Pour chacun des PractitionerRole retournés, vérifier qu'il y a au moins 1 Role (champs role) avec pour système "https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante" et un code associé à 10 (médecin)
3) Pour chacun des PractitionerRole filtrés, vérifier qu'il ne dispose pas des spécialités de radiologie (champs speciality) avec pour système "https://mos.esante.gouv.fr/NOS/TRE_R38-SpecialiteOrdinale/FHIR/TRE-R38-SpecialiteOrdinale" et un code associé SM28, SM44, SM45 ou SM55
4) Pour chacun des PractitionerRole filtrés, vérifier qu'il est toujours en activité
5) Pour chacun des PractitionerRole trouvés et filtrés, récupérer les Organization qui ont le même id que le champs organization du PractitionerRole
6) Répeter l'opération sur toutes les pages
{% endhighlight %}
</div>
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/Organization?type=SA05%2CSA07%2CSA08%2CSA09%2CSA52&_revinclude=PractitionerRole%3Aorganization"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = createClient();
var hasNext = true;
Bundle orgBundle = null;
var totalElements = 0;
var goodElements = new ArrayList<>();
var treated = 0;
var nbRoles = 0;

// construct radiology facility request
try {
orgBundle = client.search().forResource(Organization.class)
.where(Organization.TYPE.exactly().codes("SA05", "SA07", "SA08", "SA09", "SA52"))
.revInclude(PractitionerRole.INCLUDE_ORGANIZATION)
.returnBundle(Bundle.class).execute();

    totalElements = orgBundle.getTotal();
} catch (Exception e) {
e.printStackTrace();
hasNext = false;
}

logger.info("Total results - {}", orgBundle.getTotal());

do {
var bundleContent = orgBundle.getEntry();
var organizationMap = new LinkedHashMap<String, Organization>();

    for (var e : bundleContent) {
        // store the organization inside a map
        if(e.getResource() instanceof Organization) {
            var org = (Organization) e.getResource();
            organizationMap.put(org.getId(), org);
            treated++;
        }

        if(e.getResource() instanceof PractitionerRole) {
            var role = (PractitionerRole) e.getResource();
            var medic = false;
            var radiologist = false;

            nbRoles++;

            // check if the Role contains a medic
            for(var code : role.getCode()) {
                for(var coding : code.getCoding()) {
                    if (coding.getSystem().equals("https://mos.esante.gouv.fr/NOS/TRE_G15-ProfessionSante/FHIR/TRE-G15-ProfessionSante") &&
                            coding.getCode().equals("10")) {
                        medic = true;
                        break;
                    }
                }
            }

            if(medic) {
                var specialtyStringList = Arrays.asList("SM28", "SM44", "SM45", "SM55");

                // if a medic was found, check its specialties to filter only radiologist
                for (var code : role.getSpecialty()) {
                    for (var coding : code.getCoding()) {
                        logger.info("Specialty Coding {} - {}", coding.getSystem(), coding.getCode());
                        if (coding.getSystem().equals("https://mos.esante.gouv.fr/NOS/TRE_R38-SpecialiteOrdinale/FHIR/TRE-R38-SpecialiteOrdinale") &&
                                specialtyStringList.contains(coding.getCode())) {
                            radiologist = true;
                            break;
                        }
                    }
                }
            }

            // if the Role is right and still practicing, link it to the right Organization
            if(medic && !radiologist && !role.getPeriod().hasEnd() && role.getOrganization() != null) {
                var org = (Organization) role.getOrganization().getResource();

                if(organizationMap.containsKey(org.getId())) {
                    organizationMap.get(org.getId()).addContained(role);
                }
            }
        }
    }

    // loop over Organization and keep only those with roles
    for(var org : organizationMap.values()) {
        if(!org.getContained().isEmpty()) {
            goodElements.add(org);
        }
    }

    // check if result has a next page
    if (orgBundle.getLink("next")!=null) {
        try {
            orgBundle = client.loadPage().byUrl(orgBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
        } catch (Exception e) {
            logger.error("Error getting next page");
            e.printStackTrace();
            hasNext = false;
        }
    } else {
        hasNext = false;
    }

    logger.info("Progress treated - {} / {} / {} role(s)", treated, orgBundle.getTotal(), nbRoles);
} while (hasNext);

logger.info("Total organization - {}", totalElements);
logger.info("Total radiology  - {}", goodElements.size());
{% endhighlight %}
</div>

</div>


<br />


### 6. Le couloir Pharmacie

Afin de récupérer les officines de pharmacie, nous devons interroger l'endpoint Organization :
<div class="wysiwyg" markdown="1">
 * En filtrant sur le système et les types d'établissements : https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite, SA33, SA38, SA39, SA65
</div>

<br />
La liste des codes des pharmacies (ex: SA33, etc...) se trouve dans le référentiel : [TRE-R02-SecteurActivite](https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite/)

Les données récupérées sont déjà pré-filtrées selon notre besoin et ne sont que des pharmacies.


<div class="code-sample">
<div class="tab-content" data-name="Algorithmie">
{% highlight bash %}
1) Faire un appel sur l'endpoint Organization en filtrant sur les Organization qui ont un type SA33, SA38, SA39 ou SA65. Cet appel devra également inclure le filtre sur le système (ex : type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite|SA33)
2) L'ensembles des Organization récupérées sont des pharmacies
{% endhighlight %}
</div>
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/fhir/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA33%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA38%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA39%2Chttps%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%SA65"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = createClient();
var hasNext = true;
Bundle orgBundle = null;
var totalElements = 0;
var treated = 0;
var goodElements = new ArrayList<>();

// construct radiology facility request
try {
    var pharmacyCodesList = Arrays.asList("SA33", "SA38", "SA39", "SA65");
    orgBundle = client.search().forResource(Organization.class)
            .where(Organization.TYPE.exactly().systemAndValues("https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite", pharmacyCodesList))
            .returnBundle(Bundle.class).execute();

    totalElements = orgBundle.getTotal();
} catch (Exception e) {
    e.printStackTrace();
    hasNext = false;
}

logger.info("Total results - {}", totalElements);

do {
    var bundleContent = orgBundle.getEntry();

    for (var e : bundleContent) {
        // store the organization inside a map
        if(e.getResource() instanceof Organization) {
            var org = (Organization) e.getResource();
            goodElements.add(org);
            treated++;
        }
    }

    // check if result has a next page
    if (orgBundle.getLink("next")!=null) {
        try {
            orgBundle = client.loadPage().byUrl(orgBundle.getLink("next").getUrl()).andReturnBundle(Bundle.class).execute();
        } catch (Exception e) {
            logger.error("Error getting next page");
            e.printStackTrace();
            hasNext = false;
        }
    } else {
        hasNext = false;
    }

    logger.info("Progress treated - {} / {}", treated, totalElements);
} while (hasNext);

logger.info("Total organization - {}", goodElements.size());
{% endhighlight %}
</div>

</div>

<br />

 -->
