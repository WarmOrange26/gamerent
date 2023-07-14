# Warm-Project
Warm project is a sample Spring Boot MVC (Thymeleaf + REST) project.
## Description
Warm project is an application which provides the user with a web application and to perform operations for buy accounts with game.
## Technologies
The goal of this application is to get an in-depth feeling for the usage of the Spring framework. The following subjects were applied inside this project:

* Spring Boot
* Spring Security
* Spring MVC
* Thymeleaf
* Bootstrap
* Postgres in memory database
* JPA ORM (Hibernate impl.)

## (Gradle) project structure
There are many packages inside the project, in order to keep the code clean:

__src/main/java/...__
* exception: Contains all custom Exception classes.
* mapper: Contains mapper interfaces.
* model: Contains all JPA entity classes.
* repository: Contains all repositories to access the database.
* service: Contains all service classes which perform further business logic.
* util: Contains classes that help reducing code repeating.
* __web/__
* * config: Contains all configuration classes. In particular messages and security.
* * controller: Classes annotated with @Controller, which are used to return dynamically rendered HTML views.

__src/main/resources/...__

Contains templates, sql scripts and configuration files.

* db.changelog: Contains all changelogs.
* application.yml: Contains the server configuration.
* templates: This package consists of the all files .html (using the Thymeleaf Dialect).

## Authentication

### - Roles
There are three access types (user roles) in this application:

#### User
By logging in as a user that has the role of "USER" the user can issue new rentals.

#### Admin
Users with the role of "ADMIN" have access to games listings, can add/modify/delete game but cannot rent.

#### Public
Anyone can call the root website. The user can see a catalog with all games.
Public users do not have a role defined in the code.

#### Redirection
If unauthenticated users try to access paths of a specific role, which they are not part of, they will be redirected to the login screen.

### Main page
![Image alt](https://github.com/WarmOrange26/gamerent/raw/dev/src/main/resources/static/images/mainPage.jpg)
