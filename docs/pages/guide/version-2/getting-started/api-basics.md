---
layout: menu-version-1
title: Bases de l'API
subTitle: Démarrage rapide
---


### URL d'accès (base url)

#### Environnement Production

Tous les accès se font via HTTPS.

| Variables | Valeurs |
| --- | --- |
| api-url | https://gateway.api.esante.gouv.fr/fhir/v2/1.x |
| ihm-url | https://portail.openfhir.annuaire.sante.fr  |
| gravitee-url | https://portal.api.esante.gouv.fr (pour obtenir une API KEY) |
 
NOTE| Il n'existe pas d'environement bac à sable de l'API avec des données fictives. La seule API qui existe est celle de l'environnement de production avec les données publiques. Si vous créez votre propre environnement beta, merci de supprimer les données qui ont une durée de conservation supérieure à 30 jours.

### Points de terminaison (endpoints)

| Variables | Valeurs     |
| --- | --- |
| [api-url]/metadata          | Récupérer le capability statement |
| [api-url]/Practitioner      | Récupérer les exercices professionnels des professionnels intervenant dans le système de santé |
| [api-url]/PractitionerRole  | Récupérer les situations d'exercice des professionnels |
| [api-url]/Organization      | Récupérer les structures (entités juridiques, entités géographiques) |
| [api-url]/HealthcareService | Récupérer les activités de soins et les équipements sociaux |
| [api-url]/Device            | Récupérer les équipements matériels lourds (EML) |
<!-- | [api-url]/health            | Pour le healthcare du service API FHIR, accessible sans authentification | -->

### Codes d’état HTTP (HTTP status codes)

Toutes les réponses utilisent les codes HTTP Standard.
Les codes HTTP standard sont des codes de statut envoyés par un serveur en réponse à une demande HTTP. Voici une liste non exhaustive des codes HTTP les plus courants et leur signification

| Status code | Type                  | Definition                                                                                  |
| ---         | ---                   | ---                                                                                         |
| 200         | OK                    | Requête réussie                                                                             |
| 400         | Bad Request           | La requête est mal formatée. Vérifiez les paramètres ou la syntaxe                          |
| 401         |  Unauthorized          | Impossible d'authentifier la requête                                                       |
| 403         | Forbidden             | La requêtes n'est pas autorisée                                                             |
| 404         | Not found             | La ressource n'est pas trouvée                                                              |
| 500         | Internal Server Error |Une erreur inattendue est survenue                                                           |                                                         |


### Méthodes HTTP (HTTP VERBS)

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
<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
  curl -H "ESANTE-API-KEY: [api-key]" "[api-url]/metadata"  
{% endhighlight %}
</div>
</div>
<br />



#### Réponse de base -- Succès

Lien vers la spécification FHIR : <https://www.hl7.org/fhir/R4/bundle.html>

Si la recherche est un succès, le serveur répond :
<div class="wysiwyg"  markdown="1">
- Un header avec un code 200 OK HTTP
- Un body contenant une ressource [Bundle](https://www.hl7.org/fhir/R4/bundle.html) dont le type = searchset.
Le bundle encapsule 0 à n ressources Location corespondant aux critères de recherche plus les ressources incluses correspondant aux critères de recherche.
Le service développé renvoie les 200 premiers résultats et indique le total trouvé dans une balise `total`. Dans le cas où il n'y a pas de résultat le service renvoie `total`: 0.
- La recherche est un succès à partir du moment où la requête peut être exécutée. Il peut il y avoir 0 à n correspondances.
</div>
&nbsp;
Plus d'informations sur la [spécification FHIR HTTP] (https://www.hl7.org/fhir/R4/http.html)

#### Réponse de base -- Echec

Si la recherche échoue, le serveur doit répondre :
<div class="wysiwyg"  markdown="1">
- Un header avec un un code erreur (HTTP 4XX ou 5XX)
- Un body contenant une ressource [OperationOutcome](https://www.hl7.org/fhir/R4/operationoutcome.html>) qui donne les détails sur la raison de l'échec
- L'échec d'une recherche est la non-possibilité d'exécuter la requête, ce qui est différent d'aucune correspondance à la recherche.
</div>

```json
{
{
  "resourceType": "OperationOutcome",
  "issue": [ {
    "severity": "error",
    "code": "processing",
    "diagnostics": "HAPI-0302: Unknown resource type 'Practitixner' - Server knows how to handle: [Practitioner, PractitionerRole, Device, Organization, HealthcareService]"
  } ]
}

```


