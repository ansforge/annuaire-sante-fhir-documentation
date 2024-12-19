---
layout: menu-version-2
title: Introduction
subTitle: Démarrage rapide
---

### Introduction

Ce site dédié à l'API FHIR Annuaire Santé est une documentation essentielle pour tous les acteurs (industriels, établissements de santé) souhaitant intégrer des fonctionnalités dans leur système d'informations telles que la recherche de professionnels et de structures de santé. Cette documentation a pour objectif de faciliter la prise en main de cette API RESTFUL.

### Modèle de données FHIR

FHIR est un standard d'interopérabilité conçu pour l’échange de données de santé entre les différents acteurs de l’écosystème de Santé, développé par HL7 (Health Level 7).
En pleine croissance, il est de plus en plus utilisé en France et son usage est poussé par l’union européenne.
L’API FHIR Annuaire Santé est une API qui a pour objectif de respecter le standard d’interopérabilité FHIR afin d’exposer toutes les données en libre accès de l’Annuaire Santé

### Ressources FHIR
Une ressource est un ensemble de données qui représente un concept spécifique dans le domaine de la santé. Chaque ressource est un objet structuré qui encapsule des informations définies pour un domaine précis (par exemple, un professionnel de santé, un médecin, un diagnostic, une ordonnance).

L’API FHIR contient près de 161 ressources, mais nous utilisons 5 ressources dans l’API FHIR Annuaire Santé.


### Ressources FHIR
Les 5 ressources disponibles sur l'API FHIR Annuaire Santé sont les suivantes:

| Ressources | Description |
| --- | --- |
| Practitioner | Contient les informations liées à l'exercice professionnel d'un Professionnel intervenant dans le système de Santé. Cela comprend le nom et prénom d'exercice, la catégorie professionnelle, la profession, les diplômes, le savoir-faire, etc. etc. |
| PractitionerRole |  Contient les informations liées à la situation d’exercice / activité d'un professionnel avec le genre d'activité, la fonction, le mode d'exercice, la section Tableau Pharmacien, le Practitioner rattachée, l'Organization rattachée, etc. |
| Organization | Décrit les organisations du domaine sanitaire, social et médico-social |
| HealthcareService | Décrit les équipements sociaux et activités de soins rattachées aux établissements FINESS |
| Device | Décrit les Equipements Matériels Lourds (EML) mis en oeuvre au sein d’établissements. |

&nbsp;

### Météo des services de l'Annuaire Santé

<img src="img/meteo-services-api.png" style="width:100%;">

Il est possible de consulter la page [Météo des services de l'Annuaire Santé](https://status.annuaire-sante.esante.gouv.fr/){:target="_blank"} afin de vérifier si l'API FHIR Annuaire Santé est opérationnelle ou non.
Il est également possible de s'abonner pour être notifié lorsque le statut de l'API FHIR Annuaire Santé est mis à jour (situé en bas de l'écran)

<img src="img/meteo-services-abonner.png" style="width:100%;">

