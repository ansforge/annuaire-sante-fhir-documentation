---
layout: default
title: Démarrer avec l'api FHIR d'Iris Dp
---

Chaque appel à l'api doit être fait avec une clé d'api. La clé d'api peut se générer avec l'interface de [création de compte]().
Une fois votre clé obtenue vous aurez accès à l'api. 

## Votre premier appel Api

Pour cette section nous utilisons curl qui est un outil présent sur la pluspart des plateformes windows 10+, macos, linux.

Lancez la commande suivante pour récupérer le CapabilityStatement FHIR (liste des fonctionnalités du serveur) : 

{% tabs tab01 %}
{% tab tab01 bash %}
```bash
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" https://ans.com/fhir/metadata?_pretty=true&_format=json
```
{% endtab %}
{% endtabs %}



Si tout c'est bien passé, vous devriez avoir un résultat similaire à : 

```json
{
  "resourceType": "CapabilityStatement",
  "fhirVersion": "4.0.1",
  "format": [ "application/fhir+xml", "xml", "application/fhir+json", "json" ],
  "rest": [ {
    ...
```

&nbsp;

NOTE| Le capability statement permet de connaitre les fonctionnalités disponible sur le serveur FHIR (paramètres, ressources...).


Vous pouvez lancer la même requête sur une ressource par exemple pour récupérer les Practitioner:

{% tabs tab02 %}
{% tab tab02 bash %}
```bash
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" https://ans.com/fhir/Practitioner?_pretty=true&_format=json
```
{% endtab %}
{% endtabs %}


La réponse devrait ressembler à cela :

```json
{
  "resourceType": "Bundle",
  "entry": [ {
    "fullUrl": "https://.../fhir/v1/Practitioner/pra-59",
    "resource": {
      "resourceType": "Practitioner",
      "id": "pra-59",
    }
  }, {
    "fullUrl": "https://.../fhir/v1/Practitioner/pra-57",
    "resource": {
      "resourceType": "Practitioner",
      "id": "pra-57",
      ...
    }
  }
  ...
  ]
}
```

&nbsp;


## Aller plus loins 


### Ressources interne 

<div class="wysiwyg" markdown="1">
* Démarrage par langage: [JAVA]({{ '/pages/documentation/starters/java-starter.html' | relative_url }})  [PHP]({{ '/pages/documentation/starters/php-starter.html' | relative_url }})
* [Use case de synchronisation]({{ '/pages/use-cases/full/index' | relative_url }})
* [Use d'appels unitaires]({{ '/pages/use-cases/practitioner-detail/index' | relative_url }})
</div>

&nbsp;

### Ressources externes

<div class="wysiwyg" markdown="1">
* [Site officiel de FHIR](https://www.hl7.org/fhir/){:target="_blank"}
* [Librairie Java FHIR](https://hapifhir.io/){:target="_blank"}
* [Profils de l'annuaire santé](TODO){:target="_blank"}
</div>
