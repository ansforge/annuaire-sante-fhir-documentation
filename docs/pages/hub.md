---
layout: default
title: Documentation de l'API FHIR d'IRIS DP
---

<div>
    L'API FHIR d'IRIS DP de l'ANS offre une solution pour l'accès aux données de santé.
</div>
<div>
    Vous trouverez ci-dessous l'ensemble de la documentation permettant de démarrer un nouveau projet selon le langage de programmation souhaité.
</div>
<div class="mb-2">
    Vous trouverez aussi des exemples de requêtes pour les différentes ressources mises à disposition.
</div>

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3>Démarrer</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[2].subfolderitems[0].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Ressources FHIR</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[2].subfolderitems[1].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Techniques avancées</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[2].subfolderitems[2].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
</div>

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3><a id="usecase"></a>Cas d'utilisation</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[3].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
</div>
