<?php
/**
 * This file contains all samples for the FHIR Organization Api of the IRIS DP service
 *
 */


include_once 'includes.php';


echo "Find all:\n";
$response = $client->request('GET', '/fhir/Organization');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()." name=".$organization->getName()."\n");
}

echo "Find by last updated:\n";
$response = $client->request('GET', '/fhir/Organization?_lastUpdated=ge2022-08-05T14%3A51%3A04');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()." lastUpdate=".$organization->getMeta()->getLastUpdated()."\n");
}


echo "Find by identifier:\n";
$response = $client->request('GET', '/fhir/Organization?identifier=001604103000%2C01603998400%2C001604252500');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()."\n");
}


echo "Find by finess number:\n";
$response = $client->request('GET', '/fhir/Organization?identifier=http%3A%2F%2Ffiness.sante.gouv.fr%7C010000602%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C010000628%2Chttp%3A%2F%2Ffiness.sante.gouv.fr%7C010000735');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: id=".$organization->getId()."\n");
}



echo "Find by type (GEOGRAPHICAL/LEGAL):\n";
$response = $client->request('GET', '/fhir/Organization?type=http%3A%2F%2Finteropsante.org%2Ffhir%2FCodeSystem%2Ffr-v2-3307%7CGEOGRAPHICAL-ENTITY');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()." - type=".$organization->getType()[0]->getCoding()[0]->getCode()."\n");
}


echo "Find by type (\"TRE-R75\"):\n";
$response = $client->request('GET', '/fhir/Organization?type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5%7C82.19Z');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()." - type=".$organization->getType()[0]->getCoding()[0]->getCode()."\n");
}


echo "Find by type (\"TRE_R02-SecteurActivite\"):\n";
$response = $client->request('GET', '/fhir/Organization?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R02-SecteurActivite%2FFHIR%2FTRE-R02-SecteurActivite%7CSA29');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()." - type=".$organization->getType()[0]->getCoding()[0]->getCode()."\n");
}

echo "Find by name:\n";
$response = $client->request('GET', '/fhir/Organization?name%3Acontains=imagerie%2Ccentre');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()."\n");
}



echo "Find by postal code:\n";
$response = $client->request('GET', '/fhir/Organization?name%3Acontains=imagerie%2Ccentre');
/** @var  $organizations  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$organizations = $parser->parse((string) $response->getBody());
foreach($organizations->getEntry() as $entry){
    /** @var  $organization  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
    $organization = $entry->getResource();
    echo("Organization found: name=".$organization->getName()." | zipCode=".$organization->getAddress()[0]->getPostalCode()->getValue()."\n");
}






