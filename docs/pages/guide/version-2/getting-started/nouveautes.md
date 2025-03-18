---
layout: menu-version-2
title: Les nouveautés de cette API
subTitle: Nouvelle API V2
---

<div class="wysiwyg" markdown="1">
- [Quelles sont les nouveautés de l'API FHIR V2](#one-header)
- [Est-ce-que les 2 versions sont disponibles ?](#two-header)
- [Les différences sur chaque ressource](#three-header)
</div>

## <a id="one-header"></a>1) Quelles sont les nouveautés de l'API FHIR V2 ?
<div class="wysiwyg" markdown="1">
- L'API FHIR V2 a été mise à jour par rapport au nouveau guide d'implémentation rédigé par l'équipe interopérabilité de l'ANS. 
- Les réponses JSON ainsi que le capability statement (metadata) sont différents.
- Il y a eu un alignement avec les ressources FRCore
- Le choix dans l'API FHIR V2 de réduire notre dépendance à HAPI en développant nos propres API. Désormais, nous utilisons HAPI uniquement pour la structuration des données.
- L'API FHIR V1 mélangeait les deux concepts Practitioner et PractitionerRole. Une meilleure séparation a été faite dans l'API FHIR V2 entre les données liées à l'exercice professionnelle (Practitioner) et les activités du professionnel (PractitionerRole).
- Séparation des profils génériques en profils publics et privés. A noter que la V2 ne fournit pour le moment que les profils publics.
</div>
&nbsp;

## <a id="two-header"></a>2) Est-ce-que les 2 versions de l'API FHIR sont toujours disponibles?
Oui, vous pouvez appeler les deux versions de l'API FHIR. Une décommission de l'API FHIR V1 sera prévue pour T4-2025/T1-2026. Pour plus d'explications sur la manière d'interroger les deux versions de l'API, consulter le [lien suivant pour l'API FHIR V1](https://ansforge.github.io/annuaire-sante-fhir-documentation/pages/guide/version-1/getting-started/test-api.html) et le [lien suivant pour l'API FHIR V2](https://ansforge.github.io/annuaire-sante-fhir-documentation/pages/guide/version-2/getting-started/test-api.html).
<div class="wysiwyg" markdown="1">
A noter que:
- Le [démonstrateur API](https://portail.openfhir.annuaire.sante.fr/) utilisera par défaut la nouvelle version de l'API FHIR V2. 
- Lors des appels au service de l'API FHIR via cUrl, Postman, etc. (https://gateway.api.esante.gouv.fr/fhir), le service retournera une réponse via l'API FHIR V2. Ce choix a été fait pour éviter toute perturbation avec les consommateurs actuels. 
</div>

&nbsp;

## <a id="two-header"></a>3) Quelles sont les grandes différences ?


### General


| General                                                                               |   
| ---                                                                                   |
| Revue sur le format de réponse du Capability Statement (metadata)                     |
| Ajout de l'attribut : profile: fr-canonical                                           |
| Ajout de la source et profile dans le champ meta lorsqu'une ressource est désactivée  |
| Renommage de l'attribut publication par "listeRouge                                   |
| Amélioration de la recherche sur les différents champs : prénom d'exercice, nom d'exercice, adresse mail MSS, raison sociale, enseigne commerciale, etc.)                                             |
| Suppression de la ressource "Subscription"                                            |


### Ressource Practitioner

| Données       | Ressource Practitioner                                                         |   
| ---           | ---                                                                            |
| resource      | Ajout dans le profile le profile: fr-canonical                                 |
| identifier    | Ajout systématique de l'identifiant national (IDNPS)                           |
| name          | Ajout de l'attribut name contenant les données du professionnel (nom d'exercice, prénom, d'exercice, le préfix (ex:MME) et le suffixe (ex:DR).)|
| qualification | Ajout des informations sur la catégorie professionnelle, la profession, la fonction, les savoir-faire et les types de savoir-faire.   |
| telecom       | Ajout d'un attribut telecom contenant l'ensemble des informations MSSanté (Type de messagerie, Type de BAL, dématérialisation, etc.) |
| cps           | Ajout des informations liées aux cartes CPx du Professionnel                   |
| active        | Si la ressource est en statut false, les informations suivantes seront visibles: id de la ressource, identifiant du professionnel (Identifiant national, RPPS) et le champ active  |


### Ressource PractitionerRole

| Données        | Ressource PractitionerRole                                                           |    
| ---            | ---                                                                                  |
| identifier     | Ajout d'un attribut identifier au niveau du PractitionerRole. L'identifier est également un paramètre de recherche |
| reference      | Ajout de la référence à l'Organization                                               |
| code           | Suppression des attributs catégorie professionnelle et Profession (déplacés dans Practitioner) |
| cps            | Suppression des informations liées aux cartes CPx du Professionnel                   |
| telecom       | Ajout d'un attribut telecom contenant l'ensemble des informations MSSanté (Type de messagerie, Type de BAL, dématérialisation, etc.) |
| valueHumanName | Suppression des informations liées à l'exercice professionnel du Practitioner (déplacés dans Practitioner sous l'attribut Name)  |
| specialty      | Suppression des informations liées aux savoir-faire et aux types de savoir-faire     |

### Ressource Organization

| Données        | Ressource Organization                                                               |    
| ---            | ---                                                                                  |
| reference      | Ajout de la référence à l'Organization                                               |
| telecom       | Ajout d'un attribut telecom contenant l'ensemble des informations MSSanté (Type de messagerie, Type de BAL, dématérialisation, etc.). Amélioration sur l'affectation des BAL MSS sur les sites. |
| _line          | Ajout du champ district                                                              |
| active         | Si la ressource est en statut false, les informations suivantes seront visibles: id de la ressource, identifiant de la structure (finess, rpps rang, etc.), name (raison sociale) et active  |


### Ressource Device
| Données               | Ressource Device                                                                     |    
| ---                   | ---                                                                                  |
| periodAuthorization   | Ajout de la période d'autorisation (date de début / date de fin)                     |
| status                | Si la ressource est en "inactive", les champs suivants seront présents: language, numéro d'autorisation ARHGOS |

### Ressource HealthCareService
| Données               | Ressource Device                                                                     |    
| ---                   | ---                                                                                  |
| type                  | Ajout de la catégorie Activité Santaire régulée                                      |
| active                | Si la ressource est en false, les champs suivants seront présents: numéro d'autorisation ARHGOS |

### Search Parameter (Paramètres de recherche)
- l'ajout du paramètre _elements qui sera disponible.
- le paramètre _has n'a pas été pris en compte dans l'API FHIR V2

