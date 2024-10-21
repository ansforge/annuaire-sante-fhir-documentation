---
layout: default
title: Gestion des WebHooks de Subscription
subTitle: Subscription Admin
---

## Création d'une Subscription

La première étape consiste à passer un appel au serveur FHIR afin de créer la Subscription désirée.

Pour cela, l'envoi de la structure de la Subscription est nécessaire.

Exemple du payload pour la création d'une Subscription : 

```
{
	"resourceType": "Subscription",
	"criteria": "Device?_id=device1&status=active",
	"channel": {
		"type": "rest-hook",
		"endpoint": "https://my_server/hook/subscriptions",
		"payload": "application/fhir+json"
	}
}
```

L'attribut "criteria" permet de donner le filtre à laquelle la Subscription doit s'abonner. Le type de donnée sur lequel on doit s'abonner (ici "Device").
Le filtre peut être appliqué sur différentes données (ici "_id" ou encore le "status").

L'attribut "channel" contient différents paramètres permettant de spécifier le type d'envoi ("type"), l'URL à appeler ("endpoint") et le type des données que l'on souhaite recevoir lors de la notification ("payload")


<div class="code-sample">
    <div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "Accept: application/json" -H "ESANTE-API-KEY: {{site.ans.api_key }}" -X PUT -d '{"resourceType": "Subscription","criteria": "Device?_id=device1&status=active","channel": {"type": "rest-hook","endpoint": "https://my_server/hook/subscriptions","payload": "application/fhir+json"}}' {{site.ans.api_url}}/fhir/Subscription
{% endhighlight %}
    </div>
</div>

<br/>

## Vérification des Subscription
Afin de vérifier si la/les Subscription ont bien été créés, il est possible d'effectuer une recherche dans les données de type Subscription :

### Exemple de recherche
<div class="code-sample">
    <div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.api_key }}" {{site.ans.api_url}}/fhir/Subscription
{% endhighlight %}
    </div>
</div>

### Exemple de réponse attendue
```
{
	"resourceType": "Bundle",
	"id": "f3d68500-bf36-468d-810d-a2970b20e807",
	"meta": {
		"lastUpdated": "2022-10-03T15:51:02.294+02:00"
	},
	"type": "searchset",
	"total": 2,
	"link": [
		{
			"relation": "self",
			"url": "http://iris-dp-server:8080/fhir/v1/Subscription?_format=json&_pretty=true"
		}
	],
	"entry": [
		{
			"fullUrl": "http://iris-dp-server:8080/fhir/v1/Subscription/id-3",
			"resource": {
				"resourceType": "Subscription",
				"id": "id-3",
				"meta": {
					"versionId": "3",
					"lastUpdated": "2022-10-03T15:51:01.605+02:00"
				},
				"status": "active",
				"criteria": "Device?_id=device1",
				"channel": {
					"type": "rest-hook",
					"endpoint": "https://my_server/hook/subscriptions",
					"payload": "application/fhir+json"
				}
			}
		},
		{
			"fullUrl": "http://iris-dp-server:8080/fhir/v1/Subscription/id-2",
			"resource": {
				"resourceType": "Subscription",
				"id": "id-2",
				"meta": {
					"versionId": "3",
					"lastUpdated": "2022-10-03T15:51:01.710+02:00"
				},
				"status": "active",
				"criteria": "Device",
				"channel": {
					"type": "rest-hook",
					"endpoint": "https://my_server/hook/subscriptions-no-payload"
				}
			}
		}
	]
}
```

<br/>

## Gestion des erreurs

Lors d'une mise à jour de données, chaque Subscription active va déclencher la création de SubscriptionMessage.
Ces SubscriptionMessage sont envoyés selon la configuration de l'attribut "channel" d'une Subscription.

Lors de l'exécution du Batch d'envoi des Hooks, une gestion d'erreur a été mise en place.

Cette gestion prend en compte un statut sur les SubscriptionMessage :
- **PENDING** : en attente d'envoi
- **SUCCESS** : l'envoi s'est effectué avec succès
- **PENDING_RETRY** : une erreur est survenue pendant l'envoi mais une autre tentative sera effectuée ultérieurement
- **IN_ERROR** : l'envoi est en erreur et aucune autre tentative ne sera effectuée


<br/>

## Mise à jour d'une donnée
La seconde étape est de mettre à jour la donnée sur laquelle la Subscription a été créée.
Ici nous mettons donc à jour la Device avec l'_id "device1" grâce à l'envoi du payload du Device via une requête PUT :

```
{
	"resourceType": "Device",
	"id": "device1",
	"meta": {
		"versionId": "2",
		"lastUpdated": "2022-10-03T15:51:01.810+02:00",
		"profile": [
			"https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-Device"
		]
	},
	"identifier": [
		{
			"system": "http://samplesysyem",
			"value": "1"
		}
	],
	"status": "active",
	"manufacturer": "man1",
	"deviceName": [
		{
			"name": "demo-ans"
		}
	],
	"modelNumber": "model11664805061717",
	"type": {
		"coding": [
			{
				"system": "http://types/",
				"code": "type1"
			},
			{
				"system": "http://part1/",
				"code": "other1"
			}
		]
	},
	"owner": {
		"reference": "Organization/org1"
	},
	"location": {
		"reference": "Location/loc1"
	}
}
```

<div class="code-sample">
    <div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "Accept: application/json" -H "ESANTE-API-KEY: {{site.ans.api_key }}" -X PUT -d '{"resourceType": "Device","id": "device1","meta": {"versionId": "2","lastUpdated": "2022-10-03T15:51:01.810+02:00","profile": ["https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-Device"]},"identifier": [{"system": "http://samplesysyem","value": "1"}],"status": "active","manufacturer": "man1","deviceName": [{"name": "demo-ans"}],"modelNumber": "model11664805061717","type": {"coding": [{"system": "http://types/","code": "type1"},{"system": "http://part1/","code": "other1"}]},"owner": {"reference": "Organization/org1"},"location": {"reference": "Location/loc1"}}' {{site.ans.api_url}}/fhir/Device
{% endhighlight %}
    </div>
</div>
