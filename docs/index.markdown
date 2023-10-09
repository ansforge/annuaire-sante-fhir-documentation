---
layout: home
title: Bienvenue sur la documentation technique de l'API FHIR Annuaire Santé en libre accès
---

<p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Cette documentation est en cours de construction.
</p>

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
* PractionerRole,
* Organization,
* HealthcareService-Activite de soins,
* HealthcareService-Equipement Social,
* Device.
</div>


![](./assets/img/home-schema.png)

## Exemples de cas d'utilisation

<div class="row"><div class="col-sm" markdown="1">

### Pour les structures de santé

<div class="wysiwyg"  markdown="1">
* Récupérer l'identifiant national des professionnels de santé qu'elles emploient,
* Vérifier l'identité (identifiant national, nom, prénom), les qualifications et la situation d’exercice d'un professionnel,
* Faciliter la coordination ville-hôpital avec la mise en place d’un annuaire de correspondants.
</div>

</div><div class="col-sm" markdown="1">

### Pour les porteurs de projets nationaux et régionaux

<div class="wysiwyg" markdown="1">
* Spécifier des règles métier en fonctions des données d'identification (qualifications...),
* Consulter les activités d'un professionnel (comme l’adresse d'exercice) à partir de son identifiant national (par exemple lorsque le professionnel se connecte au service avec sa CPS ou sa e-CPS).
</div>

</div><div class="col-sm" markdown="1">

### Pour les industriels et éditeurs

<div class="wysiwyg" markdown="1">
* Mettre en œuvre des mécanismes de synchronisation avec un annuaire local,
* Récupérer l'identifiant national pour gérer les accès au système d'information,
* Utiliser les identifiants nationaux pour alimenter les métadonnées d’un document structuré.
* Vérifier les numéros RPPS dans les documents [#CDA](https://github.com/ansforge/TestContenuCDA)
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
