# Release Note - #ApiFhirAnnuaire - Version 1.26

Découvrez la nouvelle version de L’API FHIR Annuaire Santé en libre accès : nouveau moteur d'intégration des données, nouvelles fonctionnalités, correctifs de bogues et nombreuses optimisations améliorant encore l'expérience 
utilisateur, ...

Nous vous remercions de votre utilisation de notre API et de vos commentaires précieux. Si vous avez des questions ou des retours, n'hésitez pas à nous contacter à l'adresse LT-DTEX-Annuaire@esante.gouv.fr.

Restez à l'écoute pour de nouvelles mises à jour passionnantes !
 

### Breaking Changes (Evolutions majeures)

- Refonte totale du moteur d'intégration des données dans le serveur FHIR pour gagner en qualité et en fraîcheur.


### New Features (Nouvelles fonctionnalités)

- Informations sur les ressources supprimées : Vous avez été nombreux à nous demander cette information capitale. C’est chose faite : Vous pouvez désormais isoler les ressources supprimées entre deux dates (synchronisations) via le paramètre (active=false). 

- Implémentation d'un nouvel endpoint "Health check". Objectif : Vous permettre de surveiller l'état de santé de notre API en temps réel.

- Ajout de nouveaux paramètres de recherche : 

  - "**identifier-type**" : Les ressources Practitioner et Organization évoluent pour proposer la recherche par type d'identifiant. Vous pouvez désormais isoler les Practitioner possédant un identifiant RPPS ou ADELI, aussi les Organization de type FINESS ou SIRET, ...
  - "**_total**" : Alors que jusqu'à présent le calcul du nombre total de résulats  d'une recherche était systèmatique, il est désormais possible de choisir de le calculer ou non. Avantage? Vous permettre d'améliorer les performances des requêtes avec de grandes quantités de données.

- La ressource Organization intègre une nouvelle notion permettant de contaître l'origine des données (trial-user).

  


### Improvements (Améliorations)

- Optimisation des performances de la recherche en "contient texte" (contains).

- Nettoyage des données non pertinentes : Désormais, les identifiants internes (de type INTRN) des Oganization ne sont plus publiés (https://simplifier.net/packages/hl7.fhir.fr.core/1.1.0/files/783566) 


### Bug Fixes (Bogues corrigés)

- partof erroné pour certains EG [#62](https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/62)

- Code System incorrect [#61](https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/61)

- Ressource Practitioner définie mais inexistante [#60](https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/60)


### known bugs and workarounds (Problèmes connus et solutions de contournement)

- Les urls canoniques des profils ne sont pas fonctionnelles, certaines d'entres elles ont été modifiées mais le problème persiste toujours. Nous travaillons à résoudre ce problème dans la prochaine mise à jour.

- Next url non fontionel avec un filtre sur une organisation (https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/59)

- Address.city [#26](https://github.com/ansforge/annuaire-sante-fhir-documentation/issues/26)


### Upgrade steps (Etapes de mise à niveau)

En général, le passage de la version 1.22 à 1.26 est transparent pour l’utilisateur final et ne nécessite pas d’intervention de sa part.
Il y a toutefois quelques points de vigilance à prendre en considération lorsque vous êtes sur une synchronisation delta ou vous utilisez les urls canoniques des profils. Faisons le point !

- Suite à la refonte du moteur d'intégration des données dans notre API, il se peut que certains identifiants techniques soient changés. 
C'est pourqoui, nous vous recommandations de resynchroniser vos données à partir de zéro (from scratch).

- Les urls canoninques des profils passent de "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/{{profile-id}}" à "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/{{profile_id}}".
Conséquence : une mise à niveau est nécessaire si vous utilisez les urls dans vos implémentations.

  - "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-practitioner" au lieu de "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-Practitioner"
  - "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-device" au lieu de "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-Device"
  - "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-practitionerrole" au lieu de "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-PractitionerRole"
  - "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-organization" au lieu de "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-Organization"
  - "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-healthcareservice-healthcare-activity" au lieu de "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-HealthcareService-HealthCareActivity"
  - "http://interop.esante.gouv.fr/ig/fhir/annuaire-donnee-publique/StructureDefinition/as-healthcareservice-social-equipment" au lieu de "https://annuaire.sante.gouv.fr/fhir/StructureDefinition/AS-HealthcareService-SocialEquipment"

- La ressource organization intègre une nouvelle extension :

```xml
{
    "resourceType": "Bundle",
...
			"extension": [
				{
					"url": "https://interop.esante.gouv.fr/ig/fhir/annuaire/StructureDefinition/as-data-trace",
					"extension": [
						{
							"url": "autorite-enregistrement",
							"valueCodeableConcept": {
								"coding": [
									{
										"system": "1.2.250.1.213.1.6.1.57",
										"code": "CNOP"
									}
								]
							}
						},
						{
							"url": "systeme-information",
							"valueCode": "FINESS"
						}
					]
				}
			],     
...


} 


``` 