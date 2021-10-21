## Solution

On possède un secret et on pense qu'il est généré aléatoirement. On va donc vérifier cette théorie en décompilant le code.

```sh
$ d2j-dex2jar FlawedRNG.apk
$ java -jar jd-gui.jar
```
```java
// Generation Parameters
long iv = 1;
Random generator = new Random(iv);
int leftLimit = 'a';
int rightLimit = 'z';
int stringLength = 10;

// Generating First Password
StringBuilder buffer = new StringBuilder(stringLength);
for(int i = 0; i < stringLength; i++) {
	int random = leftLimit + (int)(generator.nextFloat() * (rightLimit - leftLimit + 1));
	buffer.append((char) random);
}
String pass1 = buffer.toString();

// Generating Second Password
buffer = new StringBuilder(stringLength);
for(int i = 0; i < stringLength; i++) {
	int random = leftLimit + (int)(generator.nextFloat() * (rightLimit - leftLimit + 1));
	buffer.append((char) random);
}
String pass2 = buffer.toString();
```

On remarque le code qui sert à générer 2 mots de passes aléatoirement, et on remarque également qu'il utilise un vecteur d'initialisation constant. Il est donc possible d'executer ce même code en local pour prévoir les chaines qui seront générées.
En executant le code, on obtient deux chaine dont l'une que l'on connait déjà. On peut donc penser, à raison, que l'autre est le code que l'on cherche.


## Contre-mesures

Il est souvent répété de ne pas utiliser de vecteur d'initialisation constant ou basé sur le temps. Ce challenge en est l'explication : avec un vecteur d'initialisation constant, la chaine de génération toujours la même pour un algorithmee donné, permettant de déterminer les générations futures. Avec un vecteur d'initialisation basé sur le temps (un timestamp) il est possible de "remonter le temps" en testant les timestamps par ordre décroissant en partant du timestamp de l'heure actuelle. On se retrouve donc dans le cas d'une génération avec vecteur d'initialisation constant, mais changeant de manière deterministe dans le temps.
La méthode la plus sécurisée pour de l'aléatoire est d'utiliser des données pseudo-aléatoires non deterministes : la température du CPU, les bits reçus sur le réseau (avec du bruit) ou les données dans un fichier tel que /dev/random. Il est également important de ne pas réutiliser ces données plusieurs fois, sinon on se retrouverait encore dans le cas d'un vecteur d'initialisation constant.
En Java, le constructeur sans paramètre de *Random* utilise un vecteur d'initialisation pseudo-aléatoire, ce qui limite fortement cette attaque. Il existe surement des mecanismes similaires dans les autres langages.


## Ressources 

* [Random Java] - Source de l'algorithme de génération de chaîne aléatoire. *(dernière consultation: (5/02/21)*


[//]: #

  [Random Java]: <https://www.baeldung.com/java-random-string>
