Veuillez trouverez, ci-attaché, la dernière version des structures définition d’IRIS DP.

Cette version v1.29 apporte deux correctifs :
- correction du nom du profil dans la ressource ASOrganization et correction du system pour degreeR48 dans la ressource ASPractitioner
- ainsi que le changement de cardinalité pour l'extension certificates à 0..0 pour la ressource organization. 


#### Lexique :
* StructureDefinition : est une « méta-ressource » qui permet de définir des structures FHIR. 
  * Elle permet de définir des ressources, des types de données et des extensions.
  * Elle est aussi utilisée pour créer les profils des ressources et des types de données (héritage et différentiel)
  * Elle définit un ensemble d’éléments et les règles de leur utilisation (cardinalités, ValueSet Binding…)
  * Elle est utilisée comme schéma de validation pour l’évaluation de la conformité (Ex: FHIR validator, HAPI FHIR)

