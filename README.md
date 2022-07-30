# Arquitectura de un mini API-Rest simulado
> Este proyecto pretende ser un ejemplo sencillo de arquitectura de un API-Rest simulado para comprender las capas que intervienen y la organización de los diferentes tipos de test, con integración continua y control de la calidad del código
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Arquitectura y Patrones para Aplicaciones Web*

[![Build Status](https://travis-ci.org/miw-upm/APAW-themes-layers.svg?branch=develop)](https://travis-ci.org/miw-upm/APAW-themes-layers)

## Tecnologías necesarias
* Java
* Maven
* GitHub

## Diseño de entidades
![themes-architecture-diagram](https://github.com/miw-upm/APAW-themes-layers/blob/develop/docs/themes-entities-class-diagram.png)

## Arquitectura
![themes-entities-class-diagram](https://github.com/miw-upm/APAW-themes-layers/blob/develop/docs/themes-architecture-diagram.png)

### Responsabilidades
#### Dispatcher
* Centraliza las peticiones y hace de repartidor
* Recupera los datos de la petición y los pasa como parámetros de método
* Captura las excepciones y las convierte en errores Http
#### restControllers
* Define el path del recurso
* Valida la entrada
* Traspasa la petición a los controladores de la capa de negocio
#### businessControllers
* Procesa la petición, apoyándose en los DAO’s
* Crea las entidades a partir de los DTO’s
* Gestiona la respuesta a partir de las entidades. Delega en los DTO’s la creación a partir de la entidad
#### daos
* Gestionan la BD
#### entities
* Son las entidades persistentes en la BD

## API
### POST /users
#### Parámetros del cuerpo
- `nick`: String (**requerido**)
- `email`: String
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### PUT /users/{id}
#### Parámetros del cuerpo
- `nick`: String (**requerido**)
- `email`: String
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
--- 
### POST /suggestions
#### Parámetros del cuerpo
- `negative`: Boolean (**requerido**)
- `description`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
---
### POST /themes
#### Parámetros del cuerpo
- `reference`: String (**requerido**)
- `category`: Category
- `userId`: String (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /themes
#### Respuesta
- 200 OK 
  - `[ {id:String,reference:String} ]`
---
### DELETE /themes/{id}
#### Respuesta
- 200 OK 
---
### POST /themes/{id}/votes
#### Parámetros del cuerpo
- `value`: Integer (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /themes/{id}/average
#### Respuesta
- 200 OK 
  - `average`: Double
- 404 NOT_FOUND
---
### PATH /themes/{id}/category
#### Parámetros del cuerpo
- `category`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /themes/search?q=average:>=3
#### Respuesta
- 200 OK
  - `[ {id:String,reference:String} ]`
- 403 BAD_REQUEST
---
##### Autor: Jesús Bernal Bermúdez U.P.M.
