 TP Systèmes distribués
===========
Remarque sur le projet :

1 - Faites une implantation simple de cette API dont les données seront stockées dans une base relationnelle

L'API a été complètement implémenté.

 - Exécuter le fichier application.java
 - Puis aller sur localhost:8080

**************
Tester les GET 
**************

Pour tester les requêtes GET, il faut écrire les commandes ci-dessous dans le champ Explorer, en remplaçant {id} par un nombre.

/competitions/

/competitions/{id}/

/competitions/{id}/equipes/

/competitions/{id}/matches/

/competitions/{id}/classement/ : on obtient la liste des équipes de la mieux classée à la moins classée

/matches/

/matches/{id}/

/matches/{id}/equipes

/equipes/

/equipes/{id}

/equipes/{id}/matches/


**************
Tester les POST
**************

Ouvrir la console et exécuter le code suivant par exemple, il s'agit de l'ajout d'une nouvelle équipe :

curl -X POST localhost:8080/equipes -H 'Content-type:application/json' -d '{
  "nomEquipe": "test",
  "villeEquipe": "test",
  "nbPoints": 7,
  "idMatch": [1, 3, 4, 6]}'


**************
Tester les PUT 
**************

Ouvrir la console et exécuter les codes suivants par exemple

- Modification du nombre de point d'une équipe :

curl -v -X PUT localhost:8080/equipes/6 -H 'Content-Type:application/json' -d '{
  "nomEquipe": "Real Madrid",
  "nbPoints": 3
}'

- Modification du nombre de matchs d'une compétition, de la liste des équipes et de la liste des matches d'une compétition :

curl -v -X PUT localhost:8080/competitions/1 -H 'Content-Type:application/json' -d '{
  "nomCompetition": "Ligue 10",
  "nbMatch": 4,
  "listeIdEquipe": [1, 2, 3, 4],
  "listeIdMatch" : [ 1, 2, 3, 11]
}'

- Modification du score à la fin d'un match :

curl -v -X PUT localhost:8080/matches/7 -H 'Content-Type:application/json' -d '{"scoreA": 4, "scoreB": 3, "statut": "FINI"}'

******************
Tester les DELETE 
******************

Ouvrir la console et exécuter le code suivant par exemple, il s'agit de la supression de l'équipe avec id=15.

- curl -v -X DELETE localhost:8080/equipes/15



2 - On veut pouvoir s'abonner au résultats en live d'une journée de championnat.

Nous n'avons pas réussi à installer le serveur rabbit-mq, nous avons donc décidé de travailler directement sur la queue que vous nous aviez indiquée.
Nous avons rencontré quelques problèmes de connexion, notre travail se trouve sur la branche test-rabbitmq, nous ne l'avons pas transféré sur master 
de peur de perdre notre travail fonctionnel.


===========
A faire par groupe de 2

Le but du TP est de réaliser une API REST pour une application permettant de gérer les scores de
championnats sportifs. Les ressources sont les suivantes

````http request
/competitions/

/competitions/{id}/

/competitions/{id}/equipes/

/competitions/{id}/matches/

/competitions/{id}/classement/

/matches/

/matches/{id}/

/matches/{id}/

/equipes/

/equipes/{id}

/equipes/{id}/matches/

````

Les matchs ont un statut
````
PREVU|EN COURS|PAUSE|FINI|REPORTE|ANNULE
````
````json
"match" : {
  "domicile": {
   "self": "http://localhost/equipes/12"
  },
  "scoredom" : 2 
  "exterieur": {
      "self": "http://localhost/equipes/13"
     }
}
````
Une équipe joue à domicile et l'autre à l'exterieur.
Le score est un score simple (2-0) 


1 - Faites une implantation simple de cette API dont les données seront stockées dans une base relationnelle

*Vous pourrez vous inspirer de cet exemple pour cette implantation*
https://spring.io/guides/tutorials/bookmarks/

2 - On veut pouvoir s'abonner au résultats en live d'une journée de championnat.
Adaptez le service pour que lorsqu'un résultat est ajouté à la base, les clients intéressés par une des équipes concernées par le résultat
reçoivent une notification du résultat grâce à un pattern publish/subscribe mis en place avec RabbitMQ.
https://spring.io/guides/gs/messaging-rabbitmq/

3 - Ecrivez un simple client Java qui s'abonne à un topic et qui reçoit les informations et les affiche dans la console au fur et à mesure

Vous prendrez soin d'écrire des tests pour votre API soit simplement en utilisant des scripts avec curl ou en utilisant
des méthodes de tests plus avancées

````
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
````

Vous pouvez utiliser une de ces queues AMPQ plutôt que d'installer un serveur RabbitMQ

````
amqp://vbyrbmdw:tgA5fxMj1TucmDS8BfZ8GCSjaaxNIha2@buck.rmq.cloudamqp.com/vbyrbmdw
amqp://rbrilnwu:ImStIfzlSEiGk72iJIg1sRphhg6IoMrZ@swan.rmq.cloudamqp.com/rbrilnwu
````
