---
layout: default
title: Demande de création d'un WebHook de Subscription
subTitle: Subscription Client
---

## Demande d'une Subscription

La souscription vous permet de vous abonner aux changements potentiels des données du serveur FHIR.

Afin de créer une souscription, vous devez envoyer une demande aux administrateurs ANS :

```TODO à compléter sur le processus de demande```

### Processus
Voici plus en détails le fonctionnement du processus de gestion des souscriptions : 

<p><img src="/annuaire-sante-fhir-documentation/assets/img/schema_subscription.jpg" alt="" /></p>


<br/>


## Recevoir l'appel WebHook
La dernière étape est la vérification de la réception de l'appel HTTP envoyé par le serveur FHIR lors de la mise 
à jour des données.

### Exemple de requête avec Payload
<div class="code-sample">
    <div class="tab-content" data-name="Java">
{% highlight java %}

package fr.ans.sample;

import org.apache.commons.codec.digest.HmacUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
* A simple subscription controller to handle web hooks
*
*/
@WebServlet(urlPatterns = "/hook/subscriptions")
public class SubscriptionController  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var hmac = req.getHeader("x-esante-api-hmac-sha256").split("=")[1];
        var text = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));

        var signature = new HmacUtils("HmacSHA256", "123456").hmacHex(text);

        System.out.println("Signature ok ? " +  signature.equals(hmac));
        System.out.println(text);
    }
}
{% endhighlight %}
</div>
</div>

### Exemple de requête sans Payload
<div class="code-sample">
    <div class="tab-content" data-name="Java">
{% highlight java %}

package fr.ans.sample;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
* A simple subscription controller to handle web hooks without payload
*
*/
@WebServlet(urlPatterns = "/hook/subscriptions-no-payload")
public class SubscriptionNoPayloadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var dt = req.getHeader("X-Esante-Api-Update-Date");
        var text = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        System.out.println("Notification vide recue: " + text);
        System.out.println("Requête à effectuer : http://localhost:8080/fhir/Device?_format=json&_pretty=true&_since="+dt);
    }
}
{% endhighlight %}
</div>
</div>

<br/>

## Gestion des erreurs

Lorsque qu'une souscription déclenche l'envoi d'un WebHook, une gestion des erreurs lors des appels Http est prise en compte.

Si l'appel s'effectue correctement (retour HTTP 2XX OK de votre serveur), alors la souscription est valide et reste à l'écoute des changements.

Si l'appel ne s'effectue pas correctement. Alors une mécanique permettant de réessayer l'envoi a été mise en place : 
<div class="wysiwyg" markdown="1">
* l'appel va être relancé plusieurs fois
* si un des essais est de nouveau en succès, alors la mécanique s'arrête et la souscription redevient valide
* au bout d'un certain nombre d'erreurs, la souscription est alors placée en erreur et aucune autre tentative d'envoi ne sera effectuée pour cette souscription
</div>


<br/>

## Mise à jour d'une donnée

Lors de la mise à jour d'une donnée, vous serez alors notifier sur le endpoint que vous avez déclaré lors de votre demande auprès de l'administrateur ANS.