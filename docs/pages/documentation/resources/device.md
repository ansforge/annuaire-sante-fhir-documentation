---
layout: documentation
title: Device
subTitle: Ressources
---

Voici des exemples de requêtes sur les matériels qui sont représentées dans le serveur FHIR par la ressource Device


## Rechercher tout

Pour rechercher du matériel, il faut faire une recherche sur le endpoint FHIR Device

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/Device
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

var bundle = client.search().forResource(Device.class).returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data:
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} name={}", device.getIdElement().getIdPart(), device.getDeviceNameFirstRep().getName());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Device found: id=device-147 name=Ronstr
Device found: id=device-389 name=Andala
Device found: id=device-146 name=Keylex
```

<br>



## Rechercher le matériel modifié depuis une date donnée

Pour rechercher du matériel, il faut faire une recherche sur le endpoint FHIR Device

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/Device?_lastUpdated=ge2022-08-07T14%3A51%3A04
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the date search parameter :
var dateParam = new DateClientParam("_lastUpdated");

var bundle = client.search()
.forResource(Device.class)
.where(dateParam.afterOrEquals().second("2022-08-07T14:51:04"))
.returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data:
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} name={}", device.getIdElement().getIdPart(), device.getDeviceNameFirstRep().getName());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Device found: id=device-385 name=Tin
Device found: id=device-58 name=Cookley
Device found: id=device-142 name=Redhold
```

<br>


## Rechercher le matériel de type "Scanographe à utilisation médicale"

Pour rechercher du matériel, il faut faire une recherche sur le endpoint FHIR Device

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/Device?type=https%3A%2F%2Fmos.esante.gouv.fr%2FNOS%2FTRE_R272-EquipementMaterielLourd%2FFHIR%2FTRE-R272-EquipementMaterielLourd%7C05602
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the type search parameter :
var typeSearchClause = Organization.TYPE.exactly().systemAndValues(
"https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd", "05602");

var bundle = client.search()
    .forResource(Device.class)
    .where(typeSearchClause)
    .returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data:
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} type={}", device.getIdElement().getIdPart(), device.getType().getCodingFirstRep().getDisplay());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Device found: id=device-147 type=Scanographe à utilisation médicale
Device found: id=device-389 type=Scanographe à utilisation médicale
```

<br>


## Rechercher le matériel par identifiant

Pour rechercher du matériel par identifiant, il faut faire une recherche sur le endpoint FHIR Device

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/Device?identifier=dev-device-147%2Cdev-device-388
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the identifier search parameters :
var identifierParams = Device.IDENTIFIER.exactly().codes("dev-device-147", "dev-device-388");

var bundle = client.search()
    .forResource(Device.class)
    .where(identifierParams)
    .returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={}", device.getIdentifierFirstRep().getValue());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Device found: id=dev-device-147
Device found: id=dev-device-388
```

<br>


## Rechercher le matériel qui dispose d'un statut "actif"

Pour rechercher du matériel par statut, il faut faire une recherche sur le endpoint FHIR Device

<div class="code-sample">
<div class="tab-content" data-name="curl">
{% highlight bash %}
curl -H "E-SANTE-API: XXXX-XXXXX-XXXXX" http://localhost:8083/fhir/Device?status=active
{% endhighlight %}
</div>
<div class="tab-content" data-name="java">
{% highlight java %}
// create the client:
var client = FhirTestUtils.createClient();

// create the active search parameters :
var activeParams = Device.STATUS.exactly().code(Device.FHIRDeviceStatus.ACTIVE.toCode());

var bundle = client.search()
        .forResource(Device.class)
        .where(activeParams)
        .returnBundle(Bundle.class).execute();

for (var deviceEntry : bundle.getEntry()) {
    // print Device data
    var device = (Device) deviceEntry.getResource();
    logger.info("Device found: id={} | status={}", device.getIdElement().getIdPart(), device.getStatus().getDisplay());
}
{% endhighlight %}
</div>

</div>

Devrait vous retourner une réponse du type:

```bash
Device found: id=device-147 | status=Active
Device found: id=device-389 | status=Active
Device found: id=device-146 | status=Active
```

<br>




