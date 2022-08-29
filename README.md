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

## Documentation et Wiki
### Prise en main rapide du service FHIR 
[Ici](https://ansforge.github.io/annuaire-sante-fhir-documentation/), vous allez trouver la documentation complète permettant de faciliter votre on-boarding sur IRIS DP : des guides de prise en main, des démonstrations, des exemples d'implémentation, des guides de bonnes pratiques, des bibliothèques de référence, des exemples de code,...

Vous y trouvrez notamment, pour chaque use case, des exemples de codes des différentes technologies (Java, JS, Python, .net, ...), de l’explication de texte, tout élément significatif pour les développeurs...

![Logo java](/img/java_logo.png?raw=true "Java") ![Logo JS](/img/js_logo.png?raw=true "JS") ![Logo Python](/img/python_logo.png?raw=true "Python") ![Logo dotnet](/img/dotnet_logo.png?raw=true ".Net") 

### Démonstration du serveur FHIR (accès restreint)
Cliquez [ici](https://demo.portail.openfhir.annuaire.asipsante.fr/) pour tester l'API.
### Wiki 
Pour consulter les questions fréquemment posées sur le service FHIR d'IRIS DP, veuillez cliquer [ici.](https://github.com/ansforge/annuaire-sante-api-openfhir/wiki)

### Espace Early adopters (accès restreint)
!!! Cet espace est résevé aux membres du club Utilisateurs participant à la co-construction du nouveau service de publication FHIR en libre accès !!! 

Pour y accéder, veuillez cliquer [ici.](https://esantegouv.sharepoint.com/sites/PartenairesRPPS/SitePages/Service-d'interrogation-IRIS-donn%C3%A9es-publiques.aspx)

### Code source
Le code open source du serveur FHIR est disponible ici : https://github.com/ansforge/annuaire-sante-fhir-serveur
