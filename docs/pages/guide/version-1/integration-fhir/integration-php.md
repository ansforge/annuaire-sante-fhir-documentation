---
layout: menu-version-1
title: Utilisation de PHP
---

Ce guide décrit comment intégrer l'API à un projet PHP.

Si vous n'avez pas de clé d'API, veuillez suivre la procédure décrite [ici]({{ '/pages/quick-start/quick-start.html' | relative_url}}).

NOTE| Dans nos différents exemples, nous utilisons composer et la librairie dcarbone/php-fhir pour FHIR et Guzzle pour le REST. FHIR reste une API HTTP JSON/XML  qui pourra être appelée avec d'autres techniques.

### Dépendances composer

dcarbone/php-fhir permet de parser et typer les objets FHIR. Cela pourra être utilisé par exemple pour la complétion. 



<div class="code-sample"><div class="tab-content" data-name="composer">
{% highlight php %}
{
  "require": {
    "dcarbone/php-fhir-generated": "v2.0.*",
    "guzzlehttp/guzzle": "^7.0"
  }
}
{% endhighlight %}

</div>
</div>


### Configuration du client HTTP FHIR avec Guzzle

Les requêtes sont des requêtes REST auxquelles nous précisons un header https. 

Voici un exemple nominal : 


<div class="code-sample"><div class="tab-content" data-name="PHP">

{% highlight php %}
<?php
require_once '../vendor/autoload.php';
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParser;
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParserConfig;

$config = new PHPFHIRResponseParserConfig([
    'registerAutoloader' => true,
    'sxeArgs' => LIBXML_COMPACT | LIBXML_NSCLEAN
]);
$parser = new PHPFHIRResponseParser($config);


$header = ['ESANTE-API-KEY' => 'eb2e94fa-ffe6-491f-aa9d-073f6a5a2415'];
$client = new GuzzleHttp\Client([
    'base_uri' => 'https://gateway.api.esante.gouv.fr',
    'timeout'  => 2.0,
    'headers'  => $header]);
{% endhighlight %}

</div></div>


