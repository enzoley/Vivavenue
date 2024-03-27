# Vivavenue



## Participants du projet

Voici les membres du groupes ayant participé au projet : 
LEROYER Enzo,
MORAND Arthur,
LEPREVOST Lucas,
PETITOT Jules,
HOANG Amaya,
LE TRAON Adelaide,
LEBOEUF Alexis,
LEPOULICHET Alexandre.

## Principe du projet

Application permettant de réaliser des recherches précises sur le site d'annonce Vivastreet.
Utilise les mots clés "and" et "or" afin de cibler la recherche plus précisément que la recherche classique.
Présente les résultats sous forme de page HTML avec des liens cliquables.

## Utilisation de l'application

Pour lancer une recherche il suffit de run le fichier Application.scala via sbt dans un terminal de commandes : 
```
sbt
run
```
Ou directement depuis un VS Code

Ensuite, il suffit de taper la requête grâce à des mots clés simples séparés par les mots clés "and" et "or"

```
rennes and python
```

## Utilisation des mots clés "and" et "or"

Les élèments de la requête sont séparés par les mots clés "and" et "or", voici quelques exemples : 
```
rennes and nantes
```

Le mot "and" signifie qu'il doit y avoir les mots "rennes" et "nantes" dans l'annonce ``

```
rennes or nantes
```

Le mot "or" signifie qu'il doit y avoir soit le mot "rennes", soit le mot "nantes" dans l'annonce

Il est possible d'imbriquer les mots clés, par exemple :

```
rennes and (python or java)
```



