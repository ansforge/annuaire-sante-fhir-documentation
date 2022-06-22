# annuaire-sante-api-openfhir
![Logo FHIR](/img/fhir_hl7_logo.png?raw=true "HL7 FHIR")

<B>IRIS-DP</B>, le nouveau service permettant au grand public de consulter les données en accès libre de l'annuaire-santé au format JSON, structurées selon le standard d’interopérabilité [FHIR](https://www.hl7.org/fhir/), développé et maintenu par l’Agence du Numérique en Santé ([ANS](https://esante.gouv.fr/)).

[L’annuaire-Santé](https://esante.gouv.fr/produits-services/annuaire-sante) est le répertoire partagé des professionnels de Santé. Il rassemble les données d’identification des professionnels de santé et de leurs structures des différents référentiels nationaux : le répertoire partagé des professionnels intervenant dans le système de santé (RPPS), le répertoire ADELI et le répertoire FINESS.

IRIS-DP est un serveur FHIR, exposant une API RESTfull basée sur la spécification HL7 FHIR. Il implémente les ressources FHIR suivantes : 
> - Practitioner, 
> - PractionerRole-Exercice professionnel, 
> - PractionerRole-Situation d’exercice, 
> - Organization, 
> - PractitionerRole-Ameli, 
> - HealthcareService-Activite de soins, 
> - Device et HealthcareService-Equipement Social.

## Première connexion ##
L'authentification se fait par API-Key/Clé-API. Pour chaque requête (get) au serveur, vous devez fournir un header/entête avec la clé d'API :
> - Header : ESANTE-API-KEY
> - API Key : XXXX XXXX XXXX XXXXX

L'obtention d'une clé d'API se fait via le portail APIM de l'ANS (l'url vous sera communiquée utlérieurement).

## Wiki ##
Pour plus d'informations, veuillez cliquer [ici.](https://github.com/ansforge/annuaire-sante-api-openfhir/wiki)


## Espace Early adopters ##
!!! Cet espace est résevé aux membres du club Utilisateurs participant à la co-construction du nouveau service de publication FHIR en libre accès !!! 

Pour y accéder, veuillez cliquer [ici.](https://esantegouv.sharepoint.com/sites/PartenairesRPPS/SitePages/Service-d'interrogation-IRIS-donn%C3%A9es-publiques.aspx)
