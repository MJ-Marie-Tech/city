# But de l'exercice

Le but de l'exercice est d'implémenter un service web REST correspondant à la spécification OpenAPI décrite dans le fichier "openapi.yml" à la racine du projet.

Le service web contient 2 opérations de recherche de ville.

La liste des villes est disponible au format JSON dans le fichier "/src/main/resources/cities.json"

Ce fichier peut être utilisé tel qu'il est proposé ou retravaillé à votre choix (Ex: SQL, YAML, etc.)  

La stack technique doit restée celle proposée (spring boot) mais l'implémentation est libre.

# Opérations

## GET /api/cities

Le but de cette opération est de lister les villes en fonction de critères de recherche

* size : taille maximum de la liste à retourner 
* offset : index du premier résultat
* sortBy : nom de la colonne utilisée pour le tri
* sortDirection : ordre de tri
* namePattern: critère de recherche par nom de ville au format "wildcard" (Ex: PAR*)
* zipCodePattern: critère de recherche par code postal au format "wildcard" (Ex: 750*)

Tous les critères peuvent être cumulés

Voir la spécification pour plus de détails

## GET /api/cities/nearest

Le but de cette opération est de trouver la ville la plus proche d'un point GPS passé en paramètre

La distance séparant 2 points peut être calculée "à vol d'oiseau"

Voir la spécification pour plus de détails

# Remarques

* Si vous n'avez pas le temps de tout faire, concentrez vous sur la première opération.
* Préférez ne faire qu'une opération sur les deux mais de manière "parfaite"
