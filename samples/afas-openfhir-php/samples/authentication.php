<?php
include_once 'includes.php';

// Make requests:
$response = $client->request('GET', '/fhir/v1/metadata');
/** @var  $object  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRCapabilityStatement*/
$capabilityStatement = $parser->parse((string) $response->getBody());
print_r($capabilityStatement);