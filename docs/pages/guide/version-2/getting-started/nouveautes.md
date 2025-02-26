---
layout: menu-version-2
title: Les nouveautés de cette API
subTitle: Nouvelle API V2
---

<div class="wysiwyg" markdown="1">
- [Création d'un compte dans Gravitee](#one-header)
- [Création d'une application dans Gravitee](#two-header)
- [Aller plus loin](#three-header)
</div>

## <a id="one-header"></a>1) Quelles sont les nouveautés de l'API FHIR V2 ?
<div class="wysiwyg" markdown="1">
- L'API FHIR V2 a été mise à jour par rapport au nouveau guide d'implémentation rédigé par l'équipe interopérabilité de l'ANS. Les réponses JSON ainsi que le metadata sont donc différents.
- Il y a eu un alignement avec les ressources de FRCore
- Le choix dans l'API FHIR V2 de réduire notre dépendance à HAPI en développant nos propres API. Désormais, nous utilisons HAPI uniquement pour la structuration des données.
- L'API FHIR V1 mélangeait les deux concepts Practitioner et PractitionerRole. Une meilleure séparation a été faite dans l'API FHIR V2 entre les données liées à l'exercice professionnelle (Practitioner) et les activités du professionnel (PractitionerRole).
- Séparation des profils génériques en profils publics et privés. A noter que la V2 ne fournit pour le moment que les profils publics.
</div>
&nbsp;

## <a id="two-header"></a>2)Est-ce-que les 2 versions seront disponibles?
Les deux versions V de l'API FHIR seront disponibles. Une décommission de l'API FHIR V1 sera prévue pour une date prévisionnelle planifiée pour décembre 2025. Il est possible de choisir la version à utiliser en modifiant vos appels (ex: fhir/v2). Nous fournissons une documentation pour plus d'explications.
<div class="wysiwyg" markdown="1">
- Le portail de démonstration de l'API FHIR utilisera la nouvelle version de l'API FHIR V2. 
- Si vous utilisez le service de l'API FHIR (via des appels hors portail de démonstration ): le service répondra en utilisant la V1 de l'API FHIR. Ce choix a été pris pour éviter toute perturbation avec les consommateurs actuels. 
</div>

&nbsp;

## <a id="two-header"></a>3) Quelles sont les différences sur chaque ressource

### Ressource Practitioner

| Ressources    |                                                |
| ---           | ---                                            |
| Practitioner  | Suppression des attributs : specialty          |
| Practitioner  | Ajout des attributs : profile: fr-canonical    |



### Ressource PractitionerRole

### Ressource Organization


### Ressource Device


### Ressource HealthCareService


### Search Parameter (Paramètres de recherche)
<div class="wysiwyg" markdown="1">
- l'ajout du paramètre _elements qui sera disponible.
- le _has n'est pas disponible dans la version 
</div>




