# Google-Drive-en-moins-bien

## ORGANISATION 
le projet possèdes les packages suivants:  
- **dao** → package pour la gestion de la BDD  
- **model** → package des classes métier (classe java classique)  
- **controller** → contient toutes les servelett

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
