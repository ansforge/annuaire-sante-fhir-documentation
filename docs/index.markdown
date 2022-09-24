---
layout: home
title: Bienvenue sur la documentation technique de l'API FHIR Annuaire Santé en libre accès
---


!!! ATTENTION !!! Cette documentation est en cours de rédaction. 

Ici, vous allez trouver toute la documentation technique permettant de faciliter l'utilisation de l'API : des guides de prise en main rapide, des guides pour les développeurs, des démonstrations, des exemples d'implémentation, des guides de bonnes pratiques, des bibliothèques de référence, des exemples de code,...

<div style="display: flex; justify-content: space-around;" class="m-5">
<div markdown="1">
[Documentation complète](pages/hub.md){:class="btn  btn--style1"}
</div>
<div markdown="1">
[Démarrage rapide](pages/quick-start/readme.md){:class="btn  btn--style1"}
</div>
</div>

## Présentation

IRIS-DP est un serveur FHIR, exposant une API RESTfull basée sur la spécification HL7 FHIR. Il implémente les ressources FHIR suivantes :

<div class="wysiwyg" markdown="1">
* Practitioner,
* PractionerRole-Exercice professionnel,
* PractionerRole-Situation d’exercice,
* Organization,
* PractitionerRole-Ameli,
* HealthcareService-Activite de soins,
* Device et HealthcareService-Equipement Social.
</div>


![](./assets/img/home-schema.png)

## Exemples de cas d'utilisation


<div class="row"><div class="col-sm" markdown="1">

### Pour un éditeur de solutions numériques en santé :

<div class="wysiwyg"  markdown="1">
* Annuaire de professionnels (focus médecins) lié à un portail patient pour prendre des rendez-vous.
* Focus sur les médecins mais intérêt également pour les autres professionnels de santé.
* Besoin de récupérer les médecins, les lieux d'exercice, le savoir-faire / spécialité de chaque professionnel.
* Besoin de monter de manière complète une base (FULL). Puis de mettre à jour unitairement les informations pour un professionnel donné et/ou une spécialité donnée.
* Besoin de récupérer en one-shot une ressource depuis le service pour récupérer toutes les informations, si les données ne sont pas présentes localement dans leur base.
</div>

</div><div class="col-sm" markdown="1">

### Pour un hôpital :

<div class="wysiwyg" markdown="1">
* Construire une base de données from scratch : besoin d’établir la correspondance et de récupérer les données sur l’ensemble des médecins libéraux de sa région (ici, différentes combinaisons de filtrage possibles). => Utilisation du service FULL avec filtres sur la profession médecin et sur la région.
* Mettre à jour une base de données existante : besoin de mettre à jour les données des professionnels de façon hebdomadaire. => Utilisation du service DELTA toutes les semaines.
</div>

</div><div class="col-sm" markdown="1">

### Pour un coordinateur de parcours de soin :

<div class="wysiwyg" markdown="1">
* Besoin de trouver les coordonnées de contact d'un médecin spécialiste  pour adresser en urgence un patient. => Utilisation du service d’appel unitaire pour retrouver les informations de ce médecin.
* Le médecin en question n'étant pas joignable (en vacances par exemple), besoin de trouver d'autres médecins de cette spécialité sur le territoire pour pouvoir adresser le patient. => Utilisation du service full avec filtres sur la spécialité et le département (la région si nécessaire).
</div>


</div></div>



&nbsp;

## Aller plus loin


<div class="row">
    <div class="col col-12 col-md-3">
        <svg class="svg-icon svg-edit" aria-hidden="true" focusable="false"><use xlink:href="{{ '/assets/ans/svg-icons/icon-sprite.svg#edit' | relative_url }}"></use></svg><br/>
        <span  class="doc-section-title"><a href="./pages/hub.html">Documentation : </a></span>
        Explorez la documentation.
    </div>
    <div class="col col-12 col-md-3">
        <svg class="svg-icon svg-edit" aria-hidden="true" focusable="false"><use xlink:href="{{ '/assets/ans/svg-icons/icon-sprite.svg#folder' | relative_url }}"></use></svg><br/>
        <span  class="doc-section-title"><a href="./pages/hub.html">Cas d'utilisation : </a></span>
        Les cas d'utilisation pouvant vous servir d'inspiration pour vos développements.
    </div>
    <div class="col col-12 col-md-3">
        <svg class="svg-icon svg-edit" aria-hidden="true" focusable="false"><use xlink:href="{{ '/assets/ans/svg-icons/icon-sprite.svg#view-projection' | relative_url }}"></use></svg><br/>
        <span  class="doc-section-title"><a href="https://portail.openfhir.annuaire.sante.fr/" target="_blank">Démonstrateur : </a></span>
        Découvrez l'API au travers de l'interface IHM.
    </div>

</div>
