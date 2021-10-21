# Projet de 3e année d'école d'ingénieur - CTF Mobile

Le but du projet est de fournir un support à l'apprentissage de la sécurité et de l'audit sur mobile. Il vient sous la forme de différents APK traitement de vulnérabilités spécifiques. Ils sont accompagnés d'un fichier d'explication, d'indices et d'une solution que vous pouvez lire ou ne pas lire en fonction de votre niveau et de vos compétences. Le fichier d'explication donne le scénario du challenge ainsi que ce qui rend cette vulnérabilité dangereuse. Les indices donnent des pistes pour aider la résolution. Le fichier de solution propose une ou plusieurs méthodes de résolutions ainsi que des contre-mesures qui doivent être mises en place pour protéger l'application.

*Note: Certaines sécurités peuvent être contournés sans réaliser l'attaque attendue si votre appareil mobile est rooté, rendant le challenge trivial. L'intérêt n'est pas là et ces challenges supposent de ne pas utiliser les droits super-utilisateur du mobile.*


### Outils conseillés

* [APKTool] - Outil de rétro-ingénierie d'application mobiles.
* [dex2jar] - Convertisseur de fichier .dex (executables propre à la plateforme Android) en fichier .class (Java) sous forme d'un .jar
* [jd-gui] - Décompileur Java
* [ZipAlign] & [APKSign] - Outils permettant d'aligner puis signer un APK lors d'une recompilation
* [ADB] - Debuggeur en ligne de commande permettant d'intéragir avec un appareil Android virtuel ou physique.
* [Android Studio] - Environement de développement d'application mobile. Conseillé pour son émulateur d'appareils mobiles.


### Installation & Utilisation

*Nous utilisons l'émulateur fourni par Android Studio pour notre exemple.*

Il peut être judicieux d'ajouter l'utilitaire ADB au PATH pour simplifier l'utilisation, Sur Windows, ADB devrait être stocké dans C:\Users\user\AppData\Local\Android\Sdk\platform-tools après l'installation de Android Studio.


Une fois l'émulateur lancé depuis Android, il devrait être appairé :
```sh
$ adb devices
List of devices attached
emulator-5554   device
```

Pour l'installer, il faut avoir téléchargé l'APK sur son ordinateur. Ensuite on l'installe à l'aide d'ADB :

```sh
$ adb install app.apk
Performing Streamed Install
Success
```

L'application doit être trouvable dans la liste des applications sur le téléphone.
Pour faire accéder au terminal du téléphone et entrer dans le contexte de l'application (ses fichiers), on utilise les commandes suivantes :

```sh
$ adb shell
$ run-as <app_id>
```

L'identifiant unique de l'application peut être trouvé dans le manifeste de l'application en question. Pour nos challences, il est généralement *com.example.<nom_du_challence_en_minuscule>*


### Inspiration & Bibliographie

Ces liens sont des sources inspirations pour certaines vulnérabilités qui sont présentés :

* [DIVA] - Application mobile volontairement vulnérable à toute sorte d'attaques. Utilisé pour l'apprentissage des audits d'applications mobiles. *(dernière consultation: 15/01/21)*
* [Insecure Bank] - Autre application mobile vulnérable également utilisé pour l'apprentissage des audits d'applications mobiles. *(dernière consultation: 15/01/21)*
* [OWASP Checklist Mobile] - Checklist des vulnérabilités à tester lors d'un audit mobile. Utilisé par les Pentesteurs. *(dernière consultation: 15/01/21)*
* [OWASP Top 10 Mobile] - Liste des 10 vulnérabilités mobiles les plus répendues en 2014 & 2016. *(dernière consultation: 15/01/21)*


[//]: # (Références utilisés dans le document, non affiché lors du parsing)

   [APKTool]: <https://ibotpeaches.github.io/Apktool/>
   [jd-gui]: <https://java-decompiler.github.io/>
   [dex2jar]: <https://github.com/pxb1988/dex2jar>
   [ZipAlign]: <https://developer.android.com/studio/command-line/zipalign>
   [APKSign]: <https://developer.android.com/studio/command-line/apksigner>
   [ADB]: <https://developer.android.com/studio/command-line/adb>
   [Android Studio]: <https://developer.android.com/studio>
   [DIVA]: <https://github.com/payatu/diva-android>
   [Insecure Bank]: <https://github.com/dineshshetty/Android-InsecureBankv2>
   [OWASP Checklist Mobile]: <https://github.com/OWASP/owasp-mstg/blob/master/Checklists/Mobile_App_Security_Checklist-French_1.2.xlsx>
   [OWASP Top 10 Mobile]: <https://owasp.org/www-project-mobile-top-10/>

