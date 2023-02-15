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

    echo("Organization found: name=".$organization->getName()."\n");
}

echo "String type with modifier contains:\n";
$response = $client->request('GET', '/fhir/v1/Organization?name%3Acontains=EURL');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: name=".$organization->getName()."\n");
}

echo "String type with modifier exact:\n";
$response = $client->request('GET', '/fhir/v1/Organization?name%3Aexact=Gautier%20EURL');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." name=".$organization->getName()."\n");
}


echo "Token type:\n";
$response = $client->request('GET', '/fhir/v1/Organization?identifier=01604103000');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." name=".$organization->getName()."\n");
}


echo "Date type:\n";
$response = $client->request('GET', '/fhir/v1/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." lastUpdate=".$organization->getMeta()->getLastUpdated()."\n");
}

echo "Multiple parameters with logical AND:\n";
$response = $client->request('GET', '/fhir/v1/Organization?name%3Acontains=Renard&name%3Acontains=et');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." | name=".$organization->getName()."\n");
}

echo "Multiple parameters with logical OR:\n";
$response = $client->request('GET', '/fhir/v1/Organization?name%3Acontains=Renard%2Cet');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();

    echo("Organization found: id=".$organization->getId()." | name=".$organization->getName()."\n");
}