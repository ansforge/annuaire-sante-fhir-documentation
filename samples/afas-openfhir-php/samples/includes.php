<?php
require_once '../vendor/autoload.php';
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParser;
use DCarbone\PHPFHIRGenerated\R4\PHPFHIRResponseParserConfig;

$config = new PHPFHIRResponseParserConfig([
    'registerAutoloader' => true,
    'sxeArgs' => LIBXML_COMPACT | LIBXML_NSCLEAN
]);
$parser = new PHPFHIRResponseParser($config);


$header = ['ESANTE-API-KEY' => 'eb2e94fa-ffe6-491f-aa9d-073f6a5a2415'];
$client = new GuzzleHttp\Client([
    'base_uri' => 'https://gateway.api.esante.gouv.fr',
    'timeout'  => 20.0,
    'headers'  => $header]);


function getExtensionByUrl(string $urlToFind, array $extensions){
    foreach($extensions as $extension){
        /** @var $extension DCarbone\PHPFHIRGenerated\R4\FHIRElement\FHIRExtension */
        if($urlToFind == $extension->getUrl()){
            return $extension;
        }
    }
    return null;
}