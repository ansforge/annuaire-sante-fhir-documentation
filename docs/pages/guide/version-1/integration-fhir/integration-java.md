---
layout: menu-version-1
title: Utilisation de Java
subTitle: Intégration FHIR
---
<!-- <p style="background-color: #ffcccc; border:1px solid grey; padding: 5px; max-width: 790px;">
Cette documentation concerne la version 1 de l'API qui sera prochainement décommissionnée. Nous vous invitons à migrer vers la version 2 de l'API FHIR Annuaire Santé.
</p> -->

Ce guide décrit comment intégrer l'API à un projet Java.

Si vous n'avez pas de clé d'API, veuillez suivre la procédure décrite [ici]({{'/pages/guide/version-1/getting-started/get-api-key.html'}}).

NOTE| Dans nos différents exemples, nous utilisons maven et la librairie Hapi. FHIR reste une API HTTP JSON/XML  qui pourra être appelée avec d'autres techniques.

### Dépendances maven

Pour l'exemple, le projet est un projet java maven. Nous utilisons la librairie [Java Hapi](https://hapifhir.io/){:target="_blank"} qui permet entre autres de faire des appels FHIR.

Pour utiliser les librairies Hapi, nous allons ajouter les dépendances suivantes dans le fichier pom.xml : 

```xml
<properties>
    <hapifhir_version>6.0.0</hapifhir_version>
</properties>
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13</version>
        <scope>test</scope>
    </dependency>
    <!-- Hapi classes -->
    <dependency>
        <groupId>ca.uhn.hapi.fhir</groupId>
        <artifactId>hapi-fhir-base</artifactId>
        <version>${hapifhir_version}</version>
    </dependency>
    <dependency>
        <groupId>ca.uhn.hapi.fhir</groupId>
        <artifactId>hapi-fhir-client</artifactId>
        <version>${hapifhir_version}</version>
    </dependency>
    <dependency>
        <groupId>ca.uhn.hapi.fhir</groupId>
        <artifactId>hapi-fhir-structures-r4</artifactId>
        <version>${hapifhir_version}</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
    </dependency>
</dependencies>
```

&nbsp;

### Configuration du client HTTP FHIR avec HAPI

Par rapport à l'utilisation de base du client HAPI, nous spécifions un Intercepeur afin d'ajouter l'API Key d'authentification. 

Voici un exemple nominal: 
<div class="code-sample"><div class="tab-content" data-name="java">
{% highlight java %}
// register the interceptor only one time:
var client = ctx.newRestfulGenericClient("{{site.ans.api_url}}/fhir");
client.registerInterceptor(new IClientInterceptor() {
    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {
        iHttpRequest.addHeader("ESANTE-API-KEY", "{{site.ans.api_key }}");
    }
    @Override
    public void interceptResponse(IHttpResponse iHttpResponse) throws IOException {}
});
// and then use the client:
var conf = client
   .capabilities()
   .ofType(CapabilityStatement.class)
   .execute();
{% endhighlight %}
</div></div>

NOTE| La création du client est coûteuse, nous recommandons de conserver le client pour plusieurs appels.

La documentation Hapi est très riche sur le fonctionnement de son client, vous pourrez trouver différents usages: [Documentation Client HAPI](https://hapifhir.io/hapi-fhir/docs/client/generic_client.html){:target="_blank"}

