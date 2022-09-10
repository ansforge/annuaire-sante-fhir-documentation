<?php
/**
 * This file contains all samples for the FHIR PractitionerRole Api of the IRIS DP service
 *
 */


include_once 'includes.php';


echo "Find all:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()." code=".$practitionerRole->getCode()[0]->getCoding()[0]->getCode()."\n");
}

echo "Find one:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole/005-5087586-6923328');
/** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
$practitionerRole = $parser->parse((string) $response->getBody());
echo("Practitioner Role found: id=".$practitionerRole->getId()."\n");


echo "Find by role:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole?role=40&role=E');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    $codes = '';
    $index = 0;
    foreach ($practitionerRole->getCode() as $cc){
        /** @var $cc \DCarbone\PHPFHIRGenerated\R4\FHIRElement\FHIRCodeableConcept */
        foreach ($cc->getCoding() as $c) {
            if($index++>0){
                $codes .= '|';
            }
            $codes .= $c->getSystem().":".$c->getCode();
        }
    }
    echo("Practitioner Role found: id=".$practitionerRole->getId()." codes=".$codes."\n");
}


echo "Find by specialty:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole?role=40&specialty=SCD01');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    $codes = '';
    $index = 0;
    foreach ($practitionerRole->getCode() as $cc){
        /** @var $cc \DCarbone\PHPFHIRGenerated\R4\FHIRElement\FHIRCodeableConcept */
        foreach ($cc->getCoding() as $c) {
            if($index++>0){
                $codes .= '|';
            }
            $codes .= $c->getSystem().":".$c->getCode();
        }
    }
    echo("Practitioner Role found: id=".$practitionerRole->getId()." codes=".$codes."\n");
}


echo "Find by smart card:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole?type-smartcard=CPS');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()."\n");
}

echo "Find by practitioner:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole?practitioner=003-138020');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()." practitioner=". $practitionerRole->getPractitioner()->getReference() ."\n");
}


echo "Find by active:\n";
$response = $client->request('GET', '/fhir/v1/PractitionerRole?active=true');
/** @var  $practitionerRoles  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
foreach($practitionerRoles->getEntry() as $entry){
    /** @var  $practitionerRole  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
    $practitionerRole = $entry->getResource();

    echo("Practitioner Role found: id=".$practitionerRole->getId()." active=". $practitionerRole->getActive() ."\n");
}
