<?php
/**
 * This file contains all samples for the FHIR Device Api of the IRIS DP service
 *
 */


include_once 'includes.php';


echo "Find all:\n";
$response = $client->request('GET', '/fhir/v1/Device');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    $extArhgos = getExtensionByUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS", $device->getExtension());
    echo("Device found: id=".$device->getId()." AuthorizationARHGOS=".$extArhgos->getValueString()."\n");
}


echo "Find by last updated:\n";
$response = $client->request('GET', '/fhir/v1/Device?_lastUpdated=ge2022-08-07T14%3A51%3A04');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    $extArhgos = getExtensionByUrl("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS", $device->getExtension());
    echo("Device found: id=".$device->getId()." AuthorizationARHGOS=".$extArhgos->getValueString()."\n");
}



echo "Find by ARHGOS number:\n";
$response = $client->request('GET', '/fhir/v1/Device?number-authorization-arhgos=93-93-67204');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()." type=".$device->getType()->getCoding()[0]->getCode()."\n");
}


echo "Find by type:\n";
$response = $client->request('GET', '/fhir/v1/Device?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R272-EquipementMaterielLourd%2FFHIR%2FTRE-R272-EquipementMaterielLourd%7C05602');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()." type=".$device->getType()->getCoding()[0]->getCode()."\n");
}



echo "Find by identifier:\n";
$response = $client->request('GET', '/fhir/v1/Device?identifier=32-31-1156%2C93-93-4364');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()."\n");
}

echo "Find by status:\n";
$response = $client->request('GET', '/fhir/v1/Device?status=active');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$devices = $parser->parse((string) $response->getBody());
foreach($devices->getEntry() as $entry){
    /** @var  $device  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRDevice */
    $device = $entry->getResource();

    echo("Device found: id=".$device->getId()." | status=".$device->getStatus()->getValue()."\n");
}



