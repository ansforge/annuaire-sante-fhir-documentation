---
layout: menu-version-1
title: Guide de démarrage rapide avec l'API
---

Vous pouvez utiliser cette collection Postman avec des exemples d'appels FHIR pour tester l'API.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/8078261-a496d05a-e735-4211-9844-31b45ba5e6dd?action=collection%2Ffork&collection-url=entityId%3D8078261-a496d05a-e735-4211-9844-31b45ba5e6dd%26entityType%3Dcollection%26workspaceId%3D0376a555-c1a8-4912-9cb4-4945375d85e8#?env%5BProd%5D=W3sia2V5IjoiYXBpX2tleSIsInZhbHVlIjoiWFhYWC1YWFhYLVhYWFgtWFhYWCIsImVuYWJsZWQiOnRydWUsInR5cGUiOiJkZWZhdWx0Iiwic2Vzc2lvblZhbHVlIjoiWFhYWC1YWFhYLVhYWFgtWFhYWCIsInNlc3Npb25JbmRleCI6MH0seyJrZXkiOiJhcGlfdXJsIiwidmFsdWUiOiJodHRwczovL2dhdGV3YXkuYXBpLmVzYW50ZS5nb3V2LmZyL2ZoaXIiLCJlbmFibGVkIjp0cnVlLCJ0eXBlIjoiZGVmYXVsdCIsInNlc3Npb25WYWx1ZSI6Imh0dHBzOi8vZ2F0ZXdheS5hcGkuZXNhbnRlLmdvdXYuZnIvZmhpciIsInNlc3Npb25JbmRleCI6MX1d)


### Configuration de la clé d'API

Une fois le projet importé dans votre espace Postman, vous devez renseigner les variables se trouvant dans le menu "Environnements > Prod".

<p align="center">
  <img src="img/postman-config.png" style="width:100%;">
</p>

Voici les variables utilisées dans le projet Postman: 

|Variable|Description|Valeur|
|---|---|---|
|api_key|Clé d'API (Si vous n'avez pas de clé, veuillez suivre la procédure décrite [ici](/annuaire-sante-fhir-documentation/pages/quick-start/quick-start.html))|Saisissez votre clé|
|api_url|L’url d’accès à l’API	|https://gateway.api.esante.gouv.fr/fhir|
