---
layout: menu-version-2 
title: Utilisation de Python 
subTitle: Intégration FHIR
---
Ce guide décrit comment intégrer l'API FHIR à un projet Python.

Si vous n'avez pas de clé d'API, veuillez suivre la procédure décrite [ici]({{'/pages/guide/version-2/getting-started/get-api-key.html'}}).

NOTE| Dans nos différents exemples, nous utilisons pip et la librairie fhir.resources. FHIR reste une API HTTP JSON/XML qui pourra être appelée avec d'autres techniques.

### Dépendances Python
Pour l'exemple, le projet est un projet Python utilisant pip pour la gestion des dépendances. Nous utilisons la librairie fhir.resources{:target="_blank"} qui permet entre autres de faire des appels FHIR.

Pour utiliser les librairies fhir.resources, nous allons ajouter les dépendances suivantes dans le fichier requirements.txt :

<div class="code-sample"><div class="tab-content" data-name="python">
fhir.resources==6.0.0
requests==2.25.1
</div></div>


Configuration du client HTTP FHIR avec fhir.resources
Par rapport à l'utilisation de base du client fhir.resources, nous spécifions un intercepteur afin d'ajouter l'API Key d'authentification.

Voici un exemple nominal :

<div class="code-sample"><div class="tab-content" data-name="python"> {% highlight python %} import requests from fhir.resources.fhirtypes import CapabilityStatement

## Configuration du client

api_url = "{{site.ans.api_url}}/fhir"
api_key = "{{site.ans.api_key}}"

headers = {
"ESANTE-API-KEY": api_key,
"Content-Type": "application/json"  
}

## Fonction pour effectuer une requête FHIR

def fetch_capability_statement():
response = requests.get(f"{api_url}/metadata", headers=headers)
if response.status_code == 200:
return CapabilityStatement(**response.json())
else:
response.raise_for_status()

## Utilisation du client

capability_statement = fetch_capability_statement()
print(capability_statement)
{% endhighlight %}

</div></div>

NOTE| La création du client est coûteuse, nous recommandons de conserver le client pour plusieurs appels.
