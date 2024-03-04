# Aplicación de Evaluación de Usuarios

Este proyecto es una aplicación de evaluación de usuarios que proporciona endpoints para registrar nuevos usuarios y gestionar sus detalles.

## Configuración

### Requisitos

````
- Java 11 o superior
- Maven 3
````
### Configuración de la Base de Datos

La aplicación utiliza una base de datos H2 en memoria. Las credenciales de conexión predeterminadas son las siguientes:
- Consola de base de datos via WEB: http://localhost:8080/h2-console/
````
- URL: jdbc:h2:mem:usuariosdb
- Driver: org.h2.Driver
- Usuario: sa
- Contraseña: password
- Plataforma de base de datos: org.hibernate.dialect.H2Dialect

````
### Configuración de Swagger

La documentación de la API está integrada con Swagger. Puedes acceder a la documentación en 
- http://localhost:8080/swagger-ui.html.

## Instalación y Ejecución

1. Clona el repositorio:

   - `git clone https://github.com/tu_usuario/tu_repositorio.git`

2. Importa el proyecto en tu IDE favorito.

3. Actualiza la configuración según sea necesario, especialmente la configuración de la base de datos.

4. Ejecuta la aplicación desde tu IDE o utilizando Maven:

mvn spring-boot:run
