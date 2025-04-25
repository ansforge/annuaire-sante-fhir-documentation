---
layout: default
title: Documentation
---

<div>
    Vous trouverez ci-dessous l'ensemble de la documentation nécessaire pour démarrer un nouveau projet en fonction de la technologie choisie. Les langages disponibles sont : Java, C# et Python.
</div>
<div class="mb-2">
    Des exemples de requêtes pour les différentes ressources mises à disposition sont également fournis.
</div>

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
    <div class="border rounded col p-2 m-1">
        <h3>Version 2</h3>
        <hr aria-hidden="true">
        <div>
            {% assign guides_menu = site.data.menu.header | where: "title", "Guides" | first %}
            {% if guides_menu %}
                {% assign version_2_menu = guides_menu.subfolderitems | where: "page", "Version 2" | first %}
                {% if version_2_menu %}
                    {% for item in version_2_menu.subfolderitems %}
                        <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
                        {% if item.subfolderitems %}
                            <ul>
                                {% for subitem in item.subfolderitems %}
                                    <li><a href="{{ subitem.url | relative_url }}">{{ subitem.title }}</a></li>
                                {% endfor %}
                            </ul>
                        {% endif %}
                    {% endfor %}
                {% else %}
                    <div>Erreur : Menu "Version 2" introuvable.</div>
                {% endif %}
            {% else %}
                <div>Erreur : Menu principal "Guides" introuvable.</div>
            {% endif %}
        </div>
    </div>
</div>
