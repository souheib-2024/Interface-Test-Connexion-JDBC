# Accès et interrogation d’une base MySQL avec JDBC en Java

## Description

Ce projet est un TP visant à accéder et interroger une base de données relationnelle MySQL via l’API JDBC en Java.  

Il permet de :  
- Établir une connexion à une base MySQL locale nommée **ParcInformatique**  
- Exécuter des requêtes SQL de consultation (SELECT) depuis une interface graphique Java AWT  
- Afficher les résultats dans une liste graphique  
- Manipuler la table `Salle` en console via la classe `ExoJDBC`, avec des opérations de lecture  

---

## Fonctionnalités principales

- Test de connexion JDBC avec le programme `JDBCTest`  
- Interface AWT pour exécuter des requêtes SQL et afficher les résultats  
- Classe `ExoJDBC` pour la gestion en console de la table `Salle` (méthode `getSalles()`)  

---

## Technologies utilisées

| Catégorie       | Technologie                |
|-----------------|---------------------------|
| Langage         | Java                      |
| Base de données | MySQL                     |
| API             | JDBC                      |
| Interface GUI   | AWT (Abstract Window Toolkit) |
| IDE             | MyEclipse                 |

---

## Installation et lancement

1. Assurez-vous d’avoir MySQL installé et une base `ParcInformatique` créée localement.  
2. Configurez la connexion JDBC avec les bons paramètres (URL, utilisateur, mot de passe) dans le code source.  
3. Compilez et lancez les programmes Java via MyEclipse IDE ou en ligne de commande.  
4. Utilisez l’interface graphique AWT pour exécuter vos requêtes SQL.  
5. Testez la classe `ExoJDBC` en console pour manipuler la table `Salle`.  

---

## Remarques

- Le projet illustre les bonnes pratiques de connexion JDBC et la manipulation simple des bases MySQL via Java.  
- L’interface AWT est basique mais fonctionnelle pour les besoins du TP.  


