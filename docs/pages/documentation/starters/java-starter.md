---
layout: documentation
title: Mettre en place un projet Java
---

Cette partie montre comment ajouter à un projet Java des appels à l'API.

Si vous n'avez pas d'API Key, suivez la [procédure]({{ '/pages/quick-start/readme' | relative_url}}).

NOTE| Dans nos différents exemples, nous utilisons maven et la librairie Hapi. FHIR reste une API HTTP JSON/XML  qui pourra être appelée avec d'autres techniques.

## Dépendances maven

Pour l'exemple, le projet est un projet java maven. Nous utilisons la librairie [Java Hapi](https://hapifhir.io/) qui permet entre autres de faire des appels FHIR.

Pour utiliser les livrairies Hapi, nous allons ajouter au fichier pom.xml les dépendances suivantes: 

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

## Configuration du client HTTP FHIR avec HAPI

Par rapport à l'utilisation de base du client HAPI, nous spécifions un Intercepeur afin d'ajouter l'API Key d'authentification. 

Voici un exemple nominal: 


<div class="code-sample"><div class="tab-content" data-name="java">
{% highlight java %}
// register the interceptor only one time:
var client = ctx.newRestfulGenericClient("https://ans.com/fhir");
client.registerInterceptor(new IClientInterceptor() {
    @Override
    public void interceptRequest(IHttpRequest iHttpRequest) {
        iHttpRequest.addHeader("ESANTE-API-KEY", "XXXX-XXXXX-XXXXX");
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

A noter que la création du client est coûteuse, nous recommandons de conserver le client pour plusieurs appels.

La document Hapi est très riche sur le fonctionnement de son client, vous pourrez trouver différents usages: [Documentation Client HAPI](https://hapifhir.io/hapi-fhir/docs/client/generic_client.html)


<br/>

## Autre langage
<div class="text-right">
    <a href="php-starter.html">
        <button type="button" class=" btn btn--plain btn--primary btn--icon-before">
            Démarrer en PHP
        </button>
    </a>
</div>