<?php
/**
 * This file contains all samples for the FHIR Practitioner Api of the IRIS DP service
 *
 */


include_once 'includes.php';


echo "Find all:\n";
$response = $client->request('GET', '/fhir/v1/Practitioner');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()."\n");
}

echo "Find by identifier:\n";
$response = $client->request('GET', '/fhir/v1/Practitioner?identifier=0012807590%2C810000005479');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()."\n");
}

echo "Find by name:\n";
$response = $client->request('GET', '/fhir/v1/Practitioner?name=MME');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." name=".$practitioner->getName()."\n");
}

echo "Find by active:\n";
$response = $client->request('GET', '/fhir/v1/Practitioner?active=true');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: name=".$practitioner->getName()." | active=". $practitioner->getActive(). "\n");
}

echo "Find by last updated:\n";
$response = $client->request('GET', '/fhir/v1/Practitioner?_lastUpdated=ge2022-08-08T06%3A47%3A02');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitioners = $parser->parse((string) $response->getBody());
foreach($practitioners->getEntry() as $entry){
    /** @var  $practitioner  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
    $practitioner = $entry->getResource();

    echo("Practitioner found: id=".$practitioner->getId()." | lastUpdate=".$practitioner->getMeta()->getLastUpdated()."\n");
}