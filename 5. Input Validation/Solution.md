## Solution

La solution à ce challenge est très simple. Comme pour un formulaire, l'injection SQL la plus basique est utilisée pour contourner l'autentification. L'injection est la suivante : *' or 1=1 -- -*

On peut supposer que la requête effectuée par l'application est la suivante :

SELECT * FROM users WHERE username = '**monUsername**' AND password = '**monPassword**'

En injectant le champs de mot de passe par exemple, on obtient la requête suivante :

SELECT * FROM users WHERE username = '**' or 1=1 -- -**' AND password = '**monPassword**'

On remarque la fermeture de l'apostrophe, créant une chaine vide pour le nom d'utilisateur, suivi d'une condition toujours vraie (1=1). Les deux tirets servent à commenter la suite de la requête, exclusant son traitement. A noter que le double tiret doit être précédé et suivi d'un espace pour garantir l'execution de la requête. Le tiret final sert à protéger l'espace final : lors d'un envoi reseau, certains équipement retirent les espaces précédant et suivant une requête HTTP et ajouter un caractère garanti la présence de ce dernier.

Au final, la requête qui est executée avec injection vérifie si le nom d'utilisateur vide existe en base de donnée (ce qui a peu de chance d'être vrai), ou si 1=1 (ce qui est toujours vrai). La requête retourne donc toujours quelque chose.


## Contre-mesures

Les injections SQL fonctionnent car le langage SQL est un langage interprété, on est de ce fait capable d'inclure du code dans une chaine de caractère en la fermant nous-même. Dans notre cas, nous avons utilisé la fonction rawQuery et nous avons concaténé le nom d'utilisateur et le mot de passe à notre requête.
Pour se protéger des injections SQL, quelle que soit la plateforme, il faut "binder" nos variables dans la requête. Une variable bindée sera cloisonnée et sera interprétée comme le type auquel elle correspond. Il sera par exemple impossible de fermer une chaine de caractère avec une apostrophe si cette dernière est bindée, l'apostrophe sera considérée comme faisant parti de la chaine. Pour Android, la méthode query de la classe SQLiteDatabase doit être utilisée pour une base de donnée SQLite, mais chaque langage possède ses propres classes permettant le binding.


## Ressources 

* [SQLiteDatabase] - Pour en savoir plus sur la classe SQLiteDatabase. *(dernière consultation: 30/01/21)*


[//]: #

  [SQLiteDatabase]: <https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase>
