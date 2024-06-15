---
layout: menu-version-1
title: Obtenir une clé API
---

<div class="wysiwyg" markdown="1">
- [Création d'un compte](#one-header)
- [Création d'une application](#two-header)
- [Tester l'API](#three-header)
- [Aller plus loin](#four-header)
</div>
<br />

Ce guide explique comment obtenir une clé d'API et la configurer pour effectuer votre premier appel à l'API.

**Souscrire à l'API**

| ETAPES | DESCRIPTION |
| --- | --- |
| 1 | Créer votre compte personnel sur l'API manager de l'ANS: [GRAVITEE](https://portal.api.esante.gouv.fr){:target="_blank"}. |
| 2 | Créer l'application qui a vocation à se connecter à l'API dans GRAVITEE |
| 3 | Obtenir un jeton depuis l'application créée dans GRAVITEE |

**Se connecter à l'API**

| ETAPES | DESCRIPTION |
| --- | --- |
| 1 | Utiliser l'API KEY "**ESANTE-API-KEY**" et le jeton récupéré par GRAVITEE |
| 2 | Effectuer des requêtes API en utilisant le démonstrateur API ou en utilisant des logiciels gratuits (ex: Postman) |

**Les liens d'accès**

| NOM | LIEN URL |
| --- | --- |
| Serveur d'accès au service | https://gateway.api.esante.gouv.fr/fhir |
| URL d'accès au Démonstrateur API | https://portail.openfhir.annuaire.sante.fr |

## <a id="one-header"></a>2) Création d'un compte dans Gravitee
Pour appeler l'API, il est nécessaire de disposer d'une clé d'API. Pour obtenir cette clé, vous devez vous rendre sur l’outil de gestion d’API de l'ANS : [GRAVITEE](https://portal.api.esante.gouv.fr/catalog/api/962f412b-e08e-4ee7-af41-2be08eeee7f6){:target="_blank"}.
La création d'un compte est obligatoire dans l'outil GRAVITEE.

<div class="wysiwyg" markdown="1">
* Rendez-vous sur le portail GRAVITEE à l'adresse suivante : [ENREGISTREZ-VOUS](https://portal.api.esante.gouv.fr/user/registration){:target="_blank"}
* Saisir votre prénom, votre nom et une adresse email pour valider l'enregistrement
* À l'issue de la création de votre compte, un email de confirmation est envoyé à l'adresse email renseignée. Cet email contient un lien permettant de terminer le processus de validation du compte.
</div>
&nbsp;

## <a id="two-header"></a>3) Création d'une application dans Gravitee
Cette étape consiste à créer une application dans GRAVITEE afin d'obtenir une clé API.

<p align="center">
  <img src="img/apim_creer_app.png" style="width:100%;">
</p>

Pour créer une application, les étapes à réaliser sont :

<div class="wysiwyg" markdown="1">
* Connectez-vous sur le portail Gravitee à l'adresse : [IDENTIFIEZ-VOUS](https://portal.api.esante.gouv.fr/user/login){:target="_blank"}
* Dans l'onglet "Applications", cliquer sur "CREER UNE APP". La création de l'application se fait en trois étapes
</div>
&nbsp;

| ETAPES | DESCRIPTION |
| --- | --- |
| GENERAL | Renseigner le nom de l'application, une description, le domaine utilisé par l'application et une image |
| SECURITE | Saisir le type (web, mobile, etc.) et le client_ID (facultatif) |
| SOUSCRIPTION | Chercher l'API suivante: API Annuaire Santé en libre accès et cliquer sur "Souscrire" et "Suivant" |
| FINALISATION | Cliquer sur "Créer l'application" pour terminer |

<div class="wysiwyg" markdown="1">
* Dans le menu "Application" dans l'onglet "Souscriptions", vous pourrez retrouver toutes les API souscrites. En cliquant dans le tableau sur l'une des API, la clé API à utiliser s'affiche
</div>
&nbsp;

<p align="center">
  <img src="img/apim_creer_app_2.png" style="width:100%;">
</p>

## <a id="three-header"></a>4) Tester l'API
Pour ces premiers tests, nous utilisons cURL pour plus de simplicité. [cURL](https://curl.se/) étant un outil présent sur la plupart des plateformes windows 10+, macos, linux.

<div class="wysiwyg" markdown="1">
* **Test 1** : lancez la commande suivante pour récupérer le CapabilityStatement FHIR (liste des fonctionnalités de l'API) :
</div>
&nbsp;

TIPS| Pour la suite de l'exercice, vous devez remplacer {{site.ans.demo_key }} par votre clé d'API.


<div class="code-sample"><div class="tab-content" data-name="bash">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/metadata?_pretty=true&_format=json"
{% endhighlight %}
</div></div>
<div class="wysiwyg" markdown="1">
* Ci-dessous la réponse de l'API : 
</div>
&nbsp;

```json
{
  "resourceType": "CapabilityStatement",
  "fhirVersion": "4.0.1",
  "format": [ "application/fhir+xml", "xml", "application/fhir+json", "json" ],
  "rest": [ {
    ...


```
&nbsp;

NOTE| Le capability statement permet de connaitre les fonctionnalités disponibles sur le serveur FHIR (paramètres, ressources...).

<div class="wysiwyg" markdown="1">
* <b>Test 2</b> : vous pouvez lancer cette requête pour récupérer les ressources "Practitioner" :
</div>
&nbsp;
<div class="code-sample"><div class="tab-content" data-name="bash">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/Practitioner?_pretty=true&_format=json"
{% endhighlight %}
</div></div>

<div class="wysiwyg" markdown="1">
* La réponse devrait ressembler à cela :
</div>
&nbsp;

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


## <a id="four-header"></a>Aller plus loin


#### Ressources internes 

<div class="wysiwyg" markdown="1">
* Démarrage par langage: [JAVA]({{ '/pages/guide/version-1/integration-fhir/integration-java.html' | relative_url }}),  [PHP]({{ '/pages/guide/version-1/integration-fhir/integration-php' | relative_url }}),[C#]({{ '/pages/guide/version-1/integration-fhir/integration-dotnet.html' | relative_url }})
* [Accéder aux ressources]({{ '/pages/hub' | relative_url }})
* [Cas d'utilisation de synchronisation]({{ '/pages/use-cases/full/uc-full' | relative_url }})
* [Cas d'utilisation d'appels unitaires]({{ '/pages/use-cases/practitioner-detail/uc-practitioner' | relative_url }})
</div>

&nbsp;

#### Ressources externes

<div class="wysiwyg" markdown="1">
* [Site officiel de FHIR](https://www.hl7.org/fhir/){:target="_blank"}
* [Librairie HAPI FHIR](https://hapifhir.io/){:target="_blank"}
* [Guide d'implémentation FHIR - Annuaire Santé](https://interop.esante.gouv.fr/ig/fhir/annuaire/0.1.0/){:target="_blank"}
</div>