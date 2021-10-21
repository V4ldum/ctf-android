## Solution

Comme pour le challenge précédent, on récupère l'identifiant de l'application dans le manifeste pour accéder à son contexte.

```sh
$ apktool d insecuredatastorage2.apk
$ cd insecuredatastorage
$ cat AndroidManifest.xml
```

```
<activity android:name="com.example.insecuredatastorage2.MainActivity">
```

```sh
$ adb shell
$ run-as com.example.insecuredatastorage2
```

Cette fois, on cherche le fichier de base de donnée Sqlite qui est stocké dans le répertoire *databases*.

```sh
$ cd databases
$ sqlite3 credentials.sqlite
```
```sql
sqlite> .tables
    android_metadata user
sqlite> SELECT * FROM user;
```


## Contre-mesures
L'utilisation d'une base de donnée Sqlite peut sembler une bonne idée au premier abord car les données sont plus difficiles à lire que dans fichier texte classique. Cependant, il est très facile de lire ce fichier en utilisant le programme sqlite en ligne de commande pour ouvrir le fichier. Comme pour les Shared Preferences, il est avisé de stocker les données sur un serveur distant.


## Ressources

* [Sqlite] - Documentation pour l'utilisation d'une base de donnée Sqlite en Java. *(dernière consultation: 28/01/21)*
* [Cursor] - Documentation pour apprendre à se servir du type Cursor en Java. *(dernière consultation: 28/01/21)*


[//]: #

   [Sqlite]: <https://developer.android.com/training/data-storage/sqlite#java>
   [Cursor]: <https://developer.android.com/reference/android/database/Cursor>
