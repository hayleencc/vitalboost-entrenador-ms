# entrenador-ms
## Descripción

Este proyecto implementa un microservicio REST para crear, consultar, actualizar y filtrar entrenadores, con manejo de DTOs, validaciones y pruebas unitarias.  

## Tecnologías

- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- JUnit + Mockito para testing

## Requisitos

- Java 17 o superior
- Maven 3.6+
- Base de datos configurada (por ejemplo, PostgreSQL o H2 para pruebas)

## Cómo levantar el proyecto

1. Clonar el repositorio:
```git clone https://github.com/hayleencc/vitalboost-entrenador-ms.git```


2. Configurar la base de datos con las variables del archivo de ejemplo y crearlo en:
```src/main/java/resources/application-dev.properties```. 


3. Construir y ejecutar con Maven:

    ```mvn clean install```
    
    ```mvn spring-boot:run```


4. La aplicación arrancará en 

    `http://localhost:8080`


5. Para probar los endpoints existentes puedes acceder a:

    ```http://localhost:8080/swagger-ui/index.html```


__Nota_: en caso de tener problemas con la creacion de la base de datos, se recomienda crearla de forma manual (a través de consola o alguna herramienta de gestión de bases de datos como pgAdmin o TablePlus)_


## Testing
Se puede usar el comando ```mvn test```


## Estructura del proyecto

Accede a la carpeta `src` y dentro se encontrará la carpeta `main/java/org/vb` teniendo como carpetas principales:
- `config/`: Archivos de configuración general
- `controller/`: Controladores REST
- `dto/`: Objetos de transferencia de datos, tanto para request como response
- `exception/`: Manejador de excepciones
- `model/`: Entidades JPA
- `repository/`: Acceso a datos
- `service/`: Lógica de negocio
- `resources/`: Configuracion para la base de datos


En la carpeta `test/java/org/vb` se encuentra la carpeta con las pruebas unitarias por capas:
- `service/`: Pruebas unitarias de la capa de servicio