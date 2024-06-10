[![pages-build-deployment](https://github.com/ansforge/annuaire-sante-fhir-documentation/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/ansforge/annuaire-sante-fhir-documentation/actions/workflows/pages/pages-build-deployment)

# Documentation API FHIR Annuaire Santé en libre accès

## Introduction 
API Annuaire Santé, le nouveau service permettant au grand public de consulter les données en accès libre de l'Annuaire Santé au format JSON, structurées selon le standard d’interopérabilité FHIR [(Fast Healthcare Interoperability Resources)](https://www.hl7.org/fhir/), développé et maintenu par l’Agence du Numérique en Santé ([ANS](https://esante.gouv.fr/)).

## A propos des endpoints de l'API
Il s'agit d'une API RESTfull, basée sur la spécification HL7 FHIR, qui expose les services suivants : 
> - Practitioner : https://gateway.api.esante.gouv.fr/fhir/v1/Practitioner
> - PractitionerRole : https://gateway.api.esante.gouv.fr/fhir/v1/PractitionerRole
> - Organization : https://gateway.api.esante.gouv.fr/fhir/v1/Organization
> - Healthcareservice : https://gateway.api.esante.gouv.fr/fhir/v1/HealthcareService
> - Device : https://gateway.api.esante.gouv.fr/fhir/v1/Device 
> - CapabilityStatement :  https://gateway.api.esante.gouv.fr/fhir/v1/metadata
> - Healthcheck : https://gateway.api.esante.gouv.fr/fhir/v1/health

## Modèle de données FHIR
Le projet utilise le standard FHIR sur la version R4.

FHIR (Fast Healthcare Interoperability Resources) est une norme d'échange de données de santé, publiée par HL7.

Cette norme nous aide à être interopérables et à proposer des modèles de données de santé approuvés à l’échelle mondiale.

Le modèle est disponible ici : https://interop.esante.gouv.fr/ig/fhir/annuaire

## Documentation et Wiki
* Documentation : https://ansforge.github.io/annuaire-sante-fhir-documentation/
* Démonstration : https://portail.openfhir.annuaire.sante.fr/
* Wiki : https://github.com/ansforge/annuaire-sante-fhir-documentation/wiki/
* Code source : https://github.com/ansforge/annuaire-sante-fhir-serveur/
* IG (StructureDefintion): https://interop.esante.gouv.fr/ig/fhir/annuaire/0.1.0/
* API statuts : https://status.annuaire-sante.esante.gouv.fr/

## CI/CD
Les pipelines de ce repository permettent de :
* Publier les pages : https://ansforge.github.io/annuaire-sante-fhir-documentation

# Pull Request
Faire la PR sur la branche main.

## Acronymes

* FHIR : Fast Healthcare Interoperability Resources
* IG : Implementation Guide
* HL7 : Health Level Seven