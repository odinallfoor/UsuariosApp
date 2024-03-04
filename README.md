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

   - `git clone https://github.com/odinallfoor/UsuariosApp_H2_SB.git`

2. Importa el proyecto en tu IDE favorito.

3. Actualiza la configuración según sea necesario, especialmente la configuración de la base de datos.

4. Ejecuta la aplicación desde tu IDE o utilizando Maven:

   - `mvn spring-boot:run`


La aplicación estará disponible en `http://localhost:8080`.

## Endpoints

- `POST /api/users/add`: Registra un nuevo usuario.
   - Cuerpo de la solicitud: Objeto JSON con los detalles del usuario a registrar.
   - Respuestas:
      - Código 500: Error de sistema.
      - Código 201: Usuario creado exitosamente.

## Estructura del Proyecto

- **`com.evaluacion.usuarios.config`**: Contiene la configuración de la aplicación, incluida la configuración de Swagger.
- **`com.evaluacion.usuarios.controller`**: Controladores que definen los endpoints de la API.
- **`com.evaluacion.usuarios.domain`**: Clases de dominio que representan los datos de la aplicación.
- **`com.evaluacion.usuarios.entities`**: Entidades JPA que representan las tablas de la base de datos.
- **`com.evaluacion.usuarios.exceptions`**: Clases de excepción y manejadores de excepciones.
- **`com.evaluacion.usuarios.repositories`**: Repositorios JPA para interactuar con la base de datos.
- **`com.evaluacion.usuarios.services`**: Interfaces y clases de servicio que manejan la lógica de negocio.


## Ejecución del Proyecto
Para ejecutar el proyecto y probar los endpoints de la API, puedes utilizar Postman o realizar solicitudes Curl directamente desde la línea de comandos.

### Utilizando Postman

1. Descarga e instala Postman, si aún no lo has hecho.
2. Abre Postman y crea una nueva solicitud.
3. Ingresa la URL del servidor local seguido del endpoint que deseas probar. Por ejemplo: 
   http://localhost:8080/api/users/add.
4. Configura el método HTTP como POST.
5. Agrega el encabezado Content-Type: application/json.
6. En el cuerpo de la solicitud, proporciona los datos del usuario en formato JSON, por ejemplo:
    ````json
    {
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez",
        "password": "hunter2",
        "phones": [
            {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }
    ````
7. Haz clic en el botón de enviar para ejecutar la solicitud y ver la respuesta del servidor.

### Utilizando Curl
También puedes ejecutar solicitudes Curl directamente desde la línea de comandos. Por ejemplo:

````txt
curl --location 'http://localhost:8080/api/users/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez",
    "password": "hunter2",
    "phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
        }
    ]
}'
````

Esta solicitud enviará datos de usuario al endpoint api/users/add del servidor local en localhost:8080. Asegúrate de que el servidor esté en funcionamiento antes de ejecutar la solicitud.