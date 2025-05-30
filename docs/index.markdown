---
layout: home
title: Bienvenue sur la documentation de l'API FHIR Annuaire Santé en libre accès
---

Voici la documentation complète pour faciliter l'utilisation de l'API. Elle comprend un guide de prise en main rapide de l'API pour les développeurs avec des démonstrations et des exemples d'implémentations. Cette documentation est conçue pour vous aider à maîtriser rapidement et efficacement l'API.

<div style="display: flex; justify-content: space-around;" class="m-5">
<div markdown="1">
[Documentation complète](pages/hub.md){:class="btn  btn--style1"}
</div>
<div markdown="1">
[Démarrage rapide](pages/guide/version-1/getting-started/get-api-key.html){:class="btn  btn--style1"}
</div>
</div>

## Présentation

L’API Annuaire Santé permet au grand public d'interroger facilement et rapidement les données en libre accès de l’Annuaire Santé. Cette API REST, basée sur la spécification HL7 FHIR, est conçue pour être simple d'utilisation, rapide et compatible par rapport au standard FHIR. 
<br />

## A quoi sert l'API ?

En intégrant l'API Annuaire Santé dans votre système d'information, vous pouvez réaliser les recherches suivantes:
<div class="wysiwyg"  markdown="1">
- Rechercher un professionnel intervenant dans le système de santé
- Rechercher une structure 
- Filtrer, ordonner et paginer les résultats selon vos souhaits.
</div>
<p align="center">
  <img src="./assets/img/home-schema.png" style="width:80%;">
</p>


### Exemples de cas d'utilisation

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3>Pour les structures de santé</h3>
        <hr aria-hidden="true">
        <div>
            <ul>
                <li>Récupérer l'identifiant national des professionnels intervenant dans le système de santé</li>
                <li>Vérifier l'identité (identifiant national, nom, prénom), les qualifications et la situation d’exercice d'un professionnel</li>
                <li>Faciliter la coordination ville-hôpital avec la mise en place d’un annuaire de correspondants</li>
            </ul>
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Pour les porteurs de projets nationaux et régionaux</h3>
        <hr aria-hidden="true">
        <div>
            <ul>
                <li>Spécifier des règles métier en fonctions des données d'identification (qualifications...)</li>
                <li>Consulter les activités d'un professionnel (comme l’adresse d'exercice) à partir de son identifiant national (par exemple lorsque le professionnel se connecte au service avec sa CPS ou sa e-CPS)</li>
            </ul>
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Pour les industriels et éditeurs</h3>
        <hr aria-hidden="true">
        <div>
            <ul>
               <li>Mettre en œuvre des mécanismes de synchronisation avec un annuaire local</li>
                <li>Récupérer l'identifiant national pour gérer les accès au système d'information</li>
                <li>Utiliser les identifiants nationaux pour alimenter les métadonnées d’un document structuré</li>
                <li>Vérifier les numéros RPPS dans les documents <a href="https://github.com/ansforge/TestContenuCDA">#CDA</a></li>
            </ul>
        </div>
    </div>
</div>


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
    <div class="col col-12 col-md-3">
        <svg class="svg-icon svg-edit" aria-hidden="true" focusable="false"><use xlink:href="{{ '/assets/ans/svg-icons/icon-sprite.svg#view-projection' | relative_url }}"></use></svg><br/>
        <span  class="doc-section-title"><a href="https://interop.esante.gouv.fr/ig/fhir/annuaire/" target="_blank">IG (Implementation Guide): </a></span>
        Découvrez le modèle FHIR de l'Annuaire Santé au travers du guide d'implémentation.
    </div>
</div>
