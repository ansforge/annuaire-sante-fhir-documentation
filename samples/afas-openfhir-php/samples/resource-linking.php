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
    /** @var  $oneResource  \DCarbone\PHPFHIRGenerated\R4\PHPFHIRContainedTypeInterface */
    $oneResource = $entry->getResource();
    switch ($oneResource->_getFHIRTypeName()):
        case 'PractitionerRole':
            // TODO treatment
            /** @var  $oneResource  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitionerRole */
            echo $oneResource->getId();
            break;
        case 'Practitioner':
            // TODO treatment
            /** @var  $oneResource  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIRPractitioner */
            echo $oneResource->getId();
            break;
        case 'Organization':
            // TODO treatment
            /** @var  $oneResource  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRDomainResource\FHIROrganization */
            echo $oneResource->getId();
            break;
        default:
            // TODO treatment
            break;
    endswitch;
}