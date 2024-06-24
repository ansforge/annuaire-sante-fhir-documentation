---
layout: menu-version-1
title: Utilisation de C#
subTitle: Intégration FHIR
---

Ce guide décrit comment intégrer l'API à un projet .Net Core.

Si vous n'avez pas de clé d'API, veuillez suivre la procédure décrite [ici]({{ '/pages/guide/version-1/integration-fhir/integration-dotnet.html'}}).

NOTE| Dans nos différents exemples, nous utilisons maven et la librairie développée par Firely Hl7.Fhir.R4. FHIR reste une API HTTP JSON/XML  qui pourra être appelée avec d'autres techniques.


### Dépendance

Ajoutez la dépendance Hl7.Fhir.R4 à votre projet. Nous choisirons la version R4 car l'api proposée est basée sur FHIR R4.

```
dotnet add package Hl7.Fhir.R4 --version 4.3.0
```


&nbsp;

### Configuration du client HTTP FHIR avec HAPI

Par rapport à l'utilisation de base du client Fhir, nous spécifions un HttpClientHandler afin d'ajouter l'API Key d'authentification. 

Voici un exemple nominal: 
<div class="code-sample"><div class="tab-content" data-name="C#">
{% highlight csharp %}
// class to add the security token in the header:
public class AuthorizationMessageHandler : HttpClientHandler
{
    protected async override System.Threading.Tasks.Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, CancellationToken cancellationToken)
    {
        request.Headers.Add("ESANTE-API-KEY", "{{site.ans.demo_key }}");
        return await base.SendAsync(request, cancellationToken);
    }
}

// client creation:
var settings = new FhirClientSettings
{
Timeout = 40000,
PreferredFormat = ResourceFormat.Json,
VerifyFhirVersion = false,
};
var handler = new AuthorizationMessageHandler();
var client = new FhirClient("{{site.ans.api_url}}/fhir/", settings, handler);

// and then use the client:
var result = client.Search<Device>();

{% endhighlight %}
</div></div>


Vous retrouverez toute la documentation de ce client sur le site de la librairie Firely HL7 FHIR SDK in .NET :
* [Site du SDK](https://fire.ly/products/firely-net-sdk/)
* [Documentation](https://docs.fire.ly/projects/Firely-NET-SDK/en/latest/)

