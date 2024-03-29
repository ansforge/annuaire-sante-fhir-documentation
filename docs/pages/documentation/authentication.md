---
layout: documentation
title: Authentification
---

L'authentification se fait via une API Key à positionner dans le header.

Pour chaque requête, vous devez positionner ce header : 



Voir la section [Quickstart]({{ '/pages/quick-start/readme' | relative_url}}) pour obtenir l'API Key.

<div class="tab">
<div class="tab-content" data-name="bash">

```bash
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" ""{{site.ans.api_url}}/fhir/metadata?_pretty=true&_format=json"
```

</div>
<div class="tab-content" data-name="java/HAPI">

```java
// register the interceptor only one time:
var client = ctx.newRestfulGenericClient("{{site.ans.api_url}}/fhir");
client.registerInterceptor(new IClientInterceptor() {
    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {
        iHttpRequest.addHeader("ESANTE-API-KEY", "{{site.ans.demo_key }}");
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
</div>
<div class="tab-content" data-name="PHP">

```php
require_once '../vendor/autoload.php';
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParser;
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParserConfig;

$config = new PHPFHIRResponseParserConfig([
    'registerAutoloader' => true,
    'sxeArgs' => LIBXML_COMPACT | LIBXML_NSCLEAN
]);
$parser = new PHPFHIRResponseParser($config);

$header = ['ESANTE-API-KEY' => '{{site.ans.demo_key }}'];
$client = new GuzzleHttp\Client([
    'base_uri' => 'http://host.docker.internal:8080',
    'timeout'  => 2.0,]);

// Make requests: 

$response = $client->request('GET', '/fhir/v1/metadata');
/** @var  $object  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRCapabilityStatement*/
$object = $parser->parse((string) $response->getBody());
```

</div>
</div>
