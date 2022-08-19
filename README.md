# annuaire-sante-fhir 
![Logo FHIR](/img/fhir_hl7_logo.png?raw=true "HL7 FHIR")

Documentation sur <B>IRIS-DP</B>, le nouveau service permettant au grand public de consulter les données en accès libre de l'annuaire-santé au format JSON, structurées selon le standard d’interopérabilité [FHIR](https://www.hl7.org/fhir/), développé et maintenu par l’Agence du Numérique en Santé ([ANS](https://esante.gouv.fr/)).

## Vue d'ensemble
### A propos du service Annuaire-Santé
[L’annuaire-Santé](https://esante.gouv.fr/produits-services/annuaire-sante) est le répertoire partagé des professionnels de Santé. Il rassemble les données d’identification des professionnels de santé et de leurs structures des différents référentiels nationaux : le répertoire partagé des professionnels intervenant dans le système de santé (RPPS), le répertoire ADELI et le répertoire FINESS.

### A propos du service FHIR d'IRIS DP
IRIS-DP est un serveur FHIR, exposant une API RESTfull basée sur la spécification HL7 FHIR. Il implémente les ressources FHIR suivantes : 
> - Practitioner, 
> - PractionerRole-Exercice professionnel, 
> - PractionerRole-Situation d’exercice, 
> - Organization, 
> - PractitionerRole-Ameli, 
> - HealthcareService-Activite de soins, 
> - Device et HealthcareService-Equipement Social.

### Fonctionnalités FHIR prises en charge 
![IRIS_DP_Img1](https://user-images.githubusercontent.com/70761903/175964172-161d53c2-e0b6-44d4-8413-e6e7f11ccdcd.jpg)
![IRIS_DP_Img2](https://user-images.githubusercontent.com/70761903/175964301-f4f1fe38-3b54-4e6b-bf20-e44c2d153855.jpg)
![IRIS_DP_Img3](https://user-images.githubusercontent.com/70761903/175964348-2cc629d8-53b9-4550-b2dd-f22f1dc3919b.jpg)

### Cas d'utilisation pour le service FHIR 
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


## Documentation 
### Prise en main du service FHIR 
[Ici](https://ansforge.github.io/annuaire-sante-fhir-documentation/), vous allez trouver des outils permettant de faciliter votre on-boarding sur IRIS DP : Best practices, Reference Libraries, code Examples,...

Vous y trouvrez notamment, pour chaque use case, des exemples de codes des différentes technologies (Java, JS, Python, .net, ...), de l’explication de texte, tout élément significatif pour les développeurs...

![Logo java](/img/java_logo.png?raw=true "Java") ![Logo JS](/img/js_logo.png?raw=true "JS") ![Logo Python](/img/python_logo.png?raw=true "Python") ![Logo dotnet](/img/dotnet_logo.png?raw=true ".Net") 

### FAQ 
Pour consulter les questions fréquemment posées sur le service FHIR d'IRIS DP, veuillez cliquer [ici.](https://github.com/ansforge/annuaire-sante-api-openfhir/wiki)


### Espace Early adopters 
!!! Cet espace est résevé aux membres du club Utilisateurs participant à la co-construction du nouveau service de publication FHIR en libre accès !!! 

Pour y accéder, veuillez cliquer [ici.](https://esantegouv.sharepoint.com/sites/PartenairesRPPS/SitePages/Service-d'interrogation-IRIS-donn%C3%A9es-publiques.aspx)
