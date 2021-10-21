## Solution

Dans un premier temps, il faut savoir ce que l'on cherche. On va donc chercher à décompiler l'application pour lire le code source.

```sh
$ d2j-dex2jar CodeTampering.apk
$ java -jar jd-gui.jar
```

Dans com/example.codetampering/MainActivity.class on remarque le code suivant :

```java
private boolean is_admin = false;
/* .... */
if(MainActivity.this.verifyCredentials(username.getText().toString(), password.getText().toString()) || MainActivity.this.is_admin) {
	// Cas Vrai
}
```

Il y a une variable de type booléen *is_admin* qui a pour valeur faux. Plus loin on remarque un test sur le nom d'utilisateur et le mot de passe (*verifyCredentials*) ainsi que notre variable *is_admin*. Si on pouvait mettre cette variable à vrai, le test serait toujours vrai.

On va utiliser apktool pour récupérer le code Smali de l'application. Le Smali est une version qui peut être lue par l'humain du bytecode utilisé par Android, une sorte d'assembleur pour Android.
Le paramètre -r sert à ne pas décoder les ressources, décoder les ressources peut entrainer des erreurs à la recompilation.


```sh
apktool d -r CodeTampering.apk
cd CodeTampering
vi smali/com/example/codetampering/MainActivity.smali
```

Dans le Smali de notre MainActivity, on remarque les portions de code suivantes :


```smali
# instance fields
.field private is_admin:Z
```
```smali
.line 14
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/example/codetampering/MainActivity;->is_admin:Z
```

Le Z correspond au type booléen, on retrouve donc notre déclaration de la variable is_admin, ainsi que son affectation : *0x0*, 0 en hexadecimal soit faux. En mettant cette valeur à vrai (*0x1*) on change la déclaration de la variable.
Plus qu'à recompiler le smali en un APK qui sera dans le repertoire dist.

```sh
$ apktool b .
$ cd dist
```
Enfin, on aligne et signe l'APK. Aligner l'APK sert à optimiser l'accès aux ressources dans l'application. Le signer n'a pas forcément d'intérêt dans notre cas mais Android refuse d'installer une application pour éviter l'usurpation lors d'une mise à jour par exemple.
Pour signer l'APK on aura également besoin de générer un couple de clé RSA.

```sh
$ zipalign -v -p 4 CodeTampering.apk codetampering_aligned.apk
$ keytool -genkey -v -keystore out.jks -keyalg RSA -keysize 2048 -validity 10000 -alias key
$ apksigner sign --ks out.jks--out codetampering_aligned.apk codetampering_signed.apk
```

On supprime l'ancienne version sur le téléphone car la signature ne correspond pas avec l'ancienne version (évidemment, on essaye d'usurper l'application). On peut finalement installer notre application et contourner le formulaire d'autentification.

```sh
$ adb install codetampering.apk
```

## Contre-mesures

Pour empêcher la modification d'une application, il faut que cette application soit *"Tamper-Resistant"* c'est-à-dire qu'elle resiste à la rétro-ingénierie en la rendant soit très dure, soit impossible. Il existe plusieurs méthodes pour empêcher la rétro-ingénierie comme l'obfuscation, la vérification de l'intégrité de l'application avec vérification de somme de contrôle (cyclic redundancy checksums ou CRC) ou même le chiffrement de tout ou partie de l'application.


## Ressources 

* [Zipalign] - Pour en savoir plus sur l'alignement d'APK. *(dernière consultation: 29/01/21)*
* [Apksigner] - Pour en savoir plus sur la signature d'APK. *(dernière consultation: 29/01/21)*
* [Obfuscation] - Guide d'obfuscation d'application de developer.android.com. *(dernière consultation: 29/01/21)*
* [MSTG] - Techniques de anti rétro-ingénieries *(dernière consultation: 29/01/21)*


[//]: #

  [Zipalign]: <https://developer.android.com/studio/command-line/zipalign>
  [Apksigner]: <https://developer.android.com/studio/command-line/apksigner>
  [Obfuscation]: <https://developer.android.com/studio/build/shrink-code>
  [MSTG]: <https://mobile-security.gitbook.io/mobile-security-testing-guide/android-testing-guide/0x05j-testing-resiliency-against-reverse-engineering>
