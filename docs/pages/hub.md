---
layout: default
title: Documentation
---

<div>
    Vous trouverez ci-dessous l'ensemble de la documentation nécessaire pour démarrer un nouveau projet en fonction de la technologie choisie. Les langages disponibles sont : Java, C# et PHP.
</div>
<div class="mb-2">
    Des exemples de requêtes pour les différentes ressources mises à disposition sont également fournis.</div>

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3>Démarrage rapide</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[1].subfolderitems[2].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Ressources FHIR</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[1].subfolderitems[3].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Techniques avancées</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[1].subfolderitems[4].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
</div>

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3>Cas d'utilisation</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[2].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
</div>
