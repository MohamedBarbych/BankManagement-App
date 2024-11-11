# LSIBank

LSIBank est une application web qui fournit une interface utilisateur pour gérer les opérations bancaires, y compris la consultation des comptes, des clients et des transactions. Le projet est construit avec Spring Boot pour le backend et Thymeleaf avec Tailwind CSS pour le frontend.

## Table des Matières

- [Fonctionnalités](#fonctionnalités)
- [Technologies Utilisées](#technologies-utilisées)
- [Installation](#installation)
- [Configuration](#configuration)
- [Structure du Projet](#structure-du-projet)

## Fonctionnalités

- **Gestion des Comptes** : Consulter et gérer les comptes bancaires.
- **Gestion des Clients** : Consulter et gérer les informations des clients.
- **Opérations de Transactions** : Consulter et suivre les transactions pour différents comptes.
- **Design Responsive** : L'interface utilisateur est conçue pour fonctionner sur ordinateurs et appareils mobiles.
- **Barre de Navigation avec Icônes** : Barre de navigation interactive avec des icônes de section pour une navigation améliorée.

## Technologies Utilisées

- **Backend** : Java, Spring Boot
- **Frontend** : Thymeleaf, Tailwind CSS, Alpine.js
- **Base de Données** : MySQL (ou toute base de données compatible avec Spring Data JPA)
- **Outil de Build** : Maven

## Installation

### Prérequis

- Java 11 ou version supérieure
- Maven
- MySQL (ou votre base de données préférée)
- (Optionnel) Docker, si vous souhaitez exécuter la base de données dans un conteneur

### Cloner le Référentiel

```bash
git clone https://github.com/yourusername/LSIBank.git
cd LSIBank
```

### Configuration de la Base de Données

1. **Créer une Base de Données**: Set up a MySQL database with the name `db_banque_lsi`.

2. **Configurer les Identifiants de la Base de Données**: Update the `application.properties` file with your MySQL credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_banque_lsi
   spring.datasource.username=here-your-username
   spring.datasource.password=here-your-password
    ```

## Structure du projet 
### Système d'information
![image](https://github.com/user-attachments/assets/e6edc6ea-0e7d-4c00-87f9-13badc19facd)

### Système CRUD
![image](https://github.com/user-attachments/assets/ce733636-c1f9-4a52-8ece-da73f3e41847)

### Conception du projet
![image](https://github.com/user-attachments/assets/e1a134df-146e-45b9-be9b-cc43681479e6)








