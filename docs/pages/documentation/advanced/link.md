---
layout: documentation
title: Liaison entre plusieurs ressources
subTitle: Ressources
---

Voici des exemples de requêtes permettant de récupérer plusieurs ressources liées en un seul appel.
Il est par exemple possible de récupérer les ressources "HealthcareServices" liées à une ressource "PractitionerRole".


## Trouver l'Organization et le Practitioner d'un PractitionerRole

Le but ici étant de récupérer les ressources "Organization" et "Practitioner" liées à un PractitionerRole.

Pour cela, nous devons utiliser l'inclusion ("include").

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir/PractitionerRole?_include=PractitionerRole%3Apractitioner&_include=PractitionerRole%3Aorganization&_count=10"
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
var client = createClient();

// The practitioner role know the practitioner and the organization, so the main request is on PractitionerRole and then we ask
// the server to include related Organization and Practitioner:

var bundleWithAllResources = (Bundle) client.search().forResource(PractitionerRole.class)
    // inclusion:
    .include(PractitionerRole.INCLUDE_PRACTITIONER.asNonRecursive())
    .include(PractitionerRole.INCLUDE_ORGANIZATION.asNonRecursive())
    .count(10)
    .execute();
{% endhighlight %}
</div>
<div class="tab-content" data-name="PHP">
{% highlight php %}
$response = $client->request('GET', '/fhir/v1/PractitionerRole?_include=PractitionerRole:practitioner&_include=PractitionerRole:organization');
/** @var  $devices  \DCarbone\PHPFHIRGenerated\R4\FHIRResource\FHIRBundle*/
$practitionerRoles = $parser->parse((string) $response->getBody());
{% endhighlight %}
</div>

</div>

La méthode ```.count()``` permet de spécifier le nombre d'entrées maximum à récupérer.
Attention, la donnée retourner peut comporter plus que 10 items car les données liées sont retournées à plat dans cette même liste de données.

Il convient donc ensuite de tester le type de donnée afin de pouvoir l'interpréter :

<div class="code-sample">
<div class="tab-content" data-name="java">
{% highlight java %}
// the bundle contains the page of PractitionerRole elements and additional elements for related Organization and Practitioner:
for(var entry : bundleWithAllResources.getEntry()){
    switch (entry.getResource().fhirType()) {
        case "PractitionerRole":
            // TODO treatment
            break;
        case "Practitioner":
            // TODO treatment
            break;
        case "Organization":
            // TODO treatment
            break;
        default:
            // TODO treatment
            break;
    }
}

// if you want to know which Practitioner/Organization is corresponding to a PractitionerRole you can do it with practitioner/organization field in the PractitionerRole resource:
var practitionerRoles = bundleWithAllResources.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "PractitionerRole".equals(e.fhirType())).map(PractitionerRole.class::cast).collect(Collectors.toList());
var practitioners = bundleWithAllResources.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Practitioner".equals(e.fhirType())).map(Practitioner.class::cast).collect(Collectors.toList());
var organizations = bundleWithAllResources.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).filter(e -> "Organization".equals(e.fhirType())).map(Organization.class::cast).collect(Collectors.toList());
{% endhighlight %}
</div>


<div class="tab-content" data-name="PHP">
{% highlight php %}
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
{% endhighlight %}
</div>
</br>
## Trouver l'ensemble des EG d'un EJ

Le but ici étant de récupérer tous les établissements géographiques (EG) rattachés à une même entité juridique (EJ)
![image](https://user-images.githubusercontent.com/70761903/234018188-76006340-5235-44ae-8040-1d7d521117d2.png)


Pour cela, nous devons utiliser l'inclusion ("revinclude").

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "ESANTE-API-KEY: {{site.ans.demo_key }}" "{{site.ans.api_url}}/fhir//Organization?identifier=010000339&_revinclude=Organization%3Apartof
{% endhighlight %}
</div>
</div>
