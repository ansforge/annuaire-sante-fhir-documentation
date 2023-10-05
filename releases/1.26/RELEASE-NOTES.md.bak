# Release Note - #ApiFhirAnnuaire - Version 1.26

Découvrez la nouvelle version de L’API FHIR Annuaire Santé en libre accès : nouveau moteur d'intégration des données, nouvelles fonctionnalités, correctifs de bogues et nombreuses optimisations améliorant encore l'expérience 
utilisateur, ...

Nous vous remercions de votre utilisation de notre API et de vos commentaires précieux. Si vous avez des questions ou des retours, n'hésitez pas à nous contacter à l'adresse LT-DTEX-Annuaire@esante.gouv.fr.

Restez à l'écoute pour de nouvelles mises à jour passionnantes !
 

### Breaking Changes (Evolutions Majeures)

- Refonte totale du moteur d'intégration des données dans le serveur FHIR pour gagner en qualité et en fraîcheur.


### New Features (Nouvelles fonctionnalités)

- Ajout de nouveaux paramètres de recherche : 

  - "identifier-type" : Les ressources practitioner et organization évoluent pour proposer la recherche par type d'identifiant. Par exemple : vous pouvez désormais isoler les Practitioner possédant un identifiant RPPS ou ADELI, les Organization de type Finess ou Siret.
  - "_total" : Alors que jusqu'à présent le calcul du nombre total de résulats  d'une recherche était systèmatique, il est désormais possible de choisir de le calculer ou non. Avantage? Vous permettre d'améliorer les performances des requêtes avec de grandes quantités de données.

- Implémentation d'un nouvel endpoint "Health check". Objectif : Vous permettre de surveiller l'état de santé de notre API en temps réel.

- La ressource Organization intègre une nouvelle notion permettant de contaître l'origine des données.

- Informations sur les ressources supprimées : Vous avez été nombreux à nous demander cette information capitale. C’est chose faite : Vous pouvez désormais isoler les ressources supprimées entre deux dates (synchronisations) via le paramètre (active=false).   


### Improvements (Améliorations)

- Optimisation des performances de la recherche en "contient texte" (contains).

- Nettoyage des données non pertinentes : Désormais, les identifiants internes (de type INTRN) des oganizations ne sont plus publiés (https://simplifier.net/packages/hl7.fhir.fr.core/1.1.0/files/783566) 


### Bug Fixes (Bogues corrigés)

- partof erroné pour certains EG [#62](https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/62)

- Code System incorrect [#61](https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/61)

- Ressource Practitioner définie mais inexistante [#60](https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/60)


### known bugs and workarounds (Problèmes connus et solutions de contournement)

- Les urls canoniques des profils ne sont pas fonctionnelles, certaines d'entres elles ont été modifiées mais le problème persiste toujours. Nous travaillons à résoudre ce problème dans la prochaine mise à jour.

- Next url non fontionel avec un filtre sur une organisation (https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/59)

- Address.city [#26] (https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/26)
