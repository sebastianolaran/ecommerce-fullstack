# E commerce - Backend

## Descripción
Este es el backend del sistema de comercio electrónico. Proporciona servicios RESTful para la gestión de productos, pedidos y autenticación de usuarios.

## Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Security
- MySQL

## Instalación
1. Clona el repositorio del backend en tu máquina local.
2. Configura el archivo application.properties con la información de tu base de datos MySQL.
3. Ejecuta el proyecto utilizando Maven:



`mvn spring-boot:run`


## Configuración
- Asegúrate de tener una base de datos MySQL configurada y accesible.
- Verifica que las credenciales de la base de datos estén correctamente configuradas en application.properties.
## Estructura del Proyecto

El proyecto sigue una estructura MVC (Modelo-Vista-Controlador) típica de Spring Boot, con los siguientes paquetes principales:


- **`org.sebastian.config`**: Aquí se encuentra la configuración de seguridad y autenticación con Spring Security, así como la configuración relacionada con la generación de tokens JWT.

- **`org.sebastian.dao`**: Contiene los Data Access Objects (DAO) que interactúan directamente con la base de datos. Estos objetos se utilizan para realizar operaciones de lectura y escritura en la base de datos.

- **`org.sebastian.domain`**: Define las entidades del dominio de la aplicación utilizando el estándar de JPA (Java Persistence API). Estas clases representan las tablas de la base de datos y sus relaciones.

- **`org.sebastian.service`**: Aquí se encuentran los servicios de la aplicación, que contienen la lógica de negocio de la aplicación. Estos servicios se utilizan para interactuar con los objetos DAO y realizar operaciones más complejas en los datos.

- **`org.sebastian.web`**: Contiene los controladores REST para las peticiones web. Estos controladores reciben las solicitudes HTTP, procesan la lógica de negocio correspondiente y devuelven las respuestas adecuadas.

Esta estructura proporciona una separación clara de responsabilidades y facilita el mantenimiento y la escalabilidad del proyecto. Cada paquete se centra en un aspecto específico de la aplicación, lo que hace que sea más fácil para los desarrolladores navegar y entender el código.

## Uso
### Endpoints de API:
- `/api/productos`: CRUD para productos.
- `/api/ordenes`: CRUD para órdenes.
- `/api/register`: Registro de nuevos usuarios.
- `/api/login`: Autenticación de usuarios y generación de tokens JWT.
s