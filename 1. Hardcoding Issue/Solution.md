## Solution

On cherche des données sensible dans le code source, la première étape doit donc être de récupérer ce code source. La plupart des applications mobiles étant codés en Java, il est possible de récupérer un fichier .jar à partir d'un APK, pour cela on utilise l'outil dex2jar :

```sh
$ d2j-dex2jar hardcoding.apk
```

Ensuite, on est en mesure de récupérer le code source décompilé à l'aide d'un décompileur java :

```sh
$ java -jar jd-gui.jar
```

## Contre-Mesures

De manière générale, il faut éviter d'écrire de la donnée sensible dans le code source, il est impossible d'empêcher quelqu'un de la décompiler, on peut au mieux le ralentir en obfusquant le code. L'idéal est de transferer la partie traitement sur un serveur distant et communiquer avec l'application à l'aide d'une API.
