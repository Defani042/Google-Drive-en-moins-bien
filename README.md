# Google-Drive-en-moins-bien


## 🏗️ Architecture du projet

## 📦 MODEL

```
model/
└── Utilisateur.java
```

👉 Contient les objets métier de l’application.

- Représente un utilisateur
- Attributs : id, login, password
- Aucune logique SQL

---

## 🗄️ DAO (Data Access Object)

```
dao/
├── ParamBD.java
└── UtilisateurDao.java
```

👉 Gère la communication avec la base de données.

### 🔹 ParamBD.java
- Charge la configuration JDBC depuis `web.xml`
- Initialise les paramètres de connexion :
  - URL de la base
  - login
  - password
  - driver

### 🔹 UtilisateurDao.java
- `InscriptionUtilisateur(login, password)` → INSERT en base
- `seConnecter(login, password)` → SELECT en base
- Contient toute la logique SQL

---

## 🎮 CONTROLLER

```
controller/
├── ControllerAccueil.java
├── ControllerConnexion.java
└── ControllerInscription.java
```

👉 Gère les requêtes HTTP (Servlets)

### 🔹 ControllerAccueil
- GET :
  - Vérifie la session utilisateur
  - Redirige vers login si non connecté
  - Sinon affiche Accueil.jsp

### 🔹 ControllerConnexion
- GET :
  - Affiche le formulaire de connexion
- POST :
  - Récupère login/password
  - Appelle `UtilisateurDao.seConnecter()`
  - Stocke l’utilisateur en session
  - Redirige vers Accueil

### 🔹 ControllerInscription
- GET :
  - Affiche le formulaire d’inscription
- POST :
  - Récupère login/password
  - Appelle `UtilisateurDao.InscriptionUtilisateur()`
  - Redirige vers Connexion  

### 🔹 ControllerDeconnexion
- GET :
  - Affiche une page pour confirmer la déconnexion et propose une redirection vers Inscription ou Connexion
- POST :
  - Rien 

---


# 📌 Résumé MVC

| Couche | Rôle |
|--------|------|
| MODEL | représente les données |
| DAO | gère la base de données |
| CONTROLLER | gère les requêtes HTTP |

## BASE DE DONNEES

### Mettre à jour le fichier `drive_db.sql`

Lorsque la base de données évolue (ajout de tables, modifications de structure ou nouvelles données), il est important de **mettre à jour le fichier `drive_db.sql`** afin de synchroniser le projet avec les autres développeurs.Pour se faire utiliser la commande suivante :

```bash
sudo mysqldump drive_db > drive_db.sql
```

### exporter la base de données

si vous souhaiter exporter la nouvelle base de données alors il faut aller dans votre terminal puis : 

1) lancer mysql

```bash
sudo mysql
```

2) lancer le fichier 

```bash
SOURCE drive_db.sql;
```
