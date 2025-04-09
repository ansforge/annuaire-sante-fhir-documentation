Projet de documentation sur l'utilisation de l'API FHIR Annuaire Santé. 

Ce projet a été pensé pour être distribué au travers de Github pages. Il utilise Jekyll qui est supporté par Github. 

Le projet Jekyll se trouve dans le dossier docs/.

# Développement

Pour lancer le projet localement, allez dans le dossier docs/ puis lancez la commande `bundle exec jekyll serve`.

Le site sera accessible sur l'url : `http://127.0.0.1:4000/annuaire-sante-fhir-documentation/`.

# Rédaction 

Pour rédiger de la documentation, vous pouvez créer / modifier les fichier .md (markdown) dans le dossier "docs/pages/". 

Répertoires: 

* _data: gère le menu principal et le menu latéral gauche
* _includes: contient le footer, la navigation et les pages
* _layout: contient le code qui permet de générer le menu latéral de gauche
* _assets: contient le css, des images et du javascript
* _pages: contient le répertoire changelog et le répertoire guide.

# Menu 

Le menu du haut est géré avec le fichier _data/menu.yml. Quand vous ajoutez une nouvelle partie, il faut la renseigner dans le menu.yml pour la voir apparaitre. Il faut bien faire attention aux indentations dans le fichier YAML.


# Publication (déploiement)

Pour mettre en ligne, publiez le projet sur un projet GitHub et dans la section "Settings > Pages", activez la fonctionnalité Github pages. 
Dans la version gratuite de Github, vous devez mettre le projet public pour que cela fonctionne. 

# Liens utiles

Github Documentation API FHIR Annuaire Santé - ansforge.github.io/annuaire-sante-fhir-documentation/
Code Source Github Documentation - https://github.com/ansforge/annuaire-sante-fhir-documentation