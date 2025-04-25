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
            {% assign guides_menu = site.data.menu.header | where: "title", "Guides" | first %}
            {% if guides_menu %}
                {% assign version_2_menu = guides_menu.subfolderitems | where: "page", "Version 2" | first %}
                {% if version_2_menu %}
                    {% assign quickstart_menu = version_2_menu.subfolderitems | where: "page", "Démarrage rapide" | first %}
                    {% if quickstart_menu %}
                        {% for item in quickstart_menu.subfolderitems %}
                            <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
                        {% endfor %}
                    {% else %}
                        <div>Erreur : Menu "Démarrage rapide" introuvable.</div>
                    {% endif %}
                {% else %}
                    <div>Erreur : Menu "Version 2" introuvable.</div>
                {% endif %}
            {% else %}
                <div>Erreur : Menu principal "Guides" introuvable.</div>
            {% endif %}
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Ressources FHIR</h3>
        <hr aria-hidden="true">
        <div>
            {% assign guides_menu = site.data.menu.header | where: "title", "Guides" | first %}
            {% if guides_menu %}
                {% assign version_2_menu = guides_menu.subfolderitems | where: "page", "Version 2" | first %}
                {% if version_2_menu %}
                    {% assign resources_menu = version_2_menu.subfolderitems | where: "page", "Ressources" | first %}
                    {% if resources_menu %}
                        {% for item in resources_menu.subfolderitems %}
                            <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
                        {% endfor %}
                    {% else %}
                        <div>Erreur : Menu "Ressources" introuvable.</div>
                    {% endif %}
                {% else %}
                    <div>Erreur : Menu "Version 2" introuvable.</div>
                {% endif %}
            {% else %}
                <div>Erreur : Menu principal "Guides" introuvable.</div>
            {% endif %}
        </div>
    </div>
    <div class="border rounded col p-2 m-1">
        <h3>Techniques avancées</h3>
        <hr aria-hidden="true">
        <div>
            {% assign guides_menu = site.data.menu.header | where: "title", "Guides" | first %}
            {% if guides_menu %}
                {% assign version_2_menu = guides_menu.subfolderitems | where: "page", "Version 2" | first %}
                {% if version_2_menu %}
                    {% assign advanced_menu = version_2_menu.subfolderitems | where: "page", "Techniques avancées" | first %}
                    {% if advanced_menu %}
                        {% for item in advanced_menu.subfolderitems %}
                            <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
                        {% endfor %}
                    {% else %}
                        <div>Erreur : Menu "Techniques avancées" introuvable.</div>
                    {% endif %}
                {% else %}
                    <div>Erreur : Menu "Version 2" introuvable.</div>
                {% endif %}
            {% else %}
                <div>Erreur : Menu principal "Guides" introuvable.</div>
            {% endif %}
        </div>
    </div>
</div>

<div class="row">
    <div class="border rounded col p-2 m-1">
        <h3>Cas d'utilisation</h3>
        <hr aria-hidden="true">
        <div>
            {% assign use_cases_menu = site.data.menu.header | where: "title", "Cas d'utilisation" | first %}
            {% if use_cases_menu %}
                {% for item in use_cases_menu.subfolderitems %}
                    <div><a href="{{ item.url | relative_url }}">{{ item.title }}</a></div>
                {% endfor %}
            {% else %}
                <div>Erreur : Menu "Cas d'utilisation" introuvable.</div>
            {% endif %}
        </div>
    </div>
</div>
