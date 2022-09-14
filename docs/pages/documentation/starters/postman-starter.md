---
layout: documentation
title: Démarrer avec Postman
---

Vous pouvez utiliser cette collection Postman pour tester l'API via des exemples d'appels FHIR.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/8078261-a496d05a-e735-4211-9844-31b45ba5e6dd?action=collection%2Ffork&collection-url=entityId%3D8078261-a496d05a-e735-4211-9844-31b45ba5e6dd%26entityType%3Dcollection%26workspaceId%3D0376a555-c1a8-4912-9cb4-4945375d85e8#?env%5BProd%5D=W3sia2V5IjoiYXBpX2tleSIsInZhbHVlIjoiWFhYWC1YWFhYLVhYWFgtWFhYWCIsImVuYWJsZWQiOnRydWUsInR5cGUiOiJkZWZhdWx0Iiwic2Vzc2lvblZhbHVlIjoiWFhYWC1YWFhYLVhYWFgtWFhYWCIsInNlc3Npb25JbmRleCI6MH0seyJrZXkiOiJhcGlfdXJsIiwidmFsdWUiOiJodHRwczovL2dhdGV3YXkuYXBpLmVzYW50ZS5nb3V2LmZyL2ZoaXIiLCJlbmFibGVkIjp0cnVlLCJ0eXBlIjoiZGVmYXVsdCIsInNlc3Npb25WYWx1ZSI6Imh0dHBzOi8vZ2F0ZXdheS5hcGkuZXNhbnRlLmdvdXYuZnIvZmhpciIsInNlc3Npb25JbmRleCI6MX1d)


# Configuration de la clé API

Une fois le projet importé dans votre espace Postman, vous devez renseigner les variables se trouvant dans  "Envionnements > Prod" :
- votre clé API dans la variable "api_key".
- l'url d'accès à l'API dans la variable "api-url" .

![img.png](postman-config.png)

Voici les variables utilisées dans le projet postman: 

| variable | description                                                                                                                                      | valeur                                |
|----------|--------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
|  api_key        | Clé de l'api. Vous pouvez la générer grâce à la procédure [Démarrage rapide](/annuaire-sante-fhir-documentation/pages/quick-start/readme)      | Saisissez votre clé                   |
|  api_url        | L'url de l'api IRIS DP                                                                                                                    | https://gateway.api.esante.gouv.fr/fhir |
