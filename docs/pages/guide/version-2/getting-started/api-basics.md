---
layout: menu-version-2
title: Bases de l'API
subTitle: Démarrage rapide
---


### URL d'accès (base url)

Tous les accès se font via HTTPS.

| Variables     | Valeurs                                                       |
| ---           | ---                                                           |
| api-url       | https://gateway.api.esante.gouv.fr/fhir/v2                    |
| ihm-url       | https://portail.openfhir.annuaire.sante.fr                    |
| gravitee-url  | https://portal.api.esante.gouv.fr (pour obtenir une API KEY)  |
 
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

### Codes d’état HTTP (HTTP status codes)

Toutes les réponses utilisent les codes HTTP standard.
Les codes HTTP standard sont des codes de statut envoyés par un serveur en réponse à une demande HTTP. Voici une liste non exhaustive des codes HTTP les plus courants et leur signification


| Status code | Type                  | Definition  |
| ---         | ---                   | ---         | 
| 200         | OK                    | Requête réussie |
| 400         | Bad request           | La requête est mal formatée. Vérifiez les paramètres ou la syntaxe |
| 401         | Unauthorized          | Impossible d'authentifier la requête |
| 403         | Forbidden             | La requête n'est pas autorisée |
| 404         | Not found             | La ressource n'existe pas |
| 410         | Gone                  | La ressource n'est plus accessible |
| 412         | Precondition Failed   | Echec de la précondition |
| 500         | Internal Server Error | Une erreur inattendue est survenue |

### Méthodes HTTP (HTTP VERBS)

L'API est conforme à la norme REST. Vous pouvez utiliser les ressources avec les méthodes HTTP suivantes :
<div class="wysiwyg"  markdown="1">
- GET : lecture de données simple  (Regex Posix : \/fhir\/(v[0-9]{0,2}\/)?[a-zA-Z]{0,30} )
- POST : lecture de données au format POST  (Regex Posix : \/fhir\/(v[0-9]{0,2}\/)?[a-zA-Z]{0,30}\/_search )
</div>
Les méthodes PUT et DELETE sont interdites.
<br />

### En-têtes (headers)
Ajouter dans les headers la clé ESANTE-API-KEY  
<img src="img/api-basics-headers.png" style="width:80%;">


<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
  curl -H "ESANTE-API-KEY: [api-key]" "[api-url]/fhir/metadata"  
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
Le bundle encapsule 0 à n ressources Location corespondant aux critères de recherche + les ressources incluses correspondant aux critères de recherche.
Le service développé renvoie les 50 premiers résultats. Dans le cas où il n'y a pas de résultat le service renvoie `total`: 0.
- La recherche est un succès à partir du moment où la requête peut être exécutée. Il peut il y avoir 0 à n résultats.
</div>
&nbsp;
Plus d'informations sur la [documentation](https://www.hl7.org/fhir/R4/http.html).

#### Réponse de base -- Echec

Si la recherche échoue, le serveur doit répondre :
<div class="wysiwyg"  markdown="1">
- Un header avec un un code erreur (HTTP 4XX ou 5XX)
- Un body contenant une ressource [OperationOutcome](https://www.hl7.org/fhir/R4/operationoutcome.html) qui donne les détails sur la raison de l'échec
- L'échec d'une recherche est la non-possibilité d'exécuter la requête, ce qui est différent d'aucune correspondance à la recherche.
- La réponse échoue car la réponse a dépassé le timeout de 30 secondes.
</div>
Il existe des OperationOutcome qui peuvent survenir dans le cas d'une suppression d'une ressource (DELETE) mais cette situation n'est pas appliquée sur notre cas.
```json
{
  "resourceType": "OperationOutcome",
  "issue": [ {
    "severity": "error",
    "code": "processing",
    "diagnostics": "HAPI-0302: Unknown resource type 'PractitiXXXner' - Server knows how to handle: [Practitioner, PractitionerRole, Device, Organization, HealthcareService]"
  } ]
}

```


