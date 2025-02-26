---
layout: menu-version-2
title: Tester l'API
subTitle: Démarrage rapide
---
<div class="wysiwyg" markdown="1">
- [Démarrer les tests API avec Postman](#one-header)
- [Démarrer les tests API avec cURL](#two-header)
- [Utiliser le Démonstrateur API FHIR Annuaire Santé](#three-header)

</div>
<br />

## <a id="one-header"></a>1) Démarrer les tests API avec Postman


### Récupérer la collection Postman

Pour tester rapidement et facilement l'API FHIR Annuaire Santé, télécharger cette collection Postman contenant des exemples d'appels FHIR.

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/28492200-99205672-d1f6-4f89-bd0d-53cd36767dcd?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D28492200-99205672-d1f6-4f89-bd0d-53cd36767dcd%26entityType%3Dcollection%26workspaceId%3D2ea80e73-7982-4059-90f6-9aecb1d67325#?env%5BPROD%20-%20API%20FHIR%20V2%5D=W3sia2V5IjoiYXBpX3VybCIsInZhbHVlIjoiaHR0cHM6Ly9nYXRld2F5LmFwaS5lc2FudGUuZ291di5mci9maGlyL3YyIiwiZW5hYmxlZCI6dHJ1ZSwidHlwZSI6ImRlZmF1bHQiLCJzZXNzaW9uVmFsdWUiOiJodHRwczovL2dhdGV3YXkuYXBpLmVzYW50ZS5nb3V2LmZyL2ZoaXIvdjIiLCJjb21wbGV0ZVNlc3Npb25WYWx1ZSI6Imh0dHBzOi8vZ2F0ZXdheS5hcGkuZXNhbnRlLmdvdXYuZnIvZmhpci92MiIsInNlc3Npb25JbmRleCI6MH0seyJrZXkiOiJhcGlfa2V5IiwidmFsdWUiOiJYWFhYLVhYWC1YWFgtWFhYWCIsImVuYWJsZWQiOnRydWUsInR5cGUiOiJkZWZhdWx0Iiwic2Vzc2lvblZhbHVlIjoiWFhYWC1YWFgtWFhYLVhYWFgiLCJjb21wbGV0ZVNlc3Npb25WYWx1ZSI6IlhYWFgtWFhYLVhYWC1YWFhYIiwic2Vzc2lvbkluZGV4IjoxfV0=)


### Configuration de la clé d'API

Une fois le projet importé dans votre espace Postman:
- dans le menu "Environnements": créer un environnement "Prod" et renseigner les variables nécessaires (ex: api_key et api_url)

<p align="center">
  <img src="img/postman-1.png" style="width:100%;">
</p>
- dans l'en-tête (Header) de votre appel, saisir le nom de la clé ESANTE-API-KEY et saisir dans la colonne "valeur" votre api_key de manière dynamique dans votre Postman 

<p align="center">
  <img src="img/postman-3.png" style="width:100%;">
</p>

- dans le menu "Collection": choisir un dossier et lancer un appel 

<p align="center">
  <img src="img/postman-2.png" style="width:100%;">
</p>
Attention: vérifier bien que les variables d'environnements sont bien sélectionnées (situé en haut à droite de l'écran) et que les variables sont bien utilisées dans les appels.
</div>
Veuillez trouver ci-dessous les variables utilisées dans le projet Postman: 

|Variable|Description|Valeur|
|---|---|---|
|api_key|Clé d'API (Si vous n'avez pas de clé, veuillez suivre la procédure décrite [ici](/annuaire-sante-fhir-documentation/pages/guide/version-2/getting-started/test-api.html))|Saisissez votre clé|
|api_url|L’url d’accès à l’API	|https://gateway.api.esante.gouv.fr/fhir/v2|

&nbsp;

## <a id="two-header"></a>2) Démarrer les tests API avec cURL

NOTE| Pour la suite de l'exercice, vous devez remplacer {{site.ans.api_key }} par votre clé d'API.

Pour ces premiers tests, nous utilisons [cURL](https://curl.se/) pour plus de simplicité.

### Récuperer le Capability Statement FHIR (Metadata)


<div class="wysiwyg" markdown="1">
* **Test 1** : lancez la commande suivante pour récupérer le CapabilityStatement (liste des fonctionnalités de l'API) :
</div>

<div class="code-sample"><div class="tab-content" data-name="Invite de commandes">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" "{{site.ans.api_url}}/metadata"
{% endhighlight %}
</div></div>
<div class="wysiwyg" markdown="1">
* Ci-dessous la réponse de l'API : 
</div>

```json
{
    "resourceType": "CapabilityStatement",
    "status": "active",
    "fhirVersion": "4.0.1",
    "format": [
        "application/fhir+json",
        "json"
    ],
    "rest": [
        {
            "resource": [
                {
                    "type": "Organization",
                    "profile": "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-organization",
                    "interaction": [
                ...
```
&nbsp;

NOTE| Le capability statement permet de connaitre les fonctionnalités disponibles sur le serveur FHIR (paramètres, ressources...).


### Récuperer la ressource Practitioner


<div class="wysiwyg" markdown="1">
* <b>Test 2</b> : vous pouvez lancer cette requête pour récupérer les ressources "Practitioner" :
</div>

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{api_key }}" "{{api_url}}/fhir/v2/Practitioner?_pretty=true&_format=json"
{% endhighlight %}
</div>
</div>


<div class="wysiwyg" markdown="1">
* Ci-dessous un exemple de réponse :
</div>
&nbsp;

```json
{
    "resourceType": "Bundle",
    "type": "searchset",
    "id": "207ab354-2ac9-4228-a2e5-86d4fd996cfe",
    "total": 1811966,
    "entry": [
        {
            "fullUrl": "https://gateway.preprod.api.esante.gouv.fr/fhir/v2/Practitioner/003-5818457-5939334",
            "resource": {
                "resourceType": "Practitioner",
                "id": "003-5818457-5939334",
                "meta": {
                    "versionId": "1",
                    "lastUpdated": "2025-01-29T15:29:39.003+01:00",
                    "source": "https://annuaire.sante.fr",
                    "profile": [
                        "https://hl7.fr/ig/fhir/core/StructureDefinition/fr-core-practitioner",
                        "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-dp-practitioner"
                    ]
                },
                "language": "fr",
                "identifier": [
                    {
                        "use": "official",
                        "type": {
                            "coding": [
                                {
                                    "system": "https://hl7.fr/ig/fhir/core/CodeSystem/fr-core-cs-v2-0203",
                                    "code": "IDNPS"
                                }
                            ]
                        },
                        ...
```

&nbsp;

## <a id="three-header"></a>3) Utiliser le Démonstrateur API FHIR Annuaire Santé

Consulter le Démonstrateur API FHIR Annuaire Santé et utiliser la clé API pour vous connecter à cette plateforme.

<p align="center">
  <img src="img/portail-api-fhir.png" style="width:80%;">
</p>

NOTE| L’erreur NET::ERR_CERT_AUTHORITY_INVALID est rencontrée car le certificat exposé sur le portail de démo de l’API FHIR est un certificat issu de l’IGC Santé de l’ANS, qui n’est pas une autorité de certification reconnue par les navigateurs du marché (a contrario des Thawte, DigiCert, etc). Pour y remédier, il faut  ajouter le certificat AC IGC Santé dans votre navigateur pour qu’il soit reconnu par la suite.![alt text](erreur-certificat.png)

Pour plus d'informations, consulter le site [IGC-Santé de l'ANS](https://igc-sante.esante.gouv.fr/PC/){:target="_blank"}

<p align="center">
  <img src="img/erreur-certificat.png" style="width:80%;">
</p>


### Taux de disponibilité

Le taux de disponibilité qui doit être assuré par le titulaire du marché qui maintient et héberge nos SI est de 99,4% (24h/24 et 7j/7).