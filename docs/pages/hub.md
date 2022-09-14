---
layout: default
title: Documentation
---

<div>
    Vous trouverez ci-dessous l'ensemble de la documentation permettant de démarrer un nouveau projet en fonction de la technologie utilisée. A ce stade du projet, les technologies disponibles dont : Java et PHP.
</div>
<div class="mb-2">
    Aussi, vous trouverez des exemples de requêtes pour les différentes ressources mises à disposition.
</div>

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3>Implémentation de clients applicatifs</h3>
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
        <h3>Cas d'utilisation</h3>
        <hr aria-hidden="true">
        <div>
            {% for item in site.data.menu.header[3].subfolderitems %}
                <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
            {% endfor %}
        </div>
    </div>
</div>
