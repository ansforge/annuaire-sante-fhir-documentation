<?php
/**
 * This file contains all samples for the FHIR HealthcareService Api of the IRIS DP service
 *
 */


include_once 'includes.php';


echo "Find all:\n";
$response = $client->request('GET', '/fhir/v1/HealthcareService');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    echo("Healthcare Service found: id=".$healthcareService->getId()."\n");
}

echo "Find by identifier:\n";
$response = $client->request('GET', '/fhir/v1/HealthcareService?identifier=52-52-49883');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    echo("Healthcare Service found: id=".$healthcareService->getId()."\n");
}

echo "Find by characteristic (TRE-R276-FormeActivite):\n";
$response = $client->request('GET', '/fhir/v1/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R276-FormeActivite%2FFHIR%2FTRE-R276-FormeActivite%7C07');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    $characteristic = $healthcareService->getCharacteristic()[0]->getCoding()[0]->getSystem() . '|' . $healthcareService->getCharacteristic()[0]->getCoding()[0]->getCode();
    echo("Healthcare Service found: id=".$healthcareService->getId()." | characteristic=". $characteristic ."\n");
}

echo "Find by characteristic (TRE-R209-TypeActivite):\n";
$response = $client->request('GET', '/fhir/v1/HealthcareService?characteristic=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R209-TypeActivite%2FFHIR%2FTRE-R209-TypeActivite%7C11');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    $characteristic = $healthcareService->getCharacteristic()[0]->getCoding()[0]->getSystem() . '|' . $healthcareService->getCharacteristic()[0]->getCoding()[0]->getCode();
    echo("Healthcare Service found: id=".$healthcareService->getId()." | characteristic=". $characteristic ."\n");
}

echo "Find by active:\n";
$response = $client->request('GET', '/fhir/v1/HealthcareService?active=true');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();

    echo("Healthcare Service found: id=".$healthcareService->getId()." | status=". $healthcareService->getActive() ."\n");
}

echo "Find by last updated:\n";
$response = $client->request('GET', '/fhir/v1/HealthcareService?_lastUpdated=ge2022-08-18');
/** @var  $healthcareServices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$healthcareServices = $parser->parse((string) $response->getBody());
foreach($healthcareServices->getEntry() as $entry){
    /** @var  $healthcareService  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRHealthcareService */
    $healthcareService = $entry->getResource();
    echo("Healthcare Service found: id=".$healthcareService->getId()." lastUpdate=". $healthcareService->getMeta()->getLastUpdated() ."\n");
}