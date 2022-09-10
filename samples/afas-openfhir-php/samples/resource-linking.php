<?php
/**
 * This file contains samples that show the resource linking
 *
 */


include_once 'includes.php';


echo "Find with include:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole?_include=PractitionerRole:practitioner&_include=PractitionerRole:organization');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()." code=".$practitionerRole->getCode()[0]->getCoding()[0]->getCode()."\n");
}