<?php
/**
 * This file contains samples that show different usages of parameters
 *
 */


include_once 'includes.php';


echo "String type without modifier:\n";
$response = $client->request('GET', '/fhir/v1/Organization?name=Renard');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()."\n");
}