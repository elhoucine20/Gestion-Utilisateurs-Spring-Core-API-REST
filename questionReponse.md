# QUESTIONS POUR CRÉER UNE DOCUMENTATION — SPRING CORE / SPRING DATA / SPRING MVC (sans Spring Boot)

## SPRING CORE — Inversion de Contrôle & Injection de Dépendances

### 1 - Qu’est-ce que Spring Core et à quoi sert-il dans une application Java ?
=> est une modulr de spring base sur deux concepte (IOC et DI)

### 2 - Que signifie le principe d’Inversion de Contrôle (IoC) ?
=> "inversion of controll" c'est ou spring prend la gestion des dependences et beans ca qui optimiser le code .

### 3 - Quelle est la différence entre IoC et Injection de Dépendances (DI) ?
=> IOC c'est une container de spring qui permet la creation et la gestion des beans, DI c'est un design pattern qui implemente IOC

### 4 - Qu’est-ce qu’un bean dans Spring ?
=> c'est un objet geré par spring core

### 5 - Quel est le rôle du conteneur IoC ?
=> la creation et la gestion des beans

### 6 - Quelle est la différence entre ApplicationContext et BeanFactory ?
=> les deux permet d'acceder au beans , mais le premier initialiser tous les beans au debut d'execution et le deuxieme a la demande .

### 7 - Quelles sont les trois approches de configuration dans Spring ?
=> XML, annotations, Java Config

### 8 - À quoi servent les annotations suivantes :

- `@Configuration` : indique a spring que cette classe est une classe de config
- `@ComponentScan` : permet a spring de scanner les composons
- `@Bean` : indique a spring cette objet est une bean , doit gerer par spring
- `@Component`, `@Service`, `@Repository`, `@Controller` : ce sont des composons
- `@Autowired`, `@Qualifier` : le premier faire l'auto injection , le deuxieme donne la prioritée a un composant exacte

### 9 - Comment Spring détecte et crée automatiquement les composants ?
=> avec l'annotation component scan

### 10 - Quelles sont les étapes du cycle de vie d’un bean ?
=> instanciation -> injection -> initialisation -> utilisation -> destraction

### 11 - Quelle est la différence entre les scopes de bean (singleton, prototype, etc.) ?
=> singleton faire la creation une fois , prototype multiple ( single instance / on deamande )

### 12 - Pourquoi la configuration manuelle (avant Spring Boot) est-elle importante à comprendre ?
=> par ce que boot faire l'abstraction de spring

---

## SPRING DATA JPA — Persistance & Transactions

### 1- Qu’est-ce que Spring Data JPA et quel problème résout-il ?
=> un module spring qui permet d'optimiser et d'automatiser les lignes de code et processus des ineractions avec la base de donnée .

### 2 - Quelle est la différence entre JPA et Hibernate ?
=> jpa c'est la specification qui nous permet d'acceder au base données , et hibernate c'est l'implimentation de jpa

### 3 - Qu’est-ce qu’une entité JPA ?
=> c'est l'entité qui existe au base de donnée , chaque instance est un ligne au tableau de base de donnée

### 4 - À quoi sert le DataSource ?
=> est une interface que permet a faire la connexion avec la base de données

### 5 - Que fait l’EntityManager ?
=> il donne un instance de connexion

### 6 - Quelle est la responsabilité du TransactionManager ?
=> gerer les transactions

### 7 - À quoi sert l’annotation @EnableJpaRepositories ?
=> indique a spring la place de jpa et il lui donnee l'acces

### 8 - Qu’est-ce qu’un repository Spring Data ?
=> est une interface qui contien les signateurs d'interaction avec DB

### 9 - Quelles sont les méthodes génériques fournies par JpaRepository ?
=> save() , find(),delete()...

### 10 - Comment gérer les transactions avec Spring (annotation, propagation, rollback, etc.) ?
=> @EnableTransactionManagement

### 11 - Pourquoi définir manuellement la connexion à la base de données avant Spring Boot ?
=> parce que spring boot automatiser configuration

### 12 - Que doit contenir une configuration de persistance complète ?
=> DataSource, EntityManagerFactory, TransactionManager

### 13 - Qu’est-ce que la validation de contrainte dans le modèle (ex. email unique, longueur, etc.) ?
=> l'annotation @Valid

### 14 - Quelle est la différence entre une suppression logique (soft delete) et une suppression physique ?
=> la suppression logique ne supprimmer pas l'objet dans DB , par contre suppression physique

---

## SPRING MVC — Contrôleurs & Couche Web

### 1 - Que signifie MVC (Model-View-Controller) et quel est son objectif dans Spring ?
=> une archeticture organisé (Model-View-Controller) , avec lobjectif de séparer les couches de l'application

### 2 - Quel est le rôle du DispatcherServlet dans Spring MVC ?
=> donner l'acces a mvc de travailler avec le servlet qui existe a tomcat par example

### 3 - Quelle est la différence entre un Controller et un RestController ?
=> Controller retun views RestController return json

### 4 - Quelle est la fonction des annotations suivantes :

- `@RequestMapping` : indique le path génerale de restController
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` : crud operations
- `@Valid` : pour verifier la validation des données qui viens de la requete
- `@RequestBody` : la requtte qui vien de formulaire
- `@PathVariable` : il extrait le variable qui viens de le url

### 5 - Comment le DispatcherServlet traite-t-il une requête HTTP du début à la fin ?
=> avec des objets fournet par HttpServlet

### 6 - Qu’est-ce que la classe de configuration Web (WebConfig) et à quoi sert-elle ?
=> il configurer la couche web ( controller , restController ) il lie le servlet avec mvc de l'app

### 7 - Pourquoi faut-il initialiser le DispatcherServlet manuellement avant Spring Boot ?
=> par ce que boot faire l'initialisation de DispatcherServlet par default

### 8 - Qu’est-ce qu’un WebAppInitializer et pourquoi remplace-t-il web.xml ?
=> configuration de lancement d'application a travers la classe java , est il le remplace pour faciliter et optimiser la gestion de configuration

### 9 - Quelles sont les étapes de traitement d’une requête REST dans Spring MVC ?
=> client -> handlerMapping -> controller -> validation -> service -> repo -> db

### 10 - Comment se fait la sérialisation et désérialisation des objets JSON ?
=> jackson

### 11 - À quoi sert un @RestControllerAdvice ?
=> il foutnit la gestion des exceptions globales

### 12 - Quelles sont les bonnes pratiques pour organiser les packages d’un projet MVC ?
=> separer la couche metier avec des package specialisé comme exception , repo , service , util , dto , mapper ...