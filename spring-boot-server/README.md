
#  Démarrage de l'application

##  Création DB 
créer la database library_db sur postgresql

##  Création des rôles nécessaires à l'application

```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

## Création de l'administrateur

avec postman ou insomnia lancer la requete suivante :

```
POST http://localhost:8080/api/auth/signup
{
"role": ["admin"],
"username": "admin",
"email": "admin@library.fr",
"password": "123456"
}
```

