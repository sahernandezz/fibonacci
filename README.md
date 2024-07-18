# Proyecto de Generación de la Serie Fibonacci

Este proyecto es una aplicación Spring Boot que genera la serie de Fibonacci basada en la hora exacta de ejecución proporcionada. También envía un correo electrónico con la hora de generación y la serie Fibonacci generada, y almacena esta información en una base de datos PostgreSQL. La aplicación está documentada con Swagger y utiliza autenticación básica para proteger los endpoints.

## Requisitos

- Java 17
- Gradle
- Docker y Docker Compose
- Cuenta de correo electrónico para el envío de correos (configurar en `application.properties`)

## Uso del Aplicativo
- La contraseña y usuario son "admin"
- El acceso a swagger es a través de la url: http://localhost:8080/swagger-ui.html 

## Configuración del Proyecto

- Ingresar credenciales de correo en el archivo `application.properties`y las de la base de datos en el archivo `application.properties`

### Dependencias

Las principales dependencias utilizadas en el proyecto son:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- PostgreSQL Driver
- Spring Boot Starter Security
- Spring Boot Starter Mail
- Spring Boot Starter Test

### Configuración de PostgreSQL

Usamos Docker para levantar un contenedor con PostgreSQL. Crea un archivo `docker-compose.yml` con el siguiente contenido:

```yaml
services:
  db:
    image: postgres:latest
    container_name: postgres
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: secret
      POSTGRES_DB: postgres
