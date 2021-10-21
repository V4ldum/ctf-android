## Solution

Dans un premier temps, on a besoin de connaitre l'identifiant unique de notre application. On la décompile donc pour accéder au manifeste.

```sh
$ apktool d SensitiveInformationMemory.apk
$ cd SensitiveInformationMemory
$ cat AndroidManifest.xml
```

Une fois en possession de l'identifiant, on peut se servir de l'outil de profilage d'Android Studio. On lance d'abord l'application, puis on va utiliser l'outil de profilage pour effectuer une image de la mémoire vive du téléphone. L'outil se trouve dans *View > Tool Windows > Profiler*. On selectionne l'application à dumper en utilisant le + en haut à gauche du profiler et en choisissant l'identifiant récupéré précédemment. On clique ensuite sur *MEMORY*, puis on effectue le dump mémoire en cliquant sur la flèche comme suit :

![DUMP1]

A l'issue du dump, on peut parcourir les données qui sont stockés en mémoire. En filtrant à l'aide de l'identifiant de l'application, on peut retrouver la ou les activités qu'elle utilise, et les parcourir pour retrouver des variables intéressantes. Ici on a la variable *pass1* qui a l'air intéressante.

![DUMP2]

En saisissant le mot de passe contenu dans la variable, on réussi à se connecter à l'application !


## Contre-mesures

Les bonnes pratiques de code sont de mises pour se protéger de cette attaque. Une donnée sensible doit être stockée en mémoire le moins de temps possible, et libérée dès qu'elle n'est plus necessaire. Alternativement, déplacer le traitement de données sensibles sur un serveur distant est encore une fois recommandé.


## Ressources 

* [Profiler Android Studio] - Outil utilisé pour récupérer les variables en mémoire vive. *(dernière consultation: (11/02/21)*


[//]: #

  [Profiler Android Studio]: <https://developer.android.com/studio/profile/memory-profiler>
  [DUMP1]: <https://gitlab.isima.fr/dasoares/projet-ctf-zz3/-/raw/master/img/dump.png>
  [DUMP2]: <https://gitlab.isima.fr/dasoares/projet-ctf-zz3/-/raw/master/img/dump2.png>
