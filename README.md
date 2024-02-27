# E-commerce 

## Descripción
Descripción
Este proyecto es un dashboard para un sistema de comercio electrónico (e-commerce) que proporciona diversas funcionalidades para la gestión y análisis de datos relacionados con la tienda en línea. Con este dashboard, los usuarios pueden acceder y utilizar varias funcionalidades, como el seguimiento de ventas, análisis de inventario, gestión de pedidos, administración de productos y categorías, entre otras herramientas diseñadas para optimizar la operación y el rendimiento del e-commerce.

El frontend de la aplicación está desarrollado utilizando Angular, ofreciendo una experiencia de usuario interactiva e intuitiva. Por otro lado, el backend está construido con Java, específicamente utilizando el framework Spring, que proporciona un entorno robusto y escalable para el desarrollo de aplicaciones web. La aplicación está conectada a una base de datos MySQL, donde se realizan operaciones y consultas para obtener y almacenar datos relacionados con los productos, pedidos y otras entidades del sistema.

## Instalación

### Requisitos Previos
Asegúrate de tener instalado Node.js y npm en tu sistema.

## Funcionalidades


### Autenticación y Autorización
- **Login y Registro**: La aplicación proporciona un sistema de autenticación basado en JWT (JSON Web Tokens) para el manejo de permisos dentro de la aplicación. Los usuarios pueden iniciar sesión con sus credenciales existentes o registrarse para obtener una nueva cuenta.
    - **JWT y Spring Security**: Se utiliza Spring Security junto con JWT para garantizar la autenticación y la autorización seguras en la aplicación. Spring Security se encarga de la gestión de usuarios y roles, mientras que JWT se utiliza para generar tokens de acceso seguros.

### Dashboard de Estadísticas
- **Pantalla de Inicio**: El dashboard muestra estadísticas sobre las ventas, incluyendo la cantidad de ventas diarias, semanales y mensuales.
    - **Ingresos Totales**: Además de las estadísticas de ventas, se presenta el dinero total obtenido en cada periodo correspondiente (diario, semanal y mensual). Esta información proporciona una visión general del rendimiento financiero del negocio.


### Gestión de Productos
- **Página de Productos**: Los usuarios pueden acceder a una página donde pueden ver todos los productos almacenados en la base de datos.
    - **Operaciones CRUD**: Los usuarios pueden realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en los productos. Pueden agregar nuevos productos, ver detalles de productos existentes, editar la información de los productos y eliminar productos que ya no sean necesarios.

### Gestión de Órdenes
- **Página de Órdenes**: Los usuarios tienen acceso a una página donde pueden ver todas las órdenes realizadas por el comercio.
    - **Detalle de Órdenes**: Cada orden muestra información detallada, como los productos incluidos, la cantidad, el precio, la fecha de creación, etc.
    - **Operaciones CRUD**: Los usuarios pueden realizar operaciones CRUD en las órdenes. Pueden crear nuevas órdenes, eliminar órdenes existentes y actualizar la información de las órdenes según sea necesario.****