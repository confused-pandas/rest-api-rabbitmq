TP Système distribués

Le but du TP est de réaliser une API REST pour une application permettant de gérer les scores de
championnats sportifs. Les ressources sont les suivantes

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


Les matchs ont un statut
PREVU|EN COURS|PAUSE|FINI|REPORTE|ANNULE

Une équipe joue à domicile et l'autre à l'exterieur.
Le score est un score simple (2-0) 


1 - Faites une implantation simple de cette API dont les données seront stockées dans une base relationnelle

2 - On veut pouvoir s'abonner au résultats en live d'une journée de championnat.
Adaptez le service pour que lorsqu'un résultat est ajouté à la base, les clients intéressé par une des équipes concernées par le résultat
reçoivent une notification du résultat grâce à un pattern publish/subscribe mis en place avec RabbitMQ.
