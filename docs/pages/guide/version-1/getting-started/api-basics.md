---
layout: menu-version-1
title: Bases de l'API
---


### URL d'accès (base url)

Tous les accès se font via HTTPS.

#### Environnement Production
<div class="wysiwyg"  markdown="1">
- [api-url] : https://gateway.api.esante.gouv.fr/fhir/v1
- [ihm-url] : https://portail.openfhir.annuaire.sante.fr 
- [gravitee-url] : https://portal.api.esante.gouv.fr        (pour obtenir une API KEY)
</div>
<br />

#### Environnement Bac à sable (en accès restreint)
<div class="wysiwyg"  markdown="1">
- [api-url] : https://gateway.preprod.api.esante.gouv.fr/fhir/v1
- [ihm-url] : https://demo.portail.openfhir.annuaire.asipsante.fr 
- [gravitee-url] : https://portal.preprod.api.esante.gouv.fr
</div>
<br />
 
### Points de terminaison (endpoints)
<div class="wysiwyg"  markdown="1">
- [api-url]/Practitioner    (pour les professionnels de santé)
- [api-url]/PractitionerRole   (pour les exercices pro et les situations d'exercice)
- [api-url]/Organization    (pour les structures)
- [api-url]/HealthcareService   (pour les activités de soins et les équipements sociaux)
- [api-url]/Device    (pour les équipements matériels lourds)
- [api-url]/metadata    (pour le capability statement)
- [api-url]/health    (pour le heathcare du service, accessible sans authentification)
</div>
<br />

### Codes d’état HTTP (HTTP status codes)

Toutes les réponses utilisent les codes HTTP Standard.
Les codes HTTP standard sont des codes de statut envoyés par un serveur en réponse à une demande HTTP. Voici une liste des codes HTTP les plus courants et leur signification

### Méthodes HTTP (http verbs)

L'API est conforme à la norme REST. Vous pouvez utiliser les ressources avec les méthodes HTTP suivantes :
<div class="wysiwyg"  markdown="1">
- GET : lecture de données simple  (Regex Posix : \/fhir\/(v[0-9]{0,2}\/)?[a-zA-Z]{0,30} )
- POST : lecture de données au format POST  (Regex Posix : \/fhir\/(v[0-9]{0,2}\/)?[a-zA-Z]{0,30}\/_search )
</div>
<br />

### En-têtes (headers)
<div class="wysiwyg"  markdown="1">
- ESANTE-API-KEY  
</div>
<br />

```xml
-- Exemple :

  curl 
    -H "ESANTE-API-KEY: XXXX-XXXX-XXXX-XXXXX"  \
    "[api-url]/metadata"  
    
    -- XXXX-XXXX-XXXX-XXXXX étant l'API KEY

```
<br />

### Construction de la réponse de base
|Status code|Type|Definition|
|---|---|---|
|200|OK|Requête réussie|
|400|Bad Request|La requête est mal formatée. Vérifiez les paramètres ou la syntaxe.|
|401|Unauthorized|Impossible d'authentifier la requête|
|403|Forbidden|La requêtes n'est pas autorisée|
|404|Not found|La ressource n'est pas trouvée||
|500|Internal Server Error|Une erreur inattendue est survenue|
|501|Not Implemented||
|503|Service Unavailable|Le service est non disponible|



#### Réponse de base -- Succès

Lien vers la spécification FHIR : <https://www.hl7.org/fhir/R4/bundle.html>

Si la recherche est un succès, le serveur répond :
<div class="wysiwyg"  markdown="1">
- Un header avec un code 200 OK HTTP
- Un body contenant une ressource [Bundle](https://www.hl7.org/fhir/R4/bundle.html) dont le type = searchset.
Le bundle encapsule 0 à n ressources Location corespondant aux critères de recherche plus les ressources incluses correspondant aux critères de recherche.
Le service développé renvoie les 200 premiers résultats et indique le total trouvé dans une balise `total`. Dans le cas où il n'y a pas de résultat le service renvoie `total`: 0.
</div>
<br />

NOTE| La recherche est un succès à partir du moment où la requête peut être exécutée. Il peut il y avoir 0 à n correspondances.
Plus de précision sur la spécification FHIR : https://www.hl7.org/fhir/R4/http.html

#### Réponse de base -- Echec

Lien vers la spécification FHIR : <https://www.hl7.org/fhir/R4/operationoutcome.html>

Si la recherche échoue, le serveur doit répondre :
<div class="wysiwyg"  markdown="1">
- Un header avec un un code erreur HTTP 4XX ou 5XX
- Un body contenant une ressource OperationOutcome[^3] qui donne les détails sur la raison de l'échec
</div>
<br />

NOTE| L'échec d'une recherche est la non-possibilité d'exécuter la requête, ce qui est différent d'aucune correspondance à la recherche.
Plus de précision sur la spécification FHIR : <https://www.hl7.org/fhir/R4/http.html>
