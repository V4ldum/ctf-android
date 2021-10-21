
## Solution

La encore, il faut récupérer l'identifiant de l'application dans le manifeste pour accéder à son contexte.

```sh
$ apktool d insecuredatastorage3.apk
$ cd insecuredatastorage
$ cat AndroidManifest.xml
```

```
<activity android:name="com.example.insecuredatastorage3.MainActivity">
```

```sh
$ adb shell
$ run-as com.example.insecuredatastorage3
```

Pour ce challenge, on cherche un fichier temporaire. Ils sont stockés dans le répertoire *cache*.

```sh
$ ls
    cache  code_cache
$ cd cache
$ ls
    pwd2377949948568946996.tmp  usr2985914113303407230.tmp
```

L'identifiant et le mot de passe sont stockés dans ces fichiers.


## Contre-mesures
La encore, le stockage dans un fichier temporaire rend la recherche plus complexe, mais pas impossible. Le stockage sur un serveur distant est conseillé.


## Ressources

* [Fichier Temporaire] - Documentation pour la création d'un fichier temporaire. *(dernière consultation: 28/01/21)*


[//]: #

   [Fichier Temporaire]: <https://developer.android.com/reference/java/io/File#createTempFile(java.lang.String,%20java.lang.String,%20java.io.File)>
