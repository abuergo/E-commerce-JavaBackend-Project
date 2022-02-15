# **E-COMMERCE (JAVA BACKEND PROJECT)**

> Following documentation is bilingual (English - Spanish).
***
# **_English Version_** :bookmark_tabs:

## About project running

Two ways to run this project:
- Clone the project from the **`main`** branch and run it normally.
- Clone the [configuration server project](https://github.com/abuergo/E-commerce-JavaBackend-Project-Config-Server). Run it and then clone the project from this repository but from the **`config-server-connected`** branch, run it too. 

    I.e, both projects must be running at the same time. In this way, this project fetch the configurations provided by the config-server project, on port 8888.

***

## This project includes:
- Spring Boot
- JWT
- Spring Data MongoDB
- Redis
- AOP
- Spring Boot Mail

***

## User Stories / Brief
- It will contain the necessary routes that allow listing existing products, entering new products, deleting and modifying their details, as well as interacting with the shopping cart.
- CRUD of products.
- A RESTful API will be implemented with the verbs GET, POST, PUT and DELETE to fulfill all the necessary actions.
- You must provide the frontend with an authorized entry mechanism to the system based on JWT (Json Web Token).
- The entered products will be stored in a MongoDB database.
- The user will be able to register their access credentials (email and password) to later be able to access their account. These credentials will be saved in the MongoDB database.
- CRUD of users.
- The client will have an active user session with a configurable expiration time.
- JWT token lifetime can be configured
- The server architecture will be based on layers (MVC)
- The server will be able to take configurations from an external file.
- Use a Config-server to get the properties
- An email will be sent to a configurable box, for each new user registration and with each purchase order generated.
- With each Request, a log must be recorded at the end of it with AOP.
- In case of encountering an exception, the server will send an error message with the following minimum fields: the id and the detail of the error.
- @ControllerAdvice is used for Exception Handling
***


## Requests
- To register a user, a request must be sent to the route '/user'. It consists of the client's full name, telephone number, email and password.
- To enter the system, a request must be sent to the route '/login' and the data to be sent is email and password. The user must use the same email and password data used at the time of registration. If the data is correct, a JWT will be returned to the user to use in all requests to the system.
- To create a product, a request must be sent to the route '/product' with the data: price, description, category and code.
- To list the products created, the route '/product' is used and it will return the list of all the products available for purchase.
- To list products by category, the route '/product/category/all' is used and the category is sent through Query Params.
Example: http://localhost:8080/product/all?category=Shoes
- To create a cart, a request must be sent to the route: '/cart', with the data of: email and delivery address. The system returns an order number.
- To add items to the cart it is necessary to have previously created a cart, since we will use the order number provided by the system when creating a cart. The request '/cart/add/:orderNumber' is sent. The data to send are: code and quantity.
- To list all items in the cart you must use the route '/cart/:orderNumber'.
- To update an item in the cart, it is necessary to send to the route '/cart/:orderNumber' the same data requested when creating an item: code and quantity.
- To delete an item from the cart it is necessary to send the item code to the route '/cart/:orderNumber'
- To finalize the order, the route '/cart/:orderNumber' must be used, which sends an email to the address set in the configuration file with the details of the order.

***

## Exception messages
Appropriate messages are sent when making requests that end with an exception. For example, if a request is sent to '/cart/add/:orderNumber' to add an item to the cart and the cart does not exist in the Mongo database, an appropriate message should be received indicating that the cart with that number of order does not exist.

*** 
## Redis and JWT token
With each login made by the user, verify:
- If the user exists in redis, return the JWT token
- If the user does not exist in Redis:
- Go to the Mongo Database to verify the credentials. If correct:
     - Save in redis the user with the entered data. The key in Redis should be: user-map
     - Return the JWT token.

**Clarifications:**
- The key expiration time is 15 minutes in Redis.
- The expiration time of the JWT token is 10 minutes.
***
## Aspect Oriented Programming (Spring Boot AOP) and log4j2
- Log: each request at the end must show:
    - log.info in case of success the method executed and the date of execution.
    - log.error in case of exception during the execution of the method executed on the date of execution.

***
## MongoDB

The following collections must be stored in MongoDB:
- Users: registered customers.

- Products: complete catalogue.
    - Unit price
    - Description
    - Category

- Cart: temporary purchase order
    - Email
    - Date and Time
    - Items with their quantities (Arrangement of product codes and quantities)
    - Delivery address

*** 
## Configuration file

There will be an external configuration file with options for development and others for production. As configuration parameters there will be the server listening port, the database url, the email that will receive notifications from the backend, the expiration time of the session, etc.

***

# **_Spanish Version_** :bookmark_tabs:

## Maneras de ejecutar el proyecto

Dos maneras de ejecutar este proyecto:
- Clonar el proyecto de la rama **`main`** y ejecutarlo normalmente.
- Clonar el [proyecto del servidor de configuración](https://github.com/abuergo/E-commerce-JavaBackend-Project-Config-Server).
Ejecutarlo y después, clonar el proyecto de este repositorio pero de la rama **`config-server-connected`**, ponerlo a ejecutar también. 

    Es decir, ambos proyectos deben estar corriendo al mismo tiempo. 
    De ésta manera, este proyecto obtiene las configuraciones que le brinda el proyecto config-server en el puerto 8888. 

***

## Este proyecto incluye:
- Spring Boot
- JWT
- Spring Data MongoDB
- Redis
- AOP
- Spring Boot Mail

***

## Historias de usuario
- Contendrá las rutas necesarias que permitan listar los productos existentes, ingresar productos nuevos, borrar y modificar sus detalles, así como interactuar con el carrito de compras.
- CRUD de productos.
- Se implementará una API RESTful con los verbos GET, POST, PUT y DELETE para cumplir con todas las acciones necesarias.
- Debe brindar al frontend un mecanismo de ingreso autorizado al sistema basado en JWT (Json Web Token).
- Los productos ingresados se almacenarán en una base de datos MongoDB. 
- El usuario podrá registrar sus credenciales de acceso (email y password) para luego poder ingresar a su cuenta. Estas credenciales serán guardadas en la base de datos MongoDB.
- CRUD de usuarios. 
- El cliente tendrá una sesión activa de usuario con tiempo de expiración configurable.
- JWT se puede configurar el tiempo de vida del token
- La arquitectura del servidor estará basada en capas (MVC)
- El servidor podrá tomar configuraciones desde un archivo externo. 
- Usar un Config-server para obtener las propiedades
- Se enviará un mail a una casilla configurable, por cada registro nuevo de usuario y con cada orden de compra generada.
- Con cada Request se deberá registrar un log al finalizar el mismo con AOP.
- En caso de detectar alguna excepción, el servidor enviará mensaje de error con los siguientes campos mínimos el id y el detalle del error.
- Se usa @ControllerAdvice para el Manejo de excepciones
***


## Solicitudes
- Para registrar un usuario se debe enviar una petición a la ruta ‘/user’. Consta del nombre completo del cliente, número telefónico, email y contraseña.
- Para ingresar al sistema se debe enviar una petición a la ruta ‘/login’ y los datos a enviar son email y contraseña. El usuario debe usar los mismos datos de email y contraseña usados al momento de registrarse. Si los datos son correctos, se le devolverá a usuario un JWT para usarlo en todas las peticiones al sistema.
- Para crear un producto se debe enviar una petición a la ruta ‘/product’ con los datos de: precio, descripcion, categoría y código.
- Para listar los productos creados se usa la ruta ‘/product’ y devolverá el listado de todos los productos disponibles para la compra.
- Para listar productos por categoría, se usa la ruta ‘/product/category/all‘ y se envía la categoría mediante Query Params. 
Ejemplo: http://localhost:8080/product/all?category=Shoes
- Para crear un carrito se debe enviar una petición a la ruta: ‘/cart’, con los datos de: email y dirección de entrega. El sistema le devuelve un número de orden.
- Para agregar items al carrito es necesario haber creado un carrito previamente, ya que vamos utilizar el número de orden provisto por el sistema al momento de crear un carrito. Se envía la petición ‘/cart/add/:orderNumber’. Los datos a enviar son: código y cantidad.
- Para listar todos items del carrito se debe usar la ruta ‘/cart/:orderNumber’.
- Para actualizar un ítem del carrito es necesario enviar a la ruta ‘/cart/:orderNumber’ los mismos datos solicitados al momento de crear un item: código y cantidad.
- Para borrar un ítem del carrito es necesario enviar el código del item a la ruta ‘/cart/:orderNumber’
- Para finalizar la orden, se debe usar la ruta ‘/cart/:orderNumber’, lo cual envia un email a la dirección puesta en el archivo de configuración con los detalles de la orden.

***

## Mensajes de excepción
- Se envían mensajes adecuados al momento de realizar peticiones que finalizan con una excepción. Por ejemplo, si se realiza una petición a ‘/cart/add/:orderNumber’ para agregar un item al carrito y este último no existe en la base de datos de Mongo, se debe recibir un mensaje adecuado indicando que el carrito con ese número de orden no existe.

***

## Redis y token JWT
Con cada login que realiza el usuario verificar:
- Si el usuario existe en redis, devolver el token JWT
- Si el usuario no existe en Redis:
- Ir a la base de Datos de Mongo para verificar las credenciales. En caso de estar correctos:
    - Guardar en redis el usuario con los datos ingresados. La key en Redis debe ser: user-map
    - Devolver el token JWT.

**Aclaraciones:**
- El tiempo de expiración de la key es de 15 minutos en Redis.
- El tiempo de expiración del token JWT es de 10 minutos.

***

## Programación orientada a aspectos (Spring Boot AOP) y log4j2
- Log: cada request al finalizar debe mostrar:
    - log.info en caso de éxito el método ejecutado y la fecha de ejecución.
    - log.error en caso de excepción durante la ejecución del método ejecutado y la fecha de ejecución.

***

## MongoDB
Se deben guardar las siguientes colecciones en la base de datos de MongoDB
- Usuarios: clientes registrados.

- Productos: catálogo completo.
    - Precio unitario
    - Descripción
    - Categoría

- Carrito: orden temporal de compra
    - Email
    - Fecha y hora
    - Items con sus cantidades aArreglo de los códigos de productos y cantidades)
    - Dirección de entrega

***

## Archivo de configuración

Se dispondrá de un archivo de configuración externo con opciones para desarrollo y otras para producción. Como parámetros de configuración estarán el puerto de escucha del servidor, la url de la base de datos, el correo electrónico que recibirá las notificaciones del backend, el tiempo de expiración de sesión, entre otros.

