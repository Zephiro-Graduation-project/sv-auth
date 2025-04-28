# Servicio de autenticación 🕵️
Servicio de autenticación para el prototipo funcional

### Notas 📝
Cosas a tener en cuenta para el proyecto:

* El archivo ``.env`` esta incluido en el ``.gitignore``, revisen que lo tienen antes de ejecutar el servicio. 
* Puedes acceder a la documentación en ``Swagger`` dando click [aqui](http://localhost:8090/swagger-ui/index.html).
* Si está en el entorno ``dev`` deberas tener una instancia de ``MongoDB`` corriendo en un contenedor de docker.
* Si está en el entorno ``test`` deberas registrar tu IP en [Atlas](https://www.mongodb.com/products/platform/atlas-database) con las credenciales del grupo.

### Configuración de MongoDB en Docker 🐳
Para configurar la base de datos del entorno ``dev`` sigue estos pasos:

1. Descargar la imagen de MongoDB

    Abre una terminal y ejecuta el siguiente comando para descargar la imagen oficial de MongoDB:  

    ```
    docker pull mongo
    ```

2. Crear y ejecutar un contenedor de MongoDB

    Ejecuta el siguiente comando para iniciar un contenedor con MongoDB:

    ``` 
    docker run -d --name dev-db -p 27017:27017 mongo:latest
    ```

3. Verificar que el contenedor está corriendo
    
    Para asegurarte de que el contenedor se está ejecutando, usa:

    ``` 
    docker ps
    ```

4. Conectar MongoDB con MongoDB Compass

    Para visualizar la base de datos en MongoDB Compass, sigue estos pasos:

    - Abre MongoDB Compass.
    - En la pantalla de inicio, selecciona "Connect" → "New Connection".
    - En el campo de conexión, ingresa la siguiente URL:

        ```
        mongodb://localhost:27017/
        ```
    
    - Haz clic en "Connect".
    - Una vez conectado, podrás explorar las bases de datos y colecciones desde la interfaz gráfica.
    