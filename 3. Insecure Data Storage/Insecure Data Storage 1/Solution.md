## Solution

La première étape pour récupérer les fichiers utilisés par une application est d'accéder au contexte de cette dernière. Pour se faire on doit récuperer l'identifiant de l'application dans le manifeste.

```sh
$ apktool d insecuredatastorage1.apk
$ cd insecuredatastorage
$ cat AndroidManifest.xml
```

```
<activity android:name="com.example.insecuredatastorage1.MainActivity">
```

Avec cet identifiant, on peut entrer dans le contexte de l'application pour parcourir le système de fichiers. On pourra y trouver le fichier qui contient les Shared Preferences, parfois utilisé pour stocker des informations sensibles.

```sh
$ adb shell
$ run-as com.example.insecuredatastorage1
$ cat /shared_prefs/MainActivity.xml
```

## Contre-mesures
L'utilisation de Shared Preferences pour stocker des informations sensibles telles que les informations de connexion n'est pas une bonne pratique car ce dernier stocke tout en clair. Il est plus avisé de déplacer ce stockage sur un serveur distant.


## Ressources 

* [Shared Preferences] - Documentation pour l'utilisation des Shared Preferences. *(dernière consultation: 28/01/21)*


[//]: #

   [Shared Preferences]: <https://developer.android.com/training/data-storage/shared-preferences#java>
