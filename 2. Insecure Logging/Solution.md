## Solution

On cherche une fuite de donnée à travers la console de log Android. Le logcat de Android Studio (en bas) permet d'afficher cette console. Après avoir lancé l'application, on cherche une ligne qui correspond à son nom, ici insecurelogging. En modifiant le mot de passe dans l'application, on se rend compte qu'il est écrit dans le log, ce qui pourrait le faire fuiter.

```
2021-01-07 15:01:12.850 9540-9540/com.example.insecurelogging D/LOG: New password : 123456
```

## Contre-mesures

Il n'y a pas vraiment de contre-mesures ici. Il faut simplement ne pas utiliser les logs sur une application disponible pour le public. Il faut faire attention aux oublis qui pourraient rester lors de la publication.
