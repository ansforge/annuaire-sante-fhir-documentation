<?php
require_once '../vendor/autoload.php';
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParser;
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParserConfig;

$config = new PHPFHIRResponseParserConfig([
    'registerAutoloader' => true,
    'sxeArgs' => LIBXML_COMPACT | LIBXML_NSCLEAN
]);
$parser = new PHPFHIRResponseParser($config);


$header = ['ESANTE-API-KEY' => 'XXXX-XXXXX-XXXXX'];
$client = new GuzzleHttp\Client([
    'base_uri' => 'http://host.docker.internal:8080',
    'timeout'  => 2.0,]);

// Make requests:

$response = $client->request('GET', '/fhir/v1/metadata');
/** @var  $object  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRCapabilityStatement*/
$capabilityStatement = $parser->parse((string) $response->getBody());
