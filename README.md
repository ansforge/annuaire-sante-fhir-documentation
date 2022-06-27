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

## Quelles sont les fonctionnalités prévues à l’ouverture du Service ? ##

## Exemples de cas d'utilisation ## 
> Pour un éditeur de solutions numériques en santé : 
-	Annuaire de professionnels (focus médecins) lié à un portail patient pour prendre des rendez-vous.  
-	Focus sur les médecins mais intérêt également pour les autres professionnels de santé.  
- Besoin de récupérer les médecins, les lieux d'exercice, le savoir-faire / spécialité de chaque professionnel.  
- Besoin de monter de manière complète une base (FULL). Puis de mettre à jour unitairement les informations pour un professionnel donné et/ou une spécialité donnée. 
- Besoin de récupérer en one-shot une ressource depuis le service pour récupérer toutes les informations, si les données ne sont pas présentes localement dans leur base.  

> Pour un hôpital :
- Construire une base de données from scratch : besoin d’établir la correspondance et de récupérer les données sur l’ensemble des médecins libéraux de sa région (ici, différentes combinaisons de filtrage possibles). => Utilisation du service FULL avec filtres sur la profession médecin et sur la région.  
- Mettre à jour une base de données existante : besoin de mettre à jour les données des professionnels de façon hebdomadaire. => Utilisation du service DELTA toutes les semaines. 

> Pour un coordinateur de parcours de soin : 
- Besoin de trouver les coordonnées de contact d'un médecin spécialiste  pour adresser en urgence un patient. => Utilisation du service d’appel unitaire pour retrouver les informations de ce médecin.    
- Le médecin en question n'étant pas joignable (en vacances par exemple), besoin de trouver d'autres médecins de cette spécialité sur le territoire pour pouvoir adresser le patient. => Utilisation du service full avec filtres sur la spécialité et le département (la région si nécessaire).  

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
