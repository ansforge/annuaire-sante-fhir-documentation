---
layout: documentation
title: Mettre en place un projet PHP
---

Cette partie montre comment ajouter à un projet PHP des appels à l'api de l'annuaire santé.

Si vous n'avez pas de jeton, suivez la [procédure]({{ '/pages/quick-start/readme' | relative_url}}).

NOTE| Dans nos différents exemples, nous utilisons composer et la librairie dcarbone/php-fhir pour FHIR et Guzzle pour le REST. FHIR reste une api HTTP JSON/XML  qui pourra être appelée avec d'autres techniques.

## Dépendances composer

dcarbone/php-fhir permet de parser et typer les objets FHIR. Cela pourra être utilise par exemple pour la complétion. 


{% tabs phpstarter01 %}
{% tab phpstarter01 composer %}
```json
{
  "require": {
    "dcarbone/php-fhir-generated": "v2.0.*",
    "guzzlehttp/guzzle": "^7.0"
  }
}
```
{% endtab %}
{% endtabs %}



## Configuration du client HTTP FHIR avec Guzzle

Les requêtes sont des requêtes REST auquels nous précisons un header http. 

Voici un exemple minimal: 

{% tabs phpstarter02 %}
{% tab phpstarter02 PHP %}
```php
require_once '../vendor/autoload.php';
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParser;
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParserConfig;

$config = new PHPFHIRResponseParserConfig([
    'registerAutoloader' => true,
    'sxeArgs' => LIBXML_COMPACT | LIBXML_NSCLEAN
]);
$parser = new PHPFHIRResponseParser($config);


$header = ['E-SANTE-API' => 'XXXX-XXXXX-XXXXX'];
$client = new GuzzleHttp\Client([
    'base_uri' => 'http://host.docker.internal:8080',
    'timeout'  => 2.0,]);

// Make requests:

$response = $client->request('GET', '/fhir/v1/metadata');
/** @var  $object  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRCapabilityStatement*/
$capabilityStatement = $parser->parse((string) $response->getBody());

```
{% endtab %}
{% endtabs %}


