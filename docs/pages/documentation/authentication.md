---
layout: documentation
title: Autetification
---

L'autentification se fait par un jeton positioné dans le header.

Pour chaque requête vous devez positionner ce header : 



Voir la section [Quickstart]({{ '/pages/quick-start/readme' | relative_url}}) pour obtenir le jeton.

{% tabs auth %}
{% tab auth bash %}
```bash
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" https://ans.com/fhir/metadata?_pretty=true&_format=json
```()
{% endtab %}
{% tab auth java/HAPI %}
```java
// register the interceptor only one time:
var client = ctx.newRestfulGenericClient("https://ans.com/fhir");
client.registerInterceptor(new IClientInterceptor() {
    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {
        iHttpRequest.addHeader("E-SANTE-API", "XXXX-XXXXX-XXXXX");
    }
    @Override
    public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {}
});
// and then use the client:
var conf = client
   .capabilities()
   .ofType(CapabilityStatement.class)
   .execute();
```
{% endtab %}
{% tab auth Php/Guzzle %}
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
$object = $parser->parse((string) $response->getBody());
```
{% endtab %}




{% endtabs %}