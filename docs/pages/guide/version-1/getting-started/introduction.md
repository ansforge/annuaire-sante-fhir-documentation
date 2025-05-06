---
layout: menu-version-1
title: Introduction
subTitle: Démarrage rapide
---

<p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
L'API FHIR V1 est dépréciée et sera prochainement décommissionnée. Nous vous encourageons à passer sur la version 2 de l'API FHIR.
</p>

### Introduction

La documentation de l'API FHIR Annuaire Santé est une ressource essentielle pour les développeurs et professionnels de santé souhaitant intégrer des fonctionnalités de recherche de professionnels et de structures de santé dans leurs systèmes d'information. Elle propose des guides pratiques, des exemples de cas d'utilisation, et des démonstrations pour faciliter la prise en main de cette API RESTful.

### Modèle de données FHIR

L'API FHIR Annuaire Santé utilise la norme FHIR dans sa version R4 et suit scrupuleusement le guide d'implémentation FHIR (Fast Healthcare Interoperability Resources). LE FHIR est une norme pour l'échange de données de santé, publiée par HL7.
Cette norme nous aide à être interopérable et à offrir des modèles de données de santé approuvés par l'écosystème santé.

### Ressources FHIR
Il existe aujourd'hui 5 ressources disponibles sur l'API FHIR Annuaire Santé:

| Ressources | Description |
| --- | --- |
| Practitioner | Décrit les données d’identification pérennes d’une personne physique, qui travaille en tant que professionnel (professionnel enregistré dans RPPS ou ADELI), personnel autorisé ou personnel d’établissement, dans les domaines sanitaire, médico-social et social. |
| PractitionerRole |  Décrit l’exercice professionel et la situation d’exercice - contient les informations décrivant notamment la profession exercée, l’identité d’exercice d’un professionnel, le cadre de son exercice (civil, militaire, etc.) ainsi que les aractéristiques de l’exercice d’un professionnel pendant une période déterminée et dans une structure déterminée  |
| Organization | Décrit les organismes du domaine sanitaire, médico-social et social |
| HealthcareService | Décrit les équipements sociaux et activités sanitaires rattachées aux etablisemments FINESS |
| Device | Décrit les équipements matériels lourds ('EML') mis en oeuvre au sein d’établissements. |

&nbsp;

### Météo des services de l'Annuaire Santé

<img src="img/meteo-services-api.png" style="width:100%;">

Il est possible de consulter la page [Météo des services de l'Annuaire Santé](https://status.annuaire-sante.esante.gouv.fr/){:target="_blank"} afin de vérifier si l'API FHIR Annuaire Santé est opérationnelle ou non.
Il est également possible de s'abonner pour être notifié lorsque le statut de l'API FHIR Annuaire Santé est mis à jour (situé en bas de l'écran)

<img src="img/meteo-services-abonner.png" style="width:100%;">

